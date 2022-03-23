package com.prueba.OyG_OPTIMUS.services;

import com.prueba.OyG_OPTIMUS.models.Proveedor;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;

import java.util.List;


public interface IProveedor {

    List<Proveedor> getProveedores();

    void registrarProveedor(Proveedor proveedor) throws ApiUnprocessableEntity;

    void eliminar(Long id);

    void cambiarEstadoProveedor(Long id);

    void actualizarProveedor(Proveedor proveedor, Long id) throws Exception;
}