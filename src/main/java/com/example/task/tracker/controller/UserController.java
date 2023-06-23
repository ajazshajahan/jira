package com.example.task.tracker.controller;

import com.example.task.tracker.dto.UserDTO;
import com.example.task.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(final UserService userService) {
        this.userService= userService;
    }

    @PostMapping("/save/user")
    public boolean saveUser(@RequestBody UserDTO save) {
        return userService.saveUser(save);
    }

    @GetMapping("/get/user")
    public UserDTO getUser(@RequestParam(name = "id", required = false) Long id){
        return userService.getUser(id);
    }

    @PutMapping("/update/user")
    public String updateUser(@RequestBody UserDTO update){
        return userService.updateUser(update);
    }

    @GetMapping("/get/all")
    public List<UserDTO> findAll(){
        return userService.getAll();
    }

}
