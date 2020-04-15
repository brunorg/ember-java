package ember.sample;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static ember.sample.SecurityConstants.EXPIRATION_TIME;
import static ember.sample.SecurityConstants.HEADER_GRAVATAR_ID;
import static ember.sample.SecurityConstants.HEADER_STRING;
import static ember.sample.SecurityConstants.SECRET;
import static ember.sample.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException {
    ember.sample.model.User creds = null;
    try {
      if (req.getContentType().equalsIgnoreCase("application/x-www-form-urlencoded")) {
        creds = new ember.sample.model.User();
        creds.setUsername(req.getParameter("username"));
        creds.setPassword(req.getParameter("password"));
      } else {
        creds = new ObjectMapper().readValue(req.getInputStream(), ember.sample.model.User.class);
      }

      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
      Authentication auth) throws IOException, ServletException {

    String token = JWT.create().withSubject(((User) auth.getPrincipal()).getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(SECRET.getBytes()));
    String gravatarId = md5(((User) auth.getPrincipal()).getUsername());
    if (req.getContentType().equalsIgnoreCase("application/x-www-form-urlencoded")) {
      res.setContentType("application/json;charset=UTF-8");
      res.getOutputStream().print("{\"access_token\":\""+ token +"\",\"token_type\":\"bearer\",\""+ HEADER_GRAVATAR_ID+"\":\""+ gravatarId+"\"}");
    }
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    res.addHeader(HEADER_GRAVATAR_ID, gravatarId);
  }


  // Source:
  // https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
  public String md5(String text) {
    String hash = null;
    try {
      // Create MessageDigest instance for MD5
      MessageDigest md = MessageDigest.getInstance("MD5");
      // Add password bytes to digest
      md.update(text.getBytes());
      // Get the hash's bytes
      byte[] bytes = md.digest();
      // This bytes[] has bytes in decimal format;
      // Convert it to hexadecimal format
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      // Get complete hashed password in hex format
      hash = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hash;
  }

}
