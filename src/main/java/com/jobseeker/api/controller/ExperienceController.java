package com.jobseeker.api.controller;

import com.jobseeker.api.dto.ExperienceDto;
import com.jobseeker.api.service.ExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile/experience")
@RequiredArgsConstructor
@Tag(name = "Experience", description = "Experience management APIs")
public class ExperienceController {
    
    private final ExperienceService experienceService;

    @GetMapping
    @Operation(summary = "Get all experiences for a profile")
    public ResponseEntity<List<ExperienceDto>> getAllExperiences(@RequestParam Long profileId) {
        return ResponseEntity.ok(experienceService.getAllExperiences(profileId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get experience by ID")
    public ResponseEntity<ExperienceDto> getExperienceById(
            @RequestParam Long profileId,
            @PathVariable Long id) {
        return ResponseEntity.ok(experienceService.getExperienceById(profileId, id));
    }

    @PostMapping
    @Operation(summary = "Add new experience")
    public ResponseEntity<ExperienceDto> createExperience(@Valid @RequestBody ExperienceDto experienceDto) {
        return new ResponseEntity<>(experienceService.createExperience(experienceDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update experience")
    public ResponseEntity<ExperienceDto> updateExperience(
            @PathVariable Long id,
            @Valid @RequestBody ExperienceDto experienceDto) {
        return ResponseEntity.ok(experienceService.updateExperience(id, experienceDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete experience")
    public ResponseEntity<Void> deleteExperience(@RequestParam Long profileId, @PathVariable Long id) {
        experienceService.deleteExperience(profileId, id);
        return ResponseEntity.noContent().build();
    }
}