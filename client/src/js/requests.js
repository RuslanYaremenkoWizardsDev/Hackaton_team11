// import { getCookie } from "./cookie";
// export const getRequest = async (url, options) => {
//   const checkToken = getCookie("token");
//   if (!checkToken && url !== `${URL}login` && url !== `${URL}registration`) {
//     return (window.location.pathname = "index.html");
//   }
//   const answer = await fetch(url, options);
//   return answer.json();
// };
// export const postRequest = async (url, options) => {
//   const checkToken = getCookie("token");
//   if (!checkToken && url !== `${URL}login` && url !== `${URL}registration`) {
//     return (window.location.pathname = "index.html");
//   }
//   const answer = await fetch(url, options);
//   return answer.json();
// };
// export const putRequest = async (url, options) => {
//   const checkToken = getCookie("token");
//   if (!checkToken && url !== `${URL}login` && url !== `${URL}registration`) {
//     return (window.location.pathname = "index.html");
//   }
//   const answer = await fetch(url, options);
//   return answer.json();
// };
// export const deleteRequest = async (url, options) => {
//   const checkToken = getCookie("token");
//   if (!checkToken && url !== `${URL}login` && url !== `${URL}registration`) {
//     return (window.location.pathname = "index.html");
//   }
//   const answer = await fetch(url, options);
//   return answer.json();
// };
// export const URL = `http://localhost:5246/`;
// export const loginPostOptions = {
//   method: "POST",
//   headers: { "Content-Type": "application/json" },
// };

// export const registrationOptions = {
//   method: "POST",
//   headers: { "Content-Type": "application/json" },
// };

// export const databaseURL = URL + "databases/";

// export const createQueryParams = (params) => {
//   const { db, id, sort, findFirstName, findLastName } = params;
//   let standart = `${db}?id=${id}&sort=${sort}`;
//   if (!findFirstName && !findLastName) {
//     return standart;
//   } else if (findFirstName && !findLastName) {
//     return (standart += `&findFirstName=${findFirstName}`);
//   } else if (!findFirstName && findLastName) {
//     return (standart += `&findLastName=${findLastName}`);
//   } else if (findFirstName && findLastName) {
//     return (standart += `&findFirstName=${findFirstName}&findLastName=${findLastName}`);
//   }
// };