package com.sqli.intern.api.validator.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "project")
public class ProjectEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
