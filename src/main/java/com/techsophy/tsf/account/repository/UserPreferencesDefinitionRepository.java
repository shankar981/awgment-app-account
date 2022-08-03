package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.UserPreferencesDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UserPreferencesDefinitionRepository extends MongoRepository<UserPreferencesDefinition,Long>
{
    Optional<UserPreferencesDefinition> findByUserId(BigInteger loggedInUserId);

    void deleteByUserId(BigInteger loggedInUserId);

    Optional<UserPreferencesDefinition> findById(BigInteger valueOf);

    boolean existsByUserId(BigInteger loggedInUserId);
}
