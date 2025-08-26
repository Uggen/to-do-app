package entity;

import java.time.LocalDateTime;

public class TaskEntity {

    private long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public TaskEntity(long id, String title, String description,TaskStatus status, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public TaskEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
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
