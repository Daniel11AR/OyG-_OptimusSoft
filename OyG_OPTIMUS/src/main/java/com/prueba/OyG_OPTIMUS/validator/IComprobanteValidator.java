package com.prueba.OyG_OPTIMUS.validator;

import com.prueba.OyG_OPTIMUS.models.Comprobante;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.stereotype.Service;

//Interface de la validación de datos recibidos para la creación de Comprobantes
@Service
public interface IComprobanteValidator {
    void validator(Comprobante comprobante) throws ApiUnprocessableEntity;
    void validatorActualizar(Comprobante comprobante)throws ApiUnprocessableEntity;
}
