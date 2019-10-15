import Component from '@ember/component';

export default Component.extend({

  value: '',

  init() {
    this._super(...arguments);
    this.filter('').then((results) => this.set('results', results));
  },

  actions: {
    query() {
      var query = this.get('search');
      this.filter(query).then(results => this.set("results", results));
    },

    launchModal() {
      this.set('confirmShown', true);
    },

    submitConfirm() {
      // trigger action on parent component
      this.set('confirmShown', false);
    },

    cancelConfirm() {
      this.set('confirmShown', false);
    }
  }
});
