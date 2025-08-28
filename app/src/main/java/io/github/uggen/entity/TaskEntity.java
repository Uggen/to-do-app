package io.github.uggen.entity;

import java.time.LocalDateTime;

public class TaskEntity {

    private final Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private final LocalDateTime created_at;
    private LocalDateTime updated_at;

    private TaskEntity(Long id, String title, String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        created_at = createdAt;
        updated_at = updatedAt;
    }

    public static TaskEntity create(String title, String description) {
        return new TaskEntity(null, title, description, TaskStatus.NEW, LocalDateTime.now(), LocalDateTime.now());
    }

    public static TaskEntity restore(long id, String title, String description, TaskStatus status,  LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new TaskEntity(id, title, description, status, createdAt, updatedAt);
    }

    public boolean isNew(){
        return id == null;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }
    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
