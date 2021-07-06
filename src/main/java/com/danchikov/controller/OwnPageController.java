package com.danchikov.controller;

import com.danchikov.entity.Task;
import com.danchikov.entity.User;
import com.danchikov.repository.TaskRepository;
import com.danchikov.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OwnPageController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;
    private List<Task> listOfTasks;
    @GetMapping("ownPage")
    public String showList(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("nick",username);

        model.addAttribute("task", new Task());

        listOfTasks = taskRepository.findAllByUserOfTask(username);

        model.addAttribute("tasks",listOfTasks);

        return "ownPage";
    }

    @PostMapping("ownPage")
    public String addTask(@ModelAttribute("task") @Valid Task task, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("nick",username);
        task.setUserOfTask(username);//каким пользователем была добавлена задача
        taskService.saveTask(task);

        return "redirect:/ownPage";
    }

    @PostMapping("task/{id}/delete")//обрабатываем post запрос по данному адресу
    public String deleteBooks(@ModelAttribute("taskModel") Task book, Model model,
                              @PathVariable(value = "id") Long id ){
        taskService.deleteTask(id);
        return "redirect:/ownPage";
    }
}
