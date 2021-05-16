export default function declineTournamentInvite () {
    const invite = document.getElementById('invite');
    const decline = document.getElementById('invite-decline');
    
    decline.addEventListener('click', () => {
        invite.classList.add('declineInvite');
    })
}