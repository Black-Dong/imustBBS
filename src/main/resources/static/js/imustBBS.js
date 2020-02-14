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
            if (response.code == 200) {
                $("#comment_section").hide();
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