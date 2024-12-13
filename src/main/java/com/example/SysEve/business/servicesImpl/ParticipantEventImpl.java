package com.example.SysEve.business.servicesImpl;

import java.util.List;
import java.util.Optional;

import com.example.SysEve.business.services.ParticipantEventService;

import com.example.SysEve.dao.entities.ParticipantEvent;
import com.example.SysEve.dao.repositories.EventRepository;
import com.example.SysEve.dao.repositories.ParticipantEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantEventImpl implements ParticipantEventService {

    private final ParticipantEventRepository participantEventRepository;

    @Autowired
    public ParticipantEventImpl(ParticipantEventRepository participantEventRepository, EventRepository eventRepository) {
        this.participantEventRepository = participantEventRepository;

    }

    @Override
    public List<ParticipantEvent> getAllParticipantEvent() {
        return this.participantEventRepository.findAll();
    }

    @Override
    public ParticipantEvent getParticipantEventById(Long id) {
        if (id == null) {
            return null;
        }
        Optional<ParticipantEvent> participantEvent = this.participantEventRepository.findById(id);
        return participantEvent.orElse(null);
    }


    @Override
    public ParticipantEvent addParticipantEvent(ParticipantEvent participantEvent) {
        if (participantEvent == null) {
            return null;
        }
        return this.participantEventRepository.save(participantEvent);
    }

    @Override
    public void deleteParticipantEventById(Long id) {
        if (id != null) {
            this.participantEventRepository.deleteById(id);
        }
    }


}
