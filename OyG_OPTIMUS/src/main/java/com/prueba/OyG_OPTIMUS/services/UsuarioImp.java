package com.prueba.OyG_OPTIMUS.services;

import com.prueba.OyG_OPTIMUS.dao.UsuarioDao;
import com.prueba.OyG_OPTIMUS.models.Usuario;
import com.prueba.OyG_OPTIMUS.models.dto.UsuarioActualizarDTO;
import com.prueba.OyG_OPTIMUS.models.dto.UsuarioDTO;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiNotFound;
import com.prueba.OyG_OPTIMUS.utils.exceptions.ApiUnprocessableEntity;
import com.prueba.OyG_OPTIMUS.validator.IUsuarioValidator;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioImp implements IUsuario{
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    IUsuarioValidator iUsuarioValidator;

    @Autowired
    private UsuarioDao usuarioDao;
    @Override
    public List<UsuarioDTO> usuariosListar() {
        return usuarioDao.findAll()
                .stream()
                .map(usuario -> UsuarioDTO.builder()
                                .id(usuario.getId())
                                .usuario(usuario.getUsuario())
                                .rol(usuario.getRol())
                                .estadoUsuario(usuario.getEstadoUsuario())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarUsuario(Integer id) throws Exception {
        Optional<Usuario> usuario = usuarioDao.findById(id);
        if(usuario.isPresent()){
            usuarioDao.delete(usuario.get());
        }else{
            throw new Exception("No se ha encontrado el usuario");
        }
    }

    @Override
    public void guardar(Usuario usuario) throws ApiUnprocessableEntity {
        iUsuarioValidator.validator(usuario);
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.save(usuario);
    }

    @Override
    public void actualizarUsuario(UsuarioActualizarDTO usuarioActualizarDTO, Integer id) throws Exception {

        Optional<Usuario> usu = usuarioDao.findById(id);

        if(usu.isPresent()) {
            Usuario user = usu.get();
            user.setRol(usuarioActualizarDTO.getRol());
            user.setUsuario(usuarioActualizarDTO.getUsuario());
            user.setPassword(usuarioActualizarDTO.getPassword());

            iUsuarioValidator.validatorActualizar(user);

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1,1024,1,user.getPassword());


            user.setPassword(hash);

            usuarioDao.save(user);
        }else{
            Exception ApiNotFound = new ApiNotFound("No existe un Usuario con el id "+id);
            throw ApiNotFound;
        }

    }

    @Override
    public void cambiarEstadoUsuario(Integer id) throws Exception {
        Optional<Usuario> usu = usuarioDao.findById(id);
        if(usu.isPresent()){
            Usuario user = usu.get();
            if(user.getEstadoUsuario().equals("Activo")){
                user.setEstadoUsuario("Inactivo");
                usuarioDao.save(user);
            }else if(user.getEstadoUsuario().equals("Inactivo")){
                user.setEstadoUsuario("Activo");
                usuarioDao.save(user);
            }
        }else{
            Exception ApiNotFound = new ApiNotFound("No existe un usuario con el id "+ id);
            throw ApiNotFound;
        }
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE usuario= :usuario";
        List<Usuario> lista = entityManager.createQuery(query)
                                    .setParameter("usuario",usuario.getUsuario())
                                    .getResultList();

        if (lista.isEmpty()) return null;

        String passwordHased = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHased, usuario.getPassword())){
            return lista.get(0);
        };
        return null;
    }
}
