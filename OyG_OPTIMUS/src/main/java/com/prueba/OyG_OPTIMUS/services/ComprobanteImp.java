package com.prueba.OyG_OPTIMUS.services;

import com.prueba.OyG_OPTIMUS.dao.ComprobanteDao;
import com.prueba.OyG_OPTIMUS.models.Comprobante;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiNotFound;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import com.prueba.OyG_OPTIMUS.validator.IComprobanteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ComprobanteImp implements IComprobante{
    @Autowired
    private ComprobanteDao comprobanteDao;
    @Autowired
    private IComprobanteValidator iComprobanteValidator;
    @Override
    public List<Comprobante> comprobantesListar() {
        return comprobanteDao.findAll();
    }

    @Override
    public void actualizarComprobante(Comprobante comprobante, Long id) throws Exception {

        Optional<Comprobante> comp = this.comprobanteDao.findById(id);
        if(comp.isPresent()){
            Comprobante compro = comp.get();
            compro.setConcepto(comprobante.getConcepto());
            compro.setFecha(comprobante.getFecha());
            compro.setIva(comprobante.getIva());
            compro.setNFactura(comprobante.getNFactura());
            compro.setIdentificacion(comprobante.getIdentificacion());
            compro.setNumeroComprobante(comprobante.getNumeroComprobante());
            compro.setObservaciones(comprobante.getObservaciones());
            compro.setPorcentajeIva(comprobante.getPorcentajeIva());
            compro.setPorcentajeRetencion(comprobante.getPorcentajeRetencion());
            compro.setRecibidoDe(comprobante.getRecibidoDe());
            compro.setRetencion(comprobante.getRetencion());
            compro.setSubTotal(comprobante.getSubTotal());
            compro.setTipoPago(comprobante.getTipoPago());
            compro.setValorNeto(comprobante.getValorNeto());
            compro.setPagadoA(comprobante.getPagadoA());
            compro.setTipoComprobante(comprobante.getTipoComprobante());
            compro.setCiudad(comprobante.getCiudad());
            iComprobanteValidator.validatorActualizar(compro);
            comprobanteDao.save(compro);
        }else {
            Exception apiNotFound = new ApiNotFound("No existe un Comprobante con el ID "+id);
            throw apiNotFound;
        }
    }

    @Override
    public void cambiarEstadoComprobante(Long id) throws Exception {
        Optional<Comprobante> comp = this.comprobanteDao.findById(id);
        if(comp.isPresent()){
            Comprobante compro = comp.get();
            if(compro.getEstadoComprobante().equals("Activo")){
                compro.setEstadoComprobante("Inactivo");
                comprobanteDao.save(compro);
            }else if(compro.getEstadoComprobante().equals("Inactivo")){
                compro.setEstadoComprobante("Activo");
                comprobanteDao.save(compro);
            }
        }else{
            Exception apiNotFound = new ApiNotFound("No existe un Comprobante con el ID "+id);
            throw apiNotFound;
        }
    }

    @Override
    public void guardar(Comprobante comprobante) throws ApiUnprocessableEntity {
            iComprobanteValidator.validator(comprobante);
            comprobanteDao.save(comprobante);
    }
}
