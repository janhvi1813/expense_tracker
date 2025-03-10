package com.project.nnd.expensetracker.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.nnd.expensetracker.model.Block;


@Repository
public interface BlockchainRepository extends MongoRepository<Block, String> {
}
