import classic from 'ember-classic-decorator';
import EmberRouter from '@ember/routing/router';
import config from './config/environment';

@classic
class Router extends EmberRouter {
  location = config.locationType;
  rootURL = config.rootURL;
}

Router.map(function() {
  this.route('customers', function () {
    this.route('edit', { path: '/:customer_id' });
  });
  this.route('products', function() {
    this.route('edit', { path: '/:product_id' });
  });
  this.route('orders', function() {
    this.route('edit', { path: '/:order_id' });
  });
});

export default Router;
