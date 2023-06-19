package com.example.task.tracker.service;

import com.example.task.tracker.dto.TaskDTO;
import com.example.task.tracker.dto.UserDTO;

public interface UserService {

    boolean saveUser(UserDTO save);
    UserDTO getUser(Long id);

}
