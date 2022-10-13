package com.lukrzak.MyMonee.MyMonee.repositories;

import com.lukrzak.MyMonee.MyMonee.enumerates.Categories;
import com.lukrzak.MyMonee.MyMonee.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(value = "SELECT * FROM expenses WHERE user_id = ?1", nativeQuery = true)
    List<Expense> getAllUsersExpenses(@Param("id") Long id);
    @Query(value = "SELECT * FROM expenses WHERE category = :#{#categories.name()} ORDER BY name", nativeQuery = true)
    List<Expense> getExpensesOfCategoryInOrder(Categories categories);

    @Query(value = "SELECT * FROM expenses WHERE user_id = ?1", nativeQuery = true)
    List<Expense> getSuggestedReplacementsForUser(Long id);

    @Query(value = "SELECT DISTINCT category FROM expenses WHERE user_id = ?1", nativeQuery = true)
    List<Categories> getUsersUsedCategories(Long id);
}
