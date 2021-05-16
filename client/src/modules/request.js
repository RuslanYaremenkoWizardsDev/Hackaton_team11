
function  postRequest(url, requestBody) {
  return new Promise(function (resolve, reject) {
        
        console.log(requestBody);

        let request = new XMLHttpRequest();
        request.open("POST", url, true);
        request.setRequestHeader("Content-Type", "application/json");
        request.setRequestHeader("Access-Control-Expose-Headers", "*");
        request.setRequestHeader("Access-Control-Allow-Headers", "*");

        request.addEventListener("load", function () {
    
            if (request.status >= 200 && request.status < 300){
                resolve(this.response.responseText);

                const role = JSON.parse(request.responseText);
                const cook = document.cookie = "role = " + role.role + ';path=/';
                console.log(request.responseText);
            

            }else if(request.status >= 400){
                reject(new Error("Request failed: " + request.statusText));
            }

        });

        request.send(requestBody);
    }); 
  
 }
  export default postRequest;