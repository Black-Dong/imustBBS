/* jquery判断数据是否为null,undefined或空字符串 */
function CheckIsNullOrEmpty(value) {
    //正则表达式用于判斷字符串是否全部由空格或换行符组成
    let reg = /^\s*$/;
    //返回值为true表示不是空字符串
    return (value != null && value != undefined && !reg.test(value));
}

/* 点击登录 */
$("#login-btn").click(function () {

    let account = $("#account").val();
    let password = $("#password").val();

    if (!CheckIsNullOrEmpty(account)) {
        alert("账号不能为空");
        return;
    }
    if (!CheckIsNullOrEmpty(password)) {
        alert("密码不能为空");
        return;
    }
    alert(account + ":" + password)

    $.ajax({
        url: "/login",
        contentType: 'application/json',
        type: "post",
        dataType: 'json',
        data: JSON.stringify(
            {
                "account": account,
                "password": password
            }
        ),
        success: function (resultDto) {
            if (resultDto.code != 200) {
                alert(resultDto.message)
                return;
            }
            if (resultDto.data.type == 101) {

            }
            window.location.reload();
        }

    })

});
/* 点击推出登录 */
$("#logout").click(function () {

    $.ajax({
        url: '/logout',
        type: 'get',
        success: function () {
            window.location.reload();
        }
    })
});

/* 跳转注册页 */
$("#jumpRegister-btn").click(function () {
    window.location.href = "/register.html"
});

/* 注册页信息验证 */
// 用户名重复验证
$("#username").blur(function () {
    let username = $("#username").val();
    if (CheckIsNullOrEmpty(username)) {
        $.ajax({
            url: "/checkUsername",
            type: "get",
            contentType: 'application/json',
            dataType: "json",
            data: {
                "username": username
            },
            success: function (resultDto) {
                if (resultDto.code != 200) {
                    $("#errorBox").show();
                    $("#errorMessage").html(resultDto.message);
                    $("#username-formGroup").removeClass("has-success");
                    $("#username-formGroup").addClass("has-error");
                } else {
                    $("#errorBox").hide();
                    $("#username-formGroup").removeClass("has-error");
                    $("#username-formGroup").addClass("has-success");
                }
            }

        })
    }
});
// 邮箱重复验证
$("#email").blur(function () {
    let email = $("#email").val();
    if (CheckIsNullOrEmpty(email)) {
        // if ()
        $.ajax({
            url: "/checkEmail",
            type: "get",
            contentType: 'application/json',
            dataType: "json",
            data: {
                "email": email
            },
            success: function (resultDto) {
                if (resultDto.code != 200) {
                    $("#errorBox").show();
                    $("#errorMessage").html(resultDto.message);
                    $("#email-formGroup").removeClass("has-success");
                    $("#email-formGroup").addClass("has-error");
                } else {
                    $("#errorBox").hide();
                    $("#email-formGroup").removeClass("has-error");
                    $("#email-formGroup").addClass("has-success");
                }

            }

        })
    }
});
// 提交注册验证
$("#register-btn").click(function () {
    let username = $("#username").val();
    if (!CheckIsNullOrEmpty(username)) {
        alert("用户名不能为空");
        return;
    }

    let email = $("#email").val();
    if (!CheckIsNullOrEmpty(email)) {
        alert("邮箱不能为空");
        return;
    }

    let registerPassword = $("#registerPassword").val();
    if (!CheckIsNullOrEmpty(registerPassword)) {
        alert("密码不能为空");
        return;
    }

    let repeatPassword = $("#repeatPassword").val();
    if (!CheckIsNullOrEmpty(repeatPassword)) {
        alert("请确认密码");
        return;
    }

    if (registerPassword != repeatPassword) {
        alert("两次密码输入不一致，请重新输入");
        return;
    }


    $.ajax({
        url: "/register",
        type: "post",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
            "username": username,
            "email": email,
            "registerPassword": registerPassword,
            "repeatPassword": repeatPassword
        }),
        success: function (resultDto) {
            if (resultDto.code != 200) {
                alert(resultDto.message);
                return;
            }

            window.location.href = "/index";
        }
    })
});
