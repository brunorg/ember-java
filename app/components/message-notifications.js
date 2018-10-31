import Component from '@ember/component';
import { computed } from '@ember/object';

export default Component.extend({
  init() {
    this._super(...arguments);
    this.set('messages', {
      'errors': [],
      'success': [],
      'fieldErrors': []
    });
    /* this.set('messages', {
      'errors': ['Error 1', 'Error 2'],
      'success': ['Success 1', 'Success 2'],
      'fieldErrors': [
        {
          'class_name': 'java.lang.Class',
          'property': 'name',
          'message': 'Not found'
        }
      ]
    }); */
  },
  hasMessages: computed('messages', function () {
    return this.messages.errors.length > 0 || this.messages.success.length > 0 || this.messages.fieldErrors.length > 0;
  }),
  actions: {
    clearMessages() {
      this.set('messages', {
        'errors': [],
        'success': [],
        'fieldErrors': []
      });
    }
  }

});
