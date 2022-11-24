package com.example.renderferma.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "status")
    private String status = "RENDERING";

    @Column(name = "create_task")
    private LocalDateTime createTask = LocalDateTime.now();

    @Column(name = "update_task")
    private LocalDateTime updateTask;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User owner;

    public Task() {

    }

    public Task(User owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreateTask() {
        return createTask;
    }

    public void setCreateTask(LocalDateTime createTask) {
        this.createTask = createTask;
    }

    public LocalDateTime getUpdateTask() {
        return updateTask;
    }

    public void setUpdateTask(LocalDateTime updateTask) {
        this.updateTask = updateTask;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", createTask=" + createTask +
                ", updateTask=" + updateTask +
                ", owner=" + owner.getName() +
                '}';
    }
}
