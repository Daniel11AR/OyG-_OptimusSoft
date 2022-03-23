// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
    actualizarUsuarioTop();
  $('#usuarios').DataTable();
});

function actualizarUsuarioTop(){
    document.getElementById('txt-usuario-top').outerHTML = localStorage.usuario;
}

async function cargarUsuarios(){

      const request = await fetch('/usuario/allusuarios', {
        method: 'GET',
        headers: getHeaders()
      });
      const usuarios = await request.json();

      let listadoHtml = '';
      for(let usuario of usuarios){
        let usuarioHtml = '<tr><td>'+usuario.id+'</td><td>'+usuario.usuario+'</td><td>'+usuario.password+'</td><td>'+usuario.rol+'</td><td>'+usuario.estadoUsuario+'</td><td><a href="#" class="btn btn-info btn-icon-split"><span class="icon text-white-50"><i class="fas fa-info-circle"></i></span><span class="text">Cambiar estado</span></a></td></tr>';
        listadoHtml += usuarioHtml;
      }

      document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}

//Para cuando cree otro método debo agregarle en el header la siguiente información
//   'Authorization': localStorage.token

function getHeaders(){
    return  {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}