const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/chat'
});

var user = "Joey";
var currentUser = user;
var isAi = true;

stompClient.onConnect = (frame) => {
    console.log('Connected: ', frame);
    stompClient.subscribe("/topic/messages", (messageDTO) => {
        const message = JSON.parse(messageDTO.body)
        console.log(message);
        addMessage(message.messageContent);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function sendMessage() { //sends name to /app/hello, called by the function in GreetingController

    const user = $("#user").val();
    const messageContent = $('#message').val()

    const message = {
        user: user,
        messageContent: messageContent
    };

    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify(message)
    });
}

function addMessage(messageContent) {
    let alignmentClass;

    if (currentUser === "Otis" || isAi) {
        alignmentClass = "left";
    } else {
        alignmentClass = "right";
    }

    const messageElement = `
        <div class="chat-message ${alignmentClass}">
            <p class="message-content">${messageContent}</p>
        </div>
    `;

    $("#messages").append(messageElement);

    const chatBody = document.getElementById("messages");
    chatBody.scrollTop = chatBody.scrollHeight;

}

function switchUser() {
    if (currentUser === "Otis") {
        currentUser = user;
    } else {
        currentUser = "Otis"
    }

    console.log(currentUser);
}

function switchOffAi() {
    stompClient.publish({
        destination: "/app/switch",
        body: "_"
    });
    isAi = false;
    console.log("switched!");
}

$(function () {
   $("#send").click(() => sendMessage());
   $("#switch").click(() => switchUser());
   $("#switchOffAi").click(() => switchOffAi());

   let input = document.getElementById("message");
   input.addEventListener("keydown", function(event) {
      if (event.key === "Enter") {
          event.preventDefault();
          sendMessage();
      }
   });



});

stompClient.activate();