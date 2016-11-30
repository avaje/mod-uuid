avaje-moduuid
=============

Provides a modified base64 encoded UUID (22 character wide UUID)


## examples

```java
    String encodedUUID = ModUUID.newId();
    
    System.out.println("newId: " + encodedUUID);
```

```console
newId: UdaQApeRQHWKrVRJ_sUvMw
```


```java
    String shortId = ModUUID.newShortId();
    
    System.out.println("shortId: " + shortId);
```

```console
shortId: qmGG8bdmDfQB
```

## notes

Internally this uses java.util.Base64 and specifically the Base64.getUrlEncoder() which uses <code>-</code> and <code>_</code> producing URL safe string values.
