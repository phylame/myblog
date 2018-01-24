$(function () {
    Vue.prototype.$axios = axios.create({
        baseURL: "http://localhost:8080/"
    })
    new Vue({
        el: "#post-list",
        data: {
            activated: "",
            posts: []
        },
        methods: {
            loadPosts: function (key) {
                if (key !== this.activated) {
                    this.posts = []
                    this.activated = key
                    var n = Math.trunc(Math.random() * 8 + 1)
                    for (var i = 0; i < n; ++i) {
                        this.posts.push({
                            id: Math.trunc(Math.random() * 100 + 1),
                            title: "This is post with order " + i + " for " + key
                        })
                    }
                }
            }
        },
        mounted: function () {
            this.loadPosts("popular")
        }
    })
    new Vue({
        el: "#category-list",
        data: {
            count: 0,
            items: [],
            loading: false
        },
        methods: {
            loadMore: function () {
                this.loading = true
                var currentCount = this.items.length;
                for (var i = 0, end = this.count - currentCount; i < end; ++i) {
                    this.items.push({
                        id: i + 1 + currentCount,
                        name: "category " + (i + 1 + currentCount),
                        posts: Math.trunc(Math.random() * 5 + 1)
                    })
                }
                this.loading = false
            }
        },
        mounted: function () {
            var vue = this
            var items = this.items
            axios.get("http://10.22.9.9:8080/api/categories?size=5")
                .then(function (rep) {
                    var data = rep.data
                    vue.count = data.page.totalElements
                    data._embedded.categories.forEach(function (category) {
                        items.push(category)
                    });
                })
        }
    })
    new Vue({
        el: "#archive-list",
        data: {
            count: 0,
            items: [],
            loading: false
        },
        methods: {
            loadMore: function () {
                this.loading = true
                var currentCount = this.items.length;
                for (var i = 0, end = this.count - currentCount; i < end; ++i) {
                    this.items.push({
                        id: i + 1 + currentCount,
                        name: "category " + (i + 1 + currentCount),
                        posts: Math.trunc(Math.random() * 5 + 1)
                    })
                }
                this.loading = false
            }
        },
        mounted: function () {
            this.count = 8
            for (var i = 0; i < 5; i++) {
                this.items.push({
                    id: i + 1,
                    name: "date str " + (i + 1),
                    posts: Math.trunc(Math.random() * 10 + 1)
                })
            }
        }
    })
})