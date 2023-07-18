package com.sqli.intern.api.validator.repositories;

import com.sqli.intern.api.validator.entities.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
    Optional<OperationEntity> findByType(String type);
}
