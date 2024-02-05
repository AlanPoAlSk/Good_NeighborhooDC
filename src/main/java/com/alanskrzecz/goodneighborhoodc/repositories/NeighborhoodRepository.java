package com.alanskrzecz.goodneighborhoodc.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alanskrzecz.goodneighborhoodc.models.Neighborhood;

public interface NeighborhoodRepository extends CrudRepository<Neighborhood, Long> {
	Neighborhood findByName(String name);
}
