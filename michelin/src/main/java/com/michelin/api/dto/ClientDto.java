package com.michelin.api.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Cliente")
public class ClientDto {

    @Column(name = "correo")
    @NotNull(message = "email is required")
    private String email;

    @Column(name = "nombre")
    @NotNull(message = "name is required")
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
