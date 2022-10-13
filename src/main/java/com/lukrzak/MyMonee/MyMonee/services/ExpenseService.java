package com.lukrzak.MyMonee.MyMonee.services;

import com.lukrzak.MyMonee.MyMonee.enumerates.Categories;
import com.lukrzak.MyMonee.MyMonee.models.Expense;
import com.lukrzak.MyMonee.MyMonee.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<Expense> getSuggestedReplacementsForUser(Long id){
        List<Expense> allExpenses = expenseRepository.getSuggestedReplacementsForUser(id);
        List<Categories> usersUsedCategories = expenseRepository.getUsersUsedCategories(id);
        List<Expense> lowestPrices = new ArrayList<>();

        for(Categories categories : usersUsedCategories){
            lowestPrices.add(getCheapestExpenseOfCategory(allExpenses, categories));
        }

        return lowestPrices;
    }

    public Expense getCheapestExpenseOfCategory(List<Expense> allExpenses, Categories categories){
        Expense cheapestProductOfCategory;
        cheapestProductOfCategory = allExpenses.stream()
                .filter(expense -> expense.getCategory().equals(categories))
                .min(Comparator.comparing(Expense::getPrice))
                .orElse(null);
        return cheapestProductOfCategory;
    }

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
