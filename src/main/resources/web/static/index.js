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


})