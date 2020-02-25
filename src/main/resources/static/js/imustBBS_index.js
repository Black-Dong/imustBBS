/* 分类块加载 */
let categoryList = new Vue({
    el: "#categoryList",
    data: {
        pageInfo: {}
    }
});
$("#categoryList").ready(function () {
    $.ajax({
        url: "/categories",
        type: "get",
        contentType: "application/json",
        dataType: "json",
        data: {},
        success: function (pageInfo) {
            console.log(pageInfo);
            // <li role="presentation" class="active marginRight-5"><a href="#">Home</a></li>
            categoryList.$data.pageInfo = pageInfo
        }
    })
});


/* 帖子列表块加载 */
let topicList = new Vue({
    el: "#topicList",
    data: {
        pageInfo: {},
    }
});
$("#topicList").ready(function () {
    $.ajax({
        url: "/topics",
        type: "get",
        contentType: "application/json",
        dataType: "json",
        data: {
            "pageNumber": 1,
            "pageSize": 10,
        },
        success: function (pageInfo) {
            topicList.$data.pageInfo = pageInfo;
        }
    })
});

function getTopics(id) {

    $.ajax({
        url: "/topics",
        type: "get",
        contentType: "application/json",
        dataType: "json",
        data: {
            "pageNumber": 1,
            "pageSize": 10,
            "categoryId": id
        },
        success: function (pageInfo) {
            topicList.$data.pageInfo = pageInfo;
        }
    });

    $(".category").removeClass("active");
    $("#category_" + id).addClass("active");
}