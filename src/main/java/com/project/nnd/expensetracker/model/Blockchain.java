package com.project.nnd.expensetracker.model;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
        // ✅ Create Genesis Block with a dummy transaction
        chain.add(new Block(0, new Transaction(0.0, "Genesis", "System", null), "0"));
    }

    // ✅ Accepts a SINGLE Transaction instead of a List<Transaction>
    public void addBlock(Transaction transaction) {
        int newIndex = chain.size();
        String previousHash = chain.get(chain.size() - 1).getHash(); // Get the last block's hash

        // ✅ Create new block with one transaction
        Block newBlock = new Block(newIndex, transaction, previousHash);
        chain.add(newBlock);
    }

    // ✅ Check if the blockchain is valid
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            // ✅ Verify hash integrity
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            // ✅ Verify previous hash linkage
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    // ✅ Get the blockchain
    public List<Block> getChain() {
        return chain;
    }
}