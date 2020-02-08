import JSONAPIAdapter from '@ember-data/adapter/json-api';

export default JSONAPIAdapter.extend({
  host: 'http://localhost:8080',
  namespace: 'api'
});
