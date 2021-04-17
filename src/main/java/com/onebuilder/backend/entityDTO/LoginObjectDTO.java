package com.onebuilder.backend.entityDTO;

public class LoginObjectDTO {
    public String email;
    public String password;

    @Override
    public String toString() {
        return "LoginObjectDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
