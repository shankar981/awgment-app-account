package com.techsophy.tsf.account.controller;

import com.techsophy.tsf.account.config.GlobalMessageSource;
import com.techsophy.tsf.account.controller.impl.MenuRoleAssignControllerImpl;
import com.techsophy.tsf.account.dto.*;
import com.techsophy.tsf.account.model.ApiResponse;
import com.techsophy.tsf.account.service.MenuRoleAssignService;
import com.techsophy.tsf.account.service.UserManagementInKeyCloak;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.techsophy.tsf.account.constants.AccountConstants.*;
import static com.techsophy.tsf.account.constants.ThemesConstants.TEST_ACTIVE_PROFILE;
import static com.techsophy.tsf.account.constants.UserConstants.ID;


@SpringBootTest
@ActiveProfiles(TEST_ACTIVE_PROFILE)
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc(addFilters = false)
 class MenuRoleAssignControllerImplTest {
    @InjectMocks
    MenuRoleAssignControllerImpl menuRoleAssignController;
    @Mock
    GlobalMessageSource globalMessageSource;
    @Mock
    MenuRoleAssignService menuRoleAssignService;

    @Test
    void createMenuRole() throws IOException {
        List<String> menu = new ArrayList<>();
        menu.add("abc");
        menu.add("abc");
        menu.add("abc");
        MenuRoleAssignSchema menuRoleAssignSchema = new MenuRoleAssignSchema(ID, ROLES, menu);
        ApiResponse<MenuRoleAssignResponseSchema> response = menuRoleAssignController.createMenuRoles(menuRoleAssignSchema);
        Assertions.assertNotNull(response);
    }

    @Test
    void getMenuRoleByIdTest() {
        ApiResponse<MenuRoleAssignSchema> response = menuRoleAssignController.getMenuRoleById(ID);
        Assertions.assertNotNull(response);
    }

    @Test()
    void deleteMenuRolesByIdTest() {
        ApiResponse response = menuRoleAssignController.deleteMenuRolesById(ID);
        Assertions.assertNotNull(response);
    }

    @Test()
    void getAssignedMenuToUserRolesTest() {
        ApiResponse<List<MenuSchema>> response = menuRoleAssignController.getAssignedMenuToUserRoles();
        Assertions.assertNotNull(response);
    }

    @Test()
    void getAllMenuRolesTest() {
        ApiResponse<Stream<MenuRoleAssignSchema>> response = menuRoleAssignController.getAllMenuRoles();
        Assertions.assertNotNull(response);
    }

}

