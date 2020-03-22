import Controller from "@ember/controller";
import { action } from "@ember/object";
import { tracked } from "@glimmer/tracking";
import { inject as service } from "@ember/service";

export default class OrdersController extends Controller {
  @service messaging;
  @tracked collapsed = true;

  get notCollapsed() {
    return !this.collapsed;
  }

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
      item.items.forEach(subItem => {
        if (subItem.hasDirtyAttributes) {
          subItem.rollbackAttributes();
        }
      });
      if (item.id) {
        item.reload();
      }
      if (item.hasDirtyAttributes) {
        item.unloadRecord();
      }
    });
    this.transitionToRoute("orders");
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
