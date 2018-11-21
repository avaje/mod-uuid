mod-uuid
=============

Provides:
 - newId() : 22 character wide UUID as modified base64 encoded UUID. This provides
a more compact encoding relative to the traditional 36 character wide UUID.
- newShortId() : 12 character string using a 72 bit randomly generated ID encoded in modified base64.
- newMediumId() : 16 character string using 96 bit randomly generated ID encoded in modified base64.
- today() : epoch today as hex string


This also provides short and medium random id strings base64 encoded. 


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

```java
    String mediumId = ModUUID.newMediumId();
    
    System.out.println("mediumId: " + mediumId);
```

```console
mediumId: n9-VD8dJqDmpfUMn
```

## Some examples of today, short, medium and UUID values

```console

---------------  now:1488507493427 today:17228
 today:434c
 short:1SZIj3Eo382i
   med:n9-VD8dJqDmpfUMn
  uuid:htsPRx4aTE2My_Wm3a9MUw
  uuid:7aaa6f0e-f830-4f22-8034-c5128b83a880
---------------  now:1488507494439 today:17228
 today:434c
 short:FzRkQ5f4-wXW
   med:5egyFHR-jf3whlfW
  uuid:bK0TPcPORQ-4wqt_Amqi0A
  uuid:b007c69d-7e00-4a98-8acb-a0eacdaf40e8
---------------  now:1488507495439 today:17228
 today:434c
 short:aFL84pX3jxfx
   med:ZGK_wBEMIXb-Epj6
  uuid:VpEdiFAkRJq9cIrDuJfscQ
  uuid:306ac7aa-22d6-4b23-a7a5-2e560c979477
---------------  now:1488507496440 today:17228
 today:434c
 short:ZnaVaV0R54Oh
   med:SMemBbrhri9vq8sV
  uuid:nZQW8RWdSl2HWt9iNFimdA
  uuid:454a26f0-2adc-482c-b518-9668ca312df3
---------------  now:1488507497440 today:17228
 today:434c
 short:r_cCZL3qkuMh
   med:RRFsDsaF3G1leHh0
  uuid:nKvY-JqmSVe5to9v41O05Q
  uuid:bf52fe00-3eb2-40b2-8dfd-aec1682133c8
  
```

## notes

Internally this uses java.util.Base64 and specifically the Base64.getUrlEncoder() which uses <code>-</code> and <code>_</code> producing URL safe string values.
