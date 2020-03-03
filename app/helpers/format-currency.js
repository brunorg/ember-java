import { helper } from '@ember/component/helper';

export default helper(function formatCurrency([value = 0], { sign = '$'} /*, hash*/) {
  if (!value || isNaN(value)) {
    value = 0;
  }

  let remainder = parseInt((value * 100) % 100, 10);
  let intPart = parseInt(value, 10);

  if (remainder.toString().length === 1) {
    remainder = "0" + remainder;
  }
  return `${sign} ${intPart}.${remainder}`;
});
