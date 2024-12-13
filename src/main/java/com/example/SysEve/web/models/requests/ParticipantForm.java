package com.example.SysEve.web.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

public class ParticipantForm{

    /* public ParticipantForm(String firstame2, String lastname2, String birthday2, String email2, int number2) {
        
    } */

    
    @NotNull
    @NotBlank(message="first name obligatoir")
    private String firstName;
    @NotNull
    @NotBlank(message="last Name obligatoir")
    private String lastName;
    @NotNull
    @NotBlank(message="birthday obligatoir")
    private String birthday;
    @NotNull
    @NotBlank(message="email obligatoir")
    private String email;
    @NotNull
    @NotBlank(message="phone number obligatoir")
    private int number;
    private String image;
    

}
