package com.prueba.OyG_OPTIMUS.controllers;

import com.prueba.OyG_OPTIMUS.models.Proveedor;
import com.prueba.OyG_OPTIMUS.services.IProveedor;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    @Autowired
    private IProveedor iProveedor;

    @GetMapping
    public ResponseEntity<List<Proveedor>> getProveedores() {
        try {
            List<Proveedor> prov = iProveedor.getProveedores();
            if (prov == null || prov.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(prov, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public void registrarProveedor(@RequestBody Proveedor proveedor) throws ApiUnprocessableEntity {  iProveedor.registrarProveedor(proveedor);  }

    @PutMapping("/{id}/actualizarProveedor")
    public void actualizarProveedor(@RequestBody Proveedor proveedor,@PathVariable Long id) throws Exception { iProveedor.actualizarProveedor(proveedor,id);  }


    @PutMapping("/{id}/cambiarestadoProveedor")
    public void cambiarEstadoProveedor(@PathVariable Long id){ iProveedor.cambiarEstadoProveedor(id);  }
}