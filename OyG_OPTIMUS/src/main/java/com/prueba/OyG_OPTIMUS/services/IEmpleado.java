package com.prueba.OyG_OPTIMUS.services;

import com.prueba.OyG_OPTIMUS.models.Empleado;
import com.prueba.OyG_OPTIMUS.models.dto.EmpleadoDTO;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;


import java.text.ParseException;
import java.util.List;

public interface IEmpleado {

    List<Empleado> empleadolistar ();

    void eliminarEmpleado (Long id) throws Exception;

    void guardar(Empleado empleado) throws ApiUnprocessableEntity;

    void update(Empleado empleado , Long id) throws Exception;

    void estadoEmpleado(Long id);

    List<EmpleadoDTO> alertas() throws ParseException;
}
