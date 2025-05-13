package com.app.todo_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.todo_app.models.Task;
import com.app.todo_app.repository.TaskRepository;

@Service
public class TodoService {
    private final TaskRepository taskRepository;

    @Autowired 
    public TodoService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        System.out.println("Tasks from DB: " + tasks);
        return tasks;
    }

	public void createTask(String title) {
		Task task = new Task();
		task.setTitle(title);
		task.setCompleted(false);
		taskRepository.save(task);
		
	}

	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
		
	}
	public void toggleTask(Long id) {
		Task task =taskRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid task"));
		task.setCompleted(!task.isCompleted());
		
		taskRepository.save(task);
		
	}
}