export default function settingsPopup () {
    const settings = document.getElementById('settings');
    const settingsPopup = document.getElementById('settingsPopup');
    const cancelBtn = document.getElementById('settingsPopupCancel');
    const crossBtn = document.getElementById('closeSettingsPopUp');
    const clear = document.querySelectorAll('[data-clear]');

    settings.addEventListener('click', () => {
        openPopup(settingsPopup);
    });
    cancelBtn.addEventListener('click', () => {
        closePopup(settingsPopup);
        clearInputs(clear);
    });
    crossBtn.addEventListener('click', () => {
        closePopup(settingsPopup);
        clearInputs(clear);
    });
    window.addEventListener('keyup', function (event) {
        exitOnEscape(event, settingsPopup, clear);
    })
    settingsPopup.addEventListener('click', function (event) {
        handlePopupClick(event, settingsPopup, clear)
    })

    function openPopup (element) {
        element.classList.remove('hideSettingsPopup');
    }
    function closePopup(element, inp) {
        element.classList.add('hideSettingsPopup');
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