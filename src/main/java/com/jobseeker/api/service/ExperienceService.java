package com.jobseeker.api.service;

import com.jobseeker.api.dto.ExperienceDto;
import com.jobseeker.api.exception.ResourceNotFoundException;
import com.jobseeker.api.model.Experience;
import com.jobseeker.api.model.Profile;
import com.jobseeker.api.repository.ExperienceRepository;
import com.jobseeker.api.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceService {
    
    private final ExperienceRepository experienceRepository;
    private final ProfileRepository profileRepository;

    public List<ExperienceDto> getAllExperiences(Long profileId) {
        List<Experience> experiences = experienceRepository.findByProfileId(profileId);
        return experiences.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ExperienceDto getExperienceById(Long profileId, Long experienceId) {
        Experience experience = experienceRepository.findByIdAndProfileId(experienceId, profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found with id: " + experienceId));
        
        return convertToDto(experience);
    }

    public ExperienceDto createExperience(ExperienceDto experienceDto) {
        Profile profile = profileRepository.findById(experienceDto.getProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + experienceDto.getProfileId()));
        
        Experience experience = new Experience();
        BeanUtils.copyProperties(experienceDto, experience);
        experience.setProfile(profile);
        
        Experience savedExperience = experienceRepository.save(experience);
        return convertToDto(savedExperience);
    }

    public ExperienceDto updateExperience(Long experienceId, ExperienceDto experienceDto) {
        Experience existingExperience = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found with id: " + experienceId));
        
        // Ensure the experience belongs to the correct profile
        if (!existingExperience.getProfile().getId().equals(experienceDto.getProfileId())) {
            throw new ResourceNotFoundException("Experience does not belong to the specified profile");
        }
        
        BeanUtils.copyProperties(experienceDto, existingExperience, "id", "profile", "createdAt");
        
        Experience updatedExperience = experienceRepository.save(existingExperience);
        return convertToDto(updatedExperience);
    }

    public void deleteExperience(Long profileId, Long experienceId) {
        Experience experience = experienceRepository.findByIdAndProfileId(experienceId, profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found with id: " + experienceId));
        
        experienceRepository.delete(experience);
    }
    
    private ExperienceDto convertToDto(Experience experience) {
        ExperienceDto dto = new ExperienceDto();
        BeanUtils.copyProperties(experience, dto);
        dto.setProfileId(experience.getProfile().getId());
        return dto;
    }
}