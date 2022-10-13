package com.lukrzak.MyMonee.MyMonee.services;

import com.lukrzak.MyMonee.MyMonee.dto.enumerations.Categories;
import com.lukrzak.MyMonee.MyMonee.export.ExcelReport;
import com.lukrzak.MyMonee.MyMonee.models.Expense;
import com.lukrzak.MyMonee.MyMonee.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;
    private final ExcelReport excelReport;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, UserService userService, ExcelReport excelReport) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
        this.excelReport = excelReport;
    }

    public List<Expense> getAllUserExpenses(Long id){
        return expenseRepository.getAllUsersExpenses(id);
    }

    public List<String> getSuggestedReplacementsForUser(Long id){
        List<Expense> allExpenses = expenseRepository.getSuggestedReplacementsForUser(id);
        List<Categories> usersUsedCategories = expenseRepository.getUsersUsedCategories(id);
        List<String> suggestions = new ArrayList<>();

        // TODO remove suggestion that references to itself
        for(Categories categories : usersUsedCategories){
            Expense lowestPrice = getCheapestExpenseOfCategory(allExpenses, categories);
            List<Expense> moreExpensiveExpenses = getMoreExpensiveExpensesOfCategory(allExpenses, categories);
            StringBuilder suggestion = new StringBuilder(lowestPrice.getName() + " " + lowestPrice.getModel() + " is cheaper compared to ");
            for(Expense expense : moreExpensiveExpenses){
                suggestion.append(expense.getName()).append(" ").append(expense.getModel()).append(", ");
            }
            suggestions.add(String.valueOf(suggestion));
        }

        return suggestions;
    }

    public Expense getCheapestExpenseOfCategory(List<Expense> allExpenses, Categories categories){
        return allExpenses.stream()
                .filter(expense -> expense.getCategory().equals(categories))
                .min(Comparator.comparing(Expense::getPrice))
                .orElse(null);
    }

    public List<Expense> getMoreExpensiveExpensesOfCategory(List<Expense> allExpenses, Categories categories){
        return allExpenses.stream()
                .filter(expense -> expense.getCategory().equals(categories))
                .toList();
    }

    public List<Expense> getExpensesOfCategoryInOrder(Categories category){
        return expenseRepository.getExpensesOfCategoryInOrder(category);
    }

    public void addExpense(Expense expense){
        userService.changeUserBalance(expense.getUser(), -expense.getPrice());
        expenseRepository.save(expense);
    }

    public void deleteExpense(Long id){
        expenseRepository.deleteById(id);
    }

    public void generateReport() throws IOException {
        excelReport.generateReport();
    }

}
