package com.app.todo_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.todo_app.models.Task;
import com.app.todo_app.repository.TaskRepository;
import com.app.todo_app.services.TodoService;

@Controller
//@RequestMapping("/tasks")
public class TaskController {

    private final TodoService todoService;

    @Autowired
    public TaskController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String getAllTasks(Model model) {
    	List<Task> tasks = todoService.getAllTasks();
        System.out.println("Tasks sent to view: " + tasks);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
    
    @PostMapping
    public String createTask(@RequestParam String title) {
    	todoService.createTask(title);
        return "redirect:/";
    }
    
    
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id ) {
    	todoService.deleteTask(id);
    	return "redirect:/";
    }
    
    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id ) {
    	todoService.toggleTask(id);
    	return "redirect:/";
    }
}