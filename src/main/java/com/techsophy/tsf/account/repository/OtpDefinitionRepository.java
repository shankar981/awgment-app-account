package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.OtpDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OtpDefinitionRepository extends MongoRepository<OtpDefinition, Long>  {

    Optional<OtpDefinition> findByToAndChannel(String to,String type);
}
