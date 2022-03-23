package com.prueba.OyG_OPTIMUS.controllers;

import com.prueba.OyG_OPTIMUS.models.Usuario;
import com.prueba.OyG_OPTIMUS.services.IUsuario;
import com.prueba.OyG_OPTIMUS.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private IUsuario iUsuario;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/usuario/login")
    public String login(@RequestBody Usuario usuario){

        Usuario usuarioLogueado = iUsuario.obtenerUsuarioPorCredenciales(usuario);

        if(usuarioLogueado!=null){

            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getUsuario());

            return tokenJwt;
        }
        return "FAIL";
    }
}
