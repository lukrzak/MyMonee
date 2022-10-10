package com.lukrzak.MyMonee.MyMonee.repositories;

import com.lukrzak.MyMonee.MyMonee.models.Expense;
import com.lukrzak.MyMonee.MyMonee.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    //TODO test query
    @Query(value = "SELECT * FROM expenses WHERE user_id = :id")
    List<Expense> getAllUsersExpenses(@Param("id") Long id);
}
