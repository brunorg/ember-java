import { module, test } from 'qunit';
import { setupRenderingTest } from 'ember-qunit';
import { render } from '@ember/test-helpers';
import { hbs } from 'ember-cli-htmlbars';

module('Integration | Helper | format-currency', function(hooks) {
  setupRenderingTest(hooks);

  // Replace this with your real tests.
  test('it renders', async function(assert) {

    this.set("inputValue", null);
    await render(hbs`{{format-currency inputValue}}`);
    assert.equal(this.element.textContent.trim(), "$ 0.00");

    this.set('inputValue', '');
    await render(hbs`{{format-currency inputValue sign="R$"}}`);
    assert.equal(this.element.textContent.trim(), 'R$ 0.00');

    this.set('inputValue', 'foo');
    await render(hbs`{{format-currency inputValue}}`);
    assert.equal(this.element.textContent.trim(), '$ 0.00');

    this.set('inputValue', '1234');
    await render(hbs`{{format-currency inputValue}}`);
    assert.equal(this.element.textContent.trim(), '$ 1234.00');

    this.set('inputValue', '1234.1234');
    await render(hbs`{{format-currency inputValue}}`);
    assert.equal(this.element.textContent.trim(), '$ 1234.12');

  });
});
