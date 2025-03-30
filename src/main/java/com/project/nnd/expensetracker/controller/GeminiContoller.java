package com.project.nnd.expensetracker.controller;

import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.nnd.expensetracker.service.BlockchainService;
import com.project.nnd.expensetracker.service.GeminiService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class GeminiContoller {
    private final GeminiService geminiService;
    private final BlockchainService blockchainService;
    
    public GeminiContoller(GeminiService geminiService,BlockchainService blockchainService) {
        this.geminiService = geminiService;
        this.blockchainService=blockchainService;
    
    }
    @GetMapping("/getExpenseReport")
    public String expenseReport(){
      List<Map<String, Object>> data=blockchainService.getCategorizedData();
      String str=geminiService.generateExpenseReport(data, 100);
      return str;
      
    }

}