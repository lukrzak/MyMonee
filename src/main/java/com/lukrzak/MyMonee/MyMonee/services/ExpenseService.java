package com.lukrzak.MyMonee.MyMonee.services;

import com.lukrzak.MyMonee.MyMonee.enumerates.Categories;
import com.lukrzak.MyMonee.MyMonee.models.Expense;
import com.lukrzak.MyMonee.MyMonee.models.User;
import com.lukrzak.MyMonee.MyMonee.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllUserExpenses(Long id){
        return expenseRepository.getAllUsersExpenses(id);
    }

    //TODO
    public List<Expense> getSuggestedReplacements(){
        List<Expense> allExpenses = expenseRepository.findAll();
        return null;
    }

    //TODO
    public List<Expense> getExpensesOfCategoryInOrder(Categories category){
        return expenseRepository.getExpensesOfCategoryInOrder(category);
    }

    public void addExpense(Expense expense){
        expenseRepository.save(expense);
    }

    public void deleteExpense(Long id){
        expenseRepository.deleteById(id);
    }

}
