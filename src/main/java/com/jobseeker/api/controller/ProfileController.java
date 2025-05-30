package com.jobseeker.api.controller;

import com.jobseeker.api.dto.ProfileDto;
import com.jobseeker.api.dto.ResumeUploadResponseDto;
import com.jobseeker.api.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
@Tag(name = "Profile", description = "Profile management APIs")
public class ProfileController {
    
    private final ProfileService profileService;

    @GetMapping
    @Operation(summary = "Get user profile")
    public ResponseEntity<ProfileDto> getProfile(@RequestParam Long id) {
        return ResponseEntity.ok(profileService.getProfile(id));
    }

    @PostMapping
    @Operation(summary = "Create a new profile")
    public ResponseEntity<ProfileDto> createProfile(@Valid @RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.createProfile(profileDto), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update user profile")
    public ResponseEntity<ProfileDto> updateProfile(@RequestParam Long id, @Valid @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.updateProfile(id, profileDto));
    }

    @PostMapping(path = "/resume", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload resume")
    public ResponseEntity<ResumeUploadResponseDto> uploadResume(
            @RequestParam Long profileId,
            @RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(profileService.uploadResume(profileId, file), HttpStatus.CREATED);
    }
}