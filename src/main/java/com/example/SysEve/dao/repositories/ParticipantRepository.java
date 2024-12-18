package com.example.SysEve.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SysEve.dao.entities.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    // Custom search query for partial matching first name, last name, and email
    Page<Participant> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
        String firstName, String lastName, String email, Pageable pageable);
}
