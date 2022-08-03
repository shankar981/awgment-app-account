package com.techsophy.tsf.account.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.idgenerator.IdGeneratorImpl;
import com.techsophy.tsf.account.config.GlobalMessageSource;
import com.techsophy.tsf.account.constants.ThemesConstants;
import com.techsophy.tsf.account.dto.*;
import com.techsophy.tsf.account.entity.BulkUserDefinition;
import com.techsophy.tsf.account.exception.BulkUserNotFoundException;
import com.techsophy.tsf.account.repository.BulkUploadDefinintionRepository;
import com.techsophy.tsf.account.service.impl.BulkUserServiceImplementation;
import com.techsophy.tsf.account.service.impl.UserManagementInKeyCloakImpl;
import com.techsophy.tsf.account.service.impl.UserServiceImpl;
import com.techsophy.tsf.account.utils.TokenUtils;
import com.techsophy.tsf.account.utils.WebClientWrapper;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;

import static com.techsophy.tsf.account.constants.AccountConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@EnableWebMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BulkUserServiceTest
{
    @Mock
    UserServiceImpl userServiceImpl;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    GlobalMessageSource globalMessageSource;
    @Mock
    IdGeneratorImpl idGenerator;
    @Mock
    TokenUtils tokenUtils;
    @Mock
    TokenUtils accountUtils;
    @Mock
    WebClientWrapper webClientWrapper;
    @Mock
    BulkUploadDefinintionRepository bulkUploadDefinintionRepository;
    @Mock
    UserManagementInKeyCloakImpl userManagementInKeyCloak;
    @InjectMocks
    BulkUserServiceImplementation bulkUserServiceImplementation;
    List<Map<String,Object>> list = new ArrayList<>();
    Map<String,Object> map = new HashMap<>();

    @BeforeEach
    public void init()
    {
        list = new ArrayList<>();
        map = new HashMap<>();
        map.put("firstname","nandini");
        map.put("id",1);
        map.put("lastname","nandini");
        list.add(map);
    }

    @Test
    void bulkUploadUsers() throws Exception
    {
        when(idGenerator.nextId()).thenReturn(BigInteger.ONE);
        BulkUserDefinition bulkUserDefinition = new BulkUserDefinition(BigInteger.valueOf(1),map,BigInteger.valueOf(1),"abc");
        File file = new File("src/test/resources/testdata/accounts_template.csv");
        FileInputStream input = new FileInputStream(file);
        List<String> s = new ArrayList<>();
        s.add("abc");
        s.add("bcd");
        GroupsSaveSchema schema1 = new GroupsSaveSchema("WorkingGroup101","abc");
        RolesSchema rolesSchema = new RolesSchema("abc","abc");
        LinkedHashMap<String,Object> userDetails = new LinkedHashMap<>();
        userDetails.put(GROUPS,s);
        Mockito.when(userServiceImpl.getCurrentlyLoggedInUserId()).thenReturn(list);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
        PaginationResponsePayload paginationResponsePayload = new PaginationResponsePayload(list,1,1L,1,1,1);
        Mockito.when(accountUtils.getPaginationResponsePayload(any(),any())).thenReturn(paginationResponsePayload);
        when(this.objectMapper.convertValue(any(),ArgumentMatchers.eq(String.class))).thenReturn("abc,abc");
        Mockito.when(bulkUploadDefinintionRepository.save(any())).thenReturn(bulkUserDefinition);
        Mockito.when(userManagementInKeyCloak.getAllGroups()).thenReturn(Stream.of(schema1)).thenReturn(Stream.of(schema1)).thenReturn(Stream.of(schema1));
        Mockito.when(userManagementInKeyCloak.getAllRoles()).thenReturn(List.of(rolesSchema));
        Mockito.when(bulkUploadDefinintionRepository.findById(BigInteger.valueOf(1))).thenReturn(bulkUserDefinition);
        bulkUserServiceImplementation.bulkUploadUsers(multipartFile);
        Mockito.verify(bulkUploadDefinintionRepository,Mockito.times(1)).findById(BigInteger.valueOf(1));
    }

    @Test
    void bulkUpdateStatus() throws Exception
    {
        Mockito.when(userServiceImpl.getCurrentlyLoggedInUserId()).thenReturn(list);
        BulkUserDefinition bulkUserDefinition = new BulkUserDefinition(BigInteger.valueOf(1),map,BigInteger.valueOf(1),"status");
        BulkUploadSchema bulkUploadSchema = new BulkUploadSchema("1",map,"1","status");
        Mockito.when(bulkUploadDefinintionRepository.findById(BigInteger.valueOf(1))).thenReturn(bulkUserDefinition);
        Mockito.when(bulkUploadDefinintionRepository.save(any())).thenReturn(bulkUserDefinition.withId(BigInteger.valueOf(Long.parseLong(ThemesConstants.ID))));
        Mockito.when(bulkUploadDefinintionRepository.existsById(BigInteger.valueOf(1))).thenReturn(true);
        BulkUploadResponse bulkUploadResponse= bulkUserServiceImplementation.bulkUpdateStatus(bulkUploadSchema);
        Assertions.assertNotNull(bulkUploadResponse);
    }

    @Test
    void getAllBulkUsersFilterTest()
    {
        bulkUserServiceImplementation.getAllBulkUsers(DOCUMENT_ID,"101",CREATED_ON,"asc");
    }

    @Test
    void getAllBulkUsersPagination()
    {
        Pageable pageable = PageRequest.of(1,1);
        BulkUserDefinition bulkUserDefinition = new BulkUserDefinition(BigInteger.valueOf(1),map,BigInteger.valueOf(1),"status");
        PaginationResponsePayload paginationResponsePayload = new PaginationResponsePayload(list,1,1L,1,1,1);
        Page<BulkUserDefinition> page = new PageImpl<>(List.of(bulkUserDefinition));
        Mockito.when(bulkUploadDefinintionRepository.findAll(pageable)).thenReturn(page);
        Mockito.when(bulkUploadDefinintionRepository.findBulkUsersByQPageable("q",pageable)).thenReturn(page);
        Mockito.when(tokenUtils.getPaginationResponsePayload(any(),any())).thenReturn(paginationResponsePayload);
        bulkUserServiceImplementation.getAllBulkUsers(DOCUMENT_ID,"123",CREATED_ON,"asc");
    }

    @Test
    void deleteBulkUserById()
    {
        Mockito.when(bulkUploadDefinintionRepository.existsById(BigInteger.valueOf(1))).thenReturn(true).thenReturn(false);
        Mockito.when(bulkUploadDefinintionRepository.deleteById(BigInteger.valueOf(1))).thenReturn(1);
        bulkUserServiceImplementation.deleteBulkUserById("1");
        Assertions.assertThrows(BulkUserNotFoundException.class,()->bulkUserServiceImplementation.deleteBulkUserById("1"));
    }
}
