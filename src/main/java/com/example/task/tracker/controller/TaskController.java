package com.example.task.tracker.controller;

import com.example.task.tracker.dto.TaskDTO;
import com.example.task.tracker.dto.TaskHistoryDTO;
import com.example.task.tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/save/task")
    public boolean saveTask(@RequestBody TaskDTO save) {
        return taskService.saveTask(save);
    }

    @GetMapping("/get/task")
    public TaskDTO getTask(@RequestParam(name = "id", required = false) Long id) {
        return taskService.getTask(id);
    }

    @PutMapping("/update/task")
    public String updateTask(@RequestBody TaskDTO update) {
        return taskService.updateTask(update);
    }

    @GetMapping("/get/all/task")
    public List<TaskDTO> findAll(){
        return taskService.getAll();
    }

    @GetMapping("/assign/task")
    public TaskDTO assignTask(@RequestParam (name = "userId",required = false)Long userId,
                           @RequestParam (name = "id",required = false)Long id){

        return taskService.assign(userId, id);

    }

    @PutMapping("/update/status")
    public String updateSTS(@RequestParam (name = "id",required = false)Long id,
                            @RequestParam (name = "status",required = false)String status){
        return taskService.updateStatus(id,status);
    }

    @GetMapping("get/all/id")
    public List<TaskHistoryDTO> getAllTask(@RequestParam (name = "id",required = false) Long id){
        return taskService.listTask(id);
    }
}
