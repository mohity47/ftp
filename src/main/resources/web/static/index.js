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
})