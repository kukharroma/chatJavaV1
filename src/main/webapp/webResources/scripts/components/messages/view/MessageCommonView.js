
var MessagesCommonView = Backbone.View.extend({
    container : "#messages_list",

    initialize: function(attr){
        this.loadMessages()
        var _this = this

        setInterval(function(){
            _this.loadLastSecondMessages(_this)
        }, 1000)

//        this.listenTo(Backbone, "scroll-list-messages",this.scrollTo,this)
    },

    loadMessages: function($this){
        var _this = $this || this
        $.ajax({
            url: "getLasHundredMessages",
            type: "GET",
            contentType: "application/json",
            success: function(data){
                _this.renderChildren(data)
                _this.timeLastMessage = _.last(data)
            }
        })
    },

    loadLastSecondMessages: function($this){
        $.ajax({
            url: "getMessagesFromSecond",
            type: "GET",
            contentType: "application/json",
            data : {
                dateFrom : $this.timeLastMessage ? $this.timeLastMessage.date : new Date().getTime()-1000
            },
            success: function(data){
                if(data.length){
                    $this.renderChildren(data)
                    if(data.length) $this.timeLastMessage = _.last(data)
                    $this.scrollTo("bottom")
                }
            }
        })
    },


    renderChildren: function(listUsers){
        var _this = this
        _.each(listUsers, function(user){
            var model = new MessagesItemModel(user);
            new MessagesItemView({model: model, container: _this.container})
        })
    },

    scrollTo: function(type){

        var height = _.reduce($("#messages_list").children(), function(height, el){
            var currentHeight = typeof height === "number" ? height : $(height).height()
            return currentHeight + $(el).height()
        })

        switch (type){
            case "top": $(this.container).scrollTop(0)
                break;
            case "bottom": $(this.container).scrollTop(height)
                break;
        }
    }
})