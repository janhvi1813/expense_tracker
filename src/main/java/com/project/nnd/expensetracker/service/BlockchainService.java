package com.project.nnd.expensetracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.nnd.expensetracker.model.Block;
import com.project.nnd.expensetracker.model.Transaction;
import com.project.nnd.expensetracker.repository.BlockchainRepository;

@Service
public class BlockchainService {
    private final BlockchainRepository blockchainRepository;
    private List<Block> blockchain;

    public BlockchainService(BlockchainRepository blockchainRepository) {
        this.blockchainRepository = blockchainRepository;
        this.blockchain = new ArrayList<>();
        
        // ✅ MongoDB se existing blockchain load karna
        List<Block> existingBlocks = blockchainRepository.findAll();
        if (!existingBlocks.isEmpty()) {
            this.blockchain = existingBlocks;
        }
    }

    // ✅ Ek transaction ko accept karke ek block create karega aur MongoDB me save karega
    public Block addBlock(Transaction transaction) {
        String prevHash = blockchain.isEmpty() ? "0" : blockchain.get(blockchain.size() - 1).getHash();
        int index = blockchain.size(); // ✅ Correct index
        Block newBlock = new Block(index, transaction, prevHash); // ✅ Block creation

        // ✅ Add to blockchain list
        blockchain.add(newBlock);

        // ✅ Save in MongoDB
        blockchainRepository.save(newBlock);

        return newBlock;
    }
    
    // ✅ Blockchain fetch karna
    public List<Block> getBlockchain() {
        return blockchainRepository.findAll();
    }

    // ✅ Blockchain validate karna
    public boolean validateBlockchain() {
        for (int i = 1; i < blockchain.size(); i++) {
            Block currentBlock = blockchain.get(i);
            Block prevBlock = blockchain.get(i - 1);

            // 🔥 Check previous hash consistency
            if (!currentBlock.getPreviousHash().equals(prevBlock.getHash())) {
                return false;
            }

            // 🔥 Check if hash is valid
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }
        }
        return true;
    }
}
