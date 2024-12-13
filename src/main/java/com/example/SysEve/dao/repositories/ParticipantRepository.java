package com.example.SysEve.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SysEve.dao.entities.Participant;

public interface ParticipantRepository extends JpaRepository <Participant, Long>{

    List<Participant>  findByFirstName(String firstName);
    
}