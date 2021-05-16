export default function switchToStat () {
    const table = document.getElementById('table');
    const statWindow = document.getElementById('stat-window');
    const tournaments = document.getElementById('tournaments');
    const statistics = document.getElementById('statistics');

    tournaments.addEventListener('click', ()=> {
        statWindow.classList.add('switchToTable');
        table.classList.remove('switchToStat');
    })
    statistics.addEventListener('click', ()=> {
        statWindow.classList.remove('switchToTable');
        table.classList.add('switchToStat');
    })
}