package com.gustavomedeiros.todosimple.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustavomedeiros.todosimple.models.User;
import com.gustavomedeiros.todosimple.repositories.UserRepository;

@Service 
public class UserService {
    
    @Autowired // Contructor for service and now have access to both
    private UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);   // Case user does't exist, will return empty
        return user.orElseThrow(() -> new RuntimeException(
            "User not found! id:" + id + ", Type: " + User.class.getName()
        )); // Return user if user exist, else return a exception and stop application
        // RunTimeException is use when the exception can be prevented                              
    }

    @Transactional // Use to Presistent something in the database, to have better control of what is happening in the application
    public User create(User obj) {
        obj.setId(null); // The new user can create itself with a ID how already exists, so let make the Id null
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional // Insert in database need to use Transaction
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Delete is no possible cause exist related entity! ");
        }
    }

}
