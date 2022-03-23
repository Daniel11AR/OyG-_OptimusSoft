package com.prueba.OyG_OPTIMUS.validator;

import com.prueba.OyG_OPTIMUS.models.Usuario;
import com.prueba.OyG_OPTIMUS.models.dto.UsuarioActualizarDTO;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.stereotype.Service;

//Interface de la validación de datos recibidos para la creación de Usuarios
@Service
public interface IUsuarioValidator{
    void validator(Usuario usuario) throws ApiUnprocessableEntity;
    void validatorActualizar(Usuario usuario) throws ApiUnprocessableEntity;
}
