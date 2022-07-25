package com.example.l2cacheredis.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String name;
    private String surname;
    private int age;
}
