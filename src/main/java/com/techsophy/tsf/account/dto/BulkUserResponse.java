package com.techsophy.tsf.account.dto;

import com.techsophy.tsf.account.entity.Auditable;
import lombok.Value;
import lombok.With;

import java.io.Serializable;
import java.util.Map;

@With
@Value
public class BulkUserResponse extends Auditable implements Serializable
{
    String id;
    Map<String,Object> userData;
    String documentId;
    String status;
}
