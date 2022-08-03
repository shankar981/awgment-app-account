package com.techsophy.tsf.account.repository.document;

import com.techsophy.tsf.account.entity.BulkUserDefinition;
import com.techsophy.tsf.account.repository.BulkUploadDefinitionCustomRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;
import static com.techsophy.tsf.account.constants.AccountConstants.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@AllArgsConstructor
public class BulkUploadDefinitionCustomRepositoryImpl implements BulkUploadDefinitionCustomRepository
{
    private final MongoTemplate mongoTemplate;

    public List<BulkUserDefinition> findBulkUsersByFilter(String filterColumn, String filterValue)
    {
        Query query=new Query();
        String searchString = URLDecoder.decode(filterValue, StandardCharsets.UTF_8);
        query.addCriteria(new Criteria().orOperator(where(USER_DATA+DOT+filterColumn).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE))
       ,Criteria.where(filterColumn).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE))));
        query.with(Sort.by(Sort.Direction.DESC,CREATED_ON));
        return mongoTemplate.find(query,BulkUserDefinition.class);
    }

    @Override
    public List<BulkUserDefinition> findBulkUsersByFilterAndSort(String filterColumn, String filterValue, String sortBy, String sortOrder)
    {
        Query query=new Query();
        String searchString = URLDecoder.decode(filterValue, StandardCharsets.UTF_8);
        query.addCriteria(new Criteria().orOperator(where(USER_DATA+DOT+filterColumn).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE))
                ,Criteria.where(filterColumn).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE))));
        query.with(Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        return mongoTemplate.find(query,BulkUserDefinition.class);
    }

    @Override
    public Page<BulkUserDefinition> findBulkUserByFilterPageable(String filterColumn, String filterValue, Pageable pageable)
    {
        Query query = new Query();
        query.addCriteria(where(USER_DATA_USER_NAME).ne(SYSTEM)).with(pageable);
        Query countQuery = new Query();
        String searchString = URLDecoder.decode(filterValue, StandardCharsets.UTF_8);
        query.addCriteria(new Criteria().orOperator(where(USER_DATA+DOT+filterColumn).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                Criteria.where(filterColumn).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)))).with(pageable);
        query.with(Sort.by(Sort.Direction.DESC,CREATED_ON));
        List<BulkUserDefinition> bulkUserDefinitionList= mongoTemplate.find(query,BulkUserDefinition.class);
        countQuery.addCriteria(new Criteria().orOperator(where(USER_DATA+DOT+filterColumn).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                Criteria.where(filterColumn).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE))));
        countQuery.addCriteria(where(USER_DATA_USER_NAME).ne(SYSTEM));
        long count=mongoTemplate.count(countQuery,BulkUserDefinition.class);
        return new PageImpl<>(bulkUserDefinitionList,pageable,count);
    }

    @Override
    public Page<BulkUserDefinition> findBulkUserByFilterSortPageable(String filterColumn, String filterValue, String sortBy, String sortOrder, Pageable pageable)
    {
        Query query = new Query();
        query.addCriteria(where(USER_DATA_USER_NAME).ne(SYSTEM)).with(pageable);
        Query countQuery = new Query();
        String searchString = URLDecoder.decode(filterValue, StandardCharsets.UTF_8);
        query.addCriteria(new Criteria().orOperator(where(USER_DATA+DOT+filterColumn).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                Criteria.where(filterColumn).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)))).with(pageable);
        query.with(Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        List<BulkUserDefinition> bulkUserDefinitionList= mongoTemplate.find(query,BulkUserDefinition.class);
        countQuery.addCriteria(new Criteria().orOperator(where(USER_DATA+DOT+filterColumn).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                Criteria.where(filterColumn).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE))));
        countQuery.addCriteria(where(USER_DATA_USER_NAME).ne(SYSTEM));
        long count=mongoTemplate.count(countQuery,BulkUserDefinition.class);
        return new PageImpl<>(bulkUserDefinitionList,pageable,count);
    }

    @Override
    public List<BulkUserDefinition> findBulkUsersByQ(String q)
    {
        Query query = new Query();
        String searchString = URLDecoder.decode(q, StandardCharsets.UTF_8);
        query.addCriteria(new Criteria().orOperator(where(ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                where(USER_DATA_USER_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(USER_DATA_FIRST_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(USER_DATA_LAST_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(USER_DATA_MOBILE_NUMBER).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_EMAIL_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_DEPARTMENT).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_COMPANY).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(DOCUMENT_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(STATUS).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_BY_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_ON).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_BY_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_BY_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_ON).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_BY_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE))));
        query.addCriteria(where(USER_DATA_USER_NAME).ne(SYSTEM));
        query.with(Sort.by(Sort.Direction.DESC,CREATED_ON));
        return mongoTemplate.find(query,BulkUserDefinition.class);
    }

    @Override
    public List<BulkUserDefinition> findBulkUsersByQSort(String q, String sortBy, String sortOrder)
    {
        Query query = new Query();
        String searchString = URLDecoder.decode(q, StandardCharsets.UTF_8);
        query.addCriteria(new Criteria().orOperator(where(ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                where(USER_DATA_USER_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(USER_DATA_FIRST_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(USER_DATA_LAST_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(USER_DATA_MOBILE_NUMBER).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_EMAIL_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_DEPARTMENT).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_COMPANY).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(DOCUMENT_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(STATUS).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_BY_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_ON).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_BY_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_BY_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_ON).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_BY_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE))));
        query.addCriteria(where(USER_DATA_USER_NAME).ne(SYSTEM));
        query.with(Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        return mongoTemplate.find(query,BulkUserDefinition.class);
    }

    @Override
    public Page<BulkUserDefinition> findBulkUsersByQPageable(String q, Pageable pageable)
    {
        Query query = new Query();
        Query countQuery = new Query();
        String searchString = URLDecoder.decode(q, StandardCharsets.UTF_8);
        query.addCriteria(new Criteria().orOperator(where(ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_USER_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_FIRST_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_LAST_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(USER_DATA_MOBILE_NUMBER).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(USER_DATA_EMAIL_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(USER_DATA_DEPARTMENT).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(DOCUMENT_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(STATUS).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_BY_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_ON).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_BY_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_BY_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_ON).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_BY_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)))).addCriteria(where(USER_DATA_USER_NAME).ne(SYSTEM)).with(pageable);
        query.with(Sort.by(Sort.Direction.DESC,CREATED_ON));
        List<BulkUserDefinition> bulkUserDefinitionList=mongoTemplate.find(query,BulkUserDefinition.class);
        countQuery.addCriteria(new Criteria().orOperator(where(ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_USER_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_FIRST_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_LAST_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_MOBILE_NUMBER).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_EMAIL_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_DEPARTMENT).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(DOCUMENT_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(STATUS).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_BY_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_ON).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_BY_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_BY_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_ON).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_BY_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE))));
        countQuery.addCriteria(where(USER_DATA_USER_NAME).ne(SYSTEM));
        long count=mongoTemplate.count(countQuery, BulkUserDefinition.class);
        return new PageImpl<>(bulkUserDefinitionList, pageable,count);
    }

    @Override
    public Page<BulkUserDefinition> findBulkUsersByQSortPageable(String q,String sortBy,String sortOrder,Pageable pageable)
    {
        Query query = new Query();
        Query countQuery = new Query();
        String searchString = URLDecoder.decode(q, StandardCharsets.UTF_8);
        query.addCriteria(new Criteria().orOperator(where(ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_USER_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_FIRST_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_LAST_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(USER_DATA_MOBILE_NUMBER).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(USER_DATA_EMAIL_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(USER_DATA_DEPARTMENT).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE| Pattern.UNICODE_CASE)),
                where(DOCUMENT_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(STATUS).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_BY_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_ON).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_BY_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_BY_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_ON).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_BY_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)))).addCriteria(where(USER_DATA_USER_NAME).ne(SYSTEM)).with(pageable);
        query.with(Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        List<BulkUserDefinition> bulkUserDefinitionList=mongoTemplate.find(query,BulkUserDefinition.class);
        countQuery.addCriteria(new Criteria().orOperator(where(ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_USER_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_FIRST_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_LAST_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_MOBILE_NUMBER).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_EMAIL_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(USER_DATA_DEPARTMENT).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(DOCUMENT_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(STATUS).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_BY_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_ON).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(CREATED_BY_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_BY_ID).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_ON).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE)),
                where(UPDATED_BY_NAME).regex(Pattern.compile(searchString,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE))));
        countQuery.addCriteria(where(USER_DATA_USER_NAME).ne(SYSTEM));
        long count=mongoTemplate.count(countQuery, BulkUserDefinition.class);
        return new PageImpl<>(bulkUserDefinitionList, pageable,count);
    }
}
