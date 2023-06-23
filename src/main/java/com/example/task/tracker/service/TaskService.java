package com.example.task.tracker.service;

import com.example.task.tracker.dto.TaskDTO;
import com.example.task.tracker.dto.TaskHistoryDTO;

import java.util.List;

public interface TaskService {

    boolean saveTask(TaskDTO save);

    TaskDTO getTask(Long id);

    String updateTask(TaskDTO update);

    List<TaskDTO> getAll();

    TaskDTO assign(Long userId, Long id);

    String updateStatus(Long id,String status);

    List<TaskHistoryDTO> listTask(Long id);

}
