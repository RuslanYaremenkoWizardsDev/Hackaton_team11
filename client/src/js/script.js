import '../scss/style.scss';
import registr  from '../modules/req';
import auth from '../modules/auth';
 
document.addEventListener('DOMContentLoaded', ()=>{

if(window.location.pathname === "/index.html"){
    auth();
}else if(window.location.pathname === "/regpage.html"){
    registr();
}

});