package com.example.demo.model;

import com.example.demo.enums.Role;
import com.example.demo.enums.UserStatus;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends UserCreat {
    private Long id;
    private Role roles;
    private UserStatus userStatus;
}
