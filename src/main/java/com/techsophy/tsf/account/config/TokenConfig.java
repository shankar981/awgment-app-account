package com.techsophy.tsf.account.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Objects;
import static com.techsophy.tsf.account.constants.AccountConstants.AUTHORIZATION;

@Component
public class TokenConfig
{
    private TokenConfig()
    {

    }
    public static String getBearerTokenHeader()
    {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getHeader(AUTHORIZATION);
    }
}
