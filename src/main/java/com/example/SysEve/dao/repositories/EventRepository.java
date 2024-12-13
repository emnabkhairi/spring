package com.example.SysEve.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SysEve.dao.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

    List<Event>  findByName(String name);
    
    
}