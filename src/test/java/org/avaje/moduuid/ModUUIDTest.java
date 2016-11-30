package org.avaje.moduuid;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

public class ModUUIDTest {

  @Test
  public void testEncode() {
    UUID uuid = UUID.randomUUID();
    String encodedUUID = ModUUID.encode(uuid);
    System.out.println("ModUUID: " + encodedUUID);
    Assert.assertNotNull(encodedUUID);
    Assert.assertEquals(22, encodedUUID.length());
  }

  @Test
  public void testNewId() {

    String encodedUUID = ModUUID.newId();
    System.out.println("newId: " + encodedUUID);
    Assert.assertNotNull(encodedUUID);
    Assert.assertEquals(22, encodedUUID.length());
  }

  @Test
  public  void testShortUid() {

    String shortId = ModUUID.newShortId();
    
    System.out.println(shortId);
    Assert.assertTrue(shortId.length() == 12);
  }

  @Test
  public  void exercise_a_bit() {

    Set<String> unique = new HashSet<>();
    for (int i = 0; i < 10_000_000; i++) {
      unique.add(ModUUID.newShortId());
    }

    Assert.assertEquals(10_000_000, unique.size());
  }

}
