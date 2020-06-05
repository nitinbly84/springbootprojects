package com.nitinagrawal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nitinagrawal.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User,String> {

}
