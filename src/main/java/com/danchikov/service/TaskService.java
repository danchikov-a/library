package com.danchikov.service;

import com.danchikov.entity.Role;
import com.danchikov.entity.Task;
import com.danchikov.entity.User;
import com.danchikov.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public boolean saveTask(Task task) {

        Task taskFromDB = taskRepository.findByName(task.getName());

        if ((taskFromDB != null) && (task.getUserOfTask().equals(taskFromDB.getUserOfTask()))) {
            return false;
        }

        taskRepository.save(task);
        return true;
    }

    public boolean deleteTask(Long id){
        if(taskRepository.findById(id).isPresent()){
            taskRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
