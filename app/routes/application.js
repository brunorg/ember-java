import { action } from "@ember/object";
import { inject as service } from "@ember/service";
import Route from '@ember/routing/route';
import ApplicationRouteMixin from "ember-simple-auth/mixins/application-route-mixin";

export default class ApplicationRoute extends Route.extend(ApplicationRouteMixin) {
  @service messaging;
  @service session;


  beforeModel() {
    if (!this.session.isAuthenticated) {
      this.transitionTo("login");
    }
  }

  setupController(controller, model) {
    super.setupController(controller, model);
    this.controllerFor("application").set("session", this.session);
  }

  @action
  error(error) {
    if (error.status === "403" || error.status === "401") {
      this.replaceWith("login");
      this.messaging.addError("Authentication error");
    } else {
      this.messaging.addError(error);
      return true;
    }
  }
}
