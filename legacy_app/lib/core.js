require('ejr/vendor/jquery');
require('ejr/vendor/handlebars');
require('ejr/vendor/ember');
require('ejr/vendor/ember-data');

App = Ember.Application.create({
  VERSION: '0.1',
  LOG_TRANSITIONS: true
});

App.generateGuid = function() {
  var result, i, j;
  result = '';
  for (j = 0; j < 32; j++) {
    if (j == 8 || j == 12 || j == 16 || j == 20) {
      result = result + '-';
    }
    i = Math.floor(Math.random() * 16).toString(16).toUpperCase();
    result = result + i;
  }
  return result;
}

// Handlebars Helpers
Ember.Handlebars.registerBoundHelper('money', function(value) {
  if (isNaN(value)) {
    return "$ " + "0.00";
  }
  var remainder = parseInt((value * 100) % 100, 10);
  var intPart = parseInt(value, 10);
  if (remainder === 0) {
    return "$ " + intPart + ".00";
  }
  if (remainder < 10) {
    return "$ " + intPart + ".0" + remainder;
  } else {
    return "$ " + intPart + "." + remainder;
  }
});