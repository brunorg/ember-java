import JSONAPIAdapter from '@ember-data/adapter/json-api';
import DataAdapterMixin from "ember-simple-auth/mixins/data-adapter-mixin";
import { computed } from "@ember/object";
import config from "../config/environment";

export default class Application extends JSONAPIAdapter.extend(DataAdapterMixin) {

  host = config.apiURL;
  namespace = config.apiNamespace;

  @computed("session.data.authenticated.access_token")
  get headers() {
    let headers = {};
    if (this.session.isAuthenticated) {
      // OAuth 2
      headers[
        "Authorization"
      ] = `Bearer ${this.session.data.authenticated.access_token}`;
    }

    return headers;
  }
}
