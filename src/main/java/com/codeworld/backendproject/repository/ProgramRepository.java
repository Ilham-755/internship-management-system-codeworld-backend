package com.codeworld.backendproject.repository;

import com.codeworld.backendproject.entity.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<ProgramEntity, Long> {
    Optional<ProgramEntity> findByActiveTrue();
//    long countByActiveAndTrue();

}
