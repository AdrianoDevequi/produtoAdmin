package com.admin.spring.service.searching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import com.admin.spring.web.dto.UserDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchResult {
    private Page<UserDto> userPage;
    private boolean numberFormatException;
}
