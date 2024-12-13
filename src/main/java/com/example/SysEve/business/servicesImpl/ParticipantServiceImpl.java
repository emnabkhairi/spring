package com.example.SysEve.business.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SysEve.business.services.ParticipantService;
import com.example.SysEve.dao.entities.Participant;
import com.example.SysEve.dao.repositories.ParticipantRepository;

@Service
public class ParticipantServiceImpl implements ParticipantService{


    private final ParticipantRepository  participantRepository;
    public ParticipantServiceImpl(ParticipantRepository participantRepository){
        this.participantRepository=participantRepository;
    } 
    @Override
    public List<Participant> getAllParticipants() {
        return    this.participantRepository.findAll();
        
    }

    @Override
    public Participant getParticipantById(Long id) {
        if(id==null){
            return null; 
        }
       return this.participantRepository.findById(id).get();
    }

    @Override
    public List<Participant> getParticipantByName(String name) {
        return this.participantRepository.findByFirstName(name);
    }

    @Override
    public Participant addParticipant(Participant event) {
        if(event==null){
            return null;
        }
       return this.participantRepository.save(event);
    }


    @Override
    public void deleteParticipantById(Long id) {
        if(id==null){
            return ;
        }
         this.participantRepository.deleteById(id);
    }

    
    
}