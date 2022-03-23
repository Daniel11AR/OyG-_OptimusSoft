package com.prueba.OyG_OPTIMUS.services;

import com.prueba.OyG_OPTIMUS.models.Usuario;
import com.prueba.OyG_OPTIMUS.models.dto.UsuarioActualizarDTO;
import com.prueba.OyG_OPTIMUS.models.dto.UsuarioDTO;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiNotFound;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;

import java.util.List;

public interface IUsuario {
    List<UsuarioDTO> usuariosListar();
    void eliminarUsuario(Integer id) throws Exception;
    void guardar(Usuario usuario) throws ApiUnprocessableEntity;
    void actualizarUsuario(UsuarioActualizarDTO usuarioActualizarDTO, Integer id) throws Exception;
    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
    void cambiarEstadoUsuario(Integer id) throws Exception;
}
