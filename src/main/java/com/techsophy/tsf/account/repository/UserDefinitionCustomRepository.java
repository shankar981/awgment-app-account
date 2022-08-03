package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.UserDefinition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface UserDefinitionCustomRepository
{
    List<UserDefinition> findAllUsers(Sort sort);
    Page<UserDefinition> findAllUsers(Pageable pageable);
    List<UserDefinition> findUserByQSort(String q,Sort sort);
    Page<UserDefinition> findUserByQPageable(String q,Pageable pageable);

}
