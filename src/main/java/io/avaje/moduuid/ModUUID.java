package io.avaje.moduuid;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

/**
 * Provides a modified base64 encoded UUID and shorter 12 character random unique value.
 * <p>
 * <h3>newId()</h3>
 * <p>
 * It produces a 22 character string that is a base64 encoded UUID with the +
 * and / characters replaced with - and _ so as to be URL safe without requiring
 * encoding.
 * </p>
 * <h3>newShortId()</h3>
 * <p>
 * It produces a 12 character string that base64 encoded random number (72 bit).
 * </p>
 * <p>
 * Note that this now internally uses java.util.Base64 to encode the values.
 * </p>
 */
public class ModUUID {

  private static final SecureRandom shortIdSecureRandom = new SecureRandom();

  private static final Base64.Encoder urlEncoder = Base64.getUrlEncoder();

  private static final long MILLIS_DAY = 1000 * 60 * 60 * 24;

  /**
   * Return epoch today as hex string.
   */
  public static String today() {
    return Integer.toHexString((int)(System.currentTimeMillis() / (MILLIS_DAY)));
  }

  /**
   * Return a 12 character string using a 72 bit randomly generated ID encoded
   * in modified base64.
   * <p>
   * A UUID is 128 bits and this is 72 bits so quite a bit smaller but still
   * very random with one in 4.7 * 10^21 chance of a collision.
   * </p>
   */
  public static String newShortId() {

    // Random 72 bits
    byte[] randomBytes = new byte[9];
    shortIdSecureRandom.nextBytes(randomBytes);
    return encode64(randomBytes);
  }

  /**
   * Return a 16 character string using 96 bit randomly generated ID encoded
   * in modified base64.
   * <p>
   * Provides a one in 7.9 * 10^28 chance of a collision.
   * </p>
   */
  public static String newMediumId() {
    // Random 96 bits
    byte[] randomBytes = new byte[12];
    shortIdSecureRandom.nextBytes(randomBytes);
    return encode64(randomBytes);
  }

  /**
   * Return a new UUID in a modified base64 encoded format as 22 characters.
   * <p>
   * Note that the + and / characters are replaced with - and _ for use in URL's
   * without requiring URL encoding.
   * </p>
   */
  public static String newId() {
    return encode(UUID.randomUUID());
  }

  /**
   * Encode the UUID in modified base64 encoding.
   * <p>
   * Note that the + and / characters are replaced with - and _ for use in URL's
   * without requiring URL encoding.
   * </p>
   */
  public static String encode(UUID uuid) {
    String val = encode64(asByteArray(uuid));
    // trim the trailing "=="
    return val.substring(0, val.length() - 2);
  }

  private static String encode64(byte[] bytes) {
    return urlEncoder.encodeToString(bytes);
  }

  private static byte[] asByteArray(UUID uuid) {

    long msb = uuid.getMostSignificantBits();
    long lsb = uuid.getLeastSignificantBits();
    byte[] buffer = new byte[16];

    for (int i = 0; i < 8; i++) {
      buffer[i] = (byte) (msb >>> 8 * (7 - i));
    }
    for (int i = 8; i < 16; i++) {
      buffer[i] = (byte) (lsb >>> 8 * (7 - i));
    }
    return buffer;
  }
}
