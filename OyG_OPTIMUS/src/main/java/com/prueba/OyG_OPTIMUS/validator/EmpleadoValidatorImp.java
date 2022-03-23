package com.prueba.OyG_OPTIMUS.validator;


import com.prueba.OyG_OPTIMUS.dao.EmpleadoDAO;
import com.prueba.OyG_OPTIMUS.models.Empleado;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoValidatorImp  implements IEmpleadoValidator{

    @Autowired
    EmpleadoDAO empleadoDAO;

    @Override
    public void validator(Empleado empleado) throws ApiUnprocessableEntity {

        boolean nombre=false;

        if (empleado.getNombreEmpleado() == null || empleado.getNombreEmpleado().isEmpty()){

            message("El nombre es un campo obligatorio");
        }

        if (empleado.getNombreEmpleado().length() <3){
            message("Nombre demaciado corrto minimo debe contener 3 caracteres");
        }

        if (empleado.getApellidoEmpleado() ==null || empleado.getApellidoEmpleado().isEmpty()){

            message("El Apellido es un campo obligatorio");
        }

        for(Empleado em: empleadoDAO.findAll()){
            if(em.getDocumento().equals(empleado.getDocumento())){
                message("Este documento ya está registrado en el sistema.");
            }
        }
        if(empleado.getDocumento().toString().charAt(0)=='0')
            message("Es obligatorio que el número de documento no inicie con un cero");

        if (empleado.getApellidoEmpleado().length() <3){
            message("Apellido demaciado corto minimo debe contener 3 caracteres");
        }

        if (empleado.getEdad() <= 18){

            message("Edad debe ser superios a los 18 años de edad");
        }
        if (empleado.getEdad() == null){
            message("Campo edad vacio");
        }

        if (empleado.getCorreoEmpleado()=="") {
            message("Campo Correo vacio");
        }

        if (empleado.getFechaIngreso() == null){

            message("Se requiere el campo fecha de ingreso");
        }
        if (empleado.getDocumento() == null){

            message("Campo Documento obligatorio");
        }
        if(empleado.getSalario() == null){
            message("Campo salario esta vacio");
        }
        if(empleado.getArl()==""){
            message("Campo Arl  vacio");
        }
        if (empleado.getEps()==""){
            message("Campo Eps Vacio");
        }
        if(empleado.getTipoDocumento()==""){
            message(("Campo Tipo documento vacio"));
        }
        if (empleado.getCorreoEmpleado().length() >100){
            message("Correo demaciado largo");
        }

    }

    @Override
    public void validatorupdate(Empleado empleado) throws ApiUnprocessableEntity {

        boolean nombre=false;

        if (empleado.getNombreEmpleado() == null || empleado.getNombreEmpleado().isEmpty()){

            message("El nombre es un campo obligatorio");
        }

        if (empleado.getNombreEmpleado().length() <3){
            message("Nombre demaciado corrto minimo debe contener 3 caracteres");
        }

        if (empleado.getCorreoEmpleado().length() >100){
            message("Correo demaciado largo");
        }

        if (empleado.getApellidoEmpleado() ==null || empleado.getApellidoEmpleado().isEmpty()){

            message("El Apellido es un campo obligatorio");
        }

        for(Empleado em: empleadoDAO.findAll()){
            if(!(empleado.getId().equals(em.getId()))){
                if(em.getDocumento().equals(empleado.getDocumento()))
                    message("Ya existe este documento en el sistema");
        }
        }

        for(int i=0;i<empleado.getNombreEmpleado().length(); i++){
            char letra = empleado.getNombreEmpleado().charAt(i);
            if(letra>='a' && letra<='z' || letra>='A' && letra<='Z'){
                nombre=true;
            }
        }

        if(empleado.getDocumento().toString().charAt(0)=='0')
            message("Es obligatorio que el número de documento no inicie con un cero");

        if (empleado.getApellidoEmpleado().length() <3){
            message("Apellido demaciado corto minimo debe contener 3 caracteres");
        }

        if (empleado.getEdad() < 18){

            message("Edad debe ser superios a los 18 años de edad");
        }

        if (empleado.getFechaIngreso() == null){

            message("Se requiere el campo fecha de ingreso");
        }
        if (empleado.getDocumento() == null){

            message("Campo Documento obligatorio");
        }

        if(empleado.getFechaVencimientoCA()==null){
            message("Campo Fecha vencimento obligatorio");
        }

    }

    private void message (String message) throws  ApiUnprocessableEntity{

        throw new ApiUnprocessableEntity(message);
    }
}
