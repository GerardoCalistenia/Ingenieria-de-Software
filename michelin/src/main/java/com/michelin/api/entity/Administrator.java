package com.michelin.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Administrador")
public class Administrator {

    @Id
    @Column(name = "administrador_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer administrator_id;

    @NotNull
    @Column(name = "nombre")
    private String name;

    @NotNull
    @Column(name = "correo")
    private String email;

    @NotNull
    @Column(name = "contraseña")
    private String password;

    public void setAdministratorId(Integer administrator_id) {
        this.administrator_id = administrator_id;
    }

    public Integer getAdministratorId() {
        return administrator_id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}