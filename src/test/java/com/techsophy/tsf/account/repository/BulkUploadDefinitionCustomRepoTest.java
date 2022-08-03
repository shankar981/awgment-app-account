package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.BulkUserDefinition;
import com.techsophy.tsf.account.repository.document.BulkUploadDefinitionCustomRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.techsophy.tsf.account.constants.ThemesConstants.CREATEDON;
import static com.techsophy.tsf.account.constants.ThemesConstants.TEST_ACTIVE_PROFILE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles(TEST_ACTIVE_PROFILE)
@SpringBootTest
class BulkUploadDefinitionCustomRepoTest
{
    @Mock
    MongoTemplate mongoTemplate;
    @InjectMocks
    BulkUploadDefinitionCustomRepositoryImpl bulkUploadDefinitionCustomRepository;
    Map<String,Object> map = new HashMap<>();
    @BeforeEach
    void init() {
        map.put("abc", "abc");
    }

    @Test
    void findBulkUsersByFilterTest()
    {
        BulkUserDefinition bulkUserDefinition = new BulkUserDefinition(BigInteger.ONE,map, BigInteger.ONE,"abc");
        Mockito.when(mongoTemplate.find(any(),eq(BulkUserDefinition.class))).thenReturn(List.of(bulkUserDefinition));
        bulkUploadDefinitionCustomRepository.findBulkUsersByFilter("documentId","123");
        verify(mongoTemplate,times(1)).find(any(),eq(BulkUserDefinition.class));
    }

    @Test
    void findBulkUsersByFilterAndSortTest()
    {
        BulkUserDefinition bulkUserDefinition = new BulkUserDefinition(BigInteger.ONE,map, BigInteger.ONE,"abc");
        Mockito.when(mongoTemplate.find(any(),eq(BulkUserDefinition.class))).thenReturn(List.of(bulkUserDefinition));
        bulkUploadDefinitionCustomRepository.findBulkUsersByFilterAndSort("abc", "abc",CREATEDON ,"asc" );
        verify(mongoTemplate,times(1)).find(any(),eq(BulkUserDefinition.class));
    }

    @Test
    void findBulkUserByFilterPageableTest()
    {
        Pageable page =  PageRequest.of(1,1);
        BulkUserDefinition bulkUserDefinition = new BulkUserDefinition(BigInteger.ONE,map, BigInteger.ONE,"abc");
        Mockito.when(mongoTemplate.find(any(),eq(BulkUserDefinition.class))).thenReturn(List.of(bulkUserDefinition));
        bulkUploadDefinitionCustomRepository.findBulkUserByFilterPageable("abc","abc",page);
        verify(mongoTemplate,times(1)).find(any(),eq(BulkUserDefinition.class));
    }

    @Test
    void findBulkUserByFilterSortPageableTest()
    {
        Pageable page =  PageRequest.of(1,1);
        BulkUserDefinition bulkUserDefinition = new BulkUserDefinition(BigInteger.ONE,map, BigInteger.ONE,"abc");
        Mockito.when(mongoTemplate.find(any(),eq(BulkUserDefinition.class))).thenReturn(List.of(bulkUserDefinition));
        bulkUploadDefinitionCustomRepository.findBulkUserByFilterSortPageable("abc","abc",CREATEDON,"desc",page);
        verify(mongoTemplate,times(1)).find(any(),eq(BulkUserDefinition.class));
    }

    @Test
    void findBulkUsersByQTest()
    {
        BulkUserDefinition bulkUserDefinition = new BulkUserDefinition(BigInteger.ONE,map, BigInteger.ONE,"abc");
        Mockito.when(mongoTemplate.find(any(),eq(BulkUserDefinition.class))).thenReturn(List.of(bulkUserDefinition));
        bulkUploadDefinitionCustomRepository.findBulkUsersByQSort("abc",CREATEDON,"desc" );
        verify(mongoTemplate,times(1)).find(any(),eq(BulkUserDefinition.class));
    }

    @Test
    void findBulkUsersByQSortTest()
    {
        BulkUserDefinition bulkUserDefinition = new BulkUserDefinition(BigInteger.ONE,map, BigInteger.ONE,"abc");
        Mockito.when(mongoTemplate.find(any(),eq(BulkUserDefinition.class))).thenReturn(List.of(bulkUserDefinition));
        bulkUploadDefinitionCustomRepository.findBulkUsersByQSort("abc",CREATEDON,"desc");
        verify(mongoTemplate,times(1)).find(any(),eq(BulkUserDefinition.class));
    }

    @Test
    void findBulkUsersByQPageableTest()
    {
        Pageable page =  PageRequest.of(1,1);
        BulkUserDefinition bulkUserDefinition = new BulkUserDefinition(BigInteger.ONE,map, BigInteger.ONE,"abc");
        Mockito.when(mongoTemplate.find(any(),eq(BulkUserDefinition.class))).thenReturn(List.of(bulkUserDefinition));
        bulkUploadDefinitionCustomRepository.findBulkUsersByQPageable("abc",page);
        verify(mongoTemplate,times(1)).find(any(),eq(BulkUserDefinition.class));
    }

}
