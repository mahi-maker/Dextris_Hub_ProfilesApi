package com.jobseeker.api.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeUploadResponseDto {
    private String message;
    private String fileUrl;
    private Long profileId;
}