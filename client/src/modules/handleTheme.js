const handleTheme = {

        handleThemeAdmin () {
            const general = document.getElementById('general');
            const selectedTheme = document.getElementById('select-theme');
            const popupClearAll = document.querySelector('.viewInvitesPopup__container')
            const darkThemeClass = 'theme-dark';
            const lightThemeClass = 'theme-light';
        
            selectedTheme.addEventListener('change', () => {
                switch (selectedTheme.value) {
                    case 'dark':
                        general.className = `general ${darkThemeClass}`;
                        break;
        
                    case 'light':
                        general.className = `general ${lightThemeClass}`;
                        popupClearAll.style.backgroundColor = '#fff';
                        break;
                }
            })
        },
        handleThemeGuest () {
            const guest = document.getElementById('guest');
            const guestselectedTheme = document.getElementById('guest-select-theme');
            const darkThemeClass = 'theme-dark';
            const lightThemeClass = 'theme-light';
        
            guestselectedTheme.addEventListener('change', () => {
                switch (guestselectedTheme.value) {
                    case 'dark':
                        guest.className = `guest ${darkThemeClass}`;
                        break;
        
                    case 'light':
                        guest.className = `guest ${lightThemeClass}`;
                        break;
                }
            })
        },
        handleThemeUser () {
            const usergeneral = document.getElementById('usergeneral');
            const usergeneralselectedTheme = document.getElementById('user-select-theme');
            const darkThemeClass = 'theme-dark';
            const lightThemeClass = 'theme-light';
        
            usergeneralselectedTheme.addEventListener('change', () => {
                switch (usergeneralselectedTheme.value) {
                    case 'dark':
                        usergeneral.className = `usergeneral ${darkThemeClass}`;
                        break;
        
                    case 'light':
                        usergeneral.className = `usergeneral ${lightThemeClass}`;
                        break;
                }
            })
        }
}

export default handleTheme;