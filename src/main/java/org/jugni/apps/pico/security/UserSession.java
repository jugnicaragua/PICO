package org.jugni.apps.pico.security;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.NoResultException;
import org.jugni.apps.pico.data.HibernateHelper;
import org.jugni.apps.pico.security.annotation.Access;
import org.jugni.apps.pico.security.dao.UserDao;
import org.jugni.apps.pico.security.exception.InvalidAccessException;
import org.jugni.apps.pico.security.model.Rol;
import org.jugni.apps.pico.security.model.User;
import org.jugni.apps.pico.security.model.UserInfo;
import org.jugni.apps.pico.ui.util.BaseInternalView;

public final class UserSession {

  private static final Logger LOGGER = Logger.getLogger(UserSession.class.getName());
  private final HibernateHelper helper;
  private UserInfo userInfo;
  private final UserDao userDao;

  public UserSession(HibernateHelper helper) {
    this.helper = helper;
    this.userDao = new UserDao(helper.getSessionFactory());
  }

  public final void login(final String username, final String password)
      throws InvalidAccessException {
    try {
      User user = userDao.getByUsername(username);
      if (user != null) {
        try {
          if (validateText(password, user.getPassword())) {
            this.userInfo = new UserInfo(user.getUsername(), user.getActive(), user.getRol());
          } else {
            throw new InvalidAccessException("Credenciales invalidas");
          }
        } catch (InvalidAccessException e) {
          throw e;
        } catch (Exception e) {
          throw new RuntimeException("No es posible validar el usuario");
        }
      } else {
        throw new InvalidAccessException("Credenciales invalidas");
      }
    } catch (NoResultException noResultException) {
      throw new InvalidAccessException("Credenciales invalidas");
    }
  }

  public void verifyAdmin() {
    if (!this.userDao.existAdminUser()) {
      registerAdmin();
    }
  }

  private void registerAdmin() {
    try {
      User admin = new User("admin", encode("admin"));
      admin.setRol(Rol.ADMIN);
      admin.setActive(true);
      this.userDao.save(admin);
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

  public void logout() {
    this.userInfo = null;
  }

  public void registerAccess(BaseInternalView view) throws InvalidAccessException {
    Access access = view.getClass().getAnnotation(Access.class);

    if (access != null) {
      LOGGER.info("User [" + ((this.userInfo != null) ? this.userInfo.getUsername() : "Anonymous")
          + "] requires access to " + (access.name()));

      if (this.isLoggin()) {
        boolean grant = false;
        for (var rol : access.rol()) {
          if (userInfo.getRol().equals(rol)) {
            grant = true;
            break;
          }
        }

        if (!grant) {
          ((SessionEvent) view).invalidRol();
          throw new InvalidAccessException("Rol invalido");
        }

      } else {
        ((SessionEvent) view).invalidSession();
        throw new InvalidAccessException("No ha iniciado sessión");
      }
    } else {
      throw new RuntimeException("La vista interna debe de estar anotada con Access");
    }
  }

  public boolean isLoggin() {
    return this.userInfo != null;
  }

  public UserInfo getUserInfo() {
    return this.userInfo;
  }

  private final String encode(final String text) throws Exception {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);

    var iterationCount = 65536;
    var keyLength = 128;

    KeySpec spec = new PBEKeySpec(text.toCharArray(), salt, iterationCount, keyLength);
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

    byte[] hash = factory.generateSecret(spec).getEncoded();
    return iterationCount + ":" + toHex(salt) + ":" + toHex(hash);
  }

  private final boolean validateText(final String textOriginal, final String textStorage)
      throws Exception {
    String[] parts = textStorage.split(":");
    int iterations = Integer.parseInt(parts[0]);
    byte[] salt = fromHex(parts[1]);
    byte[] hash = fromHex(parts[2]);

    PBEKeySpec spec = new PBEKeySpec(textOriginal.toCharArray(), salt, iterations, 128);
    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] hashOriginal = skf.generateSecret(spec).getEncoded();

    int diff = hash.length ^ hashOriginal.length;
    for (int i = 0; i < hash.length && i < hashOriginal.length; i++) {
      diff |= hash[i] ^ hashOriginal[i];
    }
    return diff == 0;
  }

  private String toHex(byte[] array) throws NoSuchAlgorithmException {
    BigInteger bi = new BigInteger(1, array);
    String hex = bi.toString(16);
    int paddingLength = (array.length * 2) - hex.length();
    if (paddingLength > 0) {
      return String.format("%0" + paddingLength + "d", 0) + hex;
    } else {
      return hex;
    }
  }

  private byte[] fromHex(String hex) throws NoSuchAlgorithmException {
    byte[] bytes = new byte[hex.length() / 2];
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
    }
    return bytes;
  }
}
