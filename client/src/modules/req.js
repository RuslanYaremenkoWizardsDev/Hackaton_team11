import postRequest from '../modules/request';

function registr(){

    const URL = 'http://localhost:8077/registration';

    const login = document.querySelector("#reg-login");
    const pass = document.querySelector('#reg-password');
    const confPass = document.querySelector('#reg-confirmPassword');
    const email = document.querySelector('#reg-email');
    const sKey = document.querySelector('#reg-secretKey');
    const btnReq = document.querySelector('#reg-submit');
    
    function getValidEntry(){
        if(login.value === "" || pass.value === "" || confPass.value === "" || email === ""){
            console.log("Fill in all the fields");
            return true;
        }
    
        if(pass.value != confPass.value){
            console.log("repeated the password unsuccessfully");
            return true;
        }
    
    }
    
    btnReq.addEventListener('click', ()=>{
    
        if(getValidEntry()){
            
        }else{
            const req = JSON.stringify({
                login: login.value,
                password: pass.value,
                email: email.value,
                secretKey: sKey.value
            });
        
            postRequest(URL, req).then(()=>{
                document.location.href = "/index.html";
            });
        }
    
       
    
    });

}

export default registr;
    



    



    







/*function req(){
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


export default req;*/






