package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.BulkUserDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;

@Repository
public interface BulkUploadDefinintionRepository extends MongoRepository<BulkUserDefinition, Long>, BulkUploadDefinitionCustomRepository
{
    BulkUserDefinition findById(BigInteger id);
    boolean existsById(BigInteger id);
    int deleteById(BigInteger id);
}
