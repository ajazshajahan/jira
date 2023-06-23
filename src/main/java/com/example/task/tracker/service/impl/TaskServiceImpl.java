package com.example.task.tracker.service.impl;

import com.example.task.tracker.dto.TaskDTO;
import com.example.task.tracker.dto.TaskHistoryDTO;
import com.example.task.tracker.entity.AuthUser;
import com.example.task.tracker.entity.Task;
import com.example.task.tracker.entity.TaskHistory;
import com.example.task.tracker.repository.TaskHistoryRepository;
import com.example.task.tracker.repository.TaskRepository;
import com.example.task.tracker.repository.UserRepository;
import com.example.task.tracker.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskHistoryRepository taskHistoryRepository;

    public TaskServiceImpl(final TaskRepository taskRepository,
                           final UserRepository userRepository,
                           final TaskHistoryRepository taskHistoryRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskHistoryRepository = taskHistoryRepository;
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
        } catch (Exception exp) {
            log.error("Exception in saveTask", exp);
            throw new RuntimeException("Task Cannot save Right Now");
        }
        return true;
    }

    @Override
    public TaskDTO getTask(Long id) {

        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {

            Task task = optionalTask.get();
            TaskDTO taskDTO = new TaskDTO();

            taskDTO.setId(task.getId());
            taskDTO.setName(task.getName());
            taskDTO.setStatus(task.getStatus());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setCreatedAt(task.getCreatedAt());
            taskDTO.setUpdatedAt(task.getUpdatedAt());

            return taskDTO;
        }
        return null;
    }

    @Override
    public String updateTask(TaskDTO update) {
        Optional<Task> optionalTask = taskRepository.findById(update.getId());

        try {
            if (optionalTask.isEmpty()) {
                throw new RuntimeException("TaskId Cannot found" + update.getId());
            }

            Task existingTask = optionalTask.get();

            existingTask.setName(update.getName());
            existingTask.setStatus(update.getStatus());
            existingTask.setDescription(update.getDescription());

            taskRepository.save(existingTask);

            return "Task Updated Successfully";
        } catch (Exception exp) {
            throw new RuntimeException("Task Cannot  Update Right Now" + update.getId());
        }
    }

    @Override
    public List<TaskDTO> getAll() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> taskDTOS = new ArrayList<>();

        for (Task task : tasks) {

            TaskDTO taskDTO = new TaskDTO();

            taskDTO.setId(task.getId());
            taskDTO.setName(task.getName());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setStatus(task.getStatus());

            taskDTOS.add(taskDTO);
        }
        return taskDTOS;
    }

    @Override
    public TaskDTO assign(Long userId, Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Optional<AuthUser> optionalAuthUser = userRepository.findById(userId);
        AuthUser existingUser = null ;

        if (optionalTask.isPresent()) {

            Task existingTask = optionalTask.get();

            if (optionalAuthUser.isPresent()) {
               existingUser = optionalAuthUser.get();

                existingTask.setAuthUser(setUser(existingUser.getId()));
            }

            taskRepository.save(existingTask);

            setTaskHistory(existingUser,existingTask);


            TaskDTO taskDTO = new TaskDTO();

            taskDTO.setId(existingTask.getAuthUser().getId());
            taskDTO.setName(existingTask.getName());
            taskDTO.setStatus(existingTask.getStatus());
            taskDTO.setDescription(existingTask.getDescription());
            taskDTO.setCreatedAt(existingTask.getCreatedAt());
            taskDTO.setUpdatedAt(existingTask.getUpdatedAt());
            taskDTO.setUserId(existingTask.getId());


            return taskDTO;

        }
        return null;
    }

    private void setTaskHistory(AuthUser user, Task task) {

        TaskHistory taskHistory = new TaskHistory();

        taskHistory.setUser(user);
        taskHistory.setTask(task);
        taskHistory.setCreatedAt(LocalDateTime.now());

        taskHistoryRepository.save(taskHistory);
    }


    @Override
    public String updateStatus(Long id, String status) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        try {
            if (optionalTask.isPresent()) {

                Task existingTask = optionalTask.get();

                existingTask.setStatus(status);

                taskRepository.save(existingTask);

                return "Status updated Successfully";
            }
        } catch (Exception exp) {
            throw new RuntimeException("status cannot update Now", exp);
        }
        return null;
    }

    @Override
    public List<TaskHistoryDTO> listTask(Long id) {

        List<TaskHistory> taskHistoryList = taskHistoryRepository.findByTaskId(id);
        List<TaskHistoryDTO> taskHistoryDTOS = new ArrayList<>();

        for (TaskHistory taskHistory : taskHistoryList){

            TaskHistoryDTO taskHistoryDTO = new TaskHistoryDTO();

            taskHistoryDTO.setId(taskHistory.getId());
            taskHistoryDTO.setUser(taskHistory.getUser());
            taskHistoryDTO.setTask(taskHistory.getTask());
            taskHistoryDTO.setCreatedAt(taskHistory.getCreatedAt());

            taskHistoryDTOS.add(taskHistoryDTO);

        }
        return taskHistoryDTOS;
    }

    private AuthUser setUser(Long id) {
        Optional<AuthUser> optionalAuthUser = userRepository.findById(id);

        return optionalAuthUser.get();
    }


}
