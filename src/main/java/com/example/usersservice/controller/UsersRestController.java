package com.example.usersservice.controller;

import com.example.usersservice.model.ItemExistException;
import com.example.usersservice.model.ItemNotExistException;
import com.example.usersservice.model.User;
import com.example.usersservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(value = RestApi.ROOT + "/users")
@CrossOrigin(origins = "*")
@RestController
public class UsersRestController {

    private final UserService userService;

    public UsersRestController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> getAll()
            throws ItemNotExistException {
        return ResponseEntity.ok(userService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Long id)
            throws ItemNotExistException {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<Object> registerNewAccount(@RequestBody User user)
            throws ItemExistException {
        return ResponseEntity.ok(
                userService.registerNewUser(user)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long id)
            throws ItemExistException {
        return ResponseEntity.ok(
                userService.updateUser(user, id)
        );
    }
}
