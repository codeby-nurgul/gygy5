# HTTP İsteğinin Anatomisi

## 1. HTTP Nedir?

**HTTP (HyperText Transfer Protocol)**, istemci (client) ile sunucu (server) arasındaki veri alışverişini düzenleyen, uygulama katmanında çalışan bir protokoldür. Web'in temel iletişim dilidir.

- **Durumsuz (Stateless):** Her istek birbirinden bağımsızdır; sunucu önceki isteği hatırlamaz.
- **Metin tabanlı:** HTTP/1.1'de istek ve yanıtlar düz metin olarak gönderilir.
- **İstemci-Sunucu modeli:** İstemci her zaman isteği başlatan taraftır; sunucu yanıtlar.

```
İstemci  ──── HTTP İsteği ────►  Sunucu
İstemci  ◄─── HTTP Yanıtı ────  Sunucu
```

---

## 2. HTTP İsteğinin Yapısı

Bir HTTP isteği 3 ana bölümden oluşur:

```
┌─────────────────────────────────────┐
│         REQUEST LINE                │  ← Metod + URL + HTTP Versiyonu
├─────────────────────────────────────┤
│            HEADERS                  │  ← Meta bilgiler
├─────────────────────────────────────┤
│             BODY                    │  ← Gönderilen veri (opsiyonel)
└─────────────────────────────────────┘
```

### 2.1 Request Line (İstek Satırı)

İlk satırdır. Üç parça içerir:

```
GET /api/users HTTP/1.1
│    │          └── Protokol versiyonu
│    └──────────── Kaynak yolu (path)
└───────────────── HTTP Metodu
```

### 2.2 HTTP Metodları

| Metod   | Amaç                             | Body var mı? |
| ------- | -------------------------------- | :----------: |
| GET     | Kaynak getir                     |    Hayır     |
| POST    | Yeni kaynak oluştur              |     Evet     |
| PUT     | Kaynağı tamamen güncelle/oluştur |     Evet     |
| PATCH   | Kaynağı kısmen güncelle          |     Evet     |
| DELETE  | Kaynağı sil                      |    Bazen     |
| HEAD    | Sadece header'ları getir         |    Hayır     |
| OPTIONS | Desteklenen metodları sorgula    |    Hayır     |

### 2.3 Headers (Başlıklar)

Header'lar `Anahtar: Değer` formatında yazılır ve isteğe dair meta bilgiler taşır.

**Sık kullanılan istek header'ları:**

| Header            | Açıklama                                               | Örnek Değer                   |
| ----------------- | ------------------------------------------------------ | ----------------------------- |
| `Host`            | Hedef sunucunun alan adı (zorunlu)                     | `api.example.com`             |
| `Content-Type`    | Gönderilen verinin formatı                             | `application/json`            |
| `Accept`          | İstemcinin kabul ettiği yanıt formatları               | `application/json, text/html` |
| `Authorization`   | Kimlik doğrulama bilgisi                               | `Bearer eyJhbGci...`          |
| `User-Agent`      | İstemci yazılımının bilgisi                            | `Mozilla/5.0 ...`             |
| `Cookie`          | Sunucuya gönderilen çerezler                           | `sessionId=abc123`            |
| `Accept-Encoding` | İstemcinin desteklediği sıkıştırma algoritmaları       | `gzip, deflate, br`           |
| `Cache-Control`   | Önbellekleme davranışı                                 | `no-cache`                    |
| `Referer`         | İsteğin hangi sayfadan geldiği                         | `https://example.com/page`    |
| `Origin`          | İsteği başlatan kaynağın adresi (CORS için kullanılır) | `https://myfrontend.com`      |

### 2.4 Body (Gövde)

Body, istemcinin sunucuya veri gönderdiği bölümdür. **GET ve HEAD** isteklerinde genellikle kullanılmaz.

**Yaygın Content-Type değerleri:**

| Content-Type                        | Açıklama                               |
| ----------------------------------- | -------------------------------------- |
| `application/json`                  | JSON formatında veri                   |
| `application/x-www-form-urlencoded` | HTML form verisi (key=value&key=value) |
| `multipart/form-data`               | Dosya yüklemeleri için                 |
| `text/plain`                        | Düz metin                              |
| `application/xml`                   | XML formatında veri                    |

---

## 3. HTTP Yanıtının Yapısı

