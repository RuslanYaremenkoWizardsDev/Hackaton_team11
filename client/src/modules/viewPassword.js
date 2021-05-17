const viewPassword = {
    viewPasswordAdmin () {
    const admineyes = document.querySelectorAll('.admineye');
    const viewpassword = document.querySelectorAll('.viewpassword');

    admineyes.forEach((eye) => {
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
    },
    viewPasswordAuth () {
        const autheyes = document.querySelectorAll('.auth-eye');
        const viewpassword = document.querySelectorAll('.viewpassword');
    
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
    },
    viewPasswordReg () {
        const regeyes = document.querySelectorAll('.regeye');
        const viewpassword = document.querySelectorAll('.viewpassword');
    
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
    },
    viewPasswordUser () {
        const usereye = document.querySelectorAll('.usereye');
        const viewpassword = document.querySelectorAll('.viewpassword');
    
        usereye.forEach((eye) => {
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
}
export default viewPassword;