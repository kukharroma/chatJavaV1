var UserCommonView = Backbone.View.extend({
    container : "#users_list",
    initialize: function(attr){
        this.loadUsers()
        var _this = this;

        setInterval(function(){
           _this.loadUsers();
        }, 30000)
    },

    loadUsers: function(){
        var _this = this;
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
        $(this.container).empty();

        var listGrouped = _.groupBy(listUsers, function(obj){ return obj.online})

        render(listGrouped.true)
        render(listGrouped.false)

        function render(list){
            _.each(list, function(user){
                var model = new UserItemModel(user);
                new UserItemView({model: model, container: _this.container})
            })
        }
    }
})