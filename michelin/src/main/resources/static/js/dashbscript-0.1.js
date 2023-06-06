window.addEventListener("load", checkSession);

var cookieValue = "";

document.getElementById("boton_cerrar_sesion").addEventListener("click", logout);
document.getElementById("boton_guardar_contrasena").addEventListener("click", updatePass)
document.getElementById("boton_guardar_contrasena").addEventListener("keyup", enableDisable)

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

function enableDisable() {
    var btnGuardarContrasena = document.getElementById("boton_guardar_contrasena");
    const nueva_contrasena = document.getElementById("nueva_contrasena").value();
    const confirmar_contrasena = document.getElementById("confirmar_contrasena").value();
    
    if (nueva_contrasena != confirmar_contrasena || nueva_contrasena == "") {
        btnGuardarContrasena.disabled = true;
    } else {
        btnGuardarContrasena.disabled = false;
    }
}

function updatePass() {
    event.preventDefault();
    const nueva_contrasena = document.getElementById("nueva_contrasena").value();
    const confirmar_contrasena = document.getElementById("confirmar_contrasena").value();

    const update = {
        nueva_contrasena: nueva_contrasena,
        confirmar_contrasena: confirmar_contrasena
    };

    fetch('http://localhost:8081/michelin/updatePass', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(update)
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        if (data.message == "actualizacion exitosa") {
            window.location.href = '/michelin/home';
        }
    })
    .catch(error => {
        console.error('Error:', error)
    });
};
