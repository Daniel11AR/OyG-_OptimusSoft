package com.prueba.OyG_OPTIMUS.validator;

import com.prueba.OyG_OPTIMUS.dao.UsuarioDao;
import com.prueba.OyG_OPTIMUS.models.Usuario;
import com.prueba.OyG_OPTIMUS.models.dto.UsuarioActualizarDTO;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidatorImp implements IUsuarioValidator{

    @Autowired
    private UsuarioDao usuarioDao;
    @Override
    public void validator(Usuario usuario) throws ApiUnprocessableEntity {
        //Con la variable u validamos que el usuario contenga letras, con p que la contraseña tenga letras y con r que Rol contenga letras
        Boolean u = false;
        Boolean p = false;
        Boolean r = true;
        if(usuario.getUsuario() == null || usuario.getUsuario().isEmpty()){
            message("El usuario no puede estar vacío.");
        }
        if(usuario.getPassword() == null || usuario.getPassword().isEmpty()){
            message("La contraseña no puede estar vacía.");
        }
        if(usuario.getRol() == null || usuario.getRol().isEmpty()){
            message("El rol del usuario no puede estar vacío.");
        }
        if(usuario.getUsuario().length() < 3){
            message("El usuario debe tener por lo menos 4 caracteres.");
        }
        if(usuario.getPassword().length() < 5){
            message("La contraseña es demasiado corta. Ingrese una más larga(mínimo 5 caracteres).");
        }
        if(usuario.getRol().length() < 3){
            message("El rol es demasiado corto.");
        }

        //Validamos que el Usuario contenga por lo menos una letra
        for(int i=0;i<usuario.getUsuario().length(); i++){
            char letra = usuario.getUsuario().charAt(i);
            if(letra>='a' && letra<='z' || letra>='A' && letra<='Z'){
                u=true;
            }
        }
        if(u==false){
            message("El usuario debe contener por lo menos una letra.");
        }

        //Validamos que la contraseña contenga por lo menos una letra
        for(int i=0;i<usuario.getPassword().length(); i++){
            char letra = usuario.getPassword().charAt(i);
            if(letra>='a' && letra<='z' || letra>='A' && letra<='Z'){
                p=true;
            }
        }
        if(p==false){
            message("La contraseña debe contener por lo menos una letra para más seguridad.");
        }

        //Validamos que el rol contenga solamente letras
        for(int i=0;i<usuario.getRol().length(); i++){
            char letra = usuario.getRol().charAt(i);
            if(!(letra>='a' && letra<='z' || letra>='A' && letra<='Z')){
                r=false;
            }
        }
        if(r==false){
            message("Es obligatorio que el Rol del Usuario solo tenga letras (No debe contener espacios).");
        }

        for(Usuario usu: usuarioDao.findAll()){
            if(usu.getUsuario().equals(usuario.getUsuario())) message("Este nombre de Usuario ya está en uso, intente con uno diferente.");
        }

    }

    @Override
    public void validatorActualizar(Usuario usuario) throws ApiUnprocessableEntity {
        //Con la variable u validamos que el usuario contenga letras, con p que la contraseña tenga letras y con r que Rol contenga letras
        Boolean u = false;
        Boolean p = false;
        Boolean r = true;
        if(usuario.getUsuario() == null || usuario.getUsuario().isEmpty()){
            message("El usuario no puede estar vacío.");
        }
        if(usuario.getPassword() == null || usuario.getPassword().isEmpty()){
            message("La contraseña no puede estar vacía.");
        }
        if(usuario.getRol() == null || usuario.getRol().isEmpty()){
            message("El rol del usuario no puede estar vacío.");
        }
        if(usuario.getUsuario().length() < 3){
            message("El usuario debe tener por lo menos 4 caracteres.");
        }
        if(usuario.getPassword().length() < 5){
            message("La contraseña es demasiado corta. Ingrese una más larga(mínimo 5 caracteres).");
        }
        if(usuario.getRol().length() < 3){
            message("El rol es demasiado corto.");
        }

        //Validamos que el Usuario contenga por lo menos una letra
        for(int i=0;i<usuario.getUsuario().length(); i++){
            char letra = usuario.getUsuario().charAt(i);
            if(letra>='a' && letra<='z' || letra>='A' && letra<='Z'){
                u=true;
            }
        }
        if(u==false){
            message("El usuario debe contener por lo menos una letra.");
        }

        //Validamos que la contraseña contenga por lo menos una letra
        for(int i=0;i<usuario.getPassword().length(); i++){
            char letra = usuario.getPassword().charAt(i);
            if(letra>='a' && letra<='z' || letra>='A' && letra<='Z'){
                p=true;
            }
        }
        if(p==false){
            message("La contraseña debe contener por lo menos una letra para más seguridad.");
        }

        //Validamos que el rol contenga solamente letras
        for(int i=0;i<usuario.getRol().length(); i++){
            char letra = usuario.getRol().charAt(i);
            if(!(letra>='a' && letra<='z' || letra>='A' && letra<='Z')){
                r=false;
            }
        }
        if(r==false){
            message("Es obligatorio que el Rol del Usuario solo tenga letras (No debe contener espacios).");
        }

        for(Usuario usu: usuarioDao.findAll()){
            if(!(usuario.getId().equals(usu.getId()))){
                if(usu.getUsuario().equals(usuario.getUsuario()))
                    message("Este nombre de Usuario ya está en uso, intente con uno diferente.");
            }
        }
    }


    private void message(String message) throws ApiUnprocessableEntity{
        throw new ApiUnprocessableEntity(message);
    }
}
