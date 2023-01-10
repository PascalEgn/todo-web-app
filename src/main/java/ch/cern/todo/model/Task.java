package ch.cern.todo.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "TASKS")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private Long id;

    @Column(name = "TASK_NAME", length = 100)
    private String name;

    @Column(name = "TASK_DESCRIPTION", length = 500)
    private String description;

    @Column(name = "DEADLINE", columnDefinition = "TIMESTAMP")
    private LocalDate deadline;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
    private TaskCategory category;

    public Task() {
    }

    public Task(Long id, String name, String description, LocalDate deadline, TaskCategory category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }
}
