import Route from '@ember/routing/route';

export default Route.extend({
  model() {
    return this.store.findAll('order', { include: 'customer' });
  },
  actions: {
    remove(record) {
      return record.destroyRecord();
    }
  }
});
