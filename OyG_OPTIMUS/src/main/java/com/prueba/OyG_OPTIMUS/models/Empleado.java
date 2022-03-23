package com.prueba.OyG_OPTIMUS.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empleados")
@Entity
public class Empleado {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="nombreEmpleado" , nullable = false,length = 50)
    private String nombreEmpleado;

    @Column(name="apellidoEmpleado",nullable = false,length = 50)
    private String apellidoEmpleado;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "estadoEmpleado",nullable = false)
    private String estadoEmpleado = "Activo";

    @Column(name="correoEmpleado")
    private String correoEmpleado;

    @Column(name="direccionEmpleado")
    private String direccionEmpleado;


    @Column(name="documento")
    private Long documento;

    @Column(name="fechaIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @Column(name="fechaVencimientoCA")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoCA;

    @Column(name="tipoDocumento")
    private String tipoDocumento;

    @Column(name="salario")
    private Double salario;

    @Column(name="telefonoEmpleado")
    private Long telefonoEmpleado;

    @Column(name="arl")
    private String arl;

    @Column(name="eps")
    private String eps;
}