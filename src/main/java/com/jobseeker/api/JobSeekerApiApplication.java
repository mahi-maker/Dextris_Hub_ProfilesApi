package com.jobseeker.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Job Seeker Profile API",
        version = "1.0",
        description = "API for managing job seeker profiles, experiences, education, and resumes"
    )
)
public class JobSeekerApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobSeekerApiApplication.class, args);
    }
}