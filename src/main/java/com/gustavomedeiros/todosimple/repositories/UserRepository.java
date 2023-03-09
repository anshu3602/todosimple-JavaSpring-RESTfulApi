package com.gustavomedeiros.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavomedeiros.todosimple.models.User;

// Create a interface from User that pass the SQL query
// JPA repository = managing the data. Use the USER class and the LONG to access the ID from the class USER
// JpaRepository already have all the method to search the data from the database
// Control + click in JpaRepository to see the methods
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    

}
