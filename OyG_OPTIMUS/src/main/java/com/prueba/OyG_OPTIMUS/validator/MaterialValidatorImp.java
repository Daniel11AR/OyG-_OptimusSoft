package com.prueba.OyG_OPTIMUS.validator;

import com.prueba.OyG_OPTIMUS.dao.MaterialDao;
import com.prueba.OyG_OPTIMUS.models.Material;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MaterialValidatorImp implements IMaterialValidator {

    @Autowired
    private MaterialDao materialDao;

    @Override
    public void validator(Material material) throws ApiUnprocessableEntity {
        boolean nombre=false;
        if(material.getNombreMaterial() == null || material.getNombreMaterial().isEmpty()){
            message("El nombre del material es obligatorio.");
        }
        if(material.getCantidadMaterial() == null){
            message("La cantidad del material es obligatorio.");
        }
        if(material.getCantidadMaterial() < 1){
            message("Debe ingresar una cantidad de material mayor a cero(0).");
        }
        if(material.getNombreMaterial().length() < 3){
            message("El nombre del material es muy corto.");
        }
        for(int i=0;i<material.getNombreMaterial().length(); i++){
            char letra = material.getNombreMaterial().charAt(i);
            if(letra>='a' && letra<='z' || letra>='A' && letra<='Z'){
                nombre=true;
            }
        }
        if(nombre==false){
            message("El material debe tener letras en el nombre.");
        }

        for (Material m:materialDao.findAll()) {
            if(m.getNombreMaterial().equals(material.getNombreMaterial())){
                message("Ya existe un material con este nombre.");
            }
        }
    }

    @Override
    public void validatorActualizar(Material material) throws ApiUnprocessableEntity {
        boolean nombre=false;
        if(material.getNombreMaterial() == null || material.getNombreMaterial().isEmpty()){
            message("El nombre del material es obligatorio.");
        }
        if(material.getCantidadMaterial() == null){
            message("La cantidad del material es obligatorio.");
        }
        if(material.getCantidadMaterial() < 1){
            message("Debe ingresar una cantidad de material mayor a cero(0).");
        }
        if(material.getNombreMaterial().length() < 3){
            message("El nombre del material es muy corto.");
        }
        for(int i=0;i<material.getNombreMaterial().length(); i++){
            char letra = material.getNombreMaterial().charAt(i);
            if(letra>='a' && letra<='z' || letra>='A' && letra<='Z'){
                nombre=true;
            }
        }
        if(nombre==false){
            message("El material debe tener letras en el nombre.");
        }

        for (Material m:materialDao.findAll()) {
            if(!(material.getId().equals(m.getId()))) {
                if (m.getNombreMaterial().equals(material.getNombreMaterial())) {
                    message("Ya existe un material con este nombre.");
                }
            }
        }
    }

    private void message(String message) throws ApiUnprocessableEntity{
        throw new ApiUnprocessableEntity(message);
    }
}
