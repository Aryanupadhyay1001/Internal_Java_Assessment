package com.example.helloWorld.controller;

import com.example.helloWorld.model.Application;
import com.example.helloWorld.service.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Show all applications in a table
    @GetMapping("/applications")
    public String listApplications(Model model) {
        List<Application> applications = applicationService.getAllApplications();
        model.addAttribute("applications", applications);
        return "applications";
    }

    // Show form to add new application
    @GetMapping("/applications/new")
    public String showAddForm(Model model) {
        model.addAttribute("application", new Application());
        return "application_form";
    }

    // Save new or edited application
    @PostMapping("/applications")
    public String saveApplication(@ModelAttribute("application") Application app) {
        applicationService.createApplication(app);
        return "redirect:/applications";
    }

    // Show form to edit an existing application
    @GetMapping("/applications/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Application app = applicationService.getApplicationById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid application Id: " + id));
        model.addAttribute("application", app);
        return "application_form";
    }

    // Delete an application
    @GetMapping("/applications/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return "redirect:/applications";
    }

    // REST-style GET by ID
    @GetMapping("/application/{id}")
    @ResponseBody
    public Application getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found with id: " + id));
    }

    // REST-style GET by status
    @GetMapping("/applications/status/{status}")
    @ResponseBody
    public List<Application> getApplicationsByStatus(@PathVariable String status) {
        return applicationService.getApplicationsByStatus(status);
    }

    // REST-style POST (if used by Postman/Hoppscotch)
    @PostMapping("/application")
    @ResponseBody
    public Application createApplication(@RequestBody Application app) {
        return applicationService.createApplication(app);
    }

    // REST-style PUT
    @PutMapping("/application/{id}")
    @ResponseBody
    public Application updateApplication(@PathVariable Long id, @RequestBody Application updatedApp) {
        Application existing = applicationService.getApplicationById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found with id: " + id));
        existing.setStudentName(updatedApp.getStudentName());
        existing.setCompany(updatedApp.getCompany());
        existing.setRole(updatedApp.getRole());
        existing.setStatus(updatedApp.getStatus());
        return applicationService.createApplication(existing);
    }

    // REST-style DELETE
    @DeleteMapping("/application/{id}")
    @ResponseBody
    public String deleteApplicationById(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return "Deleted application with ID: " + id;
    }
}
