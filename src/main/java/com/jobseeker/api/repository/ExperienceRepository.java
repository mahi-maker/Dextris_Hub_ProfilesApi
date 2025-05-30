package com.jobseeker.api.repository;

import com.jobseeker.api.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByProfileId(Long profileId);
    Optional<Experience> findByIdAndProfileId(Long id, Long profileId);
}