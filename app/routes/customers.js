import Route from '@ember/routing/route';

export default Route.extend({
  model() {
    return [{
      id: 123,
      firstName: "John",
      lastName: "Doe",
      orders: []
    }, {
      id: 456,
      firstName: "Frodo",
      lastName: "Baggins",
      orders: []
    }];
  },
  actions: {
    remove(data) {
    }
  }
});
