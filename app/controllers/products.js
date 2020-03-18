import Controller from "@ember/controller";
import { action } from "@ember/object";
import { tracked } from "@glimmer/tracking";
import { inject as service } from "@ember/service";

export default class ProductsController extends Controller {
  @service messaging;
  @tracked collapsed = true;

  @action
  toggle() {
    this.collapsed = !this.collapsed;
  }

  @action
  openEditor() {
    this.collapsed = false;
  }

  @action
  remove(record) {
    let messaging = this.messaging;

    return record.destroyRecord().catch(function(reason) {
        messaging.addError(reason);
      });
  }
}
