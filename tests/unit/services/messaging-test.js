import { module, test } from 'qunit';
import { setupTest } from 'ember-qunit';

module('Unit | Service | messaging', function(hooks) {
  setupTest(hooks);

  // Replace this with your real tests.
  test('it exists', function(assert) {
    let service = this.owner.lookup('service:messaging');
    assert.ok(service);

    service.addError('This is an error message');
    service.addSuccess('This is a success message');
    service.addFieldError('This is a field error message');

    assert.equal(service.messages.errors[0], 'This is an error message');
    assert.equal(service.messages.success.length, 1);
    assert.equal(service.messages.fieldErrors.length, 1);

    service.remove('This is a success message');
    assert.equal(service.messages.success.length, 0);

    service.empty();
    assert.equal(service.messages.errors.length, 0);
    assert.equal(service.messages.fieldErrors.length, 0);

  });
});
