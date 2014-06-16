var MessagesItemModel = Backbone.Model.extend({
    defaults: {
        id: 0,
        sender: {},
        formattedCreationTime: new Date(),
        message: ""
    }
})