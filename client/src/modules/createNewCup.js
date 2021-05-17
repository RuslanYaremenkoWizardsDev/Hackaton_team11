export default function creatingNewCup () {
    const createNewCup = document.getElementById('createNewCup');
    const createNewCupPopup = document.getElementById('createNewCupPopup');
    const cancelBtn = document.getElementById('cancelNewCup');
    const crossBtn = document.getElementById('crossNewCup');
    const findUsers = document.getElementById('findUsers');
    const findUsersPopup = document.getElementById('findUsersPopup');
 
    findUsers.addEventListener('click', () => {
        findUsersPopup.classList.remove('findUsersShowAll');
    })
    findUsersPopup.addEventListener('click', (event) => {
        if (event.target === findUsersPopup) {
            findUsersPopup.classList.add('findUsersShowAll');
        }
    })
    createNewCup.addEventListener('click', () => {
        openPopup(createNewCupPopup);
    })
    cancelBtn.addEventListener('click', () => {
        closePopup(createNewCupPopup);
    });
    crossBtn.addEventListener('click', () => {
        closePopup(createNewCupPopup);
    });
    window.addEventListener('keyup', function (event) {
        exitOnEscape(event, createNewCupPopup)
    })
    createNewCupPopup.addEventListener('click', function (event) {
        handlePopupClick(event, createNewCupPopup)
    })
    function openPopup (element) {
        element.classList.remove('hideCreateNewCupPopup');
    }
    function closePopup(element) {
        element.classList.add('hideCreateNewCupPopup');
    }
    function exitOnEscape(event, element) {
        if (event.keyCode === 27) {
          closePopup(element);
        }
    }
    function handlePopupClick(event, element) {
        if (event.target === element) {
          closePopup(element);
        }
    }
}