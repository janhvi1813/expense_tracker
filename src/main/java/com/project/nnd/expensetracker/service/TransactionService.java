
package com.project.nnd.expensetracker.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.project.nnd.expensetracker.model.Transaction;
import com.project.nnd.expensetracker.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    // ✅ Constructor Injection
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        transaction.setTimestamp(LocalDateTime.now()); // Set timestamp
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    public Transaction processTransactionSms(String message) {
        double amount = extractAmount(message);
        String category = extractCategory(message);
        return new Transaction(amount, category);
    }

    public double extractAmount(String message) {
        Pattern amountPattern = Pattern.compile("(?i)(?:Rs\\.?|₹|debited by)\\s*(\\d+(\\.\\d{1,2})?)");
    Matcher matcher = amountPattern.matcher(message);

    if (matcher.find()) {
        return Double.parseDouble(matcher.group(1));
    }
    return 0.0;
    }

    public static String extractCategory(String message) {
        Pattern categoryPattern = Pattern.compile("trf to (\\S+)|towards (\\S+)");
        Matcher matcher = categoryPattern.matcher(message);

        if (matcher.find()) {
            return matcher.group(1) != null ? matcher.group(1).trim() : matcher.group(2).trim();
        }
        return "Uncategorized";
    }
}
