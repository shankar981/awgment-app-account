package com.techsophy.tsf.account.dto;

import lombok.Value;
import lombok.With;

@With
@Value
public class GetUserGroupSchema
{
    String groupId;
    String groupName;
}
