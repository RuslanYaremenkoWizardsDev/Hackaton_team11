import { putRequest, deleteRequest, URL, databaseURL } from "./requests";
import { getCookie } from "./cookie";





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