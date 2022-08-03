package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.MenuRoleAssignDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface MenuRoleAssignRepository extends MongoRepository<MenuRoleAssignDefinition,Long>
{
    Optional<MenuRoleAssignDefinition> findById(BigInteger id);
    boolean existsById(BigInteger id);
    void deleteById(BigInteger id);
    MenuRoleAssignDefinition findByRole(String role);
}
