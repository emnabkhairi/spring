package com.example.SysEve.business.services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import com.example.SysEve.dao.entities.Event;

public interface EventService {
    //Read opérations
    List<Event> getAllEvent();
    Event getEventById(Long id) /*throws Exception*/;
    //create
    Event addEvent(Event event);
    //Update
    Event updateEvent(Event event);
    //Delete
    void deleteEventById(Long id);
    //pagination
    Page<Event> getAllEventPagination(Pageable pegeable);

    Page<Event> searchEventsWithPagination(String query, Pageable pageable) ;
    
    
    
}