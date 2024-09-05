package com.pragma.emazon_user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Role {

    private Integer roleId;
    private String roleName;
    private String roleDescription;

}