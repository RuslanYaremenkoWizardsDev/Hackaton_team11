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

    function getMilsec(arg){
        const x = new Date(arg);
        const y = new Date();
        return x - y;
    }

    create.addEventListener("click", ()=>{
            const createTurn = JSON.stringify({
                dateLastRegistrationOnTournament: String(getMilsec(lastRegDate.value)),
                dateStartTournament: String(getMilsec(startDate.value)),
                level: lavel.value,
                name: name.value,
                numberOfPlayer: partisipants.value,
                place: place.value,
                tournamentDescription: descr.value
    });

        

        postRequest(URL, createTurn).then(()=>{
            console.log("Круто");
        });


    });

    
}



export default admin;