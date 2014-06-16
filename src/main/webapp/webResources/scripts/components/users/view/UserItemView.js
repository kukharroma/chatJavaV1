var UserItemView = Backbone.View.extend({
    template: "#UserItemTmpl",
    initialize: function(attr){
        this.container = attr.container;
        this.model = attr.model;

        this.render();
    },

    render: function(){
        this.$el = _.template($(this.template).html().replace(/#/g,"%"), this.model.toJSON())
        $(this.container).append(this.$el);
    }
})