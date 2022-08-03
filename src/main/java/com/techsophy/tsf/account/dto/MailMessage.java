package com.techsophy.tsf.account.dto;

import lombok.Value;
import lombok.With;
import java.util.Map;

@With
@Value
public class MailMessage
{
    String templateId;
    Map<String,String> data;
    String to;
    String from;
    String cc;
    String subject;
    String body;
}
