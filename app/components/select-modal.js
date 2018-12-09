import Component from '@ember/component';

export default Component.extend({
  actions: {
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
