package com.prueba.OyG_OPTIMUS.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "proveedor")
@EqualsAndHashCode @NoArgsConstructor
@AllArgsConstructor
@Data
public class Proveedor {


    @Column(name = "id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nitProveedor")
    private Long nitProveedor;

    @Column(name = "nombreProveedor")
    private String nombreProveedor;

    @Column(name = "telefonoProveedor")
    private Long telefonoProveedor;

    @Column(name = "contactoProveedor")
    private String contactoProveedor;

    @Column(name = "correoProveedor")
    private String correoProveedor;

    @Column(name = "direccionProveedor")
    private String direccionProveedor;

    @Column(name = "estadoProveedor")
    private String estadoProveedor = "Activo";

}