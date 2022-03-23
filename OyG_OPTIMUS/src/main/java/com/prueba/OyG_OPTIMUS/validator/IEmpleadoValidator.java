package com.prueba.OyG_OPTIMUS.validator;


import com.prueba.OyG_OPTIMUS.models.Empleado;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.stereotype.Service;

/**
 * interface validacion para los datos de creacion de un empleado
*/
@Service
public interface IEmpleadoValidator {

    void validator(Empleado empleado) throws ApiUnprocessableEntity;

    void validatorupdate(Empleado empleado) throws ApiUnprocessableEntity;
}
