package ch.cern.todo.repository;

import ch.cern.todo.model.TaskCategory;
import org.springframework.data.repository.CrudRepository;

public interface TaskCategoryRepository extends CrudRepository<TaskCategory, Long> {
}
