package com.lukrzak.MyMonee.MyMonee.controllers;

import com.lukrzak.MyMonee.MyMonee.enumerates.Categories;
import com.lukrzak.MyMonee.MyMonee.models.Expense;
import com.lukrzak.MyMonee.MyMonee.repositories.ExpenseRepository;
import com.lukrzak.MyMonee.MyMonee.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses/{id}")
    public List<Expense> getUserExpenses(@PathVariable Long id){
        return expenseService.getAllUserExpenses(id);
        //return null;
    }

    @PostMapping("/expenses")
    public void addExpense(@RequestBody Expense expense){
        expenseService.addExpense(expense);
    }

    @DeleteMapping("/expenses/{id}")
    public void deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
    }

    @GetMapping("/expenses/suggestions")
    public List<Expense> getReplacementSuggestions(){
        return expenseService.getSuggestedReplacements();
    }

    @GetMapping("/expenses/{category}")
    public List<Expense> getExpensesOfCategoryInOrder(@PathVariable Categories categories){
        return expenseService.getExpensesOfCategoryInOrder(categories);
    }
}
