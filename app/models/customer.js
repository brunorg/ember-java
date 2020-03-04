import classic from 'ember-classic-decorator';
import { computed } from '@ember/object';
import Model, { attr, hasMany } from '@ember-data/model';

@classic
export default class Customer extends Model {
  @attr()
  firstName;

  @attr()
  lastName;

  @hasMany("order")
  orders;

  @computed("firstName", "lastName")
  get fullName() {
    return `${this.firstName} ${this.lastName}`;
  }
}
