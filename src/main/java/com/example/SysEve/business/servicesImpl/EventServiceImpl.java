package com.example.SysEve.business.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SysEve.business.services.EventService;
import com.example.SysEve.dao.entities.Event;
import com.example.SysEve.dao.repositories.EventRepository;

@Service
public class EventServiceImpl implements EventService{


    private final EventRepository  eventRepository;
    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository=eventRepository;
    } 
    @Override
    public List<Event> getAllEvent() {
        return    this.eventRepository.findAll();
        
    }

    @Override
    public Event getEventById(Long id) {
        if(id==null){
            return null; 
        }
       return this.eventRepository.findById(id).get();
    }

    @Override
    public List<Event> getEventByName(String name) {
        return this.eventRepository.findByName(name);
    }

    @Override
    public Event addEvent(Event event) {
        if(event==null){
            return null;
        }
       return this.eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        if(event==null){
            return null;
        }
       return this.eventRepository.save(event);
    }

    @Override
    public void deleteEventById(Long id) {
        if(id==null){
            return ;
        }
         this.eventRepository.deleteById(id);
    }
    
}