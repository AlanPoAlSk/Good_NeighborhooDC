package com.alanskrzecz.goodneighborhoodc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alanskrzecz.goodneighborhoodc.models.Task;
import com.alanskrzecz.goodneighborhoodc.models.Task.TaskStatus;
import com.alanskrzecz.goodneighborhoodc.models.User;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{
	List <Task> findAll();
	List<Task> findByUser_NeighborhoodId(Long neighborhoodId);
	List<Task> findByUserAndStatus(User user, TaskStatus status);
	List<Task> findByAcceptedUser(User acceptedUser);
	List<Task> findByUserOrAcceptedUser(User owner, User acceptedUser);

}
