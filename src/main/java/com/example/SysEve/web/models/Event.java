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

public class Event {

    private Long id;
    private String name;
    private String category;
    private String description;
    private String date;
    private String location;
    private int availableSeats;
    private Double price;
    private String image;

}