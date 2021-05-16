export default function viewPassword () {
    const eyes = document.querySelectorAll('.eye');
    const autheyes = document.querySelectorAll('.auth-eye');
    const regeyes = document.querySelectorAll('.regeye');
    const viewpassword = document.querySelectorAll('.viewpassword');
    eyes.forEach((eye) => {
        eye.addEventListener('click', () => {
            viewpassword.forEach((input) => {
                if (input.type === 'password') {
                    input.type = 'text';
                } else if (input.type = 'text') {
                    input.type = 'password';
                }
            })            
        })
    })
    autheyes.forEach((eye) => {
        eye.addEventListener('click', () => {
            viewpassword.forEach((input) => {
                if (input.type === 'password') {
                    input.type = 'text';
                } else if (input.type = 'text') {
                    input.type = 'password';
                }
            })            
        })
    })
    regeyes.forEach((eye) => {
        eye.addEventListener('click', () => {
            viewpassword.forEach((input) => {
                if (input.type === 'password') {
                    input.type = 'text';
                } else if (input.type = 'text') {
                    input.type = 'password';
                }
            })            
        })
    })
}