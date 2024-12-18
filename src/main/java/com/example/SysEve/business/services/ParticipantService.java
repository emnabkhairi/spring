package com.example.SysEve.business.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.SysEve.dao.entities.Participant;

public interface ParticipantService {
    //Read opérations
    List<Participant> getAllParticipants();
    Participant getParticipantById(Long id) /*throws Exception*/;
    //create
    Participant addParticipant(Participant participant);
    //Delete
    void deleteParticipantById(Long id);
    //pagination
    Page<Participant> getAllParticipantPagination(Pageable pegeable);
    //search
    Page<Participant> searchParticipants(String query, Pageable pageable);


    
    
}