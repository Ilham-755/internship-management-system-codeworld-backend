package com.codeworld.backendproject.entity;

import com.codeworld.backendproject.entity.enums.ProgramTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name= "programs")
public class ProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProgramTypeEnum type;

    @Column(nullable = false)
    private Integer programNumber;

    @Column(nullable = false)
    private Integer startMonth;

    @Column(nullable = false)
    private Integer startYear;

    private LocalDate endDate;

    @Column(nullable = false)
    private Boolean active= false;
}
