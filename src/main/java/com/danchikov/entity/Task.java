package com.danchikov.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String userOfTask;//чья задача

    @Transient
    @ManyToMany(mappedBy = "tasks")
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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

    public String getUserOfTask() {
        return userOfTask;
    }

    public void setUserOfTask(String userOfTask) {
        this.userOfTask = userOfTask;
    }
}
