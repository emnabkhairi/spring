package com.example.SysEve.business.services;

import java.util.List;

import com.example.SysEve.dao.entities.Event;

public interface EventService {
    //Read opérations
    List<Event> getAllEvent();
    Event getEventById(Long id) /*throws Exception*/;
    List<Event> getEventByName(String name);
    //create
    Event addEvent(Event event);
    //Update
    Event updateEvent(Event event);
    //Delete
    void deleteEventById(Long id);
    
}