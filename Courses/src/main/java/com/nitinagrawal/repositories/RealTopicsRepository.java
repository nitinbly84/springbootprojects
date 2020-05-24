package com.nitinagrawal.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nitinagrawal.entities.Topic;

@Repository
public interface RealTopicsRepository extends CrudRepository<Topic, String>{

}
