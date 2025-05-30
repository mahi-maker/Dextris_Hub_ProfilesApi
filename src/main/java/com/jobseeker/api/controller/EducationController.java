package com.jobseeker.api.controller;

import com.jobseeker.api.dto.EducationDto;
import com.jobseeker.api.service.EducationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile/education")
@RequiredArgsConstructor
@Tag(name = "Education", description = "Education management APIs")
public class EducationController {
    
    private final EducationService educationService;

    @GetMapping
    @Operation(summary = "Get all education entries for a profile")
    public ResponseEntity<List<EducationDto>> getAllEducations(@RequestParam Long profileId) {
        return ResponseEntity.ok(educationService.getAllEducations(profileId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get education by ID")
    public ResponseEntity<EducationDto> getEducationById(
            @RequestParam Long profileId,
            @PathVariable Long id) {
        return ResponseEntity.ok(educationService.getEducationById(profileId, id));
    }

    @PostMapping
    @Operation(summary = "Add new education")
    public ResponseEntity<EducationDto> createEducation(@Valid @RequestBody EducationDto educationDto) {
        return new ResponseEntity<>(educationService.createEducation(educationDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update education")
    public ResponseEntity<EducationDto> updateEducation(
            @PathVariable Long id,
            @Valid @RequestBody EducationDto educationDto) {
        return ResponseEntity.ok(educationService.updateEducation(id, educationDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete education")
    public ResponseEntity<Void> deleteEducation(@RequestParam Long profileId, @PathVariable Long id) {
        educationService.deleteEducation(profileId, id);
        return ResponseEntity.noContent().build();
    }
}