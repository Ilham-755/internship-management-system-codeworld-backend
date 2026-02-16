package com.codeworld.backendproject.repository;

import com.codeworld.backendproject.entity.ApplicationEntity;
import com.codeworld.backendproject.entity.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
    long countByProgramId(Long programId);
    boolean existsByProgramAndEmailAndPhone(ProgramEntity program, String email, String phone);
    Optional<ApplicationEntity> findByApplicationCode(String applicationCode);
}
