package com.sqli.intern.api.validator.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="operation")
public class OperationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String type;
    private String body;
    private String expectedResponse;
    private String actualResponse;
    private String expectedType;

    @ManyToOne
    @JoinColumn(name="project_id")
    private ProjectEntity projectEntity;
}
