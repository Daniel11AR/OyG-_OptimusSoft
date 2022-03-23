package com.prueba.OyG_OPTIMUS.dao;

import com.prueba.OyG_OPTIMUS.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioDao extends JpaRepository<Usuario,Integer> {


}
