import { putRequest, deleteRequest, URL, databaseURL } from "./requests";
import { getCookie } from "./cookie";

export const error = (str, node) => {
  // функция отрисовки ошибки
};

export const renderTable = (data, tableDiv) => {
  tableDiv.innerHTML = "";
  const table = document.createElement("table");
  const nameTH = document.createElement("th");
  nameTH.textContent = "Name";
  const modeTH = document.createElement("th");
  modeTH.textContent = "Mode";
  const placeTH = document.createElement("th");
  placeTH.textContent = "Place";
  const startDateTH = document.createElement("th");
  startDateTH.textContent = "Start date";
  const lastRegDateTH = document.createElement("th");
  lastRegDateTH.textContent = "Last reg date";
  const stateTH = document.createElement("th");
  stateTH.textContent = "State";
  const levelTH = document.createElement("th");
  levelTH.textContent = "Level";
  const participantsTH = document.createElement("th");
  participantsTH.textContent = "Participants";
  const scenarioTH = document.createElement("th");
  scenarioTH.textContent = "Scenario";
  const actionsTH = document.createElement("th");
  actionsTH.textContent = "Actions";
  table.appendChild(nameTH);
  table.appendChild(modeTH);
  table.appendChild(placeTH);
  table.appendChild(startDateTH);
  table.appendChild(lastRegDateTH);
  table.appendChild(stateTH);
  table.appendChild(levelTH);
  table.appendChild(participantsTH);
  table.appendChild(scenarioTH);
  table.appendChild(actionsTH);

  for (let i = 0; i < data.length; i++) {
    const newTR = document.createElement("tr");
    newTR.classList.add("table__kek");
    const nameTH = document.createElement("td");
    nameTH.setAttribute("data", "NAME");
    nameTH.textContent = data[i].NAME;
    const modeTH = document.createElement("td");
    modeTH.setAttribute("data", "MODE");
    modeTH.textContent = data[i].MODE;
    const ageTD = document.createElement("td");
    ageTD.setAttribute("data", "AGE");
    ageTD.textContent = data[i].AGE;
    const emailTD = document.createElement("td");
    emailTD.setAttribute("data", "EMAIL");
    emailTD.textContent = data[i].EMAIL;
    const cityTD = document.createElement("td");
    cityTD.setAttribute("data", "CITY");
    cityTD.textContent = data[i].CITY === "NULL" ? "" : data[i].CITY;
    const phoneNumberTD = document.createElement("td");
    phoneNumberTD.setAttribute("data", "PHONE_NUMBER");
    phoneNumberTD.textContent =
      data[i].PHONE_NUMBER === "NULL" ? "" : data[i].PHONE_NUMBER;
    const companyTD = document.createElement("td");
    companyTD.setAttribute("data", "COMPANY_NAME");
    companyTD.textContent =
      data[i].COMPANY_NAME === "NULL" ? "" : data[i].COMPANY_NAME;
    newTR.setAttribute("id", `${data[i].ID || data[i]._id || data[i].id}`);
    newTR.appendChild(firstNameTD);
    newTR.appendChild(lastNameTD);
    newTR.appendChild(ageTD);
    newTR.appendChild(emailTD);
    newTR.appendChild(cityTD);
    newTR.appendChild(phoneNumberTD);
    newTR.appendChild(companyTD);
    table.appendChild(newTR);
  }
  tableDiv.appendChild(table);
  table.addEventListener("dblclick", tableDBLClick);
};

export const tableDBLClick = (e) => {
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
            "USER_ID"
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
  const nashiTR = document.querySelectorAll(".table__kek");
  for (let i = 0; i < nashiTR.length; i++) {
    const trButton = document.querySelector(".active-button");
    if (nashiTR[i].classList.contains("active-tr")) {
      nashiTR[i].classList.remove("active-tr");
    }
    if (nashiTR[i].contains(trButton)) {
      nashiTR[i].removeChild(trButton);
    }
  }
  if (e.target.parentElement.classList.contains("table__kek")) {
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
};