package com.techsophy.tsf.account.model;

import lombok.Builder;
import lombok.Data;
/**
 * This class is to return an ID only. This can be used especially on create request. Create request will
 * generally return the newly create ID
 */
@Data
@Builder(toBuilder = true)
public class SimpleResponse
{
    private String id;
}
