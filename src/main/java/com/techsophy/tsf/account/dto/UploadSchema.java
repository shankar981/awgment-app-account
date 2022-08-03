package com.techsophy.tsf.account.dto;

import lombok.Value;
import lombok.With;

@With
@Value
public class UploadSchema
{
    String id;
    String name;
    Object content;
}
