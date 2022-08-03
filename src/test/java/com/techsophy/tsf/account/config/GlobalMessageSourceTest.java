package com.techsophy.tsf.account.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;
import java.util.Locale;

import static com.techsophy.tsf.account.constants.ThemesConstants.TEST_ACTIVE_PROFILE;
import static com.techsophy.tsf.account.constants.UserConstants.ARGS;
import static com.techsophy.tsf.account.constants.UserConstants.KEY;
import static com.techsophy.tsf.account.constants.UserPreferencesConstants.USER_ID;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.beans.MethodInvocationException.ERROR_CODE;

@ActiveProfiles(TEST_ACTIVE_PROFILE)
@SpringBootTest
class GlobalMessageSourceTest {
    @Mock
    MessageSource mockMessageSource;

    @InjectMocks
    GlobalMessageSource mockGlobalMessageSource;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTestSingleArgument() {
        Mockito.when(mockMessageSource.getMessage(any(), any(), any())).thenReturn(KEY);
        String responseTest = mockGlobalMessageSource.get(KEY);
        Assertions.assertNotNull(responseTest);
    }

    @Test
    void getTestDoubleArguments() {
        Mockito.when(mockMessageSource.getMessage(any(), any(), any())).thenReturn(KEY);
        String responseTest = mockGlobalMessageSource.get(KEY, ARGS);
        Assertions.assertNotNull(responseTest);
    }

    @Test
    void getTestDoubleArgumentsLocale() {
        String s[] = {"abc", "def"};
        Mockito.when(mockMessageSource.getMessage(any(), any(), any())).thenReturn(KEY);
        String responseTest = mockGlobalMessageSource.get(ERROR_CODE, s, Locale.ENGLISH);
        String response = mockGlobalMessageSource.get(ERROR_CODE, null, Locale.ENGLISH);
        String responseTest1 = mockGlobalMessageSource.get("abc","abc");
        String responseTest2 = mockGlobalMessageSource.get("abc", BigInteger.ONE);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(responseTest);
        Assertions.assertNotNull(responseTest1);
        Assertions.assertNotNull(responseTest2);
    }
}