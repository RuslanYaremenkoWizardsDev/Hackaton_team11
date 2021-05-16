import { putRequest, deleteRequest, URL, databaseURL } from "./requests";
import { getCookie } from "./cookie";

export const error = (str, node) => {
  // функция отрисовки ошибки
};

export const renderTable = (data, tableDiv) => {
  tableDiv.innerHTML = "";


  for (let i = 0; i < data.length; i++) {
    const newTR = document.createElement("tr");
    if(data[i].tournamentDescription){
      newTR.setAttribute('title', data[i].tournamentDescription)
    }
    newTR.classList.add("table_cup");
    const nameTH = document.createElement("td");
    nameTH.setAttribute("data", "name");
    nameTH.textContent = data[i].name;
    const modeTH = document.createElement("td");
    modeTH.setAttribute("data", "modeTournament");
    modeTH.textContent = data[i].modeTournament;
    const placeTH = document.createElement("td");
    placeTH.setAttribute("data", "place");
    placeTH.textContent = data[i].place === "NULL" ? "" : data[i].place;
    const startDateTH = document.createElement("td");
    startDateTH.setAttribute("data", "dateStartTournament");
    startDateTH.textContent = data[i].dateStartTournament;
    const lastRegDateTH = document.createElement("td");
    lastRegDateTH.setAttribute("data", "dateLastRegistrationOnTournament");
    lastRegDateTH.textContent = data[i].dateLastRegistrationOnTournament;
    const statusTH = document.createElement("td");
    statusTH.setAttribute("data", "status");
    statusTH.textContent = data[i].status === "In progress";
    const levelTH = document.createElement("td");
    levelTH.setAttribute("data", "level");
    levelTH.textContent = data[i].level;
    const participantsTH = document.createElement("td");
    participantsTH.setAttribute("data", "numberOfPlayer");
    participantsTH.textContent = data[i].numberOfPlayer;
    const scenarioTH = document.createElement("td");
    scenarioTH.setAttribute("data", "scenatioOfTournament");
    scenarioTH.textContent = data[i].scenatioOfTournament;
    // const actionsTH = document.createElement("td");
    // actionsTH.setAttribute("data", "ACTIONS");
    // actionsTH.textContent = data[i].ACTIONS;



    //???СМЕНА ДАННЫХ+УДАЛЕНИЕ СТРОКИ  
    newTR.setAttribute("id", `${data[i].id}`);
    newTR.appendChild(nameTH);
    newTR.appendChild(modeTH);
    newTR.appendChild(placeTH);
    newTR.appendChild(startDateTH);
    newTR.appendChild(lastRegDateTH);
    newTR.appendChild(descriptionTH);
    newTR.appendChild(levelTH);
    newTR.appendChild(participantsTH);
    newTR.appendChild(scenarioTH);
    newTR.appendChild(actionsTH);

    table.appendChild(newTR);
  }
  tableDiv.appendChild(table);
  table.addEventListener("dblclick", tableDBLClick);
};