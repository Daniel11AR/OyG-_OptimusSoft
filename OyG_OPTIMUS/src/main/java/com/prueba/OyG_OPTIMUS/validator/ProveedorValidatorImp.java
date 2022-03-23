package com.prueba.OyG_OPTIMUS.validator;


import com.prueba.OyG_OPTIMUS.dao.ProveedorDao;
import com.prueba.OyG_OPTIMUS.models.Proveedor;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProveedorValidatorImp implements IProveedorValidator {

    @Autowired
    private ProveedorDao proveedorDao;

    @Override
    public void validator(Proveedor proveedor) throws ApiUnprocessableEntity {

        //Validaciones de NIT
        for(Proveedor prov : proveedorDao.findAll()){
            if (prov.getNitProveedor().equals(proveedor.getNitProveedor())){
                message("El NIT ingresado ya se enncuentra registrado en el sistema");
            }
        }

        if (proveedor.getNitProveedor() == null || proveedor.getNitProveedor().toString().isEmpty()){
            message("El campo NIT no puede ir vacío");
        }

        //Validaciones de Nombre
        if (proveedor.getNombreProveedor() == null ||  proveedor.getNombreProveedor().isEmpty()){
            message("El campo nombre no puede ir vacío");
        }

        if (proveedor.getNombreProveedor().length() < 3){
            message("El nombre debe tener al menos 3 carácteres");
        }

        if (proveedor.getNombreProveedor().length() > 50){
            message("El nombre es demasiado largo, debe contener maxímo 50 carácteres");
        }


        //Validaciones de Contacto
        if (proveedor.getContactoProveedor() == null ||  proveedor.getContactoProveedor().isEmpty()){
            message("El campo contacto no puede ir vacío");
        }

        if (proveedor.getContactoProveedor().length() < 3){
            message("El contacto debe tener al menos 3 carácteres");
        }

        if (proveedor.getContactoProveedor().length() > 100){
            message("El contacto es demasiado largo, debe contener maxímo 100 carácteres");
        }

        //Validaciones de Telefóno
        if (proveedor.getTelefonoProveedor() == null ||  proveedor.getTelefonoProveedor().toString().isEmpty()){
            message("El campo telefono no puede ir vacío");
        }

        if (proveedor.getTelefonoProveedor().toString().length() < 7){
            message("El número telefónico debe contener al menos 7 dígitos");
        }

        //Validaciones Correo Electronico
        if (proveedor.getCorreoProveedor() == null || proveedor.getCorreoProveedor().isEmpty()){
            message("El campo correo no puede ir vacío");
        }

        if (proveedor.getCorreoProveedor().length() > 100){
            message("El correo es demasiado largo, bebe contener maxímo 100 caracteres");
        }

        //Validaciones Dirección
        if (proveedor.getDireccionProveedor() == null || proveedor.getDireccionProveedor().isEmpty()){
            message("El campo dirección no puede ir vacío");
        }

        if (proveedor.getDireccionProveedor().length() < 7){
            message("La dirección es demasiado corta, debe ingresar una dirección valida");
        }

        if (proveedor.getDireccionProveedor().length() > 100){
            message("El correo es demasiado largo, debe contener maxímo 100 carácteres");
        }


    }

    @Override
    public void validatorActualizar(Proveedor proveedor) throws ApiUnprocessableEntity {
        for(Proveedor c: proveedorDao.findAll()){
            if(!(proveedor.getId().equals(c.getId())))
                if(c.getNitProveedor().equals(proveedor.getNitProveedor()))
                    message("Ya existe un comprobante en el sistema con este mismo Número de Comprobante.");
        }

        if (proveedor.getNitProveedor() == null || proveedor.getNitProveedor().toString().isEmpty()){
            message("El campo NIT no puede ir vacío");
        }

        //Validaciones de Nombre
        if (proveedor.getNombreProveedor() == null ||  proveedor.getNombreProveedor().isEmpty()){
            message("El campo nombre no puede ir vacío");
        }

        if (proveedor.getNombreProveedor().length() < 3){
            message("El nombre debe tener al menos 3 carácteres");
        }

        if (proveedor.getNombreProveedor().length() > 50){
            message("El nombre es demasiado largo, debe contener maxímo 50 carácteres");
        }


        //Validaciones de Contacto
        if (proveedor.getContactoProveedor() == null ||  proveedor.getContactoProveedor().isEmpty()){
            message("El campo contacto no puede ir vacío");
        }

        if (proveedor.getContactoProveedor().length() < 3){
            message("El contacto debe tener al menos 3 carácteres");
        }

        if (proveedor.getContactoProveedor().length() > 100){
            message("El contacto es demasiado largo, debe contener maxímo 100 carácteres");
        }

        //Validaciones de Telefóno
        if (proveedor.getTelefonoProveedor() == null ||  proveedor.getTelefonoProveedor().toString().isEmpty()){
            message("El campo telefono no puede ir vacío");
        }

        if (proveedor.getTelefonoProveedor().toString().length() < 7){
            message("El número telefónico debe contener al menos 7 dígitos");
        }

        //Validaciones Correo Electronico
        if (proveedor.getCorreoProveedor() == null || proveedor.getCorreoProveedor().isEmpty()){
            message("El campo correo no puede ir vacío");
        }

        if (proveedor.getCorreoProveedor().length() > 100){
            message("El correo es demasiado largo, bebe contener maxímo 100 caracteres");
        }

        //Validaciones Dirección
        if (proveedor.getDireccionProveedor() == null || proveedor.getDireccionProveedor().isEmpty()){
            message("El campo dirección no puede ir vacío");
        }

        if (proveedor.getDireccionProveedor().length() < 7){
            message("La dirección es demasiado corta, debe ingresar una dirección valida");
        }

        if (proveedor.getDireccionProveedor().length() > 100){
            message("El correo es demasiado largo, debe contener maxímo 100 carácteres");
        }

    }

    private void message (String message) throws ApiUnprocessableEntity{


        throw new ApiUnprocessableEntity(message);
    }
}
