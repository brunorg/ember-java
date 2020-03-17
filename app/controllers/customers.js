import Controller from "@ember/controller";
import { action } from "@ember/object";
import { tracked } from "@glimmer/tracking";

export default class CustomersController extends Controller {
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
    return record.destroyRecord();
  }
}
