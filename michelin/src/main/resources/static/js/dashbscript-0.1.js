document.getElementsByClassName("btn btn-outline-secondary shadow-sm d-sm d-block me-5").addEventListener("click", cierre);
document.getElementsByClassName("btn btn-outline-secondary shadow-sm d-sm d-block").addEventListener("click", actualiza);

var cerrar_sesion = document.querySelector(".btn btn-outline-secondary shadow-sm d-sm d-block me-5");
var cambiar_contra = document.querySelector(".btn btn-outline-secondary shadow-sm d-sm d-block");

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
    const response = fetch('http://localhost:8081/michelin/update/password', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const jsonData = response.json();
    console.log(jsonData)
}
