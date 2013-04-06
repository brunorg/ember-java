require('ejr/core');

App.ajaxLoadingController = Ember.Object.create({
  requests: [],
  loading: false,
  addRequest: function(requestId) {
    this.get('requests').push(requestId);
  },
  removeRequest: function(requestId) {
    var reqs = this.get('requests');
    var reqs = reqs.without(requestId);
    this.set('requests', reqs);
  }
});

App.ajaxLoadingController.addObserver('requests', function() {
  var loading = this.get('requests').length > 0;
  this.set('loading', loading);
});

$.ajaxSetup({
  error: function(jqXHR, textStatus, errorThrown) {
    var msg = textStatus + " : " + jqXHR.statusText + "\n" + errorThrown;
    App.MessagesController.prototype.addMessage({
      message: msg,
      type: "Error"
    });
  }
});

$(document).ajaxSend(function(e, jqXHR, settings) {
  var requestId = App.generateGuid();
  App.ajaxLoadingController.addRequest(requestId);
  $(settings).data('requestId', requestId);
});

$(document).ajaxComplete(function(e, jqXHR, settings) {
  var requestId = $(settings).data('requestId');
  App.ajaxLoadingController.removeRequest(requestId);
});