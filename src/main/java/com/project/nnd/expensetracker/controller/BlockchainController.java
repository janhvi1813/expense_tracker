package com.project.nnd.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.nnd.expensetracker.model.Block;
import com.project.nnd.expensetracker.model.Transaction;
import com.project.nnd.expensetracker.service.BlockchainService;

@RestController
@RequestMapping("/blockchain")
public class BlockchainController {
    @Autowired
    private final BlockchainService blockchainService;

    public BlockchainController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    // ✅ Ek block sirf ek transaction se create hoga
    @PostMapping("/addBlock")
    public String addBlock(@RequestBody Transaction transaction) {
        blockchainService.addBlock(transaction);
        return "Block added successfully!";
    }

    // ✅ Puri blockchain ko fetch karna
    @GetMapping
    public List<Block> getBlockchain() {
        return blockchainService.getBlockchain();
    }

    // ✅ Blockchain valid hai ya nahi, check karna
    @GetMapping("/validate")
    public boolean validateBlockchain() {
        return blockchainService.validateBlockchain();
    }
}
