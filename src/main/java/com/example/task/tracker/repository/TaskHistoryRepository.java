package com.example.task.tracker.repository;

import com.example.task.tracker.entity.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory,Long> {

   List<TaskHistory> findByTaskId(Long id);
}
