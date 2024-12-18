package com.example.SysEve.web.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.example.SysEve.business.services.ParticipantService;
import com.example.SysEve.dao.entities.Participant;
import com.example.SysEve.web.models.requests.ParticipantForm;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ParticipantController {

    
    public static String uploadDirectory = System.getProperty("user.home") + "/SysEve/uploads";
    

    private final ParticipantService participantService;
    

    public ParticipantController(ParticipantService participantService){
        
        this.participantService= participantService;
       
    }



    @RequestMapping("/participants")
    public String getAllParticipants(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "7") int pageSize,
        @RequestParam(required = false) String searchQuery,
        Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() 
                                  && !authentication.getName().equals("anonymousUser");

        if (isAuthenticated) {
            Page<Participant> participantPage;
            if (searchQuery != null && !searchQuery.isEmpty()) {
                participantPage = this.participantService.searchParticipants(searchQuery, PageRequest.of(page, pageSize));
            } else {
                participantPage = this.participantService.getAllParticipantPagination(PageRequest.of(page, pageSize));
            }

            model.addAttribute("isAdmin", true);
            model.addAttribute("participants", participantPage.getContent());
            model.addAttribute("searchQuery", searchQuery);
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", participantPage.getTotalPages());
        } else {
            model.addAttribute("isAdmin", false);
            model.addAttribute("restorationSuccess", true);
        }

        return "participants";
    }



     @RequestMapping("/participants/create")
     public String showAddParticipantForm(Model model) {
         model.addAttribute("participantForm", new ParticipantForm());
         return "inscription";
     }


    //   @RequestMapping(path = "/create", method = RequestMethod.POST)


@RequestMapping(path = "/createParticipant", method = RequestMethod.POST)
public String addParticipant(
    @Valid @ModelAttribute ParticipantForm participantForm,
    BindingResult bindingResult,
    Model model,
    @RequestParam MultipartFile file
) {
    if (bindingResult.hasErrors()) {
        model.addAttribute("error", "Invalid data");
        return "inscription";
    }

    String fileName = null;

    if (!file.isEmpty()) {
        fileName = file.getOriginalFilename();
        Path newFilePath = Paths.get(uploadDirectory, fileName);

        try {
            Files.createDirectories(Paths.get(uploadDirectory)); // Ensure the directory exists
            Files.write(newFilePath, file.getBytes());           // Save the file
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "File upload failed");
            return "inscription";
        }
    }

    this.participantService.addParticipant(new Participant(
        null,
        participantForm.getFirstName(),
        participantForm.getLastName(),
        participantForm.getBirthday(),
        participantForm.getEmail(),
        participantForm.getNumber(),
        fileName
    ));

    return "redirect:/participants";
}





    @RequestMapping(path = "/participants/{id}/delete", method = RequestMethod.POST)
    public String deleteEventById(@PathVariable Long id) {

    

    this.participantService.deleteParticipantById(id);
    return "redirect:/participants";
}




}



