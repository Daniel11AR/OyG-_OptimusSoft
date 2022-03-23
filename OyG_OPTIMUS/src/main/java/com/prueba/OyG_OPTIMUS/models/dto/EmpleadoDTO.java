package com.prueba.OyG_OPTIMUS.models.dto;

import lombok.*;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpleadoDTO {

    private String nombreEmpleado;

    private Date fechaVencimiento;

    private Long numeroDocumento;


}
