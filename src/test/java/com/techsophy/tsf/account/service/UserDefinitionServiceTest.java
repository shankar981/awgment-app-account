package com.techsophy.tsf.account.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.idgenerator.IdGeneratorImpl;
import com.techsophy.tsf.account.config.GlobalMessageSource;
import com.techsophy.tsf.account.dto.AuditableData;
import com.techsophy.tsf.account.dto.UserData;
import com.techsophy.tsf.account.entity.UserDefinition;
import com.techsophy.tsf.account.repository.UserDefinitionRepository;
import com.techsophy.tsf.account.service.impl.UserServiceImpl;
import com.techsophy.tsf.account.utils.TokenUtils;
import lombok.Cleanup;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.time.Instant;
import static com.techsophy.tsf.account.constants.ThemesConstants.TEST_ACTIVE_PROFILE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles(TEST_ACTIVE_PROFILE)
@ExtendWith({SpringExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDefinitionServiceTest
{
    @Mock
    UserDefinitionRepository mockUserDefinitionRepository;

    @Mock
    GlobalMessageSource globalMessageSource;

    @Mock
    IdGeneratorImpl mockIdGenerator;

    @Mock
    ObjectMapper mockObjectMapper;

    @Mock
    TokenUtils mockTokenUtils;

    @InjectMocks
    @Spy
    UserServiceImpl userService;
    private static final String USER_DATA = "testdata/user-schema.json";

//    @BeforeAll
//    static void setUp()
//    {
//        MockitoAnnotations.openMocks(UserServiceImpl.class);
//    }

    @Test
    void getFormByUserId() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        @Cleanup InputStream stream = new ClassPathResource(USER_DATA).getInputStream();
        String userData = new String(stream.readAllBytes());
        UserData userSchema = objectMapper.readValue(userData, UserData.class);
        UserDefinition userDefinition = objectMapper.readValue(userData, UserDefinition.class);
        userSchema.setCreatedById("1");
        userSchema.setCreatedByName("name");
        userSchema.setCreatedOn(Instant.now());
        userSchema.setUpdatedById("1");
        userSchema.setUpdatedByName("name");
        when(mockObjectMapper.convertValue(any(), eq(UserData.class))).thenReturn(userSchema);
        when(mockUserDefinitionRepository.findByEmailIdOrUserName("user", "user")).thenReturn(java.util.Optional.ofNullable(userDefinition));
        when(mockUserDefinitionRepository.findById((BigInteger) any())).thenReturn(java.util.Optional.ofNullable(userDefinition));
        AuditableData response = userService.getUserById("12345");
        assertThat(response).isEqualTo(userSchema);
    }

    @Test
    void deleteFormByUserIdTest()
    {
        when(mockUserDefinitionRepository.existsById(BigInteger.valueOf(123))).thenReturn(true);
        doNothing().when(mockUserDefinitionRepository).deleteById(BigInteger.valueOf(123));
        userService.deleteUserById("123");
        verify(mockUserDefinitionRepository, times(1)).deleteById(BigInteger.valueOf(123));
    }
}
