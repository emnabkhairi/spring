package com.example.SysEve.web.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.SysEve.business.services.EventService;
import com.example.SysEve.business.services.ParticipantEventService;
import com.example.SysEve.business.services.ParticipantService;
import com.example.SysEve.dao.entities.Event;
import com.example.SysEve.dao.entities.Participant;
import com.example.SysEve.dao.entities.ParticipantEvent;
import com.example.SysEve.web.models.requests.EventForm;
import com.example.SysEve.web.models.requests.ParticipantEventForm;
import com.example.SysEve.web.models.requests.ParticipantForm;

import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ParticipantController {

    
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images";
    

    private final ParticipantService participantService;
    private final EventService eventService;
    private final ParticipantEventService participantEventService;
    @Autowired

    public ParticipantController(ParticipantService participantService,EventService eventService,ParticipantEventService participantEventService){
        this.eventService= eventService;
        this.participantService= participantService;
        this.participantEventService= participantEventService;
    }



    @RequestMapping({"/participants"})
     public String getAllParticipants(Model model) {
        model.addAttribute("participants", this.participantService.getAllParticipants());
         return  "participants";
     }


     @RequestMapping("/participants/create")
     public String showAddParticipantForm(Model model) {
         model.addAttribute("participantForm", new ParticipantForm());
         model.addAttribute("eventForm", new EventForm());
         model.addAttribute("participantEventForm", new ParticipantEventForm());
         return "inscription";
     }


    //   @RequestMapping(path = "/create", method = RequestMethod.POST)



 @RequestMapping(path = "/createParticipant", method = RequestMethod.POST)
    public String addEvent(
        @Valid @ModelAttribute ParticipantForm participantForm,  @Valid @ModelAttribute ParticipantEventForm participantEventForm,
        BindingResult bindingResult,
        Model model,
        @RequestParam MultipartFile file) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Données invalides");
            return "inscription";
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

            this.participantService.addParticipant(new Participant(
                null,
                participantForm.getFirstName(),
                participantForm.getLastName(),
                participantForm.getBirthday(),
                participantForm.getEmail(),
                participantForm.getNumber(),
                fileName.toString()
            ));
        } else {
            this.participantService.addParticipant(new Participant(
                null,
                participantForm.getFirstName(),
                participantForm.getLastName(),
                participantForm.getBirthday(),
                participantForm.getEmail(),
                participantForm.getNumber(),
                null
            ));
        }

       /*  Participant participant = participantService.getParticipantById(participantEventForm.getParticipantId());
        Event event = eventService.getEventById(participantEventForm.getEventId());
        ParticipantEvent participantEvent= new ParticipantEvent();
        participantEvent.setParticipant(participant);
        participantEvent.setEvent(event);
        
        participantEventService.addParticipantEvent(participantEvent); */

        return "redirect:/participants";
    }




    @RequestMapping(path = "/participants/{id}/delete", method = RequestMethod.POST)
    public String deleteEventById(@PathVariable Long id) {
    Participant participant = this.participantService.getParticipantById(id);

    
    if (participant != null && participant.getImage() != null) {
        Path filePath = Paths.get(uploadDirectory, participant.getImage());
        try {
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    this.participantService.deleteParticipantById(id);
    return "redirect:/participants";
}


}



