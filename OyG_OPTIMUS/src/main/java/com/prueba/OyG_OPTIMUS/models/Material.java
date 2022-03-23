package com.prueba.OyG_OPTIMUS.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="materiales")
@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombreMaterial",nullable = false)
    private String nombreMaterial;

    @Column(name = "cantidadMaterial",nullable = false)
    private Integer cantidadMaterial;

    @Column(name = "estadoMaterial",nullable = false)
    private String estadoMaterial = "Activo";
}
