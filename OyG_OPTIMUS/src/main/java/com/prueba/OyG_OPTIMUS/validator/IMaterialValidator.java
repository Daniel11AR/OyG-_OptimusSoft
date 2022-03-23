package com.prueba.OyG_OPTIMUS.validator;

import com.prueba.OyG_OPTIMUS.models.Material;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.stereotype.Service;

//Interface de la validación de datos recibidos para la creación de Materiales
@Service
public interface IMaterialValidator {
    void validator(Material material) throws ApiUnprocessableEntity;
    void validatorActualizar(Material material) throws ApiUnprocessableEntity;
}
