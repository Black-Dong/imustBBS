/**
 * 提交回复
 */
function comment() {
    let postId = $("#post_id").val();
    let content = $("#comment_content").val();
    comment2target(postId, 1, content);
}

/**
 * 提交二级评论
 */
function reply(e) {
    let commentId = $(e).data("id");
    let content = $("#reply-" + commentId).val();
    comment2target(commentId, 2, content);
}

function comment2target(targetId, type, content) {
    if (content.trim().length == 0) {
        alert("评论不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/own/comment",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(
            {
                "parentId": targetId,
                "content": content,
                "type": type
            }
        ),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2000) {
                    let isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("/login/github");

                        window.localStorage.setItem("closeable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}


/**
 * 展开二级评论
 */
function collapseComments(c) {
    let social_comment = $(c);
    let comment_id = social_comment.data("id");
    let comments = $("#comment-" + comment_id);
    if (comments.hasClass("in")) {
        comments.removeClass("in");
        social_comment.removeClass("color-active");
    } else {
        comments.addClass("in");
        social_comment.addClass("color-active");
    }
}