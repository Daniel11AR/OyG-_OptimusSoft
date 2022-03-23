package com.prueba.OyG_OPTIMUS.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comprobantes")
@Entity
public class Comprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "concepto",nullable = false)
    private String concepto;

    @Column(name = "fecha",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "iva",nullable = false)
    private Long iva;

    @Column(name = "nFactura",nullable = false)
    private Long nFactura;

    @Column(name = "identificacion",nullable = false)
    private String identificacion;

    @Column(name = "numeroComprobante",nullable = false)
    private Long numeroComprobante;

    @Column(name = "observaciones",nullable = false)
    private String observaciones;

    @Column(name = "porcentajeIva",nullable = false)
    private Double porcentajeIva;

    @Column(name = "porcentajeRetencion",nullable = false)
    private Double porcentajeRetencion;

    @Column(name = "recibidoDe")
    private String recibidoDe;

    @Column(name = "retencion",nullable = false)
    private Long retencion;

    @Column(name = "subTotal",nullable = false)
    private Long subTotal;

    @Column(name = "tipoPago",nullable = false)
    private String tipoPago;

    @Column(name = "valorNeto",nullable = false)
    private Long valorNeto;

    @Column(name = "pagadoA")
    private String pagadoA;

    @Column(name = "tipoComprobante", nullable = false)
    private String tipoComprobante;

    @Column(name = "ciudad",nullable = false)
    private String ciudad;

    @Column(name = "estadoComprobante",nullable = false)
    private String estadoComprobante = "Activo";

}
