package ch.cern.todo;

import ch.cern.todo.model.TaskCategory;
import ch.cern.todo.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @Override
    public void run(String... args) throws Exception {
        List<TaskCategory> categories = Arrays.asList(
                new TaskCategory(1L, "Work", "Tasks related to work", null),
                new TaskCategory(2L, "Personal", "Personal tasks", null),
                new TaskCategory(3L, "Shopping", "Tasks related to shopping", null),
                new TaskCategory(4L, "Other", "Other tasks", null)
        );

        taskCategoryRepository.saveAll(categories);
    }
}
