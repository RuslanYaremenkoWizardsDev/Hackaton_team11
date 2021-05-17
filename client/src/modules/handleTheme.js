export default function handleTheme () {
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
}