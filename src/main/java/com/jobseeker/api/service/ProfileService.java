package com.jobseeker.api.service;

import com.jobseeker.api.dto.ProfileDto;
import com.jobseeker.api.dto.ResumeUploadResponseDto;
import com.jobseeker.api.exception.ResourceNotFoundException;
import com.jobseeker.api.model.Profile;
import com.jobseeker.api.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {
    
    private final ProfileRepository profileRepository;
    private final String UPLOAD_DIR = "uploads/resumes/";

    public ProfileDto getProfile(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + id));
        
        return convertToDto(profile);
    }

    public ProfileDto createProfile(ProfileDto profileDto) {
        Profile profile = new Profile();
        BeanUtils.copyProperties(profileDto, profile);
        
        Profile savedProfile = profileRepository.save(profile);
        return convertToDto(savedProfile);
    }

    public ProfileDto updateProfile(Long id, ProfileDto profileDto) {
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + id));
        
        BeanUtils.copyProperties(profileDto, existingProfile, "id", "experiences", "educations", "createdAt");
        
        Profile updatedProfile = profileRepository.save(existingProfile);
        return convertToDto(updatedProfile);
    }

    public ResumeUploadResponseDto uploadResume(Long profileId, MultipartFile file) throws IOException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id: " + profileId));
        
        // Create uploads directory if it doesn't exist
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // Generate unique filename
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension;
        
        // Save file
        Path filePath = uploadPath.resolve(newFileName);
        Files.copy(file.getInputStream(), filePath);
        
        // Update profile with resume URL
        String fileUrl = "/resume/" + newFileName;
        profile.setResumeUrl(fileUrl);
        profileRepository.save(profile);
        
        return ResumeUploadResponseDto.builder()
                .message("Resume uploaded successfully")
                .fileUrl(fileUrl)
                .profileId(profileId)
                .build();
    }
    
    private ProfileDto convertToDto(Profile profile) {
        ProfileDto dto = new ProfileDto();
        BeanUtils.copyProperties(profile, dto);
        return dto;
    }
}