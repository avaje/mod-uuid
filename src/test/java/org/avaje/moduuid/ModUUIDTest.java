package org.avaje.moduuid;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

public class ModUUIDTest {

  @Test
  public void testEncodeDecode() {
    UUID uuid = UUID.randomUUID();
    String encodedUUID = ModUUID.encode(uuid);
    System.out.println("ModUUID: " + encodedUUID);
    UUID uuid2 = ModUUID.decode(encodedUUID);

    Assert.assertEquals(uuid, uuid2);
  }

  @Test
  public void testNewId() {
   
    String encodedUUID = ModUUID.newId();
    System.out.println("newId: " + encodedUUID);
    UUID uuid2 = ModUUID.decode(encodedUUID);

    Assert.assertNotNull(uuid2);
  }
  
  @Test
  public  void testShortUid() {
    
    String shortId = ModUUID.newShortId();
    
    System.out.println(shortId);
    Assert.assertTrue(shortId.length() == 12);
  }
}
