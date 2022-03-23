package com.prueba.OyG_OPTIMUS.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Excepción personalizada de status 401
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ApiUnauthorized extends Exception{
    public ApiUnauthorized(String message){
        super(message);
    }
}
