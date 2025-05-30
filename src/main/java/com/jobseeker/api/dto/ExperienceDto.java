package com.jobseeker.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExperienceDto {
    private Long id;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Company is required")
    private String company;
    
    private String location;
    
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private boolean current;
    
    private String description;
    
    private Long profileId;
}