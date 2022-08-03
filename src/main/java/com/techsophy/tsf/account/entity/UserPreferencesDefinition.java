package com.techsophy.tsf.account.entity;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigInteger;
import static com.techsophy.tsf.account.constants.AccountConstants.TP_USER_PREFERENCE_COLLECTION;

@EqualsAndHashCode(callSuper = true)
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = TP_USER_PREFERENCE_COLLECTION)
public class UserPreferencesDefinition extends Auditable
{
    @Id
    private BigInteger id;
    private BigInteger themeId;
    private BigInteger userId;
    private Binary profilePicture;
}
