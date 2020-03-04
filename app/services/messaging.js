import { tracked } from "@glimmer/tracking";
import { A } from '@ember/array';
import Service from '@ember/service';

export default class MessagingService extends Service {

  @tracked
  messages = {
    errors: A([]),
    success: A([]),
    fieldErrors: A([])
  };

  addError(item) {
    this.messages.errors.pushObject(item);
  }

  addSuccess(item) {
    this.messages.success.pushObject(item);
  }

  addFieldError(item) {
    this.messages.fieldErrors.pushObject(item);
  }

  remove(item) {
    this.messages.errors.removeObject(item);
    this.messages.success.removeObject(item);
    this.messages.fieldErrors.removeObject(item);
  }

  get hasMessages() {
    return (
      this.messages.errors.length > 0 ||
      this.messages.success.length > 0 ||
      this.messages.fieldErrors.length > 0
    );
  }

  empty() {
    this.messages.errors.clear();
    this.messages.success.clear();
    this.messages.fieldErrors.clear();
    this.messages = this.messages;
  }
}
