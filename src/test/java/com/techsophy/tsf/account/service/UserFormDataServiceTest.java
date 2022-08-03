package com.techsophy.tsf.account.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.idgenerator.IdGeneratorImpl;
import com.techsophy.tsf.account.config.GlobalMessageSource;
import com.techsophy.tsf.account.dto.AuditableData;
import com.techsophy.tsf.account.dto.PaginationResponsePayload;
import com.techsophy.tsf.account.dto.UserFormDataSchema;
import com.techsophy.tsf.account.dto.UserData;
import com.techsophy.tsf.account.entity.Auditable;
import com.techsophy.tsf.account.entity.UserDefinition;
import com.techsophy.tsf.account.entity.UserFormDataDefinition;
import com.techsophy.tsf.account.repository.UserFormDataDefinitionRepository;
import com.techsophy.tsf.account.service.impl.UserFormDataServiceImpl;
import com.techsophy.tsf.account.service.impl.UserServiceImpl;
import com.techsophy.tsf.account.utils.TokenUtils;
import com.techsophy.tsf.account.utils.WebClientWrapper;
import lombok.Cleanup;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.time.Instant;
import java.util.*;

import static com.techsophy.tsf.account.constants.UserConstants.USER_STRING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@EnableWebMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserFormDataServiceTest
{
    @Mock
    UserFormDataDefinitionRepository mockUserFormDataDefinitionRepository;
    @Mock
    UserServiceImpl mockUserServiceImpl;
    @Mock
    GlobalMessageSource globalMessageSource;
    @Mock
    IdGeneratorImpl mockIdGenerator;
    @Mock
    TokenUtils accountUtils;
    @Mock
    ObjectMapper mockObjectMapper;
    @Mock
    TokenUtils mockUtilityService;
    @InjectMocks
    UserFormDataServiceImpl userFormDataService;
    @Mock
    AuditableData auditableData;
    @Mock
    WebClientWrapper webClientWrapper;

    UserDefinition userDefinition=new UserDefinition();

//    @BeforeAll
//    static void setUp()
//    {
//        MockitoAnnotations.openMocks(UserServiceImpl.class);
//    }
List<Map<String,Object>> list = new ArrayList<>();
    Map<String,Object> map = new HashMap<>();
    @BeforeEach
    public void init()
    {
         list = new ArrayList<>();
         map = new HashMap<>();
        map.put("abc","abc");
        map.put("id",1);
        map.put("userId",1);
        list.add(map);
        userDefinition.setFirstName(USER_STRING);
        userDefinition.setCreatedById(BigInteger.valueOf(234234234));
        userDefinition.setId(BigInteger.valueOf(345345));

    }
    private static final String USER_DATA = "testdata/user-data.json";

    @Test
    void saveUserTest() throws IOException
    {
        ObjectMapper objectMapper=new ObjectMapper();
        InputStream stream = new ClassPathResource(USER_DATA).getInputStream();
        String userData= new String(stream.readAllBytes());
        UserFormDataSchema userFormDataSchema= objectMapper.readValue(userData,UserFormDataSchema.class);
        //UserFormDataDefinition userFormDataDefinition= objectMapper.readValue(userData,UserFormDataDefinition.class);
       UserFormDataSchema userFormDataSchema1 = new UserFormDataSchema(map,"1","1");
        UserFormDataDefinition userFormDataDefinition = new UserFormDataDefinition(BigInteger.valueOf(1),map,BigInteger.valueOf(1),1);
        UserFormDataDefinition userFormDataDefinition1 = new UserFormDataDefinition(null,map,null,1);
        UserData userSchema=objectMapper.convertValue(userFormDataSchema.getUserData(), UserData.class);
        UserDefinition userDefinition=new UserDefinition();
        userDefinition.setFirstName("user");
        userDefinition.setCreatedById(BigInteger.valueOf(234234234));
        userDefinition.setId(BigInteger.valueOf(345345));
        when(mockUserServiceImpl.getCurrentlyLoggedInUserId()).thenReturn(list);
        when(mockUserServiceImpl.saveUser(any())).thenReturn(userDefinition.withId(BigInteger.valueOf(1234)));
        when(mockUserServiceImpl.getUserByLoginId(any())).thenReturn(userSchema);
        //when(mockUserServiceImpl.setCreatedAndUpdatedUserName(any())).thenReturn(userFormDataSchema);
        when(mockObjectMapper.convertValue(any(),eq(UserFormDataDefinition.class))).thenReturn(userFormDataDefinition).thenReturn(userFormDataDefinition1);
        when(mockObjectMapper.convertValue(any(),eq(UserFormDataSchema.class))).thenReturn(userFormDataSchema1);
        when(mockObjectMapper.convertValue(any(),eq(UserData.class))).thenReturn(userSchema);
        when(mockObjectMapper.convertValue(userFormDataDefinition,UserFormDataSchema.class)).thenReturn(userFormDataSchema1);
        when(mockUserFormDataDefinitionRepository.save(any())).thenReturn(userFormDataDefinition);
        when(mockUserFormDataDefinitionRepository.findByUserId(BigInteger.valueOf(1))).thenReturn(Optional.of(userFormDataDefinition));
        UserFormDataSchema response=userFormDataService.saveUserFormData(userFormDataSchema);
        userFormDataService.saveUserFormData(userFormDataSchema1);
        verify(mockUserFormDataDefinitionRepository,times(2)).save(any());
    }

    @Test
    void getFormByUserId() throws IOException
    {
        ObjectMapper objectMapper=new ObjectMapper();
        @Cleanup InputStream stream = new ClassPathResource(USER_DATA).getInputStream();
        String userData= new String(stream.readAllBytes());
        UserFormDataSchema userFormDataSchema= objectMapper.readValue(userData,UserFormDataSchema.class);
        UserFormDataDefinition userFormDataDefinition= objectMapper.readValue(userData,UserFormDataDefinition.class);
        when(mockUserServiceImpl.getCurrentlyLoggedInUserId()).thenReturn(list);
        when(mockUserFormDataDefinitionRepository.findByUserId(any())).thenReturn(Optional.ofNullable(userFormDataDefinition));
        when(mockObjectMapper.convertValue(any(),eq(UserFormDataSchema.class))).thenReturn(userFormDataSchema);
        UserFormDataSchema response= (UserFormDataSchema) userFormDataService.getUserFormDataByUserId("1234",false);
        userFormDataService.getUserFormDataByUserId("1234",true);
        assertThat(response.getUserData()).isEqualTo(userFormDataSchema.getUserData());
    }
    @Test
    void getAllUserFormDataObjects()
    {
        UserData userSchema = new UserData("1","name","firstname","lastname","mobile","email","departmnt");
        UserFormDataDefinition userFormDataDefinition1 = new UserFormDataDefinition(null,map,null,1);
        when(mockUserServiceImpl.getAllUsers(anyString(), (Sort) any())).thenReturn(List.of(userSchema));
        when(mockUserFormDataDefinitionRepository.findAll((Sort) any())).thenReturn(List.of(userFormDataDefinition1));
        when(mockUserFormDataDefinitionRepository.findFormDataUserByQSort(anyString(),any())).thenReturn(List.of(userFormDataDefinition1));
        List  response =  userFormDataService.getAllUserFormDataObjects(false,"abc",Sort.by("abc"));
        userFormDataService.getAllUserFormDataObjects(false,"",Sort.by("abc"));
        userFormDataService.getAllUserFormDataObjects(true,"abc",Sort.by("abc"));
        assertThat(response).isNotNull();
    }
    @Test
    void getAllUserFormDataObjectsPagination()
    {
        UserData userSchema = new UserData("1","name","firstname","lastname","mobile","email","departmnt");
        UserFormDataDefinition userFormDataDefinition1 = new UserFormDataDefinition(null,map,null,1);
        Pageable pageable = PageRequest.of(1,1);
        Page page = new PageImpl(List.of(userFormDataDefinition1));
        PaginationResponsePayload paginationResponsePayload = new PaginationResponsePayload(list,1,1L,1,1,1);
        when(mockUserServiceImpl.getAllUsers(anyString(), (Sort) any())).thenReturn(List.of(userSchema));
        when(mockObjectMapper.convertValue(any(),eq(Map.class))).thenReturn(map);
        when(mockUserFormDataDefinitionRepository.findAll(pageable)).thenReturn(page);
        when(mockUserFormDataDefinitionRepository.findFormDataUserByQPageable(anyString(),any())).thenReturn(page);
        when(accountUtils.getPaginationResponsePayload(any(),any())).thenReturn(paginationResponsePayload);
        PaginationResponsePayload response =  userFormDataService.getAllUserFormDataObjects(false,"abc",pageable);
        userFormDataService.getAllUserFormDataObjects(false,"",pageable);
        userFormDataService.getAllUserFormDataObjects(true,"abc",pageable);
        verify(mockUserFormDataDefinitionRepository,times(1)).findAll(pageable);
    }
//    @Test
//    void getAllUsersByFilter()
//    {
//        UserData userSchema = new UserData("1","name","firstname","lastname","mobile","email","departmnt");
//        UserFormDataDefinition userFormDataDefinition1 = new UserFormDataDefinition(null,map,null,1);
//        when(mockUserFormDataDefinitionRepository.findByFilterColumnAndValue(any(),anyString(),anyString())).thenReturn(List.of(userFormDataDefinition1));
//        when(mockUserServiceImpl.getAllUsersByFilter("abc","abc")).thenReturn(List.of(userSchema));
//        when(mockObjectMapper.convertValue(any(),eq(Map.class))).thenReturn(map);
//        List response =userFormDataService.getAllUsersByFilter(true,"abc","abc",Sort.by("abc"),"abc");
//        userFormDataService.getAllUsersByFilter(false,"abc","abc",Sort.by("abc"),"");
//        assertThat(response).isNotNull();
//    }
    @Test
    void getAllUsersByFilterPagination()
    {
        UserData userSchema = new UserData("1","name","firstname","lastname","mobile","email","departmnt");
        UserFormDataDefinition userFormDataDefinition1 = new UserFormDataDefinition(null,map,null,1);
        Pageable pageable = PageRequest.of(1,1);
        Page page = new PageImpl(List.of(userFormDataDefinition1));
        PaginationResponsePayload paginationResponsePayload = new PaginationResponsePayload(list,1,1L,1,1,1);
        when(accountUtils.getPaginationResponsePayload(any(),any())).thenReturn(paginationResponsePayload);
        when(mockUserFormDataDefinitionRepository.findByFilterColumnAndValue(anyString(),anyString(),any(),anyString())).thenReturn(page);
        when(mockObjectMapper.convertValue(any(),eq(Map.class))).thenReturn(map);
        PaginationResponsePayload response = userFormDataService.getAllUsersByFilter(false,"abc","abc",pageable,"");
        userFormDataService.getAllUsersByFilter(true,"abc","abc",pageable,"q");
       verify(mockUserFormDataDefinitionRepository,times(2)).findByFilterColumnAndValue(anyString(),anyString(),any(),anyString());
    }
//    @Test
//    void bulkUploadUsersTest() throws Exception
//    {
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<Map<String,Object>> list = new ArrayList<>();
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("abc","abc");
//        map.put("id",1);
//        list.add(map);
//        String json = objectMapper.writeValueAsString(list);
//        WebClient webClient= WebClient.builder().build();
//        when(webClientWrapper.createWebClient(any())).thenReturn(webClient);
//        Mockito.when(webClientWrapper.webclientRequest(any(WebClient.class),anyString(),anyString(), ArgumentMatchers.eq(null))).thenReturn(json);
//        MockMultipartFile mockMultipartFile = new MockMultipartFile("content","file.csv", MediaType.TEXT_PLAIN_VALUE, objectMapper.writeValueAsString("123456").getBytes());
//        when(mockUserServiceImpl.getCurrentlyLoggedInUserId()).thenReturn(list);
//        userFormDataService.bulkUploadUsers(mockMultipartFile);
//    }

//    @Test
//    public void getAllForms() throws IOException
//    {
//        String [] list = new String[0];
//        Sort sort=mockUtilityService.getSortBy(list);
//        @Cleanup InputStream stream = new ClassPathResource(USER_DATA).getInputStream();
//        String userData= new String(stream.readAllBytes());
//        UserData userData1=new UserData();
//        userData1.setCreatedById("1");
//        userData1.setCreatedByName("name");
//        userData1.setCreatedOn(Instant.now());
//        userData1.setUpdatedById("1");
//        userData1.setUpdatedByName("name");
//        when(mockUserServiceImpl.getAllUsers("q", sort)).thenReturn(List.of(userData1));
//        List<UserData> response=userFormDataService.getAllUserFormDataObjects(true,sort);
//        assertThat(response.size()).isEqualTo(1);
//    }

//    @Test
//    public void getAllFormsOnlyMandatory() throws IOException
//    {
//        String [] list = new String[0];
//        Sort sort=mockUtilityService.getSortBy(list);
//        ObjectMapper objectMapper=new ObjectMapper();
//        @Cleanup InputStream stream = new ClassPathResource(USER_DATA).getInputStream();
//        String userData= new String(stream.readAllBytes());
//        UserFormDataSchema userFormDataSchema= objectMapper.readValue(userData,UserFormDataSchema.class);
//        UserFormDataDefinition userFormDataDefinition= objectMapper.readValue(userData,UserFormDataDefinition.class);
//        when(mockUserServiceImpl.getCurrentlyLoggedInUserId()).thenReturn(BigInteger.valueOf(Long.parseLong(UserConstants.userID)));
//        when(mockUserFormDataDefinitionRepository.findAll(sort)).thenReturn(Collections.singletonList(userFormDataDefinition));
//        when(mockUserServiceImpl.setCreatedAndUpdatedUserName(any())).thenReturn(userFormDataSchema);
//        when(mockObjectMapper.convertValue(any(),eq(UserFormDataSchema.class))).thenReturn(userFormDataSchema);
//        List<UserData> response=userFormDataService.getAllUserFormDataObjects(false,sort);
//        assertThat(response.size()).isEqualTo(1);
//    }

/*    @Test
    public void getAllFormsOnlyMandatoryPageble() throws IOException
    {
        UtilityService utilityService=new UtilityService();
        String [] list = new String[] {"createdOn"};
        Sort sort=utilityService.getSortBy(list);
        Pageable pageable= utilityService.getPageRequest(1, 1, list);
        PaginationResponsePayload paginationResponsePayload=new PaginationResponsePayload();
        ObjectMapper objectMapper=new ObjectMapper();
        @Cleanup InputStream stream = new ClassPathResource(USER_DATA).getInputStream();
        String userData= new String(stream.readAllBytes());
        UserFormDataSchema userFormDataSchema= objectMapper.readValue(userData,UserFormDataSchema.class);
        UserFormDataDefinition userFormDataDefinition= objectMapper.readValue(userData,UserFormDataDefinition.class);
        paginationResponsePayload.setContent(List.of(userFormDataSchema));
        when(mockUtilityService.getPaginationResponsePayload(any(),any())).thenReturn(paginationResponsePayload);
        when(mockUserServiceImpl.getCurrentlyLoggedInUserId()).thenReturn(BigInteger.valueOf(Long.parseLong(UserConstants.userID)));
        when(mockUserFormDataDefinitionRepository.findAll(pageable)).thenReturn( new PageImpl<>(List.of(userFormDataDefinition), pageable, 1));
        when(mockUserServiceImpl.setCreatedAndUpdatedUserName(any())).thenReturn(userFormDataSchema);
        when(mockObjectMapper.convertValue(any(),eq(UserFormDataSchema.class))).thenReturn(userFormDataSchema);
        PaginationResponsePayload response=userFormDataService.getAllUserFormDataObjects(false,  pageable);
        assertThat(response.getContent().size()).isEqualTo(1);
    }*/
/*
    @Test
    public void getAllFormsPageble() throws IOException
    {
        UtilityService utilityService=new UtilityService();
        String [] list = new String[] {"createdOn"};
        Sort sort=utilityService.getSortBy(list);
        Pageable pageable= utilityService.getPageRequest(1, 1, list);
        PaginationResponsePayload paginationResponsePayload=new PaginationResponsePayload();
        ObjectMapper objectMapper=new ObjectMapper();
        @Cleanup InputStream stream = new ClassPathResource(USER_DATA).getInputStream();
        String userData= new String(stream.readAllBytes());
        UserFormDataSchema userFormDataSchema= objectMapper.readValue(userData,UserFormDataSchema.class);
        UserFormDataDefinition userFormDataDefinition= objectMapper.readValue(userData,UserFormDataDefinition.class);
        paginationResponsePayload.setContent(List.of(userFormDataSchema));
        when(mockUserServiceImpl.getAllUsers("q", pageable)).thenReturn(paginationResponsePayload);
        when(mockUserServiceImpl.getCurrentlyLoggedInUserId()).thenReturn(BigInteger.valueOf(Long.parseLong(UserConstants.userID)));
        when(mockUserFormDataDefinitionRepository.findAll(pageable)).thenReturn( new PageImpl<>(List.of(userFormDataDefinition), pageable, 1));
        when(mockUserServiceImpl.setCreatedAndUpdatedUserName(any())).thenReturn(userFormDataSchema);
        when(mockObjectMapper.convertValue(any(),eq(UserFormDataSchema.class))).thenReturn(userFormDataSchema);
        PaginationResponsePayload response=userFormDataService.getAllUserFormDataObjects(true,  pageable);
        assertThat(response.getContent().size()).isEqualTo(1);
    }*/

    @Test
    void deleteFormByUserIdTest() throws IOException
    {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("abc","abc");
        map.put("id",1);
        list.add(map);
        ObjectMapper objectMapper=new ObjectMapper();
        @Cleanup InputStream stream = new ClassPathResource(USER_DATA).getInputStream();
        String userData= new String(stream.readAllBytes());
        UserFormDataSchema userFormDataSchema= objectMapper.readValue(userData,UserFormDataSchema.class);
        UserFormDataDefinition userFormDataDefinition= objectMapper.readValue(userData,UserFormDataDefinition.class);
        when(mockUserServiceImpl.getCurrentlyLoggedInUserId()).thenReturn(list);
        when(mockUserFormDataDefinitionRepository.findByUserId(any())).thenReturn(Optional.ofNullable(userFormDataDefinition));
        doNothing().when(mockUserServiceImpl).deleteUserById("123");
        //when(mockUserServiceImpl.setCreatedAndUpdatedUserName(any())).thenReturn(userFormDataSchema);
        when(mockObjectMapper.convertValue(any(),eq(UserFormDataSchema.class))).thenReturn(userFormDataSchema);
        userFormDataService.deleteUserFormDataByUserId("123");
        verify(mockUserServiceImpl, times(1)).deleteUserById("123");
    }

    @Test
    void getAllUsersByFilterListTest()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("id","userId");
        map.put("userId","userId");
        //AuditableData auditableData = new AuditableData("abc","abc", Instant.now(),"abc","abc",Instant.now());
        UserFormDataDefinition userFormDataDefinition = new UserFormDataDefinition(null,map,null,1);
        when(this.mockObjectMapper.convertValue(userFormDataDefinition,Map.class)).thenReturn(map);
        when(this.mockUserFormDataDefinitionRepository.findByFilterColumnAndValue(any(),any(),any())).thenReturn(List.of(userFormDataDefinition));
        when(mockUserServiceImpl.getAllUsersByFilter(any(),any())).thenReturn(List.of(auditableData));
        userFormDataService.getAllUsersByFilter(false,"abc","abc", (Sort) null,"");
        userFormDataService.getAllUsersByFilter(true,"abc","abc", (Sort) null,"q");
        verify(mockUserFormDataDefinitionRepository,times(1)).findByFilterColumnAndValue(any(),any(),any());
    }
}
