package com.sqli.intern.api.validator.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "project")
public class ProjectEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
