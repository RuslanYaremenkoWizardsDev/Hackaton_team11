 function postRequest(url, requestBody) {

    return new Promise(function (resolve, reject) {
        
        console.log(requestBody);

        let request = new XMLHttpRequest();
        request.open("POST", url, true);
        request.setRequestHeader("Content-Type", "application/json");

        request.addEventListener("load", function () {
    
            if (request.status >= 200 && request.status < 300){
                resolve(request.responseText);
            }else if(request.status >= 400){
                reject(new Error("Request failed: " + request.statusText));
            }

        });

        request.send(requestBody);
    });
  }

  export default postRequest;