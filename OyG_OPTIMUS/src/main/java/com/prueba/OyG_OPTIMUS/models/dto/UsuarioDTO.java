package com.prueba.OyG_OPTIMUS.models.dto;

import lombok.*;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UsuarioDTO {
    private Integer id;
    private String usuario;
    private String rol;
    private String estadoUsuario;
}
