package com.techsophy.tsf.account.dto;

import lombok.Value;
import lombok.With;

@With
@Value
public class OtpResponsePayload
{
     String mobileOtp;
     String emailOtp;
}
