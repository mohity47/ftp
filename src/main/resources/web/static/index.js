document.addEventListener("DOMContentLoaded", function() {
    document.getElementsByClassName("create-otp")[0].addEventListener("click", () => {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200 ) {
                document.getElementsByClassName("otp-container")[0].classList.remove("hidden");
                document.getElementsByClassName("otp")[0].innerHTML = this.responseText;
            }
        }
        xhr.open("get","/api/generateOTP",true);
        xhr.send();
    });

    document.getElementsByClassName("host")[0].addEventListener("click", ()=> {
        document.getElementsByClassName("user-type")[0].classList.add("hidden");
        document.getElementsByClassName("host-actions")[0].classList.remove("hidden");
    });

    document.getElementsByClassName("guest")[0].addEventListener("click", () => {
         document.getElementsByClassName("user-type")[0].classList.add("hidden");
         document.getElementsByClassName("guest-actions")[0].classList.remove("hidden");
    });

    document.getElementsByClassName("connect")[0].addEventListener("click", () => {
        var data = {
            otp: document.getElementById("otp-input").value
        }
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200 ) {
                if(this.responseText == "true") {
                    document.getElementsByClassName("guest-actions")[0].classList.add("hidden");
                    document.getElementsByClassName("peer-connected")[0].classList.remove("hidden");
                }
                else {
                    document.getElementsByClassName("warning")[0].classList.remove("hidden");
                }
            }
        };
        xhr.open("post","api/connect",true);
        xhr.setRequestHeader("Content-Type","application/json;charset=UTF-8");
        xhr.send(JSON.stringify(data));
    });

    document.getElementsByClassName("send-message")[0].addEventListener("click", () => {
        sendMessage();
    })

});

const baseSockUrl = "ws://localhost:8000";
var chatSocketUrl = baseSockUrl + "/chat/connect";
var fileSocketUrl = baseSockUrl + "/file/connect";
const chatSocket = new WebSocket(chatSocketUrl);
const fileSocket = new WebSocket(fileSocketUrl);

chatSocket.addEventListener("open", function(event) {
    console.log("connected to server", event);
});
chatSocket.addEventListener('message', function (event) {
    handleMessageReceived(JSON.parse(event.data));
});

function sendMessage() {
    var inputBox = document.querySelector(".message-input-box .message");
    var msg = inputBox.value;
    var message = document.createElement("div");
    message.classList.add("msg-to");
    message.innerText = msg;
    var inbox = document.getElementsByClassName("inbox")[0];
    inbox.appendChild(message);
    inputBox.value = "";
    var data = {
        message: msg
    }
    chatSocket.send(JSON.stringify(data));
}

function handleMessageReceived(rmsg) {
        var msg = rmsg.message;
        var message = document.createElement("div");
        message.classList.add("msg-from");
        message.innerText = msg;
        var inbox = document.getElementsByClassName("inbox")[0];
        inbox.appendChild(message);
}


