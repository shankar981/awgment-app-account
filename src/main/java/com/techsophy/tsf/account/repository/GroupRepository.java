package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.GroupDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;

@Repository
public interface GroupRepository extends MongoRepository<GroupDefinition, BigInteger>,GroupsCustomRepository
{
    boolean existsByGroupId(String id);

    GroupDefinition findByGroupId(String id);

    boolean existsById(String id);

    boolean existsByName(String name);
}
