import OAuth2PasswordGrantAuthenticator from "ember-simple-auth/authenticators/oauth2-password-grant";
import classic from "ember-classic-decorator";
import config from "../config/environment";

@classic
export default class OAuth2Authenticator extends OAuth2PasswordGrantAuthenticator {
  serverTokenEndpoint = config.apiURL + "/" + config.apiNamespace + "/login";
}
