import postRequest from '../modules/request';
import jwt_decode from "jwt-decode";

function auth(){

    const login = document.getElementById('login');
    const pass = document.getElementById('password');
    const btnIn = document.querySelector("#submit");

    const URL = 'http://localhost:8077/authorization';

    function getValidEntry(){
        if(login.value === '' || pass.value === ""){
            console.log("Fill in all the fields");
            return true;
        }
    
    }

    btnIn.addEventListener("click", ()=>{
        if(getValidEntry()){
            alert("No");
        }else{
            const req = JSON.stringify({
                login: login.value,
                password: pass.value,
            });
        
            postRequest(URL, req).then((res)=>{
                //console.log(res.headers)
                alert(res);
               
                document.location.href = "/admin.html";
            });
        }
    });



}

export default auth;



    


   


    
   

