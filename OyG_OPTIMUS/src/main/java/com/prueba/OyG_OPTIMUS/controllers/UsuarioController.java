package com.prueba.OyG_OPTIMUS.controllers;

import com.prueba.OyG_OPTIMUS.models.Usuario;
import com.prueba.OyG_OPTIMUS.models.dto.UsuarioActualizarDTO;
import com.prueba.OyG_OPTIMUS.models.dto.UsuarioDTO;
import com.prueba.OyG_OPTIMUS.services.IUsuario;
import com.prueba.OyG_OPTIMUS.utils.JWTUtil;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiNotFound;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import com.prueba.OyG_OPTIMUS.validator.IUsuarioValidator;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    //Anotación para el método guardar es @PostMapping
    //Solo puedo anotar con Autowired cuando la clase o la clase padre tienen anotaciones de spring
    @Autowired
    private IUsuario iUsuario;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getUsuarios(){
        try {
            List<UsuarioDTO> users = iUsuario.usuariosListar();
            if (users == null || users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @PostMapping
    public void guardarUsuario(@RequestBody Usuario usuario) throws ApiUnprocessableEntity{iUsuario.guardar(usuario);}

    @PutMapping("/{id}/actualizarusuario")
    public void actualizarUsuario(@RequestBody UsuarioActualizarDTO usuarioActualizarDTO, @PathVariable Integer id) throws Exception {iUsuario.actualizarUsuario(usuarioActualizarDTO,id);}

    @PutMapping("/{id}/cambiarestadousuario")
    public void cambiarEstadoUsuario(@PathVariable Integer id) throws Exception {iUsuario.cambiarEstadoUsuario(id); }
}
