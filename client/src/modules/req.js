


function req(){
    const btn = document.querySelector('#btn');

    const URL = 'http://localhost:8077/registration';
    const login = "Login";
    const pass = "password";
    const sKey = "secret";
    const email = "fff@fff.com";

    const req = JSON.stringify({
        login: login,
        password: pass,
        email: email,
        secretKey: sKey
        
    });
    console.log(req);



    function registration(arg){
        var xhr = new XMLHttpRequest();
        console.log(arg)
        xhr.open("POST", URL);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(arg);
        xhr.addEventListener("load", function(){
            if(xhr.status === 200){
                console.log(xhr.responseText);
            // window.location.pathname = "index.html"
            } else if(xhr.status > 400){
                console.log(xhr.responseText);
            }
        });
    }

    

    btn.addEventListener("click", ()=>{
        registration(req);
    });

}


export default req;






