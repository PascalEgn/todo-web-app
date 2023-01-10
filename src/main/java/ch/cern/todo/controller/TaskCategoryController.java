package ch.cern.todo.controller;

import ch.cern.todo.model.TaskCategory;
import ch.cern.todo.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-categories")
public class TaskCategoryController {
    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @GetMapping
    public List<TaskCategory> getAllTaskCategories() {
        return (List<TaskCategory>) taskCategoryRepository.findAll();
    }

    @PostMapping
    public TaskCategory createTaskCategory(@RequestBody TaskCategory taskCategory) {
        return taskCategoryRepository.save(taskCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskCategory> getTaskCategoryById(@PathVariable(value = "id") Long id) {
        TaskCategory taskCategory = taskCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskCategory", "id", id));
        return ResponseEntity.ok().body(taskCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskCategory> updateTaskCategory(@PathVariable(value = "id") Long id,
                                                           @RequestBody TaskCategory taskCategoryDetails) {
        TaskCategory taskCategory = taskCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskCategory", "id", id));

        taskCategory.setName(taskCategoryDetails.getName());
        taskCategory.setDescription(taskCategoryDetails.getDescription());

        TaskCategory updatedTaskCategory = taskCategoryRepository.save(taskCategory);
        return ResponseEntity.ok(updatedTaskCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTaskCategory(@PathVariable(value = "id") Long id) {
        TaskCategory taskCategory = taskCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskCategory", "id", id));

        taskCategoryRepository.delete(taskCategory);
        return ResponseEntity.ok().build();
    }
}
