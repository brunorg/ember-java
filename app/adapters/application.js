import classic from 'ember-classic-decorator';
import JSONAPIAdapter from '@ember-data/adapter/json-api';

@classic
export default class Application extends JSONAPIAdapter {
  host = 'http://localhost:8080';
  namespace = 'api';
}
