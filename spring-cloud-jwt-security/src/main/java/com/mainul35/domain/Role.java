package com.mainul35.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@EqualsAndHashCode
@Document
public class Role {
    String id;
    String roleName;

    public String getAuthority() {
        return this.roleName;
    }
}
