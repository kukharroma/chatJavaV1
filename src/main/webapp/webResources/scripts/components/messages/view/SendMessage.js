var SendMessage = Backbone.View.extend({
    template: "#SendMessageTmpl",
    el: "#messageInputBlock",
    events: {
        "click [send-message]":"send",
        "keyup #messageInput": "messageInputKeyDown"
    },


    initialize: function(){
        this.render();
    },

    render: function(){
        var template = $(this.template).html()
        this.$el.html(template)
    },

    send: function(){
        var _this = this;
        var textArea = _this.$("#messageInput")
        if(textArea.val()){
            $.ajax({
                url: "sendMessageToChat",
                type: "POST",
                data: {text: textArea.val().toString()},
                success: function(data){
                    textArea.val('')
                }
            })
        }

    },

    messageInputKeyDown: function(event) {
        if (event.keyCode == 13 && event.shiftKey) {
            this.send();
        }
    }
})