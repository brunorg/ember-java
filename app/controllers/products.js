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
    if (this.collapsed) {
      this.closeEditor();
    }
  }

  @action
  closeEditor() {
    this.collapsed = true;
    this.model.forEach(item => {
      if (item.hasDirtyAttributes) {
        item.rollbackAttributes();
      }
    });
    this.transitionToRoute("products");
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
