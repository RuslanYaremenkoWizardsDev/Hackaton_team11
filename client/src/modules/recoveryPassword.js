function recovery(){
  const btn = document.querySelector('#btn');

  const URL = 'http://localhost:8077/password';
  const login = "Login";
  const pass = "password";
  const sKey = "secret";
  

  const recovery = JSON.stringify({
      login: login,
      password: pass,
      secretKey: sKey
      
  });
  console.log(recovery);



  function recoveryPassword(arg){
      var xhr = new XMLHttpRequest();
      console.log(arg)
      xhr.open("POST", URL);
      xhr.setRequestHeader('Content-Type', 'application/json');
      xhr.send(arg);
      xhr.addEventListener("load", function(){
          if(xhr.status === 200){
              console.log(xhr.responseText);
          // window.location.pathname = "index.html"
          } else if(xhr.status > 400){
              console.log(xhr.responseText);
          }
      });
  }

  

  btn.addEventListener("click", ()=>{
    recoveryPassword(recovery);
  });

}


export default recovery;






