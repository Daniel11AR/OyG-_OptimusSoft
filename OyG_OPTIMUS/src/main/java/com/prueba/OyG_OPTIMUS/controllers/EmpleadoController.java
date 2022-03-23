package com.prueba.OyG_OPTIMUS.controllers;

import com.prueba.OyG_OPTIMUS.models.Empleado;
import com.prueba.OyG_OPTIMUS.models.dto.EmpleadoDTO;
import com.prueba.OyG_OPTIMUS.services.IEmpleado;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import com.prueba.OyG_OPTIMUS.validator.IEmpleadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController


@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleado iEmpleado;

    @Autowired
    private IEmpleadoValidator IempleadoValidator;

    @GetMapping("/allempleado")
    public ResponseEntity<List<Empleado>> getEmpleados() {

        try {
            List<Empleado> emp = iEmpleado.empleadolistar();
            if (emp == null || emp.isEmpty()) {
                return new ResponseEntity<>(emp, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(emp, HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PostMapping
    public void guardarEmpleado(@RequestBody Empleado empleado) throws ApiUnprocessableEntity {

        iEmpleado.guardar(empleado);
    }

    @PutMapping("/{id}/update")
    public void updateUser(@RequestBody Empleado empleado, @PathVariable Long id) throws Exception {

        iEmpleado.update(empleado, id);


    }

    @PutMapping("/{id}/estadoEmpleado")
    public void estado(@PathVariable Long id) {

        iEmpleado.estadoEmpleado(id);
    }

    @GetMapping("/alerta")

    public ResponseEntity<List<EmpleadoDTO>> getAlerta() {
        try {
            List<EmpleadoDTO> empdto = iEmpleado.alertas();
            if (empdto == null || empdto.isEmpty()) {
                return new ResponseEntity<>(empdto, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(empdto, HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
