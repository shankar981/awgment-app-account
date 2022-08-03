package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.BulkUserDefinition;
import com.techsophy.tsf.account.entity.UserFormDataDefinition;
import com.techsophy.tsf.account.repository.document.BulkUploadDefinitionCustomRepositoryImpl;
import com.techsophy.tsf.account.repository.document.UserFormDataDefinitionCustomRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.techsophy.tsf.account.constants.ThemesConstants.TEST_ACTIVE_PROFILE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles(TEST_ACTIVE_PROFILE)
@SpringBootTest
class UserFormDataDefinitionCustomRepoTest
{
    @Mock
    MongoTemplate mongoTemplate;
    @InjectMocks
    UserFormDataDefinitionCustomRepositoryImpl userFormDataDefinitionCustomRepository;
    Map<String,Object> map = new HashMap<>();
    @BeforeEach
    void init() {
        map.put("abc", "abc");
    }
    @Test
    void findByNameOrId()
    {
        UserFormDataDefinition userFormDataDefinition = new UserFormDataDefinition(BigInteger.ONE,map,BigInteger.ONE,1);
        Mockito.when(mongoTemplate.find(any(),eq(UserFormDataDefinition.class))).thenReturn(List.of(userFormDataDefinition));
        userFormDataDefinitionCustomRepository.findByNameOrId("abc");
        verify(mongoTemplate,times(1)).find(any(),eq(UserFormDataDefinition.class));
    }

    @Test
    void findAll()
    {
        UserFormDataDefinition userFormDataDefinition = new UserFormDataDefinition(BigInteger.ONE,map,BigInteger.ONE,1);
        Mockito.when(mongoTemplate.find(any(),eq(UserFormDataDefinition.class))).thenReturn(List.of(userFormDataDefinition));
        userFormDataDefinitionCustomRepository.findAll(Sort.by("abc"));
        verify(mongoTemplate,times(1)).find(any(),eq(UserFormDataDefinition.class));
    }

    @Test
    void findAllPageable()
    {
        UserFormDataDefinition userFormDataDefinition = new UserFormDataDefinition(BigInteger.ONE,map,BigInteger.ONE,1);
        Mockito.when(mongoTemplate.find(any(),eq(UserFormDataDefinition.class))).thenReturn(List.of(userFormDataDefinition));
        Pageable page =  PageRequest.of(1,1);
        userFormDataDefinitionCustomRepository.findAll(page);
        verify(mongoTemplate,times(1)).find(any(),eq(UserFormDataDefinition.class));
    }

    @Test
    void findFormDataUserByQSort()
    {
        UserFormDataDefinition userFormDataDefinition = new UserFormDataDefinition(BigInteger.ONE,map,BigInteger.ONE,1);
        Mockito.when(mongoTemplate.find(any(),eq(UserFormDataDefinition.class))).thenReturn(List.of(userFormDataDefinition));
        Pageable page =  PageRequest.of(1,1);
        userFormDataDefinitionCustomRepository.findFormDataUserByQSort("abc",Sort.by("abc"));
        verify(mongoTemplate,times(1)).find(any(),eq(UserFormDataDefinition.class));
    }

    @Test
    void findFormDataUserByQPageable()
    {
        UserFormDataDefinition userFormDataDefinition = new UserFormDataDefinition(BigInteger.ONE,map,BigInteger.ONE,1);
        Mockito.when(mongoTemplate.find(any(),eq(UserFormDataDefinition.class))).thenReturn(List.of(userFormDataDefinition));
        Pageable page =  PageRequest.of(1,1);
        userFormDataDefinitionCustomRepository.findFormDataUserByQPageable("abc",page);
        verify(mongoTemplate,times(1)).find(any(),eq(UserFormDataDefinition.class));
    }

    @Test
    void findByFilterColumnAndValue()
    {
        UserFormDataDefinition userFormDataDefinition = new UserFormDataDefinition(BigInteger.ONE,map,BigInteger.ONE,1);
        Mockito.when(mongoTemplate.find(any(),eq(UserFormDataDefinition.class))).thenReturn(List.of(userFormDataDefinition));
        Pageable page =  PageRequest.of(1,1);
        userFormDataDefinitionCustomRepository.findByFilterColumnAndValue(Sort.by("abc"),"abc","abc");
        verify(mongoTemplate,times(1)).find(any(),eq(UserFormDataDefinition.class));
    }

    @Test
    void findByFilterColumnAndValueTest()
    {
        UserFormDataDefinition userFormDataDefinition = new UserFormDataDefinition(BigInteger.ONE,map,BigInteger.ONE,1);
        Mockito.when(mongoTemplate.find(any(),eq(UserFormDataDefinition.class))).thenReturn(List.of(userFormDataDefinition));
        Pageable page =  PageRequest.of(1,1);
        userFormDataDefinitionCustomRepository.findByFilterColumnAndValue("abc","abc",page,"abc");
        verify(mongoTemplate,times(1)).find(any(),eq(UserFormDataDefinition.class));
    }
}
