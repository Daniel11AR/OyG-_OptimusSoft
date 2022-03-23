package com.prueba.OyG_OPTIMUS.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//La anotación @Data trae los métodos de un modelo convencional(Set, get, toString)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuarios")
@Entity
public class Usuario {

    //Anotaciones de JPA
    @Id
    //Esta anotación es para el autoincremental automático
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "password")
    private String password;
    @Column(name = "rol")
    private String rol;
    @Column(name = "estadousuario")
    private String estadoUsuario = "Activo";

}
