package com.project.nnd.expensetracker.controller;

import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.nnd.expensetracker.model.Block;
import com.project.nnd.expensetracker.model.Transaction;
import com.project.nnd.expensetracker.service.BlockchainService;
import com.project.nnd.expensetracker.service.TransactionService;

@RestController
@RequestMapping("/blockchain")
@CrossOrigin(origins = "http://localhost:5173")
public class BlockchainController {

    private final TransactionService transactionService;
    private final BlockchainService blockchainService;

    public BlockchainController(TransactionService transactionService, BlockchainService blockchainService) {
        this.transactionService = transactionService;
        this.blockchainService = blockchainService;
    }

    @PostMapping("/addBlock")
    public String addBlock(@RequestBody String message) {
        Transaction transaction = transactionService.processTransactionSms(message);
        blockchainService.addBlock(transaction);
        return "Block added successfully!";
    }


    @GetMapping("/getBlockchain")
    public List<Block> getBlockchain() {
        return blockchainService.getBlockchain();
    }


    @GetMapping("/validate")
    public boolean validateBlockchain() {
        return blockchainService.validateBlockchain();
    }

    @GetMapping("/getCategorizedAmount")
    public List<Map<String, Object>> getCategorizedData(){
        return blockchainService.getCategorizedData();
    }   

}