require('ejr/core');

App.FormView = Ember.View.extend({
  tagName: "form",
  willDestroyElement: function() {
    this.get('controller').send('rollback');
  }
});

App.TextField = Ember.TextField.extend({

  keyPress: function(e) {
    if (e.which === 13) {
      e.preventDefault();
      e.stopPropagation();
      return false;
    }
  }

});

App.IdField = App.TextField.extend({
  attributeBindings: ['readonly', 'value'],
  readonly: "readonly",
  valueBinding: "controller.id",
  placeholder: "Id"
});

App.IntegerField = Ember.TextField.extend({
  oldValue: "0",
  timeoutFunction: undefined,
  format: function() {
    var value = this.get("value");
    var oldValue = this.get("oldValue");
    if (isNaN(value) || !value.match(/^\d*$/)) {
      value = oldValue;
    }
    this.set("value", value);
    this.set("oldValue", value);
  },

  keyUp: function(e) {
    this.format();
  },
});

App.MoneyField = Ember.TextField.extend({
  oldValue: "00.0",
  timeoutFunction: undefined,
  format: function() {
    var value = this.get("value");
    var oldValue = this.get("oldValue");
    if (isNaN(value) || !value.match(/^(\d*|\d+\.\d{0,2})$/)) {
      value = oldValue;
    }
    this.set("value", value);
    this.set("oldValue", value);
  },

  keyUp: function(e) {
    this.format();
  },
});