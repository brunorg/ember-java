import Route from '@ember/routing/route';

export default Route.extend({
  model() {
    return this.store.findAll('order');
  },
  actions: {
    remove(record) {
      return record.destroyRecord();
    }
  }
});
