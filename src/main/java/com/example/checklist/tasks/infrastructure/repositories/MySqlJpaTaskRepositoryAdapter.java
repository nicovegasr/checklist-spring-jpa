package com.example.checklist.tasks.infrastructure.repositories;

import com.example.checklist.tasks.domain.models.Task;
import com.example.checklist.tasks.domain.repositories.TaskRepository;
import com.example.checklist.tasks.infrastructure.entities.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MySqlJpaTaskRepositoryAdapter implements TaskRepository {
    protected JpaTaskRepository jpaTaskRepository;

    public MySqlJpaTaskRepositoryAdapter(JpaTaskRepository jpaTaskRepository) {
        this.jpaTaskRepository = jpaTaskRepository;
    }
    public long save(Task task) {
        TaskEntity taskEntity = TaskEntity.fromDomainModel(task);
        jpaTaskRepository.save(taskEntity);
        return taskEntity.getIdTask();
    }
    public Page<Task> findAll(Pageable userPages) {
        Page<TaskEntity> allTaskEntities = jpaTaskRepository.findAll(userPages);
        return allTaskEntities.map(TaskEntity::toDomainModel);
    }
    public void delete(Task task) {
        jpaTaskRepository.delete(TaskEntity.fromDomainModel(task));
    }
    public Task findById(Long id) {
        return jpaTaskRepository.findById(id).map(TaskEntity::toDomainModel).orElse(null);
    }
}
