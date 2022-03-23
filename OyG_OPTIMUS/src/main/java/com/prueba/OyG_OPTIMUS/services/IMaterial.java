package com.prueba.OyG_OPTIMUS.services;

import com.prueba.OyG_OPTIMUS.models.Material;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiNotFound;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;

import java.util.List;

public interface IMaterial {

    List<Material> materialesListar();
    void eliminarMaterial(Long id) throws Exception;

    void guardar(Material material) throws ApiUnprocessableEntity;

    void actualizarMaterial(Material material, Long id) throws Exception;

    List<Material> alertaMateriales();

    void cambiarEstadoMaterial(Long id) throws Exception;
}
