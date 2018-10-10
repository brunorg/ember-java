import Component from '@ember/component';

export default Component.extend({
  init() {
    this._super(...arguments);
    this.set('messages', {
      'errors': ['Error 1', 'Error 2'],
      'success': ['Success 1', 'Success 2'],
      'fieldErrors': [
        {
          'class_name': 'java.lang.Class',
          'property': 'name',
          'message': 'Not found'
        }
      ]
    });
  },
  hasMessages: function () {
    return this.messages.length > 0;
  },
  actions: {
    clearMessages() {
      this.set('messages', {
        'errors': []
      });
    }
  }

});
