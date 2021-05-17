

function user(){

    // const renderTable = (data, tableDiv) => {
    //     tableDiv.innerHTML = "";
      
    //     for (let i = 0; i < data.length; i++) {
    //       const newTR = document.createElement("tr");
    //       if(data[i].tournamentDescription){
    //         newTR.setAttribute('title', data[i].tournamentDescription)
    //       }
    //       newTR.classList.add("table_cup");
    //       const nameTH = document.createElement("td");
    //       nameTH.setAttribute("data", "name");
    //       nameTH.textContent = data[i].name;
    //       const modeTH = document.createElement("td");
    //       modeTH.setAttribute("data", "modeTournament");
    //       modeTH.textContent = data[i].modeTournament;
    //       const placeTH = document.createElement("td");
    //       placeTH.setAttribute("data", "place");
    //       placeTH.textContent = data[i].place === "NULL" ? "" : data[i].place;
    //       const startDateTH = document.createElement("td");
    //       startDateTH.setAttribute("data", "dateStartTournament");
    //       startDateTH.textContent = data[i].dateStartTournament;
    //       const lastRegDateTH = document.createElement("td");
    //       lastRegDateTH.setAttribute("data", "dateLastRegistrationOnTournament");
    //       lastRegDateTH.textContent = data[i].dateLastRegistrationOnTournament;
    
    //       const statusTH = document.createElement("td");
    //       statusTH.setAttribute("data", "status");
    //       statusTH.textContent = data[i].status === "In progress";
    
    //       const levelTH = document.createElement("td");
    //       levelTH.setAttribute("data", "level");
    //       levelTH.textContent = data[i].level;
    
    //       const participantsTH = document.createElement("td");
    //       participantsTH.setAttribute("data", "numberOfPlayer");
    //       participantsTH.textContent = data[i].numberOfPlayer;
    
    //       const scenarioTH = document.createElement("td");
    //       scenarioTH.setAttribute("data", "scenatioOfTournament");
    //       scenarioTH.textContent = data[i].scenatioOfTournament;
    
    //       const descriptionTH = document.createElement("td");
    //       scenarioTH.setAttribute("data", "tournamentDescription");
    //       scenarioTH.textContent = data[i].tournamentDescription;
    
    //     //   const actionsTH = document.createElement("td");
    //     //   actionsTH.setAttribute("data", "ACTIONS");
    //     //   actionsTH.textContent = data[i].ACTIONS;
      
      
      
    //       //???СМЕНА ДАННЫХ+УДАЛЕНИЕ СТРОКИ  
    //       newTR.setAttribute("id", `${data[i].id}`);
    //       newTR.appendChild(nameTH);
    //       newTR.appendChild(modeTH);
    //       newTR.appendChild(placeTH);
    //       newTR.appendChild(startDateTH);
    //       newTR.appendChild(lastRegDateTH);
    //       newTR.appendChild(descriptionTH);
    //       newTR.appendChild(levelTH);
    //       newTR.appendChild(participantsTH);
    //       newTR.appendChild(scenarioTH);
    //     //   newTR.appendChild(actionsTH);
      
    //     tableDiv.appendChild(newTR);
    
    //     }
       
    
        
    //   /*   table.addEventListener("dblclick", tableDBLClick); */
    //   };


    const thead = document.querySelector("thead");
    function sentToServer(){
        var anstToken;
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8077/getTournament');
        xhr.send();
        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                anstToken = JSON.parse(xhr.responseText);
                console.log(anstToken);
                //const tableDiv = document.querySelector('#user-tbody')
                
            // renderTable(anstToken, tableDiv);
            table(anstToken);
            }

        };

        
    }
    sentToServer();


    function table(arg){
        for(var i = 0; i < arg.length; i++){
            const tbody = document.createElement('tbody');
            console.log(tbody);
            //tbody.classList.add('usertable__data-body usertbody');
            tbody.innerHTML = `
            <tr class="usertbody__tr">
                <td class="usertr__td usertd-name"><span class="usertr__cell">${arg[i].name}</span></td>
                <td class="usertr__td usertd-mode"><span class="usertr__cell">${arg[i].modeTournament}</span></td>
                <td class="usertr__td usertd-place"><span class="usertr__cell">${arg[i].place}</span></td>
                <td class="usertr__td usertd-startDate"><span class="usertr__cell">${arg[i].dateStartTournament}</span></td>
                <td class="usertr__td usertd-lastRegDate"><span class="usertr__cell">${arg[i].dateLastRegistrationOnTournament}</span></td>
                <td class="usertr__td usertd-state"><span class="usertr__cell">${arg[i].status}</span></td>
                <td class="usertr__td usertd-level"><span class="usertr__cell">${arg[i].level}</span></td>
                <td class="usertr__td usertd-partisipants"><span class="usertr__cell">${arg[i].numberOfPlayer}</span></td>
                <td class="usertr__td usertd-scenario"><span class="usertr__cell">${arg[i].name}</span></td>
                <td class="usertr__td usertd-actions"><span class="usertr__cell">${arg[i].name}</span></td>
            </tr>
            `;
            thead.append(tbody);

        }
    }


   










}

 



export default user;