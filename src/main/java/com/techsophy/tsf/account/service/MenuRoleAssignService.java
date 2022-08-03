package com.techsophy.tsf.account.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.account.dto.MenuRoleAssignResponseSchema;
import com.techsophy.tsf.account.dto.MenuRoleAssignSchema;
import com.techsophy.tsf.account.dto.MenuSchema;
import java.util.List;
import java.util.stream.Stream;

public interface MenuRoleAssignService
{
    MenuRoleAssignResponseSchema saveMenuRole(MenuRoleAssignSchema menuRoleAssignSchema) throws JsonProcessingException;

    MenuRoleAssignSchema getMenuRole(String id);

    Stream<MenuRoleAssignSchema> getAllMenuRole();

    List<MenuSchema> getAssignedMenuToUserRoles();

    void deleteMenuRoleById(String userId);
}
