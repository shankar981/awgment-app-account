package com.techsophy.tsf.account.dto;

import lombok.Value;
import lombok.With;
import java.util.List;

@With
@Value
public class UpdateGroupRolesSchema
{
    List<String> roles;
}
