package com.alanskrzecz.goodneighborhoodc.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.alanskrzecz.goodneighborhoodc.models.Neighborhood;
import com.alanskrzecz.goodneighborhoodc.repositories.NeighborhoodRepository;

@Service
public class NeighborhoodService {
	private NeighborhoodRepository neighborhoodRepository;

	public NeighborhoodService(NeighborhoodRepository neighborhoodRepository) {
		this.neighborhoodRepository = neighborhoodRepository;
	}
	
	// Method to get all neighborhoods
    public List<Neighborhood> getAllNeighborhoods() {
    	return StreamSupport.stream(neighborhoodRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    // Method to get a neighborhood by ID
    public Neighborhood getNeighborhoodById(Long id) {
        return neighborhoodRepository.findById(id).orElse(null);
    }

}
