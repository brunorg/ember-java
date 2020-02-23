import Component from "@glimmer/component";
import { tracked } from "@glimmer/tracking";
import { action } from "@ember/object";
import { A } from '@ember/array';

export default class MessageNotificationsComponent extends Component {
  @tracked messages = {
    errors: A([]),
    success: A([]),
    fieldErrors: A([])
  };

  get hasMessages() {
    return (
      this.messages.errors.length > 0 ||
      this.messages.success.length > 0 ||
      this.messages.fieldErrors.length > 0
    );
  }

  @action
  clearMessages() {
    this.messages.errors.clear();
    this.messages.success.clear();
    this.messages.fieldErrors.clear();
    this.messages = this.messages;
  }
}
