const input = document.querySelectorAll(".input-field");
const toggle_btn = document.querySelectorAll(".toggle");
const main = document.querySelector("main");


input.forEach((inp) => {
    inp.addEventListener("focus", () => {
        inp.classList.add("active");
    });

    inp.addEventListener("blur", () => {
        if (inp.value != "") return;
        inp.classList.remove("active");
    });
});


toggle_btn.forEach((btn) => {
    btn.addEventListener("click", () => {
        main.classList.toggle("sign-up-mode");
    });
})


function checkEmail(emailAddress) {
    let pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let res = pattern.test(emailAddress);
    return res;
}

function checkPassword(password) {
    let pwdLen = password.length;
    let res = false;
    if(pwdLen >= 6) {
        res = true;
    }
    return res;
}

function register() {

    alert("click!!!!!!");
    let email = document.getElementById('sign-email').value;
    let firstName = document.getElementById("sign-firstname").value;
    let lastName = document.getElementById("sign-lastname").value;
    let password = document.getElementById("sign-password").value;
    console.log(email);
    let res = checkEmail(email);
    let pwdRes = checkPassword(password);
    if(null === email || "" === email ||  null === firstName || "" === firstName || null === lastName || "" === lastName || null === password || "" === password ) {
        alert("Key information can not be empty. Make sure fill all the required information!");
        return;
    }
    if(res === false || pwdRes === false) {
        alert("Email pattern is not correct or password can not be shorter than 6. Please enter correct email and password!");
        return;
    }
    $.ajax({
        url : "/idlecherry/user/signup",
        type : "POST",
        data :{
            "email":email,
            "password":password,
            "firstName": firstName,
            "lastName": lastName,
        },
        dataType : "json",
        success : function(data) {

            //后台返回数据
            if (data.status == "ok") {
                alert(data.message);   //注册成功
                window.location.href = "login";
            }else {
                alert(data.message);    //注册失败
            }

        },
        error:function (res) {
            alert("请求错误！",console.res)
        }
    });
}


function login() {
    alert("登陆按钮！！！！！！！");
    let email = document.getElementById("log-email").value;
    let password = document.getElementById("log-password").value;
    console.log(password);
    //check input
    if(null == email || null == password) {
        alert("you need fill all the information!");
        return;
    }
    $.ajax({
        url : "/idlecherry/user/login",
        type : "POST",
        data : {
            "email":email,
            "password":password
        },
        dataType : "json",//后台返回来的数据类型
        success : function(data) {
            //后台返回数据
            if (data.status == "ok") {  //login successfully, and jump to index page
                alert(data.message);
                window.location.href = "index";
            }
            else {
                alert(data.message);    ////login fail, and jump to login page again
                window.location.href = "login";
            }
        },
        error:function (errorThrown) {
            alert("用户名或密码错误！")
        }
    });
}
