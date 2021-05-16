export default function viewInvitations () {
    const viewInvites = document.getElementById('viewInvites');
    const viewInvitesPopup = document.getElementById('viewInvitesPopup');
    const cancelBtn = document.getElementById('viewInvitesPopupCancel');
    const crossBtn = document.getElementById('closeviewInvitesPopup');
    const clear = document.querySelectorAll('[data-clear]');

    viewInvites.addEventListener('click', () => {
        openPopup(viewInvitesPopup);
    });
    cancelBtn.addEventListener('click', () => {
        closePopup(viewInvitesPopup);
        clearInputs(clear);
    });
    crossBtn.addEventListener('click', () => {
        closePopup(viewInvitesPopup);
        clearInputs(clear);
    });
    window.addEventListener('keyup', function (event) {
        exitOnEscape(event, viewInvitesPopup, clear);
    })
    viewInvitesPopup.addEventListener('click', function (event) {
        handlePopupClick(event, viewInvitesPopup, clear)
    })

    function openPopup (element) {
        element.classList.remove('hideviewInvitesPopup');
    }
    function closePopup(element, inp) {
        element.classList.add('hideviewInvitesPopup');
        clearInputs(inp);
    }
    function exitOnEscape(event, element, inp) {
        if (event.keyCode === 27) {
          closePopup(element);
          clearInputs(inp);
        }
    }
    function handlePopupClick(event, element, inp) {
        if (event.target === element) {
          closePopup(element);
          clearInputs(inp);
        }
    }
    function clearInputs (input) {
        if (!input) {
            return;
        }
        for (let i of input) {
            i.value = '';
        }
    }
}