var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new WebSocket('ws://localhost:8077/websocket');

    console.log(socket)

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe("/topic/errors", function(message) {
            alert("Error " + message.body);
        });

        stompClient.subscribe("/topic/tournament", function(message) {
            console.log(message)
            showGreeting(message.body);
        });
        stompClient.subscribe("/topic/replay", function(message) {
            console.log(message)
            showGreeting(message.body);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/getTournament", {}, JSON.stringify({'name': $("#name").val()}));
}
function sendName2() {
    stompClient.send("/app/addTournament", {}, JSON.stringify({ name: 'turic12',tournamentDescription:'qwerty1234' ,place:'LfLFF' ,dateStartTournament:1621166078489 ,dateLastRegistrationOnTournament:123123123123}));
}

function showGreeting(message) {
    console.log(message + " mesage!!!!!!!!!!!!!!!")

}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});