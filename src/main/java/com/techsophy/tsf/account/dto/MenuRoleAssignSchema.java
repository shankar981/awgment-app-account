package com.techsophy.tsf.account.dto;

import lombok.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class MenuRoleAssignSchema extends AuditableData implements Serializable
{
    String id;
    String role;
    transient  List<String> menus;
}
