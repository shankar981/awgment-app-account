package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.ThemesDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ThemesDefinitionRepository extends MongoRepository<ThemesDefinition,Long>,ThemesCustomRepository
{
    Optional<ThemesDefinition> findById(BigInteger id);

    boolean existsById(BigInteger id);

    void deleteById(BigInteger id);
}
