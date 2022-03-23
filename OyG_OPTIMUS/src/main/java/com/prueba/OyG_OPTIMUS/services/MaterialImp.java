package com.prueba.OyG_OPTIMUS.services;

import com.prueba.OyG_OPTIMUS.dao.MaterialDao;
import com.prueba.OyG_OPTIMUS.models.Material;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiNotFound;

import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import com.prueba.OyG_OPTIMUS.validator.IMaterialValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterialImp implements IMaterial{
    @Autowired
    private MaterialDao materialDao;

    @Autowired
    private IMaterialValidator iMaterialValidator;

    @Override
    public List<Material> materialesListar(){
        return materialDao.findAll();
    }

    @Override
    public void eliminarMaterial(Long id) throws Exception {
        Optional<Material> material = materialDao.findById(id);
        if(material.isPresent()){
            materialDao.delete(material.get());
        }else{
            Exception ApiNotFound = new ApiNotFound("No existe un material que eliminar con este ID.");
            throw ApiNotFound;
        }
    }

    @Override
    public void actualizarMaterial(Material material, Long id) throws Exception {
        Optional<Material> mat = this.materialDao.findById(id);
        if(mat.isPresent()){
            Material mate = mat.get();
            mate.setNombreMaterial(material.getNombreMaterial());
            mate.setCantidadMaterial(material.getCantidadMaterial());
            iMaterialValidator.validatorActualizar(mate);
            materialDao.save(mate);
        }else{
            Exception ApiNotFound = new ApiNotFound("No existe un material que actualizar con este ID.");
            throw ApiNotFound;
        }
    }

    @Override
    public List<Material> alertaMateriales(){
        try{
            /*
            List<Material> materiales = new ArrayList<>();
            for(Material m: materialDao.findAll()){
                if(m.getCantidadMaterial()<10){
                    materiales.add(m);
                }
            }
            return materiales;
            */

            return materialDao.findAll()
                    .stream()
                    .filter(material -> material.getCantidadMaterial() < 10)
                    .collect(Collectors.toList());
        }catch (Exception e){return null;}
    }

    @Override
    public void cambiarEstadoMaterial(Long id) throws Exception {
        //Me excitas ;) :P :3 :v XD
        Optional<Material> mat = this.materialDao.findById(id);
        if(mat.isPresent()){
            Material mate = mat.get();
            if(mate.getEstadoMaterial().equals("Activo")) {
                mate.setEstadoMaterial("Inactivo");
                materialDao.save(mate);
            }else if (mate.getEstadoMaterial().equals("Inactivo")){
                mate.setEstadoMaterial("Activo");
                materialDao.save(mate);
            }
        }else{
            Exception ApiNotFound = new ApiNotFound("No existe un material que actualizar con este ID.");
            throw ApiNotFound;
        }
    }

    @Override
    public void guardar(Material material) throws ApiUnprocessableEntity {
        iMaterialValidator.validator(material);
        materialDao.save(material);
    }
}
