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
 
document.addEventListener('DOMContentLoaded', ()=>{


    if(window.location.pathname === "/index.html"){
        auth();
        viewPassword();
    }else if(window.location.pathname === "/regpage.html"){
        registr();
        viewPassword();
    }else if(window.location.pathname === "/admin.html"){
        clearAll();
        settingsPopup();  
        sortData();  
        declineTournament();
        switchToStat();
        viewInvitations();
        creatingNewCup();
        viewPassword();
        // const tableBody = document.querySelector('#tbody'):
    //get renderTableDiv(data, tableBody)

    }else if(window.location.pathname === "/user.html"){
        settingsPopup();   
        sortData(); 
        declineTournamentInvite();
        switchToStatUser();
        viewPassword();
    }

});