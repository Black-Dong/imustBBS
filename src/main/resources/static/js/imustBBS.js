function checkRole() {
    layui.use('layer',function () {
        let layer = layui.layer;
        layer.msg("你还不是会员，请联系站长！");
    });
}

//登录弹窗
function login() {
    layui.use('layer',function () {
        let layer = layui.layer;
        layer.open({
            type: 2,
            title: false,
            area: ['450px','370px'], //宽高
            closeBtn: 0, //不显示关闭按钮
            scrollbar: false, //禁止浏览器出现滚动条
            resize: false, //禁止拉伸
            move: false, //禁止拖动
            shade: 0.8, //遮罩

            shadeClose: true, //开启遮罩关闭
            content: '/user/login.html', //这里content是一个Url
        });
    })
}

//注册弹窗
function register() {
    layui.use('layer',function () {
        let layer = layui.layer;
        layer.open({
            type: 2,
            title: false,
            area: ['450px','480px'], //宽高
            closeBtn: 0, //不显示关闭按钮
            scrollbar: false, //禁止浏览器出现滚动条
            resize: false, //禁止拉伸
            move: false, //禁止拖动
            shade: 0.8, //遮罩
            shadeClose: true, //开启遮罩关闭
            content: '/user/register.html', //这里content是一个Url
        })
    })
}

function disableUser(user) {
    layer.confirm("确认要封禁用户 " + user.username + " 吗？", function (index) {
        $.ajax({
            url: '/admin/disableUser',
            data: {
                'userId': user.userId,
            },
            contentType: 'application/json',
            success: function (result) {
                layer.close(index);
                window.location.reload();
            }
        });
    });
}
function unDisableUser(user) {
    layer.confirm("确认要解封用户 " + user.username + " 吗？", function (index) {
        $.ajax({
            url: '/admin/unDisableUser',
            data: {
                'userId': user.userId,
            },
            contentType: 'application/json',
            success: function (result) {
                layer.close(index);
                window.location.reload();
            }

        });
    });
}

$(function () {

    $("#loginLi").hover(function () {
        $(".layui-nav-child").show();
    },function () {
        $(".layui.nav.child").hide();
    })

    //用户中心菜单切换
});