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

public class EventForm {

    /* public EventForm(String name2, String category2, String description2, String date2, String location2, int availableSeats2,
            Double price2) {
        
    } */
    @NotNull
    @NotBlank(message="Nom obligatoir")
    private String name;
    @NotNull
    @NotBlank(message="categorie obligatoir")
    private String category;
    @NotNull
    @NotBlank(message="description obligatoir")
    private String description;
    @NotNull
    @NotBlank(message="date obligatoir")
    private String date;
    @NotNull
    @NotBlank(message="location obligatoir")
    private String location;
    @NotNull
    @NotBlank(message="availabe seats obligatoir")
    private int availableSeats;
    @NotNull
    @NotBlank(message="price obligatoir")
    private Double price;
    private String image;

}