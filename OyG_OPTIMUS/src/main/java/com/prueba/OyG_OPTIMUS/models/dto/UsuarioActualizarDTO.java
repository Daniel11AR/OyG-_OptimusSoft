package com.prueba.OyG_OPTIMUS.models.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UsuarioActualizarDTO {
    private String usuario;
    private String password;
    private String rol;
}