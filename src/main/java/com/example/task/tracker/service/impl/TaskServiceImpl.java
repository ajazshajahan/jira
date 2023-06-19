package com.example.task.tracker.service.impl;

import com.example.task.tracker.dto.TaskDTO;
import com.example.task.tracker.entity.AuthUser;
import com.example.task.tracker.entity.Task;
import com.example.task.tracker.repository.TaskRepository;
import com.example.task.tracker.repository.UserRepository;
import com.example.task.tracker.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(final TaskRepository taskRepository,
                           final UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    @Override
    public boolean saveTask(TaskDTO save) {

        try {
            Task saveTaskData = new Task();

            saveTaskData.setName(save.getName());
            saveTaskData.setDescription(save.getDescription());
            saveTaskData.setStatus(save.getStatus());
            saveTaskData.setAuthUser(setUser(save.getUserId()));
            saveTaskData.setCreatedAt(LocalDateTime.now());
            saveTaskData.setUpdatedAt(LocalDateTime.now());

            taskRepository.save(saveTaskData);
        }catch (Exception exp){
            log.error("Exception in saveTask",exp);
            throw new RuntimeException("Task Cannot save Right Now");
        }
        return true;
    }

    private AuthUser setUser(Long id) {
        Optional<AuthUser> optionalAuthUser = userRepository.findById(id);

        return optionalAuthUser.get();
    }


}
