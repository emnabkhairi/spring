package com.example.SysEve.dao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
public class ParticipantEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "participant_id", nullable = false)
    private  Participant participant;

    @ManyToOne
    @JoinColumn(name= "event_id", nullable = false)
    private  Event event;
}
