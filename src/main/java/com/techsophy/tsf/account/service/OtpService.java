package com.techsophy.tsf.account.service;

import com.techsophy.tsf.account.dto.OtpRequestPayload;
import com.techsophy.tsf.account.dto.OtpVerifyPayload;

public interface OtpService
{
    void generateOtp(OtpRequestPayload otpRequestPayload);

    Boolean verifyOtp(OtpVerifyPayload otpVerifyPayload);
}
