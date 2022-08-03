package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.UserDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserDefinitionRepository extends MongoRepository<UserDefinition, Long>, UserDefinitionCustomRepository
{
    Optional<UserDefinition> findById(BigInteger userKey);

    boolean existsById(BigInteger userKey);

    void deleteById(BigInteger userKey);

    boolean existsByEmailId(String emailId);

    boolean existsByUserName(String userName);

    Optional<UserDefinition> findByEmailIdOrUserName(String emailId, String userName);
}
