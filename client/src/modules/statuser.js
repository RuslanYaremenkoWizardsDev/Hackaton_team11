export default function switchToStatUser () {
    const table = document.getElementById('usertable');
    const statWindow = document.getElementById('userstat-window');
    const tournaments = document.getElementById('usertournaments');
    const statistics = document.getElementById('userstatistics');

    tournaments.addEventListener('click', ()=> {
        statWindow.classList.add('switchToTable');
        table.classList.remove('switchToStat');
    })
    statistics.addEventListener('click', ()=> {
        statWindow.classList.remove('switchToTable');
        table.classList.add('switchToStat');
    })
}