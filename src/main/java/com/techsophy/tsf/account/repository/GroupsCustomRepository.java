package com.techsophy.tsf.account.repository;

import com.techsophy.tsf.account.entity.GroupDefinition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GroupsCustomRepository
{
    List<GroupDefinition> findGroupsByQSorting(String q, Sort sort);
    Page<GroupDefinition> findGroupsByQPageable(String q, Pageable pageable);
    List<GroupDefinition> findByIdIn(List<String> idList);
}
