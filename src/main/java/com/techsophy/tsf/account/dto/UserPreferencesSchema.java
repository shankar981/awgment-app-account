package com.techsophy.tsf.account.dto;

import lombok.Value;
import lombok.With;
import javax.validation.constraints.NotBlank;
import static com.techsophy.tsf.account.constants.AccountConstants.THEME_ID_NOT_NULL;

@With
@Value
public class UserPreferencesSchema
{
    String id;
    String userId;
    @NotBlank(message = THEME_ID_NOT_NULL)
    String themeId;
    String profilePicture;
}
