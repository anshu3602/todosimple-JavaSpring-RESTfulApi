package com.gustavomedeiros.todosimple.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavomedeiros.todosimple.models.User;
import com.gustavomedeiros.todosimple.models.User.CreateUser;
import com.gustavomedeiros.todosimple.models.User.UpdateUser;
import com.gustavomedeiros.todosimple.services.UserService;

@RestController
@RequestMapping("/user")
// @RequestMapping(value = "/user", method = RequestMethod.GET)
@Validated // Validate 
public class UserController {

    @Autowired // Atribute without anotation
    private UserService userService;

    // localhost:8080/user/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) { // Return entity type USER
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj);
    } 

    @PostMapping
    @Validated(CreateUser.class) // Reference to model User interface with all the params and rules
    public ResponseEntity<Void> create(@Valid @RequestBody User obj) { // To validate the obj and request body
        this.userService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}") // Notation
    @Validated(UpdateUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id) { //@valid @requestBody são notações
        obj.setId(id);
        this.userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
