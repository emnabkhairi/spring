package com.example.SysEve.web.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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
public class ParticipantForm {


    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;


    @NotBlank(message = "Birthday is required") 
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Birthday must be in DD-MM-YYYY format") 
    private String birthday;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email should be valid")
    private String email;

    @NotNull(message = "Phone number is required")
    @Positive(message = "Phone number should be a positive number")
    private Integer number; // Integer type allows for null validation
    
    private String image;
}
