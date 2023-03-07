package com.gustavomedeiros.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import com.gustavomedeiros.todosimple.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

        
    List<Task> findByUser_Id(Long id); // Inside of the user, catch the UserId
    

    // Other Options // 

    // Optional<Task> findById(long id); // Optional = Data Treatment, case of Null or empty 

    // @Query(value = "SELECT t FROM Task t WHERE t.user.id = :id") // :id reference Param Id that we need to see
    // List<Task> findByUser_Id(@Param("id") Long id);

    // @Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true)
    // List<Task> findByUser_Id(@Param("id") Long Id);

}
