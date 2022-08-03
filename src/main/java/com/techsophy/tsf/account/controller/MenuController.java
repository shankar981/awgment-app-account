package com.techsophy.tsf.account.controller;

import com.techsophy.tsf.account.dto.MenuResponseSchema;
import com.techsophy.tsf.account.dto.MenuSchema;
import com.techsophy.tsf.account.model.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.stream.Stream;
import static com.techsophy.tsf.account.constants.AccountConstants.*;

@RequestMapping(BASE_URL + VERSION_V1)
public interface MenuController
{
    @PostMapping(MENUS)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<MenuResponseSchema> createMenu(@RequestBody MenuSchema menuSchema) throws IOException;

    @GetMapping(MENUS+MENU_ID)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<MenuSchema> getMenuById(@PathVariable(ID) String id);

    @GetMapping(MENUS)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<Stream<MenuSchema>> getAllMenus();

    @DeleteMapping(MENUS+MENU_ID)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<Void> deleteMenuById(@PathVariable(ID) String id);
}
