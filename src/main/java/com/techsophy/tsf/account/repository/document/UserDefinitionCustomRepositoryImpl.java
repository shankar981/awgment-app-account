package com.techsophy.tsf.account.repository.document;

import com.techsophy.tsf.account.entity.UserDefinition;
import com.techsophy.tsf.account.repository.UserDefinitionCustomRepository;
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

@AllArgsConstructor
public class UserDefinitionCustomRepositoryImpl implements UserDefinitionCustomRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<UserDefinition> findUserByQSort(String q, Sort sort)
    {
        Query query = new Query();
        String searchString = URLDecoder.decode(q, StandardCharsets.UTF_8);
        query.addCriteria(new Criteria().orOperator(Criteria.where(USER_DEFINITION_USER_NAME).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_FIRST_NAME).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_LAST_NAME).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_MOBILE_NUMBER).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_EMAIL_ID).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_DEPARTMENT).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
        ));
        query.addCriteria(Criteria.where(USER_DEFINITION_USER_NAME).ne(SYSTEM));
        query.with(Sort.by(Sort.Direction.ASC, USER_DEFINITION_USER_NAME));
        return mongoTemplate.find(query, UserDefinition.class);
    }

    @Override
    public Page<UserDefinition> findUserByQPageable(String q, Pageable pageable)
    {
        Query query = new Query();
        Query countQuery = new Query();
        String searchString = URLDecoder.decode(q, StandardCharsets.UTF_8);
        query.addCriteria(new Criteria().orOperator(Criteria.where(USER_DEFINITION_USER_NAME).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_FIRST_NAME).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_LAST_NAME).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_MOBILE_NUMBER).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_EMAIL_ID).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_DEPARTMENT).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
        )).addCriteria(Criteria.where(USER_DEFINITION_USER_NAME).ne(SYSTEM)).with(pageable);
        List<UserDefinition> userDefinitions = mongoTemplate.find(query, UserDefinition.class);
        query.with(Sort.by(Sort.Direction.ASC, USER_DEFINITION_USER_NAME));
        countQuery.addCriteria(new Criteria().orOperator(Criteria.where(USER_DEFINITION_USER_NAME).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_FIRST_NAME).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_LAST_NAME).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_MOBILE_NUMBER).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_EMAIL_ID).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                Criteria.where(USER_DEFINITION_DEPARTMENT).regex(Pattern.compile(searchString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))
        ));
        countQuery.addCriteria(Criteria.where(USER_DEFINITION_USER_NAME).ne(SYSTEM));
        long count=mongoTemplate.count(countQuery,UserDefinition.class);
        return new PageImpl<>(userDefinitions, pageable,count );
    }

    @Override
    public List<UserDefinition> findAllUsers(Sort sort)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(USER_DEFINITION_USER_NAME).ne(SYSTEM));
        return mongoTemplate.find(query,UserDefinition.class);
    }

    @Override
    public Page<UserDefinition> findAllUsers(Pageable pageable)
    {
        Query query = new Query();
        Query countQuery = new Query();
        query.addCriteria(Criteria.where(USER_DEFINITION_USER_NAME).ne(SYSTEM)).with(pageable);
        List<UserDefinition> userDefinitions= mongoTemplate.find(query,UserDefinition.class);
        countQuery.addCriteria(Criteria.where(USER_DEFINITION_USER_NAME).ne(SYSTEM));
        long count=mongoTemplate.count(countQuery,UserDefinition.class);
        return new PageImpl<>(userDefinitions, pageable, count);
    }
}
