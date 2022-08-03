package com.techsophy.tsf.account.controller;

import com.techsophy.tsf.account.config.CustomFilter;
import com.techsophy.tsf.account.config.GlobalMessageSource;
import com.techsophy.tsf.account.exception.EntityIdNotFoundException;
import com.techsophy.tsf.account.exception.GlobalExceptionHandler;
import com.techsophy.tsf.account.exception.ProfilePictureNotPresentException;
import com.techsophy.tsf.account.exception.UserPreferencesNotFoundByLoggedInUserIdException;
import com.techsophy.tsf.account.service.UserPreferencesThemeService;
import com.techsophy.tsf.account.utils.TokenUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static com.techsophy.tsf.account.constants.AccountConstants.*;
import static com.techsophy.tsf.account.constants.ErrorConstants.PROFILE_PICTURE_CANNOT_BE_EMPTY;
import static com.techsophy.tsf.account.constants.ThemesConstants.TEST_ACTIVE_PROFILE;
import static com.techsophy.tsf.account.constants.UserPreferencesConstants.FILE;
import static com.techsophy.tsf.account.constants.UserPreferencesConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(TEST_ACTIVE_PROFILE)
@ExtendWith({MockitoExtension.class})
@AutoConfigureMockMvc(addFilters = false)
class UserPreferenceControllerExceptionTest
{
    private static  final SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor jwtSaveOrUpdate = jwt().authorities(new SimpleGrantedAuthority(AWGMENT_ACCOUNT_CREATE_OR_UPDATE));
    private static  final SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor jwtRead = jwt().authorities(new SimpleGrantedAuthority(AWGMENT_ACCOUNT_READ));
    private static  final SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor jwtDelete = jwt().authorities(new SimpleGrantedAuthority(AWGMENT_ACCOUNT_DELETE));

    @MockBean
    TokenUtils mockTokenUtils;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    CustomFilter customFilter;
    @Mock
    UserPreferencesController mockUserPreferencesController;
    @Mock
    GlobalMessageSource mockGlobalMessageSource;
    @MockBean
    UserPreferencesThemeService mockUserPreferencesThemeService;

    @BeforeEach
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(mockUserPreferencesController)
                .setControllerAdvice(new GlobalExceptionHandler(mockGlobalMessageSource))
                .build();
    }

    @Test
    void getUserPreferenceThemeEntityNotFoundByIdExceptionTest() throws Exception
    {
        Mockito.when(mockTokenUtils.getIssuerFromToken(TOKEN)).thenReturn(TENANT);
        Mockito.when(mockUserPreferencesController.getUserPreferencesThemesDataByUserId()).thenThrow(new EntityIdNotFoundException(USER_NOT_FOUND_WITH_GIVEN_ID,USER_NOT_FOUND_WITH_GIVEN_ID));
        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.get(BASE_URL + VERSION_V1 + USER_PREFERENCES_URL)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isInternalServerError())
                .andExpect(result -> assertEquals(500,result.getResponse().getStatus()))
                .andReturn();
    }

    @Test
    void deleteUserProfilePicturePreferencesNotFoundExceptionTest() throws Exception
    {
        Mockito.when(mockTokenUtils.getIssuerFromToken(TOKEN)).thenReturn(TENANT);
        Mockito.when(mockUserPreferencesController.deleteProfilePhotoByUserId()).thenThrow(new UserPreferencesNotFoundByLoggedInUserIdException(USER_PREFERENCE_SCHEMA_NOT_FOUND,USER_PREFERENCE_SCHEMA_NOT_FOUND));
        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.delete(BASE_URL + VERSION_V1 + USER_PREFERENCES_URL+PROFILE_PICTURE_URL)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isInternalServerError()).andReturn();
        assertEquals(500,mvcResult.getResponse().getStatus());
    }

    @Test
    void uploadProfilePictureNotPresentExceptionTest() throws Exception
    {
        byte[] mockProfilePicture=new byte[]{123};
        MockMultipartFile file=new MockMultipartFile(FILE,IMAGE_PNG,MediaType.IMAGE_PNG_VALUE,PICTURE_CONTENT.getBytes());
        Mockito.when(mockTokenUtils.getIssuerFromToken(TOKEN)).thenReturn(TENANT);
        Mockito.when(mockUserPreferencesController.uploadProfilePictureByUserId(file)).thenThrow(new ProfilePictureNotPresentException(PROFILE_PICTURE_CANNOT_BE_EMPTY,PROFILE_PICTURE_CANNOT_BE_EMPTY));
        RequestBuilder requestBuilderTest=MockMvcRequestBuilders
                .multipart(BASE_URL+VERSION_V1+USER_PREFERENCES_URL+PROFILE_PICTURE_URL).file(file)
                .content(mockProfilePicture);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isInternalServerError()).andReturn();
        assertEquals(500,mvcResult.getResponse().getStatus());
    }
}
