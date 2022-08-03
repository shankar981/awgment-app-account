package com.techsophy.tsf.account.dto;

import lombok.*;
import java.util.Map;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class BulkUploadSchema
{
    String id;
    Map<String,Object> userData;
    String documentId;
    String status;
}
