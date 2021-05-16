export default function declineTournament () {
    const entry = document.getElementById('entry');
    const decline = document.getElementById('entry-decline');
    
    decline.addEventListener('click', () => {
        entry.classList.add('declineEntry');
    })
}