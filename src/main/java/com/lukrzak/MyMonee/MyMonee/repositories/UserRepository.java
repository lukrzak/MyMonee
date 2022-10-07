package com.lukrzak.MyMonee.MyMonee.repositories;

import com.lukrzak.MyMonee.MyMonee.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