```
┌─────────────────────────────────────┐
│          STATUS LINE                │  ← Protokol + Durum Kodu + Mesaj
├─────────────────────────────────────┤
│            HEADERS                  │  ← Sunucuya ait meta bilgiler
├─────────────────────────────────────┤
│             BODY                    │  ← Döndürülen veri
└─────────────────────────────────────┘
```

### 3.1 Status Line (Durum Satırı)

```
HTTP/1.1 200 OK
│         │   └── Durum mesajı
│         └───── Durum kodu
└─────────────── Protokol versiyonu
```

### 3.2 Durum Kodları

Durum kodları 5 gruba ayrılır:

#### 1xx – Bilgi (Informational)

| Kod | Anlam                                                  |
| --- | ------------------------------------------------------ |
| 100 | Continue – İstek alındı, devam edilebilir              |
| 101 | Switching Protocols – WebSocket gibi protokol değişimi |

#### 2xx – Başarı (Success)

| Kod | Anlam                                                        |
| --- | ------------------------------------------------------------ |
| 200 | OK – İstek başarılı                                          |
| 201 | Created – Yeni kaynak oluşturuldu (POST sonrası)             |
| 204 | No Content – Başarılı ama yanıt gövdesi yok (DELETE sonrası) |

#### 3xx – Yönlendirme (Redirection)

| Kod | Anlam                                             |
| --- | ------------------------------------------------- |
| 301 | Moved Permanently – Kaynak kalıcı olarak taşındı  |
| 302 | Found – Geçici yönlendirme                        |
| 304 | Not Modified – Önbellekteki versiyon hâlâ geçerli |

#### 4xx – İstemci Hatası (Client Error)

| Kod | Anlam                                                    |
| --- | -------------------------------------------------------- |
| 400 | Bad Request – Geçersiz istek formatı                     |
| 401 | Unauthorized – Kimlik doğrulama gerekiyor                |
| 403 | Forbidden – Yetki yok                                    |
| 404 | Not Found – Kaynak bulunamadı                            |
| 405 | Method Not Allowed – Bu metoda izin verilmiyor           |
| 409 | Conflict – Kaynak çakışması                              |
| 422 | Unprocessable Entity – Veri geçersiz (validasyon hatası) |
| 429 | Too Many Requests – Rate limit aşıldı                    |

#### 5xx – Sunucu Hatası (Server Error)

| Kod | Anlam                                                 |
| --- | ----------------------------------------------------- |
| 500 | Internal Server Error – Sunucu taraflı genel hata     |
| 502 | Bad Gateway – Arka uç sunucudan geçersiz yanıt        |
| 503 | Service Unavailable – Sunucu geçici olarak devre dışı |
| 504 | Gateway Timeout – Arka uç sunucu zaman aşımı          |

**Sık kullanılan yanıt header'ları:**

| Header           | Açıklama                                |
| ---------------- | --------------------------------------- |
| `Content-Type`   | Döndürülen verinin formatı              |
| `Content-Length` | Body'nin byte cinsinden boyutu          |
| `Set-Cookie`     | İstemciye çerez atama                   |
| `Location`       | Yönlendirme adresi (3xx ile kullanılır) |
| `Cache-Control`  | Önbellekleme direktifleri               |
| `ETag`           | Kaynağın sürüm parmak izi               |

---

## 4. URL Anatomisi

```
https://api.example.com:443/users/42?sort=asc&page=1#section
│       │               │   │        │                └── Fragment (sadece istemci tarafında kullanılır)
│       │               │   │        └────────────────── Query String (sorgu parametreleri)
│       │               │   └─────────────────────────── Path (kaynak yolu)
│       │               └─────────────────────────────── Port
│       └─────────────────────────────────────────────── Host (alan adı)
└─────────────────────────────────────────────────────── Scheme (protokol)
```

- **Scheme:** Hangi protokolün kullanıldığını belirtir (`http`, `https`, `ftp`…).
- **Host:** Sunucunun alan adı veya IP adresi.
- **Port:** Belirtilmezse HTTP için 80, HTTPS için 443 varsayılan kullanılır.
- **Path:** Sunucudaki kaynağın yolu.
- **Query String:** `?` ile başlar, `&` ile ayrılır; arama/filtreleme parametrelerini taşır.
- **Fragment:** `#` ile başlar; sayfanın bir bölümüne işaret eder ve sunucuya **gönderilmez**.

---

## 5. Kimlik Doğrulama (Authentication)

