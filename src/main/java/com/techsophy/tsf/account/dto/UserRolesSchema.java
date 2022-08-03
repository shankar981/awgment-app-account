package com.techsophy.tsf.account.dto;

import lombok.Value;
import lombok.With;
import javax.validation.constraints.NotNull;
import java.util.List;
import static com.techsophy.tsf.account.constants.AccountConstants.USER_ID_NOT_NULL;

@With
@Value
public class UserRolesSchema
{
    @NotNull(message = USER_ID_NOT_NULL) String userId;
    List<String> roles;
}
