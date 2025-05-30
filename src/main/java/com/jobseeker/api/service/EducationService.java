package com.jobseeker.api.service;

import com.jobseeker.api.dto.EducationDto;
import com.jobseeker.api.exception.ResourceNotFoundException;
import com.jobseeker.api.model.Education;
import com.jobseeker.api.model.Profile;
import com.jobseeker.api.repository.EducationRepository;
import com.jobseeker.api.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationService {
    
    private final EducationRepository educationRepository;
    private final ProfileRepository profileRepository;

    public List<EducationDto> getAllEducations(Long profileId) {
        List<Education> educations = educationRepository.findByProfileId(profileId);
        return educations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public EducationDto getEducationById(Long profileId, Long educationId) {
        Education education = educationRepository.findByIdAndProfileId(educationId, profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id: " + educationId));
        
        return convertToDto(education);
    }

    public EducationDto createEducation(EducationDto educationDto) {
        Profile profile = profileRepository.findById(educationDto.getProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + educationDto.getProfileId()));
        
        Education education = new Education();
        BeanUtils.copyProperties(educationDto, education);
        education.setProfile(profile);
        
        Education savedEducation = educationRepository.save(education);
        return convertToDto(savedEducation);
    }

    public EducationDto updateEducation(Long educationId, EducationDto educationDto) {
        Education existingEducation = educationRepository.findById(educationId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id: " + educationId));
        
        // Ensure the education belongs to the correct profile
        if (!existingEducation.getProfile().getId().equals(educationDto.getProfileId())) {
            throw new ResourceNotFoundException("Education does not belong to the specified profile");
        }
        
        BeanUtils.copyProperties(educationDto, existingEducation, "id", "profile", "createdAt");
        
        Education updatedEducation = educationRepository.save(existingEducation);
        return convertToDto(updatedEducation);
    }

    public void deleteEducation(Long profileId, Long educationId) {
        Education education = educationRepository.findByIdAndProfileId(educationId, profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with id: " + educationId));
        
        educationRepository.delete(education);
    }
    
    private EducationDto convertToDto(Education education) {
        EducationDto dto = new EducationDto();
        BeanUtils.copyProperties(education, dto);
        dto.setProfileId(education.getProfile().getId());
        return dto;
    }
}