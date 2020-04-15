import Controller from "@ember/controller";
import { inject as service } from "@ember/service";
import { action } from "@ember/object";

export default class LoginController extends Controller {
  @service session;
  @service messaging;

  @action
  async authenticate() {
    let { identification, password } = this;
    try {
      await this.session.authenticate('authenticator:oauth2', identification, password);
    } catch(error) {
      if (error.status) {
        this.messaging.addError("Login failed");
      } else {
        this.messaging.addError(error.error || error);
      }

    }

    if (this.session.isAuthenticated) {
      this.session.set("data.login", identification);
    }
  }

}
