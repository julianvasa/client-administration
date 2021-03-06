package com.client.administration.audit;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Audit class defines the audit fields that need to be updated before a new record is created, an update or delete record
 *
 * @author julianvasa
 */
@Embeddable
@Getter
@Setter
public class Audit {

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
        createdBy = "test";
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
        updatedBy = "test";
    }

    @PreRemove
    public void preDelete() {
        updatedOn = LocalDateTime.now();
        updatedBy = "test";
    }
}
