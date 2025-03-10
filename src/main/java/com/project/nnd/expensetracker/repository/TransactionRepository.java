package com.project.nnd.expensetracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.nnd.expensetracker.model.Transaction;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    // No need to write code, Spring Data MongoDB provides CRUD methods automatically
}
