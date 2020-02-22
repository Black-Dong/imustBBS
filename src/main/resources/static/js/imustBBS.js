let app = new Vue({
    el: "#app",
    data: {
        url: "/images/xh_imusBBS.jpg",
        avatar: "/images/xh.jpg",
        search: "",
        pageInfo: {}
    },
    methods: {
        onEnterSearch: function () {
            if (this.search.length <= 0) {
                alert("搜索内容不能为空")
            }
            console.log("search: "+this.search)

        }
    },
    beforeCreate: function () {
        $.ajax({
            type: "get",
            url: "/admin/categories",
            data: {},
            dataType: 'json',
            success: function (result) {
                app.$data.pageInfo = result;
                console.log(app.$data.pageInfo)
            }
        })
    }
})