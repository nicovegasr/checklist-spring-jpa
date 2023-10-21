package com.example.checklist.tasks.entities;
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
    @Column(name = "status", nullable = false)
    private Boolean status;
    @Column(name = "erased", nullable = false)
    private Boolean erased;
}
