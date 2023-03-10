package ch.cern.todo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASKS")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private Long taskId;

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

    public Task(Long taskId, String taskName, String taskDescription, LocalDateTime deadline, TaskCategory taskCategory) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.taskCategory = taskCategory;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public TaskCategory getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }
}
