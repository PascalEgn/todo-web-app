package ch.cern.todo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASKS")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TASK_ID")
    private Long id;

    @Column(name = "TASK_NAME", length = 100)
    private String taskName;

    @Column(name = "TASK_DESCRIPTION", length = 500)
    private String taskDescription;

    @Column(name = "DEADLINE", columnDefinition = "TIMESTAMP")
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private TaskCategory taskCategory;

    public Task() {
    }

    public Task(Long id, String taskName, String taskDescription, LocalDateTime deadline, TaskCategory taskCategory) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.taskCategory = taskCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public TaskCategory getCategory() {
        return taskCategory;
    }

    public void setCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }
}
