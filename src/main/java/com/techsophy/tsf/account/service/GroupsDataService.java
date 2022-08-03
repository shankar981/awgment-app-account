package com.techsophy.tsf.account.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.account.dto.AssignGroupRoles;
import com.techsophy.tsf.account.dto.GroupsData;
import com.techsophy.tsf.account.dto.GroupsDataSchema;
import com.techsophy.tsf.account.dto.PaginationResponsePayload;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface GroupsDataService
{
    List<GroupsDataSchema> getAllGroups(String q, Sort sort,String deploymentIdList) throws JsonProcessingException;

    PaginationResponsePayload getAllGroups(String q, Pageable pageable) throws JsonProcessingException;

    GroupsDataSchema getGroupById(String id) throws JsonProcessingException;

    GroupsDataSchema saveGroup(GroupsData groupsData);

    void deleteGroup(String id);

    void assignRolesToGroup( String id, AssignGroupRoles groupRoles) throws JsonProcessingException;
}
