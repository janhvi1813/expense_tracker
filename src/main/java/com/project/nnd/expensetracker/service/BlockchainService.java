package com.project.nnd.expensetracker.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    
    public List<Block> getBlockchain() {
        return blockchainRepository.findAll();
    }
    public double getTotalAmountSpent() {
        List<Block> blocks = blockchainRepository.findAll();
        double totalAmount = 0;
        for (Block block : blocks) {
            Transaction transaction = block.getTransaction();
            if (transaction != null) {
                totalAmount += transaction.getAmount();
            }
        }
        return totalAmount;
    }
    public List<Map<String, Object>> getCategorizedData() {
        List<Block> blocks = blockchainRepository.findAll();
        Map<String, Double> categoryAmounts = new HashMap<>();
        Map<String, String> mappedCategories = Map.ofEntries(
    Map.entry("swiggy", "food"),
    Map.entry("zomato", "food"),
    Map.entry("uber", "travel"),
    Map.entry("ola", "travel"),
    Map.entry("myntra", "shopping"),
    Map.entry("amazon", "shopping"),
    Map.entry("flipkart", "shopping"),
    Map.entry("meesho", "shopping"),
    Map.entry("netflix", "entertainment"),
    Map.entry("hotstar", "entertainment"),
    Map.entry("prime", "entertainment")
);

    
        for (Block block : blocks) {
            Transaction transaction = block.getTransaction();
            if (transaction == null) {
                continue; // Skip if transaction is null
            }
            
            String category = transaction.getCategory();
            if (category == null) {
                category = "misc"; // Handle null category
            } else {
                category = category.toLowerCase();
            }
            
            double amount = transaction.getAmount();
            String finalCategory = mappedCategories.getOrDefault(category, "misc");
            
            categoryAmounts.merge(finalCategory, amount, Double::sum);
        }
    
        List<Map<String, Object>> jsonResponse = new ArrayList<>();
        for (Map.Entry<String, Double> entry : categoryAmounts.entrySet()) {
            jsonResponse.add(Map.of(
                "category", entry.getKey(),
                "amount", entry.getValue()
            ));
        }
    
        return jsonResponse;
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