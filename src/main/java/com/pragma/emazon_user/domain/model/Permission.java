package com.pragma.emazon_user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Permission {

    private Integer permissionId;
    private String permissionName;

}