package com.example.SysEve.web.models;



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

public class Participant {

    private Long id;
    private String firstName;
    private String lastName;
    private String birthday;
    private String email;
    private int number;
    private String image;

    

}