import Component from "@glimmer/component";
import { action } from "@ember/object";
import { inject as service } from '@ember/service';

export default class MessageNotificationsComponent extends Component {
  @service messaging;

  get errors() {
    return this.messaging.messages.errors;
  }

  get success() {
    return this.messaging.messages.success;
  }

  get fieldErrors() {
    return this.messaging.messages.fieldErrors;
  }

  @action
  clearMessages() {
    this.messaging.empty();
  }
}
