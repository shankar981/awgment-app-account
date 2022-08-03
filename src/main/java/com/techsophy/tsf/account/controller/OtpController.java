package com.techsophy.tsf.account.controller;

import com.techsophy.tsf.account.dto.OtpRequestPayload;
import com.techsophy.tsf.account.dto.OtpVerifyPayload;
import com.techsophy.tsf.account.model.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import static com.techsophy.tsf.account.constants.AccountConstants.*;

@RequestMapping(ACCOUNTS_URL + VERSION_V1 + OTP_URL)
public interface OtpController
{
    @PostMapping(GENERATE)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<Void> generateOtp(@RequestBody OtpRequestPayload otpRequestPayload) throws IOException;

    @PostMapping(VALIDATE)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<Void> verifyOtp(@RequestBody OtpVerifyPayload otpVerifyPayload) throws IOException;
}
