package com.techsophy.tsf.account.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;
import javax.validation.constraints.NotEmpty;
import java.util.Map;
import static com.techsophy.tsf.account.constants.AccountConstants.USER_DATA_NOT_EMPTY;

@EqualsAndHashCode(callSuper = true)
@With
@Value
public class UserFormDataSchema extends AuditableData
{
    @NotEmpty(message = USER_DATA_NOT_EMPTY)
    Map<String, Object> userData;
    String userId;
    String version;
}
