package com.techsophy.tsf.account.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Setter
@Getter
public class AuditableData
{
    private String createdById;
    private String createdByName;
    private Instant createdOn;
    private String updatedById;
    private String updatedByName;
    private Instant updatedOn;
}
