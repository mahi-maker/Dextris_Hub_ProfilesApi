package com.jobseeker.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDto {
    private Long id;
    
    @NotBlank(message = "Institution is required")
    private String institution;
    
    @NotBlank(message = "Degree is required")
    private String degree;
    
    private String fieldOfStudy;
    
    @NotNull(message = "Start date is required")
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private boolean current;
    
    private String description;
    
    private Double grade;
    
    private Long profileId;
}