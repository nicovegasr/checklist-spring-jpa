package com.example.checklist.tasks.infrastructure.entities;
import com.example.checklist.tasks.domain.models.Task;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task", nullable = false)
    private long idTask;
    @Column(name = "title", length = 50, nullable = false)
    private String title;
    @Column(name = "description", length = 100, nullable = false)
    private String description;
    @Column(name = "completed", nullable = false)
    private Boolean completed;
    @Column(name = "erased", nullable = false)
    private Boolean erased;
    public Task toDomainModel() {
        return new Task(this.getIdTask(), this.getTitle(), this.getDescription(), getCompleted(), getErased());
    }
    public static TaskEntity fromDomainModel(Task task) {
        return new TaskEntity(task.idTask(), task.title(), task.description(), task.completed(), task.erased());
    }
}
