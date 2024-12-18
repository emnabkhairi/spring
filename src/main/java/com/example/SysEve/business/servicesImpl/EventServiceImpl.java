package com.example.SysEve.business.servicesImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    public Page<Event> searchEventsWithPagination(String query, Pageable pageable) {
        return eventRepository.findByNameContainingOrCategoryContaining(query, query, pageable);
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

    @Override
    public Page<Event> getAllEventPagination(Pageable pegeable) {
        if(pegeable ==null){
            return null;
        }
        return this.eventRepository.findAll(pegeable);

      
    }

    
    
}