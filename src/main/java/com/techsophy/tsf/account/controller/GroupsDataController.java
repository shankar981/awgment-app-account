package com.techsophy.tsf.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.account.model.ApiResponse;
import com.techsophy.tsf.account.dto.AssignGroupRoles;
import com.techsophy.tsf.account.dto.GroupsData;
import com.techsophy.tsf.account.dto.GroupsDataSchema;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.techsophy.tsf.account.constants.AccountConstants.*;

@RequestMapping(BASE_URL + VERSION_V1)
public interface GroupsDataController
{
    @PostMapping(GROUPS_URL)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<GroupsDataSchema> saveGroup(@RequestBody GroupsData groupsData) throws JsonProcessingException;

    @GetMapping(GROUPS_URL)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<List<GroupsDataSchema>> getAllGroups(@RequestParam(value = QUERY,required = false) String q, @RequestParam(value = PAGE, required = false) Integer page,
                                                     @RequestParam(value = SIZE, required = false) Integer pageSize,
                                                     @RequestParam(value = SORT_BY, required = false) String[] sortBy,
                                                     @RequestParam(value=DEPLOYMENT,required = false) String deploymentIdList) throws JsonProcessingException;

    @GetMapping(GROUP_BY_ID_URL)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<GroupsDataSchema> getGroupById(@PathVariable(ID) String id) throws JsonProcessingException;

    @DeleteMapping(GROUP_BY_ID_URL)
    @PreAuthorize(DELETE_OR_ALL_ACCESS)
    ApiResponse<Void> deleteGroup( @PathVariable(ID) String id) throws JsonProcessingException;

    @PostMapping(GROUP_BY_ID_ROLES_URL)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<GroupsDataSchema> assignRolesToGroup(@PathVariable(ID) String id, @RequestBody AssignGroupRoles groupRoles) throws JsonProcessingException;
}
