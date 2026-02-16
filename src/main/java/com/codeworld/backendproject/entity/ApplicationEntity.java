package com.codeworld.backendproject.entity;

import com.codeworld.backendproject.entity.enums.*;
import jakarta.persistence.*;
import com.codeworld.backendproject.entity.enums.ApplicationStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "applications",
        uniqueConstraints = @UniqueConstraint(columnNames =
                {"program_id", "email", "phone_number"}))
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String applicationCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String field;

    @Column(nullable = false)
    private String surname;

    @Column(name = "phone_number", nullable = false)
    private String phone;


    @Column(nullable = false)
    private String email;

   @Column(nullable = false, length = 2000)
    private String motivation;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
    private EducationTypeEnum educationType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)

    private ApplicationStatusEnum status= ApplicationStatusEnum.PENDING;

    @Column(length = 2000)
    private String adminNote;

    private LocalDateTime createdAtTime=LocalDateTime.now();

    private String cvPath;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private ProgramEntity program;

}
