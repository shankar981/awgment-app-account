package com.techsophy.tsf.account.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.account.dto.MenuResponseSchema;
import com.techsophy.tsf.account.dto.MenuSchema;
import java.util.stream.Stream;

public interface MenuService
{
    MenuResponseSchema saveMenu(MenuSchema menuSchema) throws JsonProcessingException;

    MenuSchema getMenuById(String id);

    Stream<MenuSchema> getAllMenus();

    void deleteMenuById(String userId);
}
