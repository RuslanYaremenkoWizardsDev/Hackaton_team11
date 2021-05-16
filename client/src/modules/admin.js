import postRequest from '../modules/request';
// Добавление юзера в турн /user
// Создание игры /game


function admin(){
    const URL = "http://localhost:8077/addTournament";

    const name = document.querySelector('#name');
    const mode = document.querySelector('#mode');
    const descr = document.querySelector('#description');
    const place = document.querySelector('#place');
    const scenario = document.querySelector('#scenario');
    const startDate = document.querySelector('#startDate');
    const lastRegDate = document.querySelector('#lastRegDate');
    const lavel = document.querySelector('#lavel');
    const partisipants = document.querySelector('#partisipants');
    const lang = document.querySelector('#lang');
    const create = document.querySelector('#create');

    create.addEventListener("click", ()=>{
        const createTurn = JSON.stringify({
            date_last_registration_on_tournament: lastRegDate.value,
            date_start_tournament: startDate.value,
            level: lavel.value,
            mode_tournament: mode.value,
            name: name.value,
            number_of_player: 25,
            place: place.value,
            scenatio_of_tournament: scenario.value,
            status: "status",
            tournament_description: descr.value,
        });

        postRequest(URL, createTurn).then(()=>{
            console.log("Круто");
        })

        console.log(createTurn);
    });

    
}



export default admin;