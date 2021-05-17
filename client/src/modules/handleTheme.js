export default function handleTheme () {
    const guest = document.getElementById('guest');
    const guestselectedTheme = document.getElementById('guest-select-theme');
    const darkThemeClass = 'theme-dark';
    const lightThemeClass = 'theme-light';

    guestselectedTheme.addEventListener('change', () => {
        console.log(guestselectedTheme.value)
        switch (guestselectedTheme.value) {
            case 'dark':
                guest.className = `guest ${darkThemeClass}`;
                console.log(guest.className)
                // guest.style.backgroundColor = "#333";
                break;

            case 'light':
                guest.className = `guest ${lightThemeClass}`;
                // guest.style.backgroundColor = "#fff";
                break;
        }
    })
// console.log(guestselectedTheme.value)
//     function setTheme(rootElement, theme) {
//         switch (theme) {
//             case 'dark':
//                 rootElement.className = `general ${darkThemeClass}`;
//                 break;

//             case 'light':
//                 rootElement.className = `general ${lightThemeClass}`;
//                 break;
//         }
//     }
}