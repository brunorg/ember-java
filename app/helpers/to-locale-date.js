import { helper } from '@ember/component/helper';

export default helper(function toLocaleDate([value = new Date()] /*, hash*/) {
  return value.toLocaleDateString() + " " + value.toLocaleTimeString();
});
