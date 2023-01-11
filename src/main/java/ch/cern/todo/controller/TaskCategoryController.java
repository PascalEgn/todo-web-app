package ch.cern.todo.controller;

import ch.cern.todo.model.TaskCategory;
import ch.cern.todo.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class TaskCategoryController {
    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @GetMapping
    public List<TaskCategory> getAllTaskCategories() {
        return (List<TaskCategory>) taskCategoryRepository.findAll();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<TaskCategory> getTaskCategory(@PathVariable(value = "categoryId") Long categoryId) {
        TaskCategory taskCategory = taskCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("TaskCategory", "categoryId", categoryId));
        return ResponseEntity.ok().body(taskCategory);
    }
    @PostMapping("/create")
    public TaskCategory createTaskCategory(@RequestBody TaskCategory taskCategory) {
        return taskCategoryRepository.save(taskCategory);
    }


    @PutMapping("/update/{categoryId}")
    public ResponseEntity<TaskCategory> updateTaskCategory(@PathVariable(value = "categoryId") Long categoryId,
                                                           @RequestBody TaskCategory taskCategoryDetails) {
        TaskCategory taskCategory = taskCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("TaskCategory", "categoryId", categoryId));

        taskCategory.setCategoryName(taskCategoryDetails.getCategoryName());
        taskCategory.setCategoryDescription(taskCategoryDetails.getCategoryDescription());

        TaskCategory updatedTaskCategory = taskCategoryRepository.save(taskCategory);
        return ResponseEntity.ok(updatedTaskCategory);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<Object> deleteTaskCategory(@PathVariable(value = "categoryId") Long categoryId) {
        TaskCategory taskCategory = taskCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("TaskCategory", "categoryId", categoryId));

        taskCategoryRepository.delete(taskCategory);
        return ResponseEntity.ok().build();
    }
}
