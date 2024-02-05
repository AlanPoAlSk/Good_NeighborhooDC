package com.alanskrzecz.goodneighborhoodc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alanskrzecz.goodneighborhoodc.models.Neighborhood;
import com.alanskrzecz.goodneighborhoodc.models.Task;
import com.alanskrzecz.goodneighborhoodc.models.Task.TaskStatus;
import com.alanskrzecz.goodneighborhoodc.models.User;
import com.alanskrzecz.goodneighborhoodc.repositories.TaskRepository;

@Service
public class TaskService {
	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public List<Task> allTasks(Neighborhood neighborhood) {
        // Use the custom query method to fetch tasks based on neighborhood ID
		return taskRepository.findByUser_NeighborhoodId(neighborhood.getId());
    }
	
	public Task addTask(Task newTask) {
		return taskRepository.save(newTask);
	}
	
	public Task oneTask(Long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		if(optionalTask.isPresent()) {
			return optionalTask.get();
		}else {
			return null;
		}
	}
	
	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}
	
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}

	
    public List<Task> findAcceptedTasksByUser(User user) {
        return taskRepository.findByUserAndStatus(user, TaskStatus.ACCEPTED);
    }
    
    public List<Task> getAppliedTasks(User user) {
        // Assuming there is a Task entity with a field representing the accepted user
        return taskRepository.findByAcceptedUser(user);
    }
    
    public List<Task> findTasksByUserOrAcceptedUser(User user) {
        return taskRepository.findByUserOrAcceptedUser(user, user);
    }
    
    



}
