package ch.cern.todo.controller;

import ch.cern.todo.model.Task;
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

    @GetMapping
    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "taskId") Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }


    @PutMapping("/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "taskId") Long id,
                                           @RequestBody Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

        task.setTaskName(taskDetails.getTaskName());
        task.setTaskDescription(taskDetails.getTaskDescription());
        task.setDeadline(taskDetails.getDeadline());
        task.setCategory(taskDetails.getCategory());

        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable(value = "taskId") Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

        taskRepository.delete(task);
        return ResponseEntity.ok().build();
    }
}

