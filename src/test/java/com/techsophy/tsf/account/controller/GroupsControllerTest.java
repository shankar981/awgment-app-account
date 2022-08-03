package com.techsophy.tsf.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.account.config.GlobalMessageSource;
import com.techsophy.tsf.account.controller.impl.GroupsControllerImpl;
import com.techsophy.tsf.account.dto.GroupsSaveSchema;
import com.techsophy.tsf.account.dto.GroupsSchema;
import com.techsophy.tsf.account.model.ApiResponse;
import com.techsophy.tsf.account.service.UserManagementInKeyCloak;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static com.techsophy.tsf.account.constants.ThemesConstants.TEST_ACTIVE_PROFILE;
import static com.techsophy.tsf.account.constants.UserConstants.ID;
import static com.techsophy.tsf.account.constants.UserConstants.NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles(TEST_ACTIVE_PROFILE)
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class GroupsControllerTest
{
    @InjectMocks
    GroupsControllerImpl groupsControllerImpl;
    @Mock
    UserManagementInKeyCloak userManagementInKeyCloak;
    @Mock
    GlobalMessageSource globalMessageSource;
    MockHttpServletRequest request = new MockHttpServletRequest();
    List<String> roles = new ArrayList<>();
    String token;
    Integer page;
    Integer pageSize;
    String sortBy;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @BeforeEach
    public void init()
    {
        token = "token";
        roles.add("5aa92c95-310e-4411-a031-d0d0f6c5b30d");
        page =1;
        pageSize=3;
        sortBy="Ascending";
    }

    @Test
    void createGroupTest() throws JsonProcessingException
    {
        GroupsSchema groupsSchema = new GroupsSchema(ID, NAME);
        GroupsSchema groupsSchema1 = new GroupsSchema(null, NAME);
        Mockito.when(userManagementInKeyCloak.createGroup(groupsSchema)).thenReturn(Stream
                .of(new GroupsSaveSchema(ID, NAME))
                .collect(Collectors.toList()));
        ApiResponse<List<GroupsSaveSchema>> responseEntity = groupsControllerImpl.createGroup(request, groupsSchema);
        ApiResponse<List<GroupsSaveSchema>> responseEntity1 = groupsControllerImpl.createGroup(request, groupsSchema1);
        List<GroupsSaveSchema> data= userManagementInKeyCloak.createGroup(groupsSchema);
        assertEquals(1,data.size());
        assertEquals(true,responseEntity.getSuccess());
        assertEquals(true,responseEntity1.getSuccess());
        verify(userManagementInKeyCloak, times(1)).createGroup(groupsSchema);
    }

    @Test
    void getAllGroupsTest() throws JsonProcessingException
    {
        ApiResponse<Stream<GroupsSaveSchema>> responseEntity =groupsControllerImpl.getAllGroups(request,null,5,null);
        Mockito.when(userManagementInKeyCloak.getAllGroups()).thenReturn(Stream
                .of(new GroupsSaveSchema(ID, NAME),new GroupsSaveSchema(ID, NAME)));
        Stream<GroupsSaveSchema> groupsSchemaStream= userManagementInKeyCloak.getAllGroups();
//        Mockito.when(userManagementInKeyCloak.getAllGroups(page,pageSize,sortBy)).thenReturn(new PaginationResponsePayload());
//        PaginationResponsePayload groupsPayload = userManagementInKeyCloak.getAllGroups(page,pageSize,sortBy);
        assertEquals(2, (int) groupsSchemaStream.count());
        assertEquals(true,responseEntity.getSuccess());
        verify(userManagementInKeyCloak, times(1)).getAllGroups();
//        verify(userManagementInKeyCloak,times(1)).getAllGroups(page,pageSize,sortBy);
    }

    @Test
    void getGroupByIdTest() throws JsonProcessingException
    {
        ApiResponse responseEntity=groupsControllerImpl.getGroupById(request,ID);
        Mockito.when(userManagementInKeyCloak.getGroupById(ID)).thenReturn(new GroupsSchema(ID, NAME));
        GroupsSchema data= userManagementInKeyCloak.getGroupById(ID);
        assertEquals(true,responseEntity.getSuccess());
        verify(userManagementInKeyCloak, times(1)).getGroupById(ID);
    }

    @Test
    void deleteGroupTest() throws JsonProcessingException
    {
        ApiResponse<Void> responseEntity=groupsControllerImpl.deleteGroup(request,ID);
        assertEquals(true,responseEntity.getSuccess());
        userManagementInKeyCloak.deleteGroup(ID);
    }
}
