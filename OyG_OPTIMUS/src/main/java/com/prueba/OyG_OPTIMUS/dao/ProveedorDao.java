package com.prueba.OyG_OPTIMUS.dao;

import com.prueba.OyG_OPTIMUS.models.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProveedorDao extends JpaRepository<Proveedor, Long> {
}