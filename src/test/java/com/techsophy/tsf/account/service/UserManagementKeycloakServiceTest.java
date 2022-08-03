package com.techsophy.tsf.account.service;/*
package com.techsophy.tsf.account.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.account.service.impl.UserManagementInKeyCloakImpl;
import com.techsophy.tsf.account.utils.WebClientWrapper;
import com.techsophy.tsf.account.utils.AccountUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//
//@ActiveProfiles("test")
//@SpringBootTest
//@ExtendWith({SpringExtension.class})
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class UserManagementKeycloakServiceTest
//{
//    @Mock
//    WebClientWrapper mockWebClientWrapper;
//    @Mock
//    AccountUtils mockTokenUtils;
//    @Mock
//    ObjectMapper mockObjectMapper;
    @InjectMocks
    static UserManagementInKeyCloakImpl userManagementInKeyCloak;
    @Value("${user.management.keycloak-api}")
    private String keyCloakApi;
    @Value("${user.management.user-api}")
    private String userCreationApi;
    @Value("${user.management.roles-api}")
    private String getRolesApi;
    @Value(("${user.management.groups-api}"))
    private String getGroupsApi;
    @Value("${user.management.count-api}")
    private String getCountApi;
    private static final String USER_DATA = "testdata/user-data.json";
    private static final String ADD_ROLES = "testdata/add-roles.json";
    private static final String GROUPS_SCHEMA = "testdata/groups-schema.json";
}
//    @BeforeEach
//     void setUp() throws IOException
//    {
//        Properties p = new Properties();
//        p.load(new FileReader("src/test/resources/application-test.yaml"));
//        MockitoAnnotations.openMocks(UserManagementInKeyCloakImpl.class);
//        ReflectionTestUtils.setField(userManagementInKeyCloak, "keyCloakApi", "http://localhost:8080/auth/admin/realms/");
//        ReflectionTestUtils.setField(userManagementInKeyCloak, "userCreationApi", userCreationApi);
//        ReflectionTestUtils.setField(userManagementInKeyCloak, "getCountApi", getCountApi);
//        ReflectionTestUtils.setField(userManagementInKeyCloak, "getRolesApi", getRolesApi);
//        ReflectionTestUtils.setField(userManagementInKeyCloak, "getGroupsApi", getGroupsApi);
//    }
//
//    @Test
//    @Order(1)
//    void testSaveUser() throws Exception
//    {
//        when(mockTokenUtils.getIssuerFromContext()).thenReturn("techsophy-platform");
//        ObjectMapper objectMap1 = new ObjectMapper();
//        @Cleanup InputStream stream = new ClassPathResource(USER_DATA).getInputStream();
//        UserDataSchema userDataSchema = objectMap1.readValue(new String(stream.readAllBytes()), UserDataSchema.class);
//        UserSchema userSchema=objectMap1.convertValue(userDataSchema.getUserData(),UserSchema.class);
//        when(mockObjectMapper.convertValue(any(),eq(UserSchema.class))).thenReturn(userSchema);
//        when(mockWebClientWrapper.we(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
//                .thenReturn(null);
//        when(mockWebClientWrapper.getWebClientRequest(any(), any()))
//                .thenReturn("[{\"id\":\"6f1ed74b-e064-4151-97e4-4f8ce4e30d75\",\"username\":\"abc\"}]");
//       Map<String,Object> response= userManagementInKeyCloak.createUser("token", userDataSchema);
//    Assertions.assertNotNull(response);
//    }
//
//    @Test
//    @Order(2)
//    void testDeleteUser() throws Exception
//    {
//        when(mockTokenUtils.getIssuerFromContext()).thenReturn("techsophy-platform");
//        when(mockWebClientWrapper.getWebClientRequest(any(), any()))
//                .thenReturn("[{\"id\":\"6f1ed74b-e064-4151-97e4-4f8ce4e30d75\",\"username\":\"abc\"}]");
//
//        when(mockWebClientWrapper.deleteWebClientRequest(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
//                .thenReturn(null);
//        userManagementInKeyCloak.deleteUser("token", "abc");
//        Assertions.assertNull(null);
//    }

//    @Test
//    @Order(3)
//    void testAssignRoles() throws Exception {
//        when(utilityService.getIssuerFromContext()).thenReturn("techsophy-platform");
//        String realmDetails="{\"id\":\"master\",\"realm\":\"master\",\"displayName\":\"Keycloak\",\"defaultRoles\":[\"offline_access\",\"uma_authorization\"]}";
//        ObjectMapper objectMap = new ObjectMapper();
//        @Cleanup InputStream stream = new ClassPathResource(ADD_ROLES).getInputStream();
//        Map<String,Object> mapping= objectMap.readValue(realmDetails, new TypeReference<>() {});
//        when(objectMapper.readValue(anyString(), (TypeReference<HashMap<String, Object>>) any())).thenReturn((HashMap<String, Object>) mapping);
////        when(objectMapper.readValue(anyString(), RolesSchema.class)).thenReturn((HashMap<String, Object>) mapping);
//
//        UserRolesSchema userDataSchema = objectMap.readValue(new String(stream.readAllBytes()), UserRolesSchema.class);
//        when(webClientWrapper.getWebClientRequest(null, keyCloakApi)).thenReturn(realmDetails);
//        when(webClientWrapper.getWebClientRequest(null, keyCloakApi + userCreationApi + "/" + "abc1" + "/role-mappings/realm")).thenReturn("[{\"id\":\"a3f20be1-8e36-43ce-8597-27922fbfae37\",\"name\":\"uma_authorization\"},{\"id\":\"8f94e4f1-2d30-4bd5-af06-37d873e4192a\",\"name\":\"offline_access\"}]");
//        when(webClientWrapper.getWebClientRequest(null, keyCloakApi+ getRolesApi)).thenReturn("[{\"id\":\"587eb340-9064-46d0-b63a-1a81c3f64cda\",\"name\":\"admin\"},{\"id\":\"8f94e4f1-2d30-4bd5-af06-37d873e4192a\",\"name\":\"offline_access\"},{\"id\":\"a3f20be1-8e36-43ce-8597-27922fbfae37\",\"name\":\"uma_authorization\"},{\"id\":\"a864c5a3-5958-45e3-905c-b89d8b8ce257\",\"name\":\"user\"}]");
//        when(webClientWrapper.postWebClientRequest(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
//                .thenReturn(null);
//        userManagementInKeyCloak.assignUserRole("token", userDataSchema);
//    }
//
//    @Test
//    @Order(4)
//    void testGetRoles() throws Exception {
//        when(utilityService.getIssuerFromContext()).thenReturn("techsophy-platform");
//        when(webClientWrapper.getWebClientRequest(null, keyCloakApi + getRolesApi)).thenReturn("[{\"id\":\"587eb340-9064-46d0-b63a-1a81c3f64cda\",\"name\":\"admin\"},{\"id\":\"8f94e4f1-2d30-4bd5-af06-37d873e4192a\",\"name\":\"offline_access\"},{\"id\":\"a3f20be1-8e36-43ce-8597-27922fbfae37\",\"name\":\"uma_authorization\"},{\"id\":\"a864c5a3-5958-45e3-905c-b89d8b8ce257\",\"name\":\"user\"}]");
//        userManagementInKeyCloak.getAllRoles("token");
//
//    }
//
//    @Test
//    @Order(5)
//    void testCreateGroup() throws Exception {
//        when(utilityService.getIssuerFromContext()).thenReturn("techsophy-platform");
//        ObjectMapper objectMapper = new ObjectMapper();
//        @Cleanup InputStream stream = new ClassPathResource(GROUPS_SCHEMA).getInputStream();
//        GroupsSchema groupsSchema = objectMapper.readValue(new String(stream.readAllBytes()), GroupsSchema.class);
//        when(webClientWrapper.postWebClientRequest(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
//                .thenReturn(null);
//        when(webClientWrapper.getWebClientRequest(any(), any()))
//                .thenReturn("[{\"id\":\"6f1ed74b-e064-4151-97e4-4f8ce4e30d75\",\"name\":\"abc\"}]");
//        when(webClientWrapper.getWebClientRequest(null,keyCloakApi)).thenReturn("{\"id\": \"techsophy-platform\",\"realm\": \"techsophy-platform\",\"defaultRoles\":[\"offline_access\",\"uma_authorization\"]}");
//        userManagementInKeyCloak.createGroup("token", groupsSchema);
//    }
//
//    @Test
//    @Order(6)
//    void testGetAllGroups() throws JsonProcessingException {
//        when(utilityService.getIssuerFromContext()).thenReturn("techsophy-platform");
//        when(webClientWrapper.getWebClientRequest(any(), any()))
//                .thenReturn("[{\"id\":\"6f1ed74b-e064-4151-97e4-4f8ce4e30d75\",\"name\":\"abc\"}]");
//        when(webClientWrapper.getWebClientRequest(null,keyCloakApi)).thenReturn("{\"id\": \"techsophy-platform\",\"realm\": \"techsophy-platform\",\"defaultRoles\":[\"offline_access\",\"uma_authorization\"]}");
//        userManagementInKeyCloak.getAllGroups("token");
//    }
//
//    @Test
//    @Order(7)
//    void testGetGroupById() throws JsonProcessingException {
//        when(utilityService.getIssuerFromContext()).thenReturn("techsophy-platform");
//        when(webClientWrapper.getWebClientRequest(any(), any()))
//                .thenReturn("{\"id\":\"6f1ed74b-e064-4151-97e4-4f8ce4e30d75\",\"name\":\"abc\",\"realmRoles\": [\"587eb340-9064-46d0-b63a-1a81c3f64cda\",\"a864c5a3-5958-45e3-905c-b89d8b8ce257\"]}");
//        when(webClientWrapper.getWebClientRequest(null,keyCloakApi+getRolesApi))
//                .thenReturn("[{\"id\":\"6f1ed74b-e064-4151-97e4-4f8ce4e30d75\",\"name\":\"abc\"},{\"id\":\"6f1ed74b-e064-4151-97e4-4f8ce4e30d75\",\"name\":\"abc\"}]");
//        when(webClientWrapper.getWebClientRequest(null,keyCloakApi)).thenReturn("{\"id\": \"techsophy-platform\",\"realm\": \"techsophy-platform\",\"defaultRoles\":[\"offline_access\",\"uma_authorization\"]}");
//        userManagementInKeyCloak.getGroupById("token",ID);
//
//    }

//    @Test
//    @Order(8)
//    void testDeleteGroupById()
//    {
//        when(mockTokenUtils.getIssuerFromContext()).thenReturn("techsophy-platform");
//        when(mockWebClientWrapper.deleteWebClientRequest(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
//                .thenReturn(null);
//        userManagementInKeyCloak.deleteGroup("token", UserConstants.ID);
//        Assertions.assertNull(null);
//    }

//    @Test
//    @Order(9)
//    void testGetAllGroupsPagination() throws JsonProcessingException {
//        when(utilityService.getIssuerFromContext()).thenReturn("techsophy-platform");
//        String pageForPagination = String.valueOf(0);
//        String pageSizeForPagination = String.valueOf((5));
//
//        when(webClientWrapper.getWebClientRequest(null,keyCloakApi + getGroupsApi+getCountApi+"?top=true")).thenReturn("{\"count\": 24}");
//        when(webClientWrapper.getWebClientRequest(null, keyCloakApi + getGroupsApi + "?first=" + pageForPagination + "&max=" + pageSizeForPagination)).thenReturn("[{\"id\":\"e1059ddb-d770-46f9-8517-0b09ab1a35f2\",\"name\":\"Bhavya 1\"}]");
//        userManagementInKeyCloak.getAllGroups("token",1,5,null );
//    }
//}
*/
