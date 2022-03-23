$(document).ready(function() {

});

async function iniciarSesion(){
    let datos = {};
    datos.usuario = document.getElementById("txtUsuario").value;
    datos.password = document.getElementById("txtPassword").value;




      const request = await fetch('/usuario/login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      const respuesta = await request.text();
      if(respuesta!='FAIL'){
        localStorage.token = respuesta;
        localStorage.usuario = datos.usuario;
        window.location.href='usuarios.html';
      }else{
        alert("Las credenciales son incorrectas. Intente nuevamente.");
      }

}