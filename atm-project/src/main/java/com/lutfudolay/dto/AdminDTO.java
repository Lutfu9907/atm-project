package com.lutfudolay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private Long id;
    private String username;
    private int userCount; // Bu admin'e bağlı kaç kullanıcı var
}