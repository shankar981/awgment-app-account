package com.techsophy.tsf.account.repository.document;

import com.techsophy.tsf.account.entity.GroupDefinition;
import com.techsophy.tsf.account.repository.GroupsCustomRepository;
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
public class GroupsCustomRepositoryImpl implements GroupsCustomRepository
{
    private final MongoTemplate mongoTemplate;

    @Override
    public List<GroupDefinition> findGroupsByQSorting(String q, Sort sort)
    {
        Query query=new Query();
        String searchString = URLDecoder.decode(q, StandardCharsets.UTF_8);
        query.addCriteria(new Criteria().orOperator
                (Criteria.where(GROUP_DEFINITION_NAME).regex
                                (Pattern.compile(searchString,
                                        Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                        Criteria.where(GROUP_DEFINITION_DESCRIPTION).regex
                                (Pattern.compile(searchString,
                                        Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))));
        query.with(Sort.by(Sort.Direction.ASC, GROUP_DEFINITION_NAME));
        return mongoTemplate.find(query,GroupDefinition.class);
    }

    @Override
    public Page<GroupDefinition> findGroupsByQPageable(String q, Pageable pageable)
    {
        Query query = new Query();
        Query countQuery = new Query();
        String searchString = URLDecoder.decode(q, StandardCharsets.UTF_8);
        query.with(pageable).addCriteria(new Criteria().orOperator
                (Criteria.where(GROUP_DEFINITION_NAME).regex
                                (Pattern.compile(searchString,
                                        Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                        Criteria.where(GROUP_DEFINITION_DESCRIPTION).regex
                                (Pattern.compile(searchString,
                                        Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))));
        query.with(Sort.by(Sort.Direction.ASC, GROUP_DEFINITION_NAME));
        countQuery.addCriteria(new Criteria().orOperator
                (Criteria.where(GROUP_DEFINITION_NAME).regex
                                (Pattern.compile(searchString,
                                        Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)),
                        Criteria.where(GROUP_DEFINITION_DESCRIPTION).regex
                                (Pattern.compile(searchString,
                                        Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE))));
        List<GroupDefinition> list = mongoTemplate.find(query, GroupDefinition.class);
        long count=mongoTemplate.count(countQuery, GroupDefinition.class);
        return new PageImpl<>(list, pageable,count );
    }

    @Override
    public List<GroupDefinition> findByIdIn(List<String> idList) {

        Query query = new Query(Criteria.where(GROUPS_DEFINITION_ID).in(idList));
        return mongoTemplate.find(query, GroupDefinition.class);
    }
}
