package com.techsophy.tsf.account.dto;

import lombok.Value;
import lombok.With;

@With
@Value
public class UserPreferencesResponse
{
    String id;
    String userId;
    String themeId;
}
