package com.project.nnd.expensetracker.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blocks") 
public class Block {
    
    @Id  
    private String id;
    
    private int index;
    private String timestamp;
    private Transaction transaction;
    private String previousHash;
    private String hash;

    
    public Block(int index, Transaction transaction, String previousHash) {
        this.index = index;
        this.timestamp = LocalDateTime.now().toString();
        this.transaction = transaction;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    
    public String calculateHash() {
        String dataToHash = index + timestamp + transaction.toString() + previousHash;
        return applySHA256(dataToHash);
    }

    private String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

   
    public String getId() {
        return id;
    }

    public String getHash() {
        return this.hash;
    }

    public String getPreviousHash() {
        return this.previousHash;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public int getIndex() {
        return this.index;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String toString() {
        return "Block{" +
                "index=" + index +
                ", timestamp='" + timestamp + '\'' +
                ", transaction=" + transaction +
                ", previousHash='" + previousHash + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
