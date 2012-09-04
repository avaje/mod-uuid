package org.avaje.moduuid;

import java.io.IOException;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

/**
 * Provides a modified base64 encoded UUID.
 * <p>
 * It produces a 22 character string that is a base64 encoded UUID with the +
 * and / characters replaced with - and _ so as to be URL safe without requiring
 * encoding.
 * </p>
 */
@SuppressWarnings("restriction")
public class ModUUID {

  private static final int LENGTH_OF_UUID = 22;

  /**
   * Return a new UUID in a modified base64 encoded format.
   */
  public static String newId() {
    return encode(UUID.randomUUID());
  }

  /**
   * Encode the UUID in modified base64 encoding.
   */
  public static String encode(UUID uuid) {
    return format(encode64(asByteArray(uuid)));
  }

  /**
   * Decode the encoded value into a UUID.
   */
  public static UUID decode(String encoded64) {
    try {
      // restore plus and slash characters
      encoded64 = encoded64.replace('-', '+');
      encoded64 = encoded64.replace('_', '/');
      encoded64 += "==";

      // decode back to UUID
      byte[] ba = decode64(encoded64);
      return toUUID(ba);

    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  private static String format(String s) {
    // remove last 2 padding characters ==
    s = s.substring(0, LENGTH_OF_UUID);

    // replace + and / with - and _ to make URL and filename safe
    s = s.replace('+', '-');
    s = s.replace('/', '_');
    return s;
  }

  private static String encode64(byte[] ba) {
    return DatatypeConverter.printBase64Binary(ba);
  }

  private static byte[] decode64(String s) throws IOException {
    return DatatypeConverter.parseBase64Binary(s);
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

  private static UUID toUUID(byte[] byteArray) {

    long msb = 0;
    long lsb = 0;
    for (int i = 0; i < 8; i++) {
      msb = (msb << 8) | (byteArray[i] & 0xff);
    }
    for (int i = 8; i < 16; i++) {
      lsb = (lsb << 8) | (byteArray[i] & 0xff);
    }
    UUID result = new UUID(msb, lsb);

    return result;
  }
}
