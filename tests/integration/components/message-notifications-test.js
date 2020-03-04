import { module, test } from 'qunit';
import { setupRenderingTest } from 'ember-qunit';
import { click, render } from '@ember/test-helpers';
import { hbs } from 'ember-cli-htmlbars';

module('Integration | Component | message-notifications', function(hooks) {
  setupRenderingTest(hooks);

  test('it renders', async function(assert) {
    // Set any properties with this.set('myProperty', 'value');
    // Handle any actions with this.set('myAction', function(val) { ... });

    await render(hbs`<MessageNotifications />`);

    assert.equal(this.element.textContent.trim(), '');

    // Template block usage:
    await render(hbs`
      <MessageNotifications>
        template block text
      </MessageNotifications>
    `);

    assert.equal(this.element.textContent.trim(), 'template block text');

    const messaging = this.owner.lookup('service:messaging');

    messaging.addError('This is an error!!!');

    await render(hbs`<MessageNotifications />`);

    assert.equal(
      this.element.querySelector('.msg .msg-error').textContent.trim(),
      'This is an error!!!',
      'First message notificaion is an error'
    );

    //Click on the button
    await click('#closeMessages');

    assert.equal(
      this.element.querySelector(".row").textContent.trim(),
      "",
      "No more messages after clear messages"
    );

  });
});
