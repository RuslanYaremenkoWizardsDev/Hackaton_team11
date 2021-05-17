import '../scss/style.scss';
import registr  from '../modules/req';
import auth from '../modules/auth';
import clearAll from '../modules/clearAll';
import settingsPopup from '../modules/settings';
import sortData from '../modules/header-sort';
import declineTournament from '../modules/tournamentEntry';
import declineTournamentInvite from '../modules/tournamentInvite';
import switchToStat from '../modules/statistics';
import switchToStatUser from '../modules/statuser';
import viewInvitations from '../modules/viewInvitations';
import creatingNewCup from '../modules/createNewCup';
import viewPassword from '../modules/viewPassword';
import handleTheme from '../modules/handleTheme';
import user from '../modules/user';
 
document.addEventListener('DOMContentLoaded', ()=>{


    if(window.location.pathname === "/index.html"){
        auth();
        viewPassword.viewPasswordAuth();
    }else if(window.location.pathname === "/regpage.html"){
        registr();
        viewPassword.viewPasswordReg();
    }else if(window.location.pathname === "/admin.html"){
        clearAll();
        settingsPopup();  
        sortData();  
        declineTournament();
        switchToStat();
        viewInvitations();
        creatingNewCup();
        viewPassword.viewPasswordAdmin();
        handleTheme.handleThemeAdmin();
        // const tableBody = document.querySelector('#tbody'):
    //get renderTableDiv(data, tableBody)

    }else if(window.location.pathname === "/user.html"){
        settingsPopup();   
        sortData(); 
        declineTournamentInvite();
        switchToStatUser();
        viewPassword.viewPasswordUser();
        handleTheme.handleThemeUser();
        user();
    }else if(window.location.pathname === "/guest.html"){
        handleTheme.handleThemeGuest();
    }

});