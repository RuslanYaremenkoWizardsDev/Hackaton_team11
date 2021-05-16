import { putRequest, deleteRequest, URL, databaseURL } from "./requests";
import { getCookie } from "./cookie";

export const error = (str, node) => {
  // функция отрисовки ошибки
};

export const renderTable = (data, tableStat1) => {
  tableStat1.innerHTML = "";


  for (let i = 0; i < data.length; i++) {
    const newTR = document.createElement("tr");
    
    newTR.classList.add("sopthead__tr");
    const nameTH = document.createElement("td");
    nameTH.setAttribute("data", "login");
    nameTH.textContent = data[i].login;
    const winsTH = document.createElement("td");
    winsTH.setAttribute("data", "wins");
    winsTH.textContent = data[i].wins;
    const drawsTH = document.createElement("td");
    drawsTH.setAttribute("data", "draws");
    drawsTH.textContent = data[i].draws;
    const loseTH = document.createElement("td");
    loseTH.setAttribute("data", "lose");
    loseTH.textContent = data[i].lose;
    const winsCupTH = document.createElement("td");
    winsCupTH.setAttribute("data", "winsCup");
    winsCupTH.textContent = data[i].winsCup;
 
    newTR.setAttribute("id", `${data[i].id}`);
    newTR.appendChild(loginTH);
    newTR.appendChild(winsTH);
    newTR.appendChild(drawsTH);
    newTR.appendChild(loseTH);
    newTR.appendChild(winsCupTH);

    table.appendChild(newTR);
  }
  tableStat1.appendChild(table);
};



// {
//   "allTournaments": 1,
//   "active": 0,
//   "finished": 1,
//   "notStarted": 0
// }
export const renderTable = (data, tableStat2) => {
  tableStat2.innerHTML = "";


  for (let i = 0; i < data.length; i++) {
    const newTR = document.createElement("tr");
    
    newTR.classList.add("sopthead__tr");
    const allTournamentsTH = document.createElement("td");
    allTournamentsTH.setAttribute("data", "allTournaments");
    allTournamentsTH.textContent = data[i].allTournaments;
    const activeTH = document.createElement("td");
    activeTH.setAttribute("data", "active");
    activeTH.textContent = data[i].active;
    const finishedTH = document.createElement("td");
    finishedTH.setAttribute("data", "finished");
    finishedTH.textContent = data[i].finished;
    const notStartedTH = document.createElement("td");
    notStartedTH.setAttribute("data", "notStarted");
    notStartedTH.textContent = data[i].notStarted;


    //???СМЕНА ДАННЫХ+УДАЛЕНИЕ СТРОКИ  
    newTR.setAttribute("id", `${data[i].id}`);
    newTR.appendChild(loginTH);
    newTR.appendChild(winsTH);
    newTR.appendChild(drawsTH);
    newTR.appendChild(loseTH);
    newTR.appendChild(winsCupTH);

    table.appendChild(newTR);
  }
  tableStat1.appendChild(table);
};