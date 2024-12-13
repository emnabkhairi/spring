package com.example.SysEve.web.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotNull;


@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter


public class ParticipantEventForm {
    
 

    @NotNull
    private  Long  participantId;

    @NotNull
    private  Long eventId;

    public long pid(){
        return this.participantId;
    }
}


