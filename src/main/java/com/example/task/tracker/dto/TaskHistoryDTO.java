package com.example.task.tracker.dto;

import com.example.task.tracker.entity.AuthUser;
import com.example.task.tracker.entity.Task;

import java.time.LocalDateTime;

public class TaskHistoryDTO {

    private Long id;

    private LocalDateTime createdAt;

    private Task task;

    private AuthUser user;

      public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public AuthUser getUser() {
        return user;
    }

    public void setUser(AuthUser user) {
        this.user = user;
    }

       public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
