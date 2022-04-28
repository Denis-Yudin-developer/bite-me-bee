package ru.coderiders.bitemebee.entity;

import java.io.Serializable;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority, Serializable {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
