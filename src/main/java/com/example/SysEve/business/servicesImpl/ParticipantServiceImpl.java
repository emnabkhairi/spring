package com.example.SysEve.business.servicesImpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Participant> getAllParticipantPagination(Pageable pegeable) {
        if(pegeable ==null){
            return null;
        }
        return this.participantRepository.findAll(pegeable);

    }
    
    @Override
    public Page<Participant> searchParticipants(String query, Pageable pageable) {
        return this.participantRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            query, query, query, pageable);
    }
    
    
}