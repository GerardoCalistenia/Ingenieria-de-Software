package com.michelin.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Cliente")
public class Client {

    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer client_id;

    @NotNull
    @Column(name = "nombre")
    private String name;

    @NotNull
    @Column(name = "correo")
    private String email;

    @NotNull
    @Column(name = "contraseña")
    private String password;

    @NotNull
    @Column(name = "fecha_nacimiento")
    private String date_of_birth;


    public void setAdministratorId(Integer client_id) {
        this.client_id = client_id;
    }

    public Integer getAdministratorId() {
        return client_id;
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

    public void setDateOfBirth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getDateOfBirth() {
        return date_of_birth;
    }
}
