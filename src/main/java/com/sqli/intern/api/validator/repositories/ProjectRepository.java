package com.sqli.intern.api.validator.repositories;

import com.sqli.intern.api.validator.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    Optional<ProjectEntity> findByName(String name);
    List<ProjectEntity> findAllByDeletedFalse();
    Optional<ProjectEntity> findByIdAndDeletedFalse(Long id);

}
