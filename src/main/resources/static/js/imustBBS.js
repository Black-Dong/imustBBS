/* 登录普通用户js */
/**
 * 回复帖子：点击回复
 * @param fatherReplyId
 * @param fatherUserId
 * @param username
 */
function replyFor(fatherReplyId, fatherUserId, username) {
    // alert(username)
    // 父回复id
    $("#fatherReplyId").val(fatherReplyId);
    // 父回复UserId
    $("#fatherUserId").val(fatherUserId);
    // 修改恢复类型为2，非顶级恢复
    $("#replyType").val(2);
    // 修改回复内容框的placeholder
    $("#L_reply").attr("placeholder", "回复：" + username);
    // 显示取消回复按钮
    $("#cancelReply").css('display', '');
}

/**
 * 回复帖子：点击取消回复
 */
function cancelReplyF() {
    $("#fatherReplyId").val(null);
    $("#fatherUserId").val(null);
    $("#replyType").val(1);
    $("#L_reply").attr("placeholder", "请输入回复内容");
    $("#cancelReply").css('display', 'none');
}


/* 登录管理员用户 */
/**
 * 封禁用户
 * @param user
 */
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

/**
 * 解封用户
 * @param user
 */
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

/* 删除帖子 */
function delTopic(topicId, topicTitle) {
    layer.msg('确定删除：' + topicTitle + ' 吗？', {
        time: 0 //不自动关闭
        , btn: ['确定删除', '取消操作']
        , yes: function (index) {
            layer.close(index);
            $.ajax({
                url: '/admin/deleteTopic',
                data: {"id": topicId},
                method: "GET",
                success: function (result) {
                    layer.msg('删除成功！！！', {
                        time: 1000
                        ,icon: 6
                        ,btn: ['关闭']
                    });
                    window.location.href = "/";
                },
                error: function (result) {
                    layer.msg('删除失败！！！', {
                        time: 1000
                        ,icon: 5
                        ,btn: ['关闭']
                    });
                }
            })
        }
    });
}




$(function () {

});