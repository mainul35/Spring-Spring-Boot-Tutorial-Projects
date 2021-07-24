package com.mainul35.springbootjaxrs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String uuid;
    private String username;
    private String name;
    private String email;

}
