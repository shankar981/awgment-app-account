package com.techsophy.tsf.account.dto;

import lombok.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class MenuSchema  extends AuditableData implements Serializable
{
     String  id;
     String type;
     String label;
     String url;
     boolean divider;
     String version;
}
