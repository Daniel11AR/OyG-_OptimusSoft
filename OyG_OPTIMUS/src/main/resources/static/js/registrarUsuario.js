$(document).ready(function() {

});

async function registrarUsuario(){
    let datos = {};
    datos.usuario = document.getElementById("txtUsuario").value;
    datos.password = document.getElementById("txtPassword").value;
    datos.rol = document.getElementById("txtRol").value;

    let repetirPassword = document.getElementById("txtRepeatPassword").value;
    if(repetirPassword != datos.password){
        alert('Las contraseñas no coiciden.');
        return;

    }


  const request = await fetch('/usuario/guardarUsuario', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
}