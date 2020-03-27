import { computed } from '@ember/object';
import Model, { attr, hasMany } from '@ember-data/model';

export default class CustomerModel extends Model {
  @attr() firstName;
  @attr() lastName;

  @hasMany("order")
  orders;

  @computed("firstName", "lastName")
  get fullName() {
    return `${this.firstName} ${this.lastName}`;
  }
}
