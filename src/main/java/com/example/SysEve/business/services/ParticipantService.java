package com.example.SysEve.business.services;

import java.util.List;

import com.example.SysEve.dao.entities.Participant;

public interface ParticipantService {
    //Read opérations
    List<Participant> getAllParticipants();
    Participant getParticipantById(Long id) /*throws Exception*/;
    List<Participant> getParticipantByName(String firstname);
    //create
    Participant addParticipant(Participant participant);
    //Delete
    void deleteParticipantById(Long id);

    
    
}