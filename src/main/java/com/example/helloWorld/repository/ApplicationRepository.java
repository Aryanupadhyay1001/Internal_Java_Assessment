package com.example.helloWorld.repository;

import com.example.helloWorld.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStatus(String status);
}
