var cookieValue = "";

document.getElementById("boton_cerrar_sesion").addEventListener("click", logout);

function checkSession() {
  var cookieName = "nombreCookie";
  cookieValue = getCookie(cookieName);

  if (cookieValue !== "") {
    console.log("La sesión está iniciada");
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

window.addEventListener("load", checkSession);

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

