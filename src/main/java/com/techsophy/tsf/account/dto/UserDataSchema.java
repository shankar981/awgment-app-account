package com.techsophy.tsf.account.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.Map;

@With
@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class UserDataSchema
{
    @NotNull Map<String,Object> userData;
    String userId;
}
