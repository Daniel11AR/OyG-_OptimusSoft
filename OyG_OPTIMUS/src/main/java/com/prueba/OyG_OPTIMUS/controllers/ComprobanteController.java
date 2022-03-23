package com.prueba.OyG_OPTIMUS.controllers;

import com.prueba.OyG_OPTIMUS.models.Comprobante;
import com.prueba.OyG_OPTIMUS.services.IComprobante;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import com.prueba.OyG_OPTIMUS.validator.IComprobanteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comprobante")
public class ComprobanteController {

    @Autowired
    private IComprobante iComprobante;

    @Autowired
    private IComprobanteValidator iComprobanteValidator;

    @GetMapping("/allcomprobantes")
    public ResponseEntity<List<Comprobante>> getComprobantes(){
        try {
            List<Comprobante> comp = iComprobante.comprobantesListar();
            if (comp == null || comp.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(comp, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public void guardarComprobante(@RequestBody Comprobante comprobante) throws ApiUnprocessableEntity { iComprobante.guardar(comprobante); }

    @PutMapping("/{id}/actualizarcomprobante")
    public void actualizarComprobante(@RequestBody Comprobante comprobante, @PathVariable Long id) throws Exception {iComprobante.actualizarComprobante(comprobante,id); }

    @PutMapping("/{id}/cambiarestadocomprobante")
    public void cambiarEstadoComprobante(@PathVariable Long id) throws Exception {
        iComprobante.cambiarEstadoComprobante(id);
    }
}
