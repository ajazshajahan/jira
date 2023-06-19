package com.example.task.tracker.controller;

import com.example.task.tracker.dto.TaskDTO;
import com.example.task.tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/save/task")
    public boolean saveTask(@RequestBody TaskDTO save){
        return taskService.saveTask(save);
    }

}
