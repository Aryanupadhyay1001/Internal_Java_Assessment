package com.example.helloWorld.service;

import com.example.helloWorld.model.Application;
import com.example.helloWorld.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    @Override
    public List<Application> getApplicationsByStatus(String status) {
        return applicationRepository.findByStatus(status);
    }

    @Override
    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Application updateApplication(Long id, Application updatedApplication) {
        return applicationRepository.findById(id).map(app -> {
            app.setStudentName(updatedApplication.getStudentName());
            app.setCompany(updatedApplication.getCompany());
            app.setRole(updatedApplication.getRole());
            app.setStatus(updatedApplication.getStatus());
            return applicationRepository.save(app);
        }).orElse(null);
    }

    @Override
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}
