package com.techsophy.tsf.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.account.config.CustomFilter;
import com.techsophy.tsf.account.config.GlobalMessageSource;
import com.techsophy.tsf.account.dto.UserPreferencesSchema;
import com.techsophy.tsf.account.service.UserPreferencesThemeService;
import com.techsophy.tsf.account.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
import static com.techsophy.tsf.account.constants.ThemesConstants.ID;
import static com.techsophy.tsf.account.constants.ThemesConstants.TEST_ACTIVE_PROFILE;
import static com.techsophy.tsf.account.constants.UserPreferencesConstants.*;
import static com.techsophy.tsf.account.constants.UserPreferencesConstants.FILE;
import static com.techsophy.tsf.account.constants.UserPreferencesConstants.TOKEN;
import static com.techsophy.tsf.account.constants.UserPreferencesConstants.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(TEST_ACTIVE_PROFILE)
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc(addFilters = false)
class UserPreferencesControllerTest {
    private static final SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor jwtSaveOrUpdate = jwt().authorities(new SimpleGrantedAuthority(AWGMENT_ACCOUNT_CREATE_OR_UPDATE));
    private static final SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor jwtRead = jwt().authorities(new SimpleGrantedAuthority(AWGMENT_ACCOUNT_READ));
    private static final SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor jwtDelete = jwt().authorities(new SimpleGrantedAuthority(AWGMENT_ACCOUNT_DELETE));

    @MockBean
    UserPreferencesThemeService mockUserPreferencesThemeService;
    @Mock
    GlobalMessageSource mockGlobalMessageSource;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    TokenUtils mockTokenUtils;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    CustomFilter customFilter;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(customFilter)
                .apply(springSecurity())
                .build();
    }

    @Test
    void saveUserPreferencesThemeTest() throws Exception {
        ObjectMapper objectMapperTest = new ObjectMapper();
        UserPreferencesSchema userPreferencesSchema = new UserPreferencesSchema(ID, USER_ID, THEMEID, IMAGE);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.multipart(BASE_URL + VERSION_V1 + USER_PREFERENCES_URL)
                .with(jwtSaveOrUpdate).content(objectMapperTest.writeValueAsString(userPreferencesSchema)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void uploadProfilePictureTest() throws Exception {
        byte[] mockProfilePicture = new byte[]{123};
        MockMultipartFile file = new MockMultipartFile(FILE, IMAGE_PNG, MediaType.IMAGE_PNG_VALUE, PICTURE_CONTENT.getBytes());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.multipart(BASE_URL + VERSION_V1 + USER_PREFERENCES_URL + PROFILE_PICTURE_URL)
                .file(file)
                .with(jwtSaveOrUpdate).content(mockProfilePicture);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void getUserPreferencesThemeTest() throws Exception {
        ObjectMapper objectMapperTest = new ObjectMapper();
        UserPreferencesSchema userPreferencesSchema = new UserPreferencesSchema(ID, USER_ID, THEMEID, IMAGE);
        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.get(BASE_URL + VERSION_V1 + USER_PREFERENCES_URL)
                .with(jwtRead).content(objectMapperTest.writeValueAsString(userPreferencesSchema)).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = this.mockMvc.perform(requestBuilderTest).andExpect(status().isOk()).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void deleteProfilePictureTest() throws Exception {
        Mockito.when(mockTokenUtils.getIssuerFromToken(TOKEN)).thenReturn(TENANT);
        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.delete(BASE_URL + VERSION_V1 + USER_PREFERENCES_URL + PROFILE_PICTURE_URL)
                .with(jwtDelete);
        this.mockMvc.perform(requestBuilderTest).andExpect(status().isOk());
    }

    @Test
    void deleteUserPreferencesThemeDataByUserIdTest() throws Exception {
        Mockito.when(mockTokenUtils.getIssuerFromToken(TOKEN)).thenReturn(TENANT);
        RequestBuilder requestBuilderTest = MockMvcRequestBuilders.delete(BASE_URL + VERSION_V1 + USER_PREFERENCES_URL)
                .with(jwtDelete);
        this.mockMvc.perform(requestBuilderTest).andExpect(status().isOk());
    }
}
