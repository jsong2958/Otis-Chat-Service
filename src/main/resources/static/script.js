const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/chat'
});

stompClient.onConnect = (frame) => {
    console.log('Connected: ', frame);
    stompClient.subscribe("/topic/messages", (messageDTO) => {
        const message = JSON.parse(messageDTO.body)
        console.log(message);
        addMessage(message.user, message.messageContent);
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

function addMessage(user, messageContent) {
    let alignmentClass;

    if (user !== "Otis") {
        alignmentClass = "right";
    } else {
        alignmentClass = "left";
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

$(function () {
   $("#send").click(() => sendMessage());
});

stompClient.activate();