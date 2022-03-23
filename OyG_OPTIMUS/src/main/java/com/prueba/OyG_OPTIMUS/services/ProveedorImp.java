package com.prueba.OyG_OPTIMUS.services;


import com.prueba.OyG_OPTIMUS.dao.ProveedorDao;
import com.prueba.OyG_OPTIMUS.models.Proveedor;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiNotFound;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import com.prueba.OyG_OPTIMUS.validator.IProveedorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorImp implements IProveedor {

    @Autowired
    private ProveedorDao proveedorDao;

    @Autowired
    private IProveedorValidator iProveedorValidator;

    @Override
    public List<Proveedor> getProveedores() { return proveedorDao.findAll(); }

    @Override
    public void registrarProveedor(Proveedor proveedor) throws ApiUnprocessableEntity {
        iProveedorValidator.validator(proveedor);
        proveedorDao.save(proveedor);
    }

    @Override
    public void eliminar(Long id) {

    }


    @Override
    public void actualizarProveedor(Proveedor proveedor, Long id) throws Exception {
        Optional<Proveedor> prov = this.proveedorDao.findById(id);
        if(prov.isPresent()){
            Proveedor prove = prov.get();
            prove.setNitProveedor(proveedor.getNitProveedor());
            prove.setNombreProveedor(proveedor.getNombreProveedor());
            prove.setTelefonoProveedor(proveedor.getTelefonoProveedor());
            prove.setContactoProveedor(proveedor.getContactoProveedor());
            prove.setCorreoProveedor(proveedor.getCorreoProveedor());
            prove.setDireccionProveedor(proveedor.getDireccionProveedor());
            iProveedorValidator.validatorActualizar(prove);
            proveedorDao.save(prove);
        }else if(!prov.isPresent()){
            Exception ApiNotFound = new ApiNotFound("El ID ingresado no existe.");
            throw ApiNotFound;
        }
    }

    @Override
    public void cambiarEstadoProveedor(Long id) {
        try{
            Optional<Proveedor> p = this.proveedorDao.findById(id);
            if(p.isPresent()){
                Proveedor prov = p.get();
                if(prov.getEstadoProveedor().equals("Activo")) {
                    prov.setEstadoProveedor("Inactivo");
                    proveedorDao.save(prov);
                }else if (prov.getEstadoProveedor().equals("Inactivo")){
                    prov.setEstadoProveedor("Activo");
                    proveedorDao.save(prov);
                }
            }
        }catch (Exception e){}
    }
}