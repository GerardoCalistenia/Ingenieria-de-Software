window.addEventListener("load", checkSession);
document.getElementById("boton_guardar_contrasena").addEventListener("click", updatePass);

var cookieValue = "";

document.getElementById("boton_cerrar_sesion").addEventListener("click", logout);
  
function checkSession() {
  var cookieName = "nombreCookie";
  cookieValue = getCookie(cookieName);

  if (cookieValue !== "") {
    console.log("La sesión está iniciada");
    document.body.style.display = "block";
  } else {
    console.log("La sesión no está iniciada");
    window.location.href = '/michelin/login';

  }
}

function getCookie(cookieName) {
  var name = cookieName + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var cookieArray = decodedCookie.split(";");

  for (var i = 0; i < cookieArray.length; i++) {
    var cookie = cookieArray[i];
    while (cookie.charAt(0) == " ") {
      cookie = cookie.substring(1);
    }
    if (cookie.indexOf(name) == 0) {
      return cookie.substring(name.length, cookie.length);
    }
  }

  return "";
}

function logout() {
    
    fetch('http://localhost:8081/michelin/logout', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
		if(data.message == "cierre de sesion exitoso"){
			window.location.href = '/michelin/login';
		}
    })
    .catch(error => {
        console.error('Error:', error);
    });

}

var correo = "";
function updatePass() {
  
  correo = getCookie("nombreCookie"); // Obtener el correo de la cookie
  const newPass1 = document.getElementById("nueva_contrasena").value;
  const newPass2 = document.getElementById("confirmar_contrasena").value;

  if (newPass1 !== newPass2) {
    console.log("Las contraseñas no coinciden");
    return;
  }

  const usuario = {
    new_password: newPass2,
    mail: correo
  };



  fetch(`http://localhost:8081/michelin/update/password/${correo}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(usuario)
  })
  .then(response => response.json())
  .then(data => {
    console.log(data);
    // Realizar las acciones necesarias con la respuesta del servidor
  })
  .catch(error => {
    console.error('Error:', error);
  });
}

