package com.example.helloWorld.service;

import com.example.helloWorld.model.Application;
import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    List<Application> getAllApplications();
    Optional<Application> getApplicationById(Long id);
    List<Application> getApplicationsByStatus(String status);
    Application createApplication(Application application);
    Application updateApplication(Long id, Application application);
    void deleteApplication(Long id);
}
