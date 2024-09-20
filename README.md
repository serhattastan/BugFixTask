# Android Development Case Study Hata Raporu

---

**Hazırlayan:** Serhat Taştan  
**GitHub:** [Serhat Taştan](https://github.com/serhattastan)  
**LinkedIn:** [Serhat Taştan](https://www.linkedin.com/in/serhat-taştan-2100b820b/)  
**Proje Repo:** [BugFixTask](https://github.com/serhattastan/BugFixTask)

---

## Hata Listesi

### 1. java.lang.IllegalStateException: Method setCurrentState must be called on the main thread

**Hata Açıklaması:**  
Uygulamada bir şehir öğesine tıkladığımda, uygulama çöküyor ve şu hata mesajı alınıyordu:  
*"java.lang.IllegalStateException: Method setCurrentState must be called on the main thread"*.  
Bu hata, `CityFragment.kt` içindeki `navigateDetail()` fonksiyonunda, `NavController` ile gezinme işleminin arka planda yapılmaya çalışılmasından kaynaklanıyordu. UI işlemleri her zaman ana iş parçacığında (main thread) yapılmalı.

**Çözüm:**  
Geçiş işlemi ana iş parçacığına (`Dispatchers.Main`) taşınarak `lifecycleScope` kullanıldı.

---

### 2. RecyclerView'de Şehir Kartlarının Renklerinin Değişmesi

**Hata Açıklaması:**  
Şehir kartlarının renkleri ekranda aşağıya veya yukarıya kaydırıldığında karışıyordu. Bu sorun, `RecyclerView`'ın `ViewHolder` yapısının yeniden kullanımıyla ilgiliydi. Eski veriler yeni öğelere taşınıyordu.

**Çözüm:**  
Her `bind` işleminde kartın rengi sıfırlanarak yeniden ayarlandı.

---

### 3. İlk Tıklamada Navigasyon Gerçekleşmemesi

**Hata Açıklaması:**  
Şehir öğelerine ilk tıklamada işlem gerçekleşmiyordu, ancak ikinci tıklamada navigasyon işlemi başarılı oluyordu. Bu durum, `ViewModel` içindeki `selectedItem`'ın ilk tıklamada güncellenmemesinden kaynaklanıyordu.

**Çözüm:**  
`selectedItem` her tıklamada güncellenir hale getirildi ve ardından navigasyon işlemi gerçekleştirildi.

---

### 4. Android 13 Geri Tuşu Yönetimi

**Hata Açıklaması:**  
Android 13 ve üstü sürümlerde geri tuşu işlevselliği eski yöntemlerle uyumlu değildi. Uygulama geri tuşuna basıldığında beklenen davranışı sergilemiyordu.

**Çözüm:**  
Android 13 için `OnBackInvokedCallback`, eski sürümler için `OnBackPressedDispatcher` kullanılarak uyumluluk sağlandı.

---

### 5. Şehir Arama Özelliğinin Çalışmaması

**Hata Açıklaması:**  
Arama çubuğu işlevsel değildi. Kullanıcı arama kelimesini yazdığında liste güncellenmiyordu çünkü arama işlevi tetiklenmiyordu.

**Çözüm:**  
Arama çubuğu ile `OnTextChangeListener` bağlanarak her metin değişikliğinde arama işlevi çalıştırıldı.

---

### 6. Ekran Aşağıya Kaymama Sorunu

**Hata Açıklaması:**  
`RecyclerView`, tam kaydırma işlevini gerçekleştiremiyordu. Görüntülenen öğe sayısı sınırlıydı ve tüm öğeler listelenemiyordu.

**Çözüm:**  
`ConstraintLayout`'un `layout_height` değeri `wrap_content` olarak ayarlandı.

---

### 7. Küçük Hata Düzeltmeleri

**Açıklama:**  
Proje genelinde küçük iyileştirmeler ve hatalar düzeltildi.
