package com.jobseeker.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDto {
    private Long id;
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
    
    private String phone;
    
    @NotBlank(message = "Location is required")
    private String location;
    
    private String headline;
    
    private String summary;
    
    private String resumeUrl;
}