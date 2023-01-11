package ch.cern.todo.controller;

import ch.cern.todo.model.Task;
import ch.cern.todo.model.TaskCategory;
import ch.cern.todo.repository.TaskCategoryRepository;
import ch.cern.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "taskId") Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "taskId", taskId));
        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        TaskCategory category = taskCategoryRepository.findById(task.getTaskCategory().getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", task.getTaskCategory().getCategoryId()));
        task.setTaskCategory(category);
        return taskRepository.save(task);
    }


    @PutMapping("/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "taskId") Long taskId,
                                           @RequestBody Task taskDetails) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "taskId", taskId));

        if (taskDetails.getTaskCategory() != null) {
            TaskCategory category = taskCategoryRepository.findById(taskDetails.getTaskCategory().getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", taskDetails.getTaskCategory()));
            task.setTaskCategory(category);
        } else {
            task.setTaskCategory(task.getTaskCategory());
        }
        task.setTaskName(taskDetails.getTaskName());
        task.setTaskDescription(taskDetails.getTaskDescription());
        task.setDeadline(taskDetails.getDeadline());

        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable(value = "taskId") Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId));

        taskRepository.delete(task);
        return ResponseEntity.ok().build();
    }
}

