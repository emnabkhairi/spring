package com.example.SysEve.web.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.example.SysEve.business.services.EventService;
import com.example.SysEve.dao.entities.Event;
import com.example.SysEve.web.models.requests.EventForm;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Controller
public class EventController {

    
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images";
    

    private final EventService eventService;

    @RequestMapping({"/", "/events"})
     public String getAllEvents(Model model) {
        model.addAttribute("events", this.eventService.getAllEvent());
         return  "test";
     }


     @RequestMapping("/events/create")
     public String showAddEventForm(Model model) {
         model.addAttribute("eventForm", new EventForm());
         return "add-event";
     }


    //   @RequestMapping(path = "/create", method = RequestMethod.POST)



 @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String addEvent(
        @Valid @ModelAttribute EventForm eventForm,
        BindingResult bindingResult,
        Model model,
        @RequestParam MultipartFile file) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Données invalides");
            return "add-event";
        }

        if (!file.isEmpty()) {
            StringBuilder fileName = new StringBuilder();
            fileName.append(file.getOriginalFilename());
            Path newFilePath = Paths.get(uploadDirectory, fileName.toString());

            try {
                Files.write(newFilePath, file.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }

            this.eventService.addEvent(new Event(
                null,
                eventForm.getName(),
                eventForm.getCategory(),
                eventForm.getDescription(),
                eventForm.getDate(),
                eventForm.getLocation(),
                eventForm.getAvailableSeats(),
                eventForm.getPrice(),
                fileName.toString()
            ));
        } else {
            this.eventService.addEvent(new Event(
                null,
                eventForm.getName(),
                eventForm.getCategory(),
                eventForm.getDescription(),
                eventForm.getDate(),
                eventForm.getLocation(),
                eventForm.getAvailableSeats(),
                eventForm.getPrice(),
                null
            ));
        }

        return "redirect:/events";
    }




@RequestMapping(path = "/events/{id}/edit")
public String showUpdateEventForm(@PathVariable Long id, Model model) {
    Event event = this.eventService.getEventById(id);
    if (event == null) {
        model.addAttribute("error", "L'événement n'existe pas.");
        return "redirect:/events";
    }
    model.addAttribute("eventForm", new EventForm(event.getName(), event.getCategory(), event.getDescription(), event.getDate(), event.getLocation(), event.getAvailableSeats(), event.getPrice(),  event.getImage()));
    model.addAttribute("id", id);
    return "update-event";
}




@RequestMapping(path = "/events/{id}/edit", method = RequestMethod.POST)
public String updateEvent(
        @Valid @ModelAttribute EventForm eventForm,
        BindingResult bindingResult,
        @PathVariable Long id,
        Model model,
        @RequestParam MultipartFile file
) {
    if (bindingResult.hasErrors()) {
        model.addAttribute("error", "Invalid input");
        return "update-event";
    }

    Event event = this.eventService.getEventById(id);
    event.setName(eventForm.getName());
    event.setCategory(eventForm.getCategory());
    event.setDescription(eventForm.getDescription());
    event.setDate(eventForm.getDate());
    event.setLocation(eventForm.getLocation());
    event.setAvailableSeats(eventForm.getAvailableSeats());
    event.setPrice(eventForm.getPrice());

    if (!file.isEmpty()) {
        StringBuilder fileName = new StringBuilder();
        Path newFilePath = Paths.get(uploadDirectory, file.getOriginalFilename());
        fileName.append(file.getOriginalFilename());
        try {
            Files.write(newFilePath, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (event.getImage() != null) {
            Path filePath = Paths.get(uploadDirectory, event.getImage());
            try {
                Files.deleteIfExists(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        event.setImage(fileName.toString());
    }

    this.eventService.updateEvent(event);
    return "redirect:/events";
}




    @RequestMapping(path = "/events/{id}/delete", method = RequestMethod.POST)
    public String deleteEventById(@PathVariable Long id) {
    Event event = this.eventService.getEventById(id);

    
    if (event != null && event.getImage() != null) {
        Path filePath = Paths.get(uploadDirectory, event.getImage());
        try {
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    this.eventService.deleteEventById(id);
    return "redirect:/events";
}

@RequestMapping({"/AllEvents"})
     public String getAllEventsClient(Model model) {
        model.addAttribute("events", this.eventService.getAllEvent());
         return  "events";
     }

     @RequestMapping("/AllEvents/create")
     public String showInscriptionForm(Model model) {
         model.addAttribute("eventForm", new EventForm());
         return "inscription";
     }

}



