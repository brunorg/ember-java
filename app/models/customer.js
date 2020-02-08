import Model, { attr, hasMany } from '@ember-data/model';
import { computed } from "@ember/object";

export default Model.extend({
  firstName: attr(),
  lastName: attr(),
  orders: hasMany("order"),
  fullName: computed("firstName", "lastName", function() {
    return `${this.firstName} ${this.lastName}`;
  })
});
