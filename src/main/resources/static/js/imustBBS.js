let app = new Vue({
    el: "#app",
    data: {
        url: "/images/xh_imusBBS.jpg",
        search: "",
        pageInfo: {}
    },
    methods: {},
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