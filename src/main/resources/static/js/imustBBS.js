function comment() {
    let postId = $("#post_id").val();
    let content = $("#comment_content").val();

    $.ajax({
        type: "POST",
        url: "/own/comment",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(
            {
                "parentId": postId,
                "content": content,
                "type": 1
            }
        ),
        success: function (response) {
            console.log(response)
        },
        dataType: "json"
    });
}