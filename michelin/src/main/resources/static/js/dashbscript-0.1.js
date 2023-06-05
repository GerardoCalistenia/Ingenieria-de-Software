document.getElementById("boton_cerrar_sesion").addEventListener("click", cierre);


function cierre() {
    const response = fetch('http://localhost:8081/michelin/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const jsonData = response.json();
    console.log(jsonData)
}

function actualiza() {
    document.getElementById("formulario_contrasena").style.display = "block";
    console.log("ola");
}
