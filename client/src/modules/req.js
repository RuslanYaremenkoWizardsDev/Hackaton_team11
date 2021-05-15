/*
    public static final String REG_EXP_FOR_LOGIN = "^[a-zA-Z](.[a-zA-Z0-9_-]*)$";
    public static final String REG_EXP_FOR_PASSWORD = "[0-9a-zA-Z]{6,}";
*/




function req(){
    const login = document.querySelector("#reg-login");
    const pass = document.querySelector('#reg-password');
    const confPass = document.querySelector('#reg-confirmPassword');
    const email = document.querySelector('#reg-email');
    const btnReq = document.querySelector('#reg-submit');

    const URL = 'http://localhost:8077/registration';



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
                window.location.pathname = "../html/index.html";
            } else if(xhr.status > 400){
                console.log(xhr.responseText);
            }
        });
    }

    

    btnReq.addEventListener("click", ()=>{

        const req = JSON.stringify({
            login: login.value,
            password: pass.value,
            email: "emailvalue",
            secretKey: "sKey"
            
        });

        console.log(req);
        registration(req);
    });

}


export default req;






