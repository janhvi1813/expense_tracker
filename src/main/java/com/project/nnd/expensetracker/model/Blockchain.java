package com.project.nnd.expensetracker.model;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
        chain.add(new Block(0, new Transaction(0.0, "Genesis"), "0"));
    }
    
    public void addBlock(Transaction transaction) {
        int newIndex = chain.size();
        String previousHash = chain.get(chain.size() - 1).getHash(); 

       
        Block newBlock = new Block(newIndex, transaction, previousHash);
        chain.add(newBlock);
    }

    
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    
    public List<Block> getChain() {
        return chain;
    }
}