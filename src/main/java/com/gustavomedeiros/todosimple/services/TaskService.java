package com.gustavomedeiros.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustavomedeiros.todosimple.models.Task;
import com.gustavomedeiros.todosimple.models.User;
import com.gustavomedeiros.todosimple.repositories.TaskRepository;
import com.gustavomedeiros.todosimple.repositories.UserRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id) { 
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
            "Task not found, Id:" + id + ", Type: " + Task.class.getName()
        ));
    }

    @Transactional // Importante para persistencia e insersões de dados no banco
    public Task create(Task obj) {
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete this task cause exist related entity");
        }
    }

    
}
