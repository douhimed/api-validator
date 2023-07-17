package com.sqli.intern.api.validator.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProjectEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String name;

    private boolean deleted;

    @OneToMany(mappedBy = "projectEntity")
    private List<OperationEntity> operationEntities;

}
