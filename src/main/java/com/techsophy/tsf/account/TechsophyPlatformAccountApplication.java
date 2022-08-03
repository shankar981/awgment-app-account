package com.techsophy.tsf.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import static com.techsophy.tsf.account.constants.AccountConstants.CURRENT_PROJECT;
import static com.techsophy.tsf.account.constants.AccountConstants.MULTITENANCY_PROJECT;

@RefreshScope
@SpringBootApplication
@ComponentScan({CURRENT_PROJECT,MULTITENANCY_PROJECT})
public class TechsophyPlatformAccountApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(TechsophyPlatformAccountApplication.class, args);
    }
}
