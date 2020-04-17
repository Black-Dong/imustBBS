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
    if (user.isOff) {
        layer.msg('用户已被封禁！！！', {
            time: 1000
            , icon: 6
        });
        return;
    }
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
    if (!user.isOff) {
        layer.msg('用户没有被封禁！！！', {
            time: 1000
            , icon: 6
        });
        return;
    }
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
                        , icon: 6
                        , btn: ['关闭']
                    });
                    window.location.href = "/";
                },
                error: function (result) {
                    layer.msg('删除失败！！！', {
                        time: 1000
                        , icon: 5
                        , btn: ['关闭']
                    });
                }
            })
        }
    });
}

/**
 * 帖子置顶管理
 * @param topicId 帖子id
 * @param topicTitle 帖子标题
 * @param topStatus 帖子置顶状态
 */
function topTopic(topicId, topicTitle, topStatus) {
    if (!topStatus) {
        layer.msg('确定置顶帖子：' + topicTitle + ' 吗？', {
            time: 0 //不自动关闭
            , btn: ['确定置顶', '取消操作']
            , yes: function (index) {
                layer.close(index);
                $.ajax({
                    url: '/admin/topTopic',
                    data: {"id": topicId, "topStatus": topStatus},
                    method: "GET",
                    success: function (result) {
                        layer.msg('置顶成功！！！', {
                            time: 1000
                            , icon: 6
                            , btn: ['关闭']
                        });
                        window.location.reload();
                    },
                    error: function (result) {
                        layer.msg('置顶失败', {
                            time: 1000
                            , icon: 5
                            , btn: ['关闭']
                        });
                    }
                })
            }
        });
    } else {
        layer.msg('确定取消置顶帖子：' + topicTitle + ' 吗？', {
            time: 0 //不自动关闭
            , btn: ['确定取消', '取消操作']
            , yes: function (index) {
                layer.close(index);
                $.ajax({
                    url: '/admin/topTopic',
                    data: {"id": topicId, "topStatus": topStatus},
                    method: "GET",
                    success: function (result) {
                        layer.msg('取消置顶成功！！！', {
                            time: 1000
                            , icon: 6
                            , btn: ['关闭']
                        });
                        window.location.reload();
                    },
                    error: function (result) {
                        layer.msg('取消置顶失败', {
                            time: 1000
                            , icon: 5
                            , btn: ['关闭']
                        });
                    }
                })
            }
        });
    }

}

/**
 * 帖子精品管理
 * @param topicId
 * @param topicTitle
 * @param boutiqueStatus
 */
function boutiqueTopic(topicId, topicTitle, boutiqueStatus) {
    if (!boutiqueStatus) {
        layer.msg('确定加精帖子：' + topicTitle + ' 吗？', {
            time: 0 //不自动关闭
            , btn: ['确定加精', '取消操作']
            , yes: function (index) {
                layer.close(index);
                $.ajax({
                    url: '/admin/boutiqueTopic',
                    data: {"id": topicId, "boutiqueStatus": boutiqueStatus},
                    method: "GET",
                    success: function (result) {
                        layer.msg('加精成功！！！', {
                            time: 1000
                            , icon: 6
                            , btn: ['关闭']
                        });
                        window.location.reload();
                    },
                    error: function (result) {
                        layer.msg('加精失败', {
                            time: 1000
                            , icon: 5
                            , btn: ['关闭']
                        });
                    }
                })
            }
        });
    } else {
        layer.msg('确定取消加精帖子：' + topicTitle + ' 吗？', {
            time: 0 //不自动关闭
            , btn: ['确定取消', '取消操作']
            , yes: function (index) {
                layer.close(index);
                $.ajax({
                    url: '/admin/boutiqueTopic',
                    data: {"id": topicId, "boutiqueStatus": boutiqueStatus},
                    method: "GET",
                    success: function (result) {
                        layer.msg('取消加精成功！！！', {
                            time: 1000
                            , icon: 6
                            , btn: ['关闭']
                        });
                        window.location.reload();
                    },
                    error: function (result) {
                        layer.msg('取消加精失败', {
                            time: 1000
                            , icon: 5
                            , btn: ['关闭']
                        });
                    }
                })
            }
        });
    }
    ;
};


/* 超级管理员用户登录 */
/**
 * 普通用户授权为管理员
 * @param userId
 * @param username
 */
function authorizeAdmin(userId, username) {
    /*Deauthorize*/
    layer.confirm('确认要升级用户：' + username + ' 为管理员吗？', function (index) {
        $.ajax({
            url: '/super/authorizeAdmin',
            data: {"userId": userId},
            method: "GET",
            success: function (result) {
                layer.msg('升级成功！！！', {
                    time: 1000,
                    icon: 6,
                });
                window.location.reload();
            },
            error: function (result) {
                layer.msg('升级失败！！！', {
                    time: 1000,
                    icon: 5,
                });
            }
        });
    });
};

/**
 * 管理员用户取消授权为普通用户
 * @param userId
 * @param username
 */
function deauthorizeAdmin(userId, username) {

    layer.confirm('确认要取消：' + username + ' 的管理员权限吗？', function (index) {
        $.ajax({
            url: '/super/deauthorizeAdmin',
            data: {"userId": userId},
            method: "GET",
            success: function (result) {
                layer.msg('取消成功！！！', {
                    time: 1000,
                    icon: 6,
                });
                window.location.reload();

            },
            error: function (result) {
                layer.msg('取消失败！！！', {
                    time: 1000,
                    icon: 5,
                });
            }
        })
    })
}

/**
 * 修改分类描述
 */
function modifyCategory() {
    let serializeArray = $("#modifyCategoryForm").serializeArray();
    let modifyCategory = {};
    for (let i = 0; i < serializeArray.length; i++) {
        modifyCategory[serializeArray[i].name] = serializeArray[i].value;
    }
    $.ajax({
        type: 'POST',
        url: '/super/modifyCategory',
        contentType: 'application/json;',
        dataType: 'JSON',
        data: JSON.stringify(modifyCategory),
        success: function (result) {
            if (result.status) {
                window.location.href = '/user/manager/6/1'
                alert("修改成功")
            } else {
                alert(result.message)
            }
            return;
        },
        error: function (result) {
            alert(result.message)
        }
    })
}


function addCategory(){
    let serializeArray = $("#addCategoryForm").serializeArray();
    let addCategoryForm = {};
    for (let i = 0; i < serializeArray.length; i++) {
        addCategoryForm[serializeArray[i].name] = serializeArray[i].value;
    }
    $.ajax({
        type: 'POST',
        url: '/super/addCategory',
        contentType: 'application/json;',
        dataType: 'JSON',
        data: JSON.stringify(addCategoryForm),
        success: function (result) {
            if (result.status) {
                window.location.href = '/user/manager/6/1'
                alert("新增成功")
            } else {
                alert(result.message)
            }
            return;
        },
        error: function (result) {
            alert(result.message)
        }
    })
}
$(function () {

});