### 5.1 Basic Auth

Kullanıcı adı ve şifre `kullanicıadı:sifre` formatında Base64 ile kodlanır ve `Authorization` header'ında gönderilir. **Güvensizdir**, yalnızca HTTPS ile kullanılmalıdır.

```
Authorization: Basic dXNlcjpwYXNzd29yZA==
```

### 5.2 Bearer Token (JWT)

Modern API'lerde en yaygın yöntemdir. Sunucu, kullanıcıya bir token verir; istemci bu token'ı her istekte gönderir.

```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

JWT (JSON Web Token) 3 bölümden oluşur: `header.payload.signature`

### 5.3 API Key

Genellikle header veya query string olarak gönderilir:

```
X-API-Key: abc123xyz
```

### 5.4 OAuth 2.0

Üçüncü taraf uygulamaların (ör. "Google ile giriş") kullanıcı adına işlem yapmasına olanak tanır. Token tabanlıdır ve Bearer Token ile birlikte çalışır.

---

## 6. CORS (Cross-Origin Resource Sharing)

**Origin (kaynak):** `scheme + host + port` üçlüsüdür. Farklı bir origin'den gelen istek "cross-origin" sayılır.

Örneğin `https://frontend.com` adresindeki bir uygulama `https://api.backend.com`'a istek atıyorsa bu bir cross-origin isteğidir.

Tarayıcılar güvenlik nedeniyle cross-origin isteklerini varsayılan olarak engeller. Sunucunun CORS header'ları ile izin vermesi gerekir.

### Önemli CORS Header'ları

| Header                             | Açıklama                                           |
| ---------------------------------- | -------------------------------------------------- |
| `Access-Control-Allow-Origin`      | İzin verilen origin (`*` herkese açık demektir)    |
| `Access-Control-Allow-Methods`     | İzin verilen HTTP metodları                        |
| `Access-Control-Allow-Headers`     | İzin verilen istek header'ları                     |
| `Access-Control-Allow-Credentials` | Cookie/auth bilgisine izin verilip verilmediği     |
| `Access-Control-Max-Age`           | Preflight sonucunun kaç saniye önbelleğe alınacağı |

### Preflight İsteği

`POST`, `PUT`, `DELETE` veya özel header içeren isteklerden önce tarayıcı otomatik olarak bir `OPTIONS` isteği gönderir (preflight). Sunucu bu isteğe CORS header'larıyla yanıt verirse asıl istek gönderilir.

---

## 7. HTTPS ve TLS

**HTTPS = HTTP + TLS (Transport Layer Security)**

TLS, veriyi şifreleyerek şu üç güvenceyi sağlar:

- **Gizlilik (Confidentiality):** Veri, araya giren biri tarafından okunamaz.
- **Bütünlük (Integrity):** Veri iletim sırasında değiştirildiğinde fark edilir.
- **Kimlik Doğrulama (Authentication):** Sunucunun gerçekten doğru sunucu olduğu doğrulanır (sertifika ile).

### TLS El Sıkışması (Handshake) Özeti

1. İstemci desteklediği TLS versiyonlarını ve şifre takımlarını (cipher suites) bildirir.
2. Sunucu bir seçim yapar ve sertifikasını gönderir.
3. Sertifika, bir **CA (Certificate Authority)** tarafından imzalanmış olmalıdır.
4. İki taraf bir oturum anahtarı (session key) üzerinde anlaşır.
5. Bu noktadan itibaren tüm iletişim simetrik şifreleme ile korunur.

---

## 8. Özet Tablo

| Kavram         | Kısa Açıklama                                                    |
| -------------- | ---------------------------------------------------------------- |
| HTTP Metodu    | Ne yapılacağını belirtir (GET, POST, PUT, DELETE…)               |
| URL            | Hangi kaynağa erişileceğini belirtir                             |
| Header         | İstek veya yanıt hakkında meta bilgi taşır                       |
| Body           | Gönderilen/alınan gerçek veriyi taşır                            |
| Status Kodu    | Sunucunun işlem sonucunu rakamsal olarak ifade eder              |
| Authentication | İstemcinin kimliğini kanıtlama yöntemi (Basic, Bearer, API Key…) |
| CORS           | Farklı originler arası erişimi yönetir                           |
| TLS            | HTTP trafiğini şifreler; HTTPS'nin şifreleme katmanıdır          |

---
