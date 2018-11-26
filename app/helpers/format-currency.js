import { helper } from '@ember/component/helper';

export function formatCurrency(value, namedArgs) {
  if (isNaN(value)) {
    value = 0;
  }

  let sign = namedArgs.sign === undefined ? '$' : namedArgs.sign;
  let remainder = parseInt((value * 100) % 100, 10);
  let intPart = parseInt(value, 10);

  if (remainder.toString().length === 1) {
    remainder = '0' + remainder;
  }
  return `${sign} ${intPart}.${remainder}`;
}

export default helper(formatCurrency);
