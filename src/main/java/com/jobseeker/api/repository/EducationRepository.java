package com.jobseeker.api.repository;

import com.jobseeker.api.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByProfileId(Long profileId);
    Optional<Education> findByIdAndProfileId(Long id, Long profileId);
}