package com.expensetracker.service;

import com.expensetracker.model.Transaction;
import com.expensetracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return repo.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return repo.findAll();
    }

    public List<Transaction> getByType(String type) {
        return repo.findByType(type);
    }

    public List<Transaction> getByDateRange(LocalDate start, LocalDate end) {
        return repo.findByDateBetween(start, end);
    }

    public List<Transaction> getByCategory(String category) {
        return repo.findByCategory(category);
    }

    public double getTotalIncome() {
        return repo.findByType("INCOME").stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double getTotalExpense() {
        return repo.findByType("EXPENSE").stream().mapToDouble(Transaction::getAmount).sum();
    }
}

