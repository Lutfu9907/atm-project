package com.lutfudolay.mapper;

import com.lutfudolay.dto.UserDTO;
import com.lutfudolay.entities.User;

public class UserMapper {

	public static UserDTO toDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getAccount() != null ? user.getAccount().getId() : null
        );
    }
}
