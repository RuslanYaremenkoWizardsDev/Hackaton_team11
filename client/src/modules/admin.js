
function admin(){
    var Stomp = require('stompjs');
    var stompClient = null;

    var socket = new WebSocket('ws://localhost:8077/websocket');
    console.log(socket);

    stompClient = Stomp.over(socket);
    console.log(stompClient);
}



export default admin;