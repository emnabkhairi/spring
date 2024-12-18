package com.example.SysEve.web.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @Size(max = 100, message = "Description must not be longer than 100 characters")
    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in DD-MM-YYYY format")
    private String date;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Available seats are required")
    @Positive(message = "Available seats must be positive")
    private Integer availableSeats;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;
    
    private String image;
}
