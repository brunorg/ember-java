import { action } from "@ember/object";
import { inject as service } from "@ember/service";
import Route from '@ember/routing/route';

export default class ApplicationRoute extends Route {
  @service messaging;

  @action
  error(error) {
    if (error.status === "403") {
      this.replaceWith("login");
    } else {
      this.messaging.addError(error);
      return true;
    }
  }
}
