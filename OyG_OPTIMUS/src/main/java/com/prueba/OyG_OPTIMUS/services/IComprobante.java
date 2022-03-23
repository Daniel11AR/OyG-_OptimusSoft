package com.prueba.OyG_OPTIMUS.services;

import com.prueba.OyG_OPTIMUS.models.Comprobante;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;

import java.text.ParseException;
import java.util.List;

public interface IComprobante {
    List<Comprobante> comprobantesListar();
    void actualizarComprobante(Comprobante comprobante, Long id) throws Exception;
    void guardar(Comprobante comprobante) throws ApiUnprocessableEntity;
    void cambiarEstadoComprobante(Long id) throws Exception;
}
