## RESTENDPOINTS

***BulkUserController***
 
**bulkUploadUsers**

* curl -X POST $apigateway_uri/accounts/v1/users
  -H "Content-Type: multipart/form-data" \

**bulkUpdateStatus**
* curl -X POST $apigateway_uri/accounts/v1/users/bulk-upload/status \
  -H "Content-Type: application/json" \
  -d '{"id": 123456, "userData": {"name" : "Nandini"} ,"status" : "inProgress"}'

**getAllBulkUsers**
* curl $apigateway_uri/accounts/v1/users/bulk-upload

**deleteBulkUserById**

* curl -X DELETE $apigateway_uri/accounts/v1/users/bulk-upload?id=1 \
  -H "Accept: application/json"

***GroupsController***

**getAllGroups**
* curl $apigateway_uri/accounts/v1/keycloak/groups

**getGroupById**
* curl $apigateway_uri/accounts/v1/keycloak/groups/{id}

**createGroup**
* curl -X POST $apigateway_uri/accounts/v1/keycloak/groups \
  -H "Content-Type: application/json" \
  -d '{"id": 123456,"name" : "Awgment test group"}'

**deleteGroup**
* curl -X DELETE $apigateway_uri/accounts/v1/keycloak/groups/{id} \
  -H "Accept: application/json"

***GroupsDataController***

**saveGroup**
* curl -X POST $apigateway_uri/accounts/v1/groups \
  -H "Content-Type: application/json" \
  -d '{"name": "Awgment test group,"groupId" : "03a9eeec-682a-451c-b518-263462609b0d"}'

**getAllGroups**
* curl $apigateway_uri/accounts/v1/groups

**getGroupById**
* curl $apigateway_uri/accounts/v1/groups/{id}

**deleteGroup**
* curl -X DELETE $apigateway_uri/accounts/v1/groups/{id} \
  -H "Accept: application/json"

**assignRolesToGroup**
* curl -X POST $apigateway_uri/accounts/v1/groups/{id}/roles \
  -d '{"Admin","awgment-account-all"}'


***MenuController***

**createMenu**
* curl -X POST $apigateway_uri/accounts/v1/menus \
  -H "Content-Type: application/json" \
  -d '{"type": "component,"label" : "Workflow Modeler","url":"workflow-modeler","divider":false}'

**getMenuById**
* curl $apigateway_uri/accounts/v1/menus/{id}

**getAllMenus**
* curl $apigateway_uri/accounts/v1/menus

**deleteMenuById**
* curl -X DELETE $apigateway_uri/accounts/v1/menus/{id} \
  -H "Accept: application/json"

***MenuRoleAssignController***

**createMenuRoles**
* curl -X POST $apigateway_uri/accounts/v1/menurole \
  -H "Content-Type: application/json" \
  -d '{"id": 123456, "role": "awgment-modeler-workflow" ,"menus":["961523592173740032"]}'

**getMenuRoleById**
* curl $apigateway_uri/accounts/v1/menurole/{id}

**getAllMenuRoles**
* curl $apigateway_uri/accounts/v1/menurole

**getAssignedMenuToUserRoles**
* curl $apigateway_uri/accounts/v1/roles/menu

**deleteMenuRolesById**
* curl -X DELETE $apigateway_uri/accounts/v1/menurole/{id} \
  -H "Accept: application/json"

***OtpController***

**generateOtp**
* curl -X POST $apigateway_uri/accounts/v1/otp/generate \
  -H "Content-Type: application/json" \
  -d '{"channel": "mobile","to": "918008256474","from":"no-reply@trovity.com","subject": "from postman","body": "${otp} is the OTP for verification. DO NOT disclose it to anyone"}'

**verifyOtp**
* curl -X POST $apigateway_uri/accounts/v1/otp/validate \
  -H "Content-Type: application/json" \
  -d '{"channel": "mobile", "to": "918008256474","otp":" "}'

***ThemesController***

**saveThemesData**
* curl -X POST $apigateway_uri/accounts/v1/themes \
  -H "Content-Type: application/json" \
  -d '{"name": "web","content":{"name": "akhil","theme": "Themes"}}'

**getThemesDataById**
* curl $apigateway_uri/accounts/v1/themes/{id}

**getAllThemesData**
* curl $apigateway_uri/accounts/v1/themes/{id}

**deleteThemesDataById**
* curl -X DELETE $apigateway_uri/accounts/v1/themes/{id} \
  -H "Accept: application/json"

**downloadTheme**
* curl $apigateway_uri/accounts/v1/themes/{id}/export

**uploadTheme**
* curl -X POST $apigateway_uri/accounts/v1/themes/import \
  -H "Content-Type: multipart/form-data" \
  -d '{"name": "abc"}'

***UserFormDataController***

**saveUser**
* curl -X POST $apigateway_uri/accounts/v1/users \
  -H "Content-Type:application/json " \
  -d '{"userData":{"userName":"Nandini","firstName":"Ganga","lastName":"kudumula"}}'

**getUserByUserId**
* curl $apigateway_uri/accounts/v1/users/{userId}/

**getAllUsers**
* curl $apigateway_uri/accounts/v1/users

**deleteUserByUserId**
* curl -X DELETE $apigateway_uri/accounts/v1/users \
  -H "Accept: application/json"

***UserManagementInKeyCloakController***

**createUser**
* curl -X POST $apigateway_uri/accounts/v1/keycloak/users \
  -H "Content-Type:application/json " \
  -d '{"userData":{"userName":"Nandini","firstName":"Ganga","lastName":"kudumula"}}'

**deleteUser**
* curl -X DELETE $apigateway_uri/accounts/v1/keycloak/users?userName="abc" \
-H "Accept: application/json"

**assignRoleToUser**
* curl -X POST $apigateway_uri/accounts/v1/keycloak/users/roles \
  -H "Content-Type:application/json " \
  -d '{"userId":"51802558-c380-4989-b107-2d98e44653d7","roles": ["DEVELOPER101"]}'

**getAllRoles**
* curl $apigateway_uri/accounts/v1/keycloak/roles

**assignGroupToUser**
* curl -X POST $apigateway_uri/accounts/v1/keycloak/users/groups \
  -H "Content-Type:application/json " \
  -d '{"userId":"abc"}'

**changePassword**
* curl -X POST $apigateway_uri/accounts/v1/keycloak/users/changePassword \
  -H "Content-Type:application/json " \

**setPassword**
* curl -X POST $apigateway_uri/accounts/v1/keycloak/users/setPassword?userName="abc" \
  -H "Content-Type:application/json " \

***UserPreferencesController***

**saveUserPreferencesTheme**
* curl -X POST $apigateway_uri/accounts/v1/users/preferences \
  -H "Content-Type:application/json " \
  -d '{"themeId":"910114840494047232","userId":"909019929479634944"}'

**getUserPreferencesThemesDataByUserId**
* curl $apigateway_uri/accounts/v1/users/preferences

**deleteUserPreferencesThemeDataByUserId**
* curl -X DELETE $apigateway_uri/accounts/v1/users/preferences \
  -H "Accept: application/json"

**uploadProfilePictureByUserId**
* curl -X POST $apigateway_uri/accounts/v1/users/preferences/profile-picture \
  -H "Content-Type:multipart/form-data " \

**deleteProfilePhotoByUserId**
* curl -X DELETE $apigateway_uri/accounts/v1/users/preferences/profile-picture \
  -H "Accept: application/json"