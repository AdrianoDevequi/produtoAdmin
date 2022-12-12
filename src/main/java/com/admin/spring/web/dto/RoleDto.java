package com.admin.spring.web.dto;

import com.admin.spring.customAnnotations.ValidRoleName;
import lombok.Data;

@Data
public class RoleDto {
    Long id;
    @ValidRoleName
    String name;
}
