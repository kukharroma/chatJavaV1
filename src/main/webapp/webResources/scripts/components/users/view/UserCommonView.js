var UserCommonView = Backbone.View.extend({
    container : "#users_list",
    initialize: function(attr){
        this.loadUsers()

//        setInterval(function(){
//            this.loadUsers();
//        }, 3000)
    },

    loadUsers: function(){
        var _this = this
        $.ajax({
            url: "getAllUsers",
            type: "GET",
            contentType: "application/json",
            success: function(data){
                _this.renderChildren(data)
            }
        })
    },

    renderChildren: function(listUsers){
        var _this = this
        _.each(listUsers, function(user){
            var model = new UserItemModel(user);
            new UserItemView({model: model, container: _this.container})
        })
    }
})