package com.example.task.tracker.service;

import com.example.task.tracker.dto.UserDTO;

import java.util.List;

public interface UserService {

    boolean saveUser(UserDTO save);

    UserDTO getUser(Long id);

    String updateUser(UserDTO update);

    List<UserDTO> getAll();

}
