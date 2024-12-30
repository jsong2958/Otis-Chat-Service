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

    stompClient.subscribe("/topic/ai-messages", (messageDTO) => {
        const message = JSON.parse(messageDTO.body)
        console.log(message);
        addAiMessage(message.messageContent);
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function sendMessage() { //sends name to /app/message, called by the function in ChatController

    const user = $("#user").val();
    const messageContent = $('#message').val()

    const message = {
        user: user,
        messageContent: messageContent
    };

    if (isAi) {
        stompClient.publish({
            destination: "/app/message",
            body: JSON.stringify(message)
        });
        stompClient.publish({
            destination: "/app/ai-message",
            body: JSON.stringify(message)
        });
    } else {
        stompClient.publish({
            destination: "/app/message",
            body: JSON.stringify(message)
        });
    }
}

function addMessage(messageContent) {

    const alignmentClass = currentUser === "Otis" ? "left" : "right";

    const messageElement = `
        <div class="chat-message ${alignmentClass}">
            <p class="message-content">${messageContent}</p>
        </div>
    `;

    $("#messages").append(messageElement);

    const chatBody = document.getElementById("messages");
    chatBody.scrollTop = chatBody.scrollHeight;

}

function addAiMessage(messageContent) {
    const alignmentClass = "left";

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

function switchAi() {
    isAi = !isAi;
}

$(function () {
   $("#send").click(() => sendMessage());
   $("#switch").click(() => switchUser());
   $("#switchOffAi").click(() => switchAi());

   let input = document.getElementById("message");
   input.addEventListener("keydown", function(event) {
      if (event.key === "Enter") {
          event.preventDefault();
          sendMessage();
      }
   });



});

stompClient.activate();