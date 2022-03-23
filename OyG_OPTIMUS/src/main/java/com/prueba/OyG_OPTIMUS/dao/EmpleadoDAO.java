package com.prueba.OyG_OPTIMUS.dao;

import com.prueba.OyG_OPTIMUS.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoDAO extends JpaRepository <Empleado,Long> {
}
