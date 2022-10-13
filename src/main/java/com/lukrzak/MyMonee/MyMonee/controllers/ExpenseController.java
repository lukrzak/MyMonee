package com.lukrzak.MyMonee.MyMonee.controllers;

import com.lukrzak.MyMonee.MyMonee.enumerations.Categories;
import com.lukrzak.MyMonee.MyMonee.models.Expense;
import com.lukrzak.MyMonee.MyMonee.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    @ResponseStatus(code = HttpStatus.OK)
    public List<Expense> getUserExpenses(@PathVariable Long id){
        return expenseService.getAllUserExpenses(id);
    }

    @PostMapping("/expenses")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addExpense(@RequestBody Expense expense){
        expenseService.addExpense(expense);
    }

    @DeleteMapping("/expenses/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
    }

    @GetMapping("/expenses/suggestions/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<String> getReplacementSuggestions(@PathVariable Long id){
        return expenseService.getSuggestedReplacementsForUser(id);
    }

    @GetMapping("/expenses")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Expense> getExpensesOfCategoryInOrder(@RequestParam(value="category") String categories){
        return expenseService.getExpensesOfCategoryInOrder(Categories.valueOf(String.valueOf(categories)));
    }

    @GetMapping("/expenses/report")
    @ResponseStatus(code = HttpStatus.OK)
    public void generateReport() throws IOException {
        expenseService.generateReport();
    }
}
