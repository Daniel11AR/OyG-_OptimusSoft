package com.prueba.OyG_OPTIMUS.models.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponseDTO {
    private String codError;
    private String descripcionError;
}