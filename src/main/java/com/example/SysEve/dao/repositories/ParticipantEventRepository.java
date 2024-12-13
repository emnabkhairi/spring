package com.example.SysEve.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SysEve.dao.entities.ParticipantEvent;

public interface ParticipantEventRepository extends JpaRepository<ParticipantEvent, Long>{
    
}