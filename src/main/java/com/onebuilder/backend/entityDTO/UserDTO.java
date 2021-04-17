package com.onebuilder.backend.entityDTO;

public class UserDTO {
    public Long UID;
    public String name;
    public String email;
    public String password;
    public boolean isAdmin;

    public void setUID(Long UID) {
        this.UID = UID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
