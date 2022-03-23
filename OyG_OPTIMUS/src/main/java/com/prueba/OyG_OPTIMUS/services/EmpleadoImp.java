package com.prueba.OyG_OPTIMUS.services;

import com.prueba.OyG_OPTIMUS.dao.EmpleadoDAO;
import com.prueba.OyG_OPTIMUS.models.Empleado;
import com.prueba.OyG_OPTIMUS.models.dto.EmpleadoDTO;
import com.prueba.OyG_OPTIMUS.utils.Fechas;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiNotFound;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import com.prueba.OyG_OPTIMUS.validator.IEmpleadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service

public class EmpleadoImp implements IEmpleado{
    @Autowired
    private IEmpleadoValidator IempleadoValidator;
    @Autowired
    private EmpleadoDAO empleadoDAO;
    @Override
    public List<Empleado> empleadolistar() {
        return empleadoDAO.findAll();
    }

    @Override
    public void eliminarEmpleado(Long id) throws Exception {

    }

    @Override
    public void guardar(Empleado empleado) throws ApiUnprocessableEntity {


            IempleadoValidator.validator(empleado);
            empleadoDAO.save(empleado);

}

    @Override
    public void update(Empleado empleado, Long id) throws Exception{

            Optional<Empleado> empleado1 = this.empleadoDAO.findById(id);

            if (empleado1.isPresent()) {

                Empleado empleado2 = empleado1.get();

                empleado2.setNombreEmpleado(empleado.getNombreEmpleado());
                empleado2.setApellidoEmpleado(empleado.getApellidoEmpleado());
                empleado2.setCorreoEmpleado(empleado.getCorreoEmpleado());
                empleado2.setEstadoEmpleado(empleado.getEstadoEmpleado());
                empleado2.setDireccionEmpleado(empleado.getDireccionEmpleado());
                empleado2.setEdad(empleado.getEdad());
                empleado2.setArl(empleado.getArl());
                empleado2.setEps(empleado.getEps());
                empleado2.setFechaIngreso(empleado.getFechaIngreso());
                empleado2.setFechaVencimientoCA(empleado.getFechaVencimientoCA());
                empleado2.setTelefonoEmpleado(empleado.getTelefonoEmpleado());
                empleado2.setSalario(empleado.getSalario());
                empleado2.setDocumento(empleado.getDocumento());

                IempleadoValidator.validatorupdate(empleado2);

                empleadoDAO.save(empleado2);
            }else {
                Exception apiNotFound = new ApiNotFound("No existe este id en el sistema  " +id);
                throw apiNotFound;}


    }

    @Override
    public void estadoEmpleado(Long id) {
        try{
            Optional<Empleado> emp1 = empleadoDAO.findById(id);
            if(emp1.isPresent()){
                Empleado emp2 = emp1.get();
                if(emp2.getEstadoEmpleado().equals("Activo")){
                    emp2.setEstadoEmpleado("Inactivo");
                    empleadoDAO.save(emp2);
                }else if (emp2.getEstadoEmpleado().equals("Inactivo")){
                    emp2.setEstadoEmpleado("Activo");
                    empleadoDAO.save(emp2);
                }
            }

        }catch (Exception e){

        }
    }

    @Override
    public List<EmpleadoDTO> alertas() throws ParseException {
        List <EmpleadoDTO> empleadoDTOList = new ArrayList();
        Date fechaActual = new Date();
        for (Empleado alertaEmpleado : empleadoDAO.findAll()

        ) {
            if (Fechas.calculateDaysBetweenDates(alertaEmpleado.getFechaVencimientoCA(), fechaActual  )<30){

                EmpleadoDTO empleadoDTO1 = new EmpleadoDTO();

                empleadoDTO1.setNombreEmpleado(alertaEmpleado.getNombreEmpleado());
                empleadoDTO1.setFechaVencimiento(alertaEmpleado.getFechaVencimientoCA());
                empleadoDTO1.setNumeroDocumento(alertaEmpleado.getDocumento());
                empleadoDTOList.add(empleadoDTO1);
            }
        }return empleadoDTOList;
      //crear for para aguegar el listado del dto








    }



}
