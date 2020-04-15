package ember.sample.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.github.jasminb.jsonapi.annotations.Type;

import org.springframework.data.annotation.Transient;

/**
 * User
 */
@Type("users")
public class User extends BaseModel {

  @Email
  @NotEmpty
  private String username;

  @NotEmpty
  private String password;

  @NotEmpty
  private boolean active = false;

  @Transient
  private String gravatar;

  /**
   * @return String return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return boolean return the active
   */
  public boolean isActive() {
    return active;
  }

  /**
   * @param active the active to set
   */
  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * @return the gravatar
   */
  public String getGravatar() {
    return gravatar;
  }

  /**
   * @param gravatar the gravatar to set
   */
  public void setGravatar(String gravatar) {
    this.gravatar = gravatar;
  }

  @Override
  public String toString() {
    return String.format("User [id=%s, username='%s', active='%s']", id, username, active);
  }

}
