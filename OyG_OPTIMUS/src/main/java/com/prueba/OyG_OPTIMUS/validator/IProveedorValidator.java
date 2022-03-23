package com.prueba.OyG_OPTIMUS.validator;

import com.prueba.OyG_OPTIMUS.models.Proveedor;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.stereotype.Service;

@Service
public interface IProveedorValidator {
    void validator(Proveedor proveedor) throws ApiUnprocessableEntity;
    void validatorActualizar(Proveedor proveedor) throws ApiUnprocessableEntity;
}
