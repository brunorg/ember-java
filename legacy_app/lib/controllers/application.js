require('ejr/core');

App.ApplicationController = Ember.Controller.extend({
  needs: ["messages"],
  messagesBinding: "controllers.messages",
  clearMessages: function() {
    this.get('messages').clearMessages();
  }
});

App.MessagesController = Ember.ObjectController.extend({
  errors: [],
  fieldErrors: [],
  sucess: [],
  clearMessages: function() {
    this.get('errors').clear();
    this.get('fieldErrors').clear();
    this.get('sucess').clear();
  },
  addMessage: function(msg) {
    var messageType = 'sucess';
    if (msg.type == 'Error') {
      messageType = 'errors';
    }
    if (msg.type == 'ViolationError') {
      messageType = 'fieldErrors';
    }
    var messages = this.get(messageType);
    messages.pushObject(Ember.Object.create(msg));
    this.set(messageType, messages);
  },
  addMessages: function(messages) {
    messages.forEach(function(msg) {
      this.addMessage(msg);
    }, this);
  },
  hasMessages: function() {
    return (this.get('errors').length + this.get('fieldErrors').length + this.get('sucess').length) > 0;
  }.property('errors.@each', 'fieldErrors.@each', 'sucess.@each')
});

App.TransactionalController = Ember.ObjectController.extend({
  didCreate: Ember.K,
  didUpdate: Ember.K,
  modelType: undefined,
  content: Ember.Object.create(),

  update: function() {
    App.MessagesController.prototype.clearMessages();
    var c = this.get('content'),
      transaction = c.get('transaction'),
      modelType = this.get('modelType');
    var ctrl = this;
    c.on('becameInvalid', function(record) {
      var errors = record.get('errors');
      App.MessagesController.prototype.addMessages(errors);
      var oldValue = record.toJSON();
      var t = record.get('transaction');
      t.rollback();
      t = App.store.transaction();
      record.materializeAttributes(oldValue);
      if (oldValue.id == undefined) {
        var newRecord = t.createRecord(modelType, oldValue);
        ctrl.set('content', newRecord);
      } else {
        t.add(record);
      }
    });
    c.on('didUpdate', function(record) {
      Ember.run.next(ctrl, function() {
        this.didUpdate(record);
      });
    });
    c.on('didCreate', function(record) {
      Ember.run.next(ctrl, function() {
        this.didCreate(record);
      });
    });
    transaction.commit();
  },

  rollback: function() {
    var content = this.get('content');

    if (content.get('isNew')) {
      content.deleteRecord();
    }
    if (content.get('isDirty')) {
      Ember.run.next(this, function() {
        content.rollback();

        Ember.run.next(this, function() {

          content.eachRelationship(function(name, relationship) {
            if (this.cacheFor(name) === undefined || relationship === undefined) {
              return;
            }

            var relatedItens = this.get(name);
            var allItems = App.store.all(relationship.type);
            Ember.Logger.log(relationship);

            allItems.get('store.recordCache').forEach(function(rec) {
              if (rec.id) {
                rec.get('stateManager').transitionTo('loaded');
                App.store.reloadRecord(rec);
              }
            }, relatedItens);

          }, content);

        });

      });
    }
    Ember.Logger.log('abandon transaction');
  },

  canSave: function() {
    var isNew = this.get('content.isNew'),
      isValid = this.get('content.isValid'),
      isDirty = this.get('content.isDirty');
    return (isNew && isValid && isDirty);
  }.property('content.isNew', 'content.isValid', 'content.isDirty'),

  canUpdate: function() {
    var isNew = this.get('content.isNew'),
      isValid = this.get('content.isValid'),
      isDirty = this.get('content.isDirty');
    return (!isNew && isValid && isDirty);
  }.property('content.isNew', 'content.isValid', 'content.isDirty')

});