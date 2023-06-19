package com.example.task.tracker.service.impl;

import com.example.task.tracker.dto.UserDTO;
import com.example.task.tracker.entity.AuthUser;
import com.example.task.tracker.repository.UserRepository;
import com.example.task.tracker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;


    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    @Override
    public boolean saveUser(UserDTO save) {

        try {
            AuthUser saveUserData = new AuthUser();

            saveUserData.setName(save.getName());
            saveUserData.setEmail(save.getEmail());
            saveUserData.setPassword(save.getPassword());
            saveUserData.setFirstName(save.getFirstName());
            saveUserData.setLastName(save.getLastName());
            userRepository.save(saveUserData);

        } catch (Exception exp) {
            log.error("Exception in saveUser()", exp);
            throw new RuntimeException("User Data Cannot Be Save Right Now");
        }

        return true;
    }

    @Override
    public UserDTO getUser(Long id) {

        Optional<AuthUser> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {

            AuthUser user = optionalUser.get();
            UserDTO userDTO = new UserDTO();

            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());

            return userDTO;
        }
        return null;
    }


}
