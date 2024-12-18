package com.example.SysEve.web.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.example.SysEve.business.services.EventService;
import com.example.SysEve.dao.entities.Event;
import com.example.SysEve.web.models.requests.EventForm;
import org.springframework.data.domain.Page;
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

    
    public static String uploadDirectory = System.getProperty("user.home") + "/SysEve/uploads";
    

    private final EventService eventService;

    @RequestMapping({ "/events"})
     public String getAllEvents(@RequestParam(defaultValue = "0") int page,
     @RequestParam(defaultValue = "7") int pageSize,
     Model model) {
        Page<Event> eventPage = this.eventService.getAllEventPagination(PageRequest.of(page, pageSize));
        model.addAttribute("events", eventPage.getContent());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventPage.getTotalPages()); 

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
    @RequestParam MultipartFile file
) {
    if (bindingResult.hasErrors()) {
        model.addAttribute("error", "Invalid data");
        return "add-event";
    }

    String fileName = null;

    if (!file.isEmpty()) {
        fileName = file.getOriginalFilename();
        Path newFilePath = Paths.get(uploadDirectory, fileName);

        try {
            Files.createDirectories(Paths.get(uploadDirectory)); 
            Files.write(newFilePath, file.getBytes());          
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "File upload failed");
            return "add-event";
        }
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
        fileName
    ));

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
    

    this.eventService.deleteEventById(id);
    return "redirect:/events";
}

@RequestMapping({"/", "/AllEvents"})
     public String getAllEventsClient(@RequestParam(defaultValue = "0") int page,
     @RequestParam(defaultValue = "10") int pageSize,
     Model model) {
        Page<Event> eventPage = this.eventService.getAllEventPagination(PageRequest.of(page, pageSize));
        model.addAttribute("events", eventPage.getContent());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventPage.getTotalPages()); 

         return  "events";

     }

     @RequestMapping("/AllEvents/create")
     public String showInscriptionForm(Model model) {
         model.addAttribute("eventForm", new EventForm());
         return "inscription";
     }



     @RequestMapping(path = "/events/search", method = RequestMethod.GET)
    public String searchEventsByNameOrCategory(
        @RequestParam(value = "query", required = false) String query,
        @RequestParam(value = "page", required = false, defaultValue = "0") String page,  // Keep as String
        @RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize,
        Model model) {

    int currentPage = 0;  // Default to 0 if invalid input

    try {
        currentPage = Integer.parseInt(page);  // Try to parse the page as an integer
    } catch (NumberFormatException e) {
        // If it fails, the page will remain as 0 (or you could log the error)
    }

    // Fetch events matching the query with pagination
    Page<Event> eventPage = (query == null || query.trim().isEmpty()) 
            ? eventService.getAllEventPagination(PageRequest.of(currentPage, pageSize))  // Fetch all events if query is empty
            : eventService.searchEventsWithPagination(query, PageRequest.of(currentPage, pageSize));  // Add pagination for search
    
    // Add data to the model
    model.addAttribute("events", eventPage.getContent());
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("currentPage", currentPage);
    model.addAttribute("totalPages", eventPage.getTotalPages());
    model.addAttribute("searchQuery", query);

    // Return the appropriate view
    return page;  // Use the same view as for normal events listing
}


}






