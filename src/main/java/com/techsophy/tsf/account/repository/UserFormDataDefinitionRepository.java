package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.UserFormDataDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserFormDataDefinitionRepository extends MongoRepository<UserFormDataDefinition, Long>, UserFormDataDefinitionCustomRepository
{
    Optional<UserFormDataDefinition> findByUserId(BigInteger userId);

    int deleteByUserId(BigInteger userId);
}
