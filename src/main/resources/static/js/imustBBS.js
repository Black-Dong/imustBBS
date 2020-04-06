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


/* 点击回复 */
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

/* 点击取消回复 */
function cancelReplyF() {
    $("#fatherReplyId").val(null);
    $("#fatherUserId").val(null);
    $("#replyType").val(1);
    $("#L_reply").attr("placeholder", "请输入回复内容");
    $("#cancelReply").css('display', 'none');
}

$(function () {

});