package com.prueba.OyG_OPTIMUS.controllers;


import com.prueba.OyG_OPTIMUS.models.Material;
import com.prueba.OyG_OPTIMUS.services.IMaterial;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import com.prueba.OyG_OPTIMUS.validator.IMaterialValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private IMaterial iMaterial;

    @Autowired
    private IMaterialValidator iMaterialValidator;

    @GetMapping("/alertamateriales")
    public ResponseEntity<List<Material>> alertaMateriales(){
        try {
            List<Material> matAlertas = iMaterial.alertaMateriales();
            if (matAlertas == null || matAlertas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(matAlertas, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allmateriales")
    public ResponseEntity<List<Material>> getMateriales(){
        try {
            List<Material> mat = iMaterial.materialesListar();
            if (mat == null || mat.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(mat, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public void guardarMaterial(@RequestBody Material material) throws ApiUnprocessableEntity {iMaterial.guardar(material);}

    @PutMapping("/{id}/actualizarmaterial")
    public void actualizarMaterial(@RequestBody Material material,@PathVariable Long id) throws Exception {  iMaterial.actualizarMaterial(material,id); }

    @PutMapping("/{id}/cambiarestadomaterial")
    public void cambiarEstadoMaterial(@PathVariable Long id) throws Exception {iMaterial.cambiarEstadoMaterial(id);}

}
