import { putRequest, deleteRequest, URL, databaseURL } from "./requests";
import { getCookie } from "./cookie";

export const error = (str, node) => {
  // функция отрисовки ошибки
};

export const renderTable = (data, tableDiv) => {
  tableDiv.innerHTML = "";
//   const table = document.createElement("table");
//   const nameTH = document.createElement("th");
//   nameTH.textContent = "Name";
//   const modeTH = document.createElement("th");
//   modeTH.textContent = "Mode";
//   const placeTH = document.createElement("th");
//   placeTH.textContent = "Place";
//   const startDateTH = document.createElement("th");
//   startDateTH.textContent = "Start date";
//   const lastRegDateTH = document.createElement("th");
//   lastRegDateTH.textContent = "Last registration date";
//   const levelTH = document.createElement("th");
//   levelTH.textContent = "Level";
//   const participantsTH = document.createElement("th");
//   participantsTH.textContent = "Participants";
//   const scenarioTH = document.createElement("th");
//   scenarioTH.textContent = "Scenario";
//   const actionsTH = document.createElement("th");
//   actionsTH.textContent = "Actions";
//   table.appendChild(nameTH);
//   table.appendChild(modeTH);
//   table.appendChild(placeTH);
//   table.appendChild(statusTH);
//   table.appendChild(startDateTH);
//   table.appendChild(lastRegDateTH);
//   table.appendChild(levelTH);
//   table.appendChild(participantsTH);
//   table.appendChild(scenarioTH);
//   table.appendChild(actionsTH);
// let data=[{
//   "id": 28,
//   "status": "IN_PROGRESS",
//   "name": "fffff",
//   "tournamentDescription": "qwerty1234",
//   "modeTournament": "CUP",
//   "place": "LfLFF",
//   "dateStartTournament": 16211059339239,
//   "dateLastRegistrationOnTournament": 123123123123,
//   "level": "MIDDLE",
//   "numberOfPlayer": 128,
//   "scenatioOfTournament": "ONE_MATCH"
// }]

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
/*   table.addEventListener("dblclick", tableDBLClick); */
};

/* export const tableDBLClick = (e) => {
  if (e.target.parentElement.classList.contains("active-tr")) {
    const input = document.createElement("input");
    input.setAttribute("type", "text");
    input.classList.add("table__input");
    const oldText = e.target.innerHTML;
    e.target.innerHTML = "";
    e.target.appendChild(input);
    input.focus();
    input.value = oldText;
    input.addEventListener("blur", (event) => {
      const token = getCookie("token");
      const dbSelect = document.querySelector("#db");
      const options = {
        method: "PUT",
        headers: { "Content-Type": "application/json", Authorization: token },
        body: JSON.stringify({
          changeParam: e.target.getAttribute("data"),
          newData: input.value,
        }),
      };
      putRequest(
        URL +
          `databases/${dbSelect.value}?id=${localStorage.getItem(
            "id"
          )}&tableID=${e.target.parentElement.id}`,
        options
      ).then((data) => {
        if (data.message !== "done") return (e.target.innerHTML = oldText);
        e.target.innerHTML = input.value;
        e.target.parentElement.classList.remove("active-tr");
        const tdButton = document.querySelector(".active-button");
        e.target.parentElement.removeChild(tdButton);
      });
    });
  }
  const nashiTR = document.querySelectorAll(".table__cup");
  for (let i = 0; i < nashiTR.length; i++) {
    const trButton = document.querySelector(".active-button");
    if (nashiTR[i].classList.contains("active-tr")) {
      nashiTR[i].classList.remove("active-tr");
    }
    if (nashiTR[i].contains(trButton)) {
      nashiTR[i].removeChild(trButton);
    }
  }
  if (e.target.parentElement.classList.contains("table__cup")) {
    const trButton = document.querySelectorAll(".active-button");
    e.target.parentElement.classList.add("active-tr");
    for (let i = 0; i < trButton.length; i++) {
      if (e.target.parentElement.contains(trButton[i])) return;
    }
    const newButton = document.createElement("button");
    newButton.classList.add("active-button");
    newButton.textContent = "x";
    newButton.addEventListener("click", deleteTableUnit);
    e.target.parentElement.appendChild(newButton);
  }
}; */