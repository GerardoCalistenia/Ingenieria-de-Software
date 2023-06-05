document.getElementById("boton_reingresar").addEventListener("click", relogin);

function relogin() {
    const response = fetch('http://localhost:8081/michelin/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    const jsonData = response.json();
    console.log(jsonData)
}