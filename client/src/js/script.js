import '../scss/style.scss';
import registr  from '../modules/req';
import auth from '../modules/auth';
import clearAll from '../modules/clearAll';
import settingsPopup from '../modules/settings';
import sortData from '../modules/header-sort';
 
document.addEventListener('DOMContentLoaded', ()=>{

if(window.location.pathname === "/index.html"){
    auth();
}else if(window.location.pathname === "/regpage.html"){
    registr();
}else if(window.location.pathname === "/admin.html"){
    clearAll();
    settingsPopup();  
    sortData();  
}else if(window.location.pathname === "/user.html"){
    settingsPopup();   
    sortData(); 
}

});