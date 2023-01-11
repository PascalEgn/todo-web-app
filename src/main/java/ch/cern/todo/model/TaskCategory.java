package ch.cern.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "TASK_CATEGORIES")
@JsonIgnoreProperties("tasks")
public class TaskCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME", length = 100)
    private String categoryName;

    @Column(name = "CATEGORY_DESCRIPTION", length = 500)
    private String categoryDescription;

    @OneToMany(mappedBy = "taskCategory", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public TaskCategory() {
    }

    public TaskCategory(Long categoryId, String categoryName, String categoryDescription, List<Task> tasks) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.tasks = tasks;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long id) {
        this.categoryId = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
