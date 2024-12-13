package com.example.SysEve.business.services;

import java.util.List;

import com.example.SysEve.dao.entities.ParticipantEvent;

public interface ParticipantEventService {


     //Read opérations
    List<ParticipantEvent> getAllParticipantEvent();
    ParticipantEvent getParticipantEventById(Long id) /*throws Exception*/;
    
    //create
    ParticipantEvent addParticipantEvent(ParticipantEvent participantEvent);
    //Delete
    void deleteParticipantEventById(Long id);
    
}
