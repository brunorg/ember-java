import Component from '@glimmer/component';
import { inject as service } from '@ember/service';
import { action, computed } from "@ember/object";

export default class AvatarComponent extends Component {
  @service session;

  get isAuthenticated() {
    return this.session.isAuthenticated;
  }

  @computed('session.data.login')
  get login() {
    return this.session.data["login"];
  }

  get gravatar() {
    return this.session.data.authenticated["Gravatar-Id"];
  }

  @action
  logout() {
    this.session.set("data.login", null);
    this.session.invalidate();
  }

}
