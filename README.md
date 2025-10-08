# 📄 Özgeçmiş Oluşturucu (CV Generator)

Java ile geliştirilmiş, terminal üzerinden kullanıcı girişi alan ve profesyonel PDF özgeçmiş oluşturan bir uygulamadır.

## ✨ Özellikler

- ✅ **Türkçe Karakter Desteği** - Türkçe karakterler (ı, ğ, ü, ş, ö, ç) düzgün gösterilir
- ✅ **Kullanıcı Dostu** - Tüm bilgiler terminal üzerinden kolayca girilir
- ✅ **Özelleştirilebilir** - İş deneyimi ve yetenek sayısını siz belirlersiniz
- ✅ **Fotoğraf Desteği** - İsteğe bağlı profil fotoğrafı ekleyebilirsiniz
- ✅ **Profesyonel Tasarım** - Temiz ve okunabilir PDF çıktısı
- ✅ **Çoklu Platform** - Windows, Linux ve Mac desteği

## 📋 Gereksinimler

- **Java JDK 11** veya üzeri
- **Apache PDFBox 3.0.3** veya üzeri
- **Commons Logging 1.2**

## 📁 Proje Yapısı

```
ozgecmis-olusturucu/
├── src/
│   ├── Main.java
│   ├── KisiselBilgiler.java
│   └── IsDeneyimi.java
├── lib/                              # JAR dosyaları burada
│   ├── pdfbox-app-3.0.3.jar         # Tüm bağımlılıkları içerir
│   └── commons-logging-1.2.jar      # (pdfbox-app kullanıyorsanız gerekmez)
├── kaynak/                           # Opsiyonel
│   └── foto.jpg                      # Profil fotoğrafı
├── README.md
└── ozgecmis.pdf                      # Oluşturulan çıktı
```

## 🚀 Kurulum

### 1. Projeyi İndirin

```bash
git clone https://github.com/kullanici-adi/ozgecmis-olusturucu.git
cd ozgecmis-olusturucu
```

### 2. Gerekli Kütüphaneleri İndirin

**Kolay Yöntem (Önerilen):**
- [pdfbox-app-3.0.3.jar](https://archive.apache.org/dist/pdfbox/3.0.3/pdfbox-app-3.0.3.jar) dosyasını indirin
- `lib/` klasörüne kopyalayın

**Alternatif Yöntem:**
Ayrı ayrı JAR dosyalarını indirin:
- [pdfbox-3.0.3.jar](https://repo1.maven.org/maven2/org/apache/pdfbox/pdfbox/3.0.3/pdfbox-3.0.3.jar)
- [fontbox-3.0.3.jar](https://repo1.maven.org/maven2/org/apache/pdfbox/fontbox/3.0.3/fontbox-3.0.3.jar)
- [pdfbox-io-3.0.3.jar](https://repo1.maven.org/maven2/org/apache/pdfbox/pdfbox-io/3.0.3/pdfbox-io-3.0.3.jar)
- [commons-logging-1.2.jar](https://repo1.maven.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar)

### 3. Projeyi Derleyin

```bash
# Derleme
javac -cp "lib/*" src/*.java -d bin

# Windows için
javac -cp "lib/*" src/*.java -d bin
```

## 💻 Kullanım

### Uygulamayı Çalıştırma

```bash
# Linux/Mac
java -cp "bin:lib/*" Main

# Windows
java -cp "bin;lib/*" Main
```

### Örnek Kullanım

```
==============================================
         ÖZGEÇMİŞ OLUŞTURUCU
==============================================

--- KİŞİSEL BİLGİLER ---
Ad Soyad: Ahmet Yılmaz
E-posta: ahmet.yilmaz@email.com
Telefon: +90 532 123 45 67
Adres: İstanbul, Türkiye
Başlık (örn: Java Developer): Senior Java Developer

--- İŞ DENEYİMLERİ ---
Kaç iş deneyimi eklemek istiyorsunuz? 2

1. İş Deneyimi:
  Pozisyon: Kıdemli Java Developer
  Şirket: TechCorp A.Ş.
  Tarih (örn: Ocak 2022 - Günümüz): Ocak 2022 - Günümüz
  Açıklama: Mikroservis mimarisi ile e-ticaret platformu geliştirdim.

2. İş Deneyimi:
  Pozisyon: Java Developer
  Şirket: SoftwarePlus Ltd.
  Tarih: Haziran 2020 - Aralık 2021
  Açıklama: REST API tasarımı ve implementasyonu yaptım.

--- EĞİTİM ---
Eğitim bilgisi: Bilgisayar Mühendisliği, İTÜ (2015-2019)

--- YETENEKLER ---
Kaç yetenek eklemek istiyorsunuz? 3
  1. Yetenek: Java, Spring Boot, Spring MVC
  2. Yetenek: PostgreSQL, MySQL, MongoDB
  3. Yetenek: Docker, Kubernetes

--- FOTOĞRAF ---
Fotoğraf yolu (boş bırakabilirsiniz): kaynak/foto.jpg

Çıktı dosya adı (örn: ozgecmis.pdf): benim_cv.pdf

✓ PDF oluşturuluyor...
✓ Özgeçmiş başarıyla oluşturuldu: benim_cv.pdf
```

## 📸 Fotoğraf Ekleme

Profil fotoğrafı eklemek için:

1. Fotoğrafınızı `kaynak/` klasörüne kopyalayın (örn: `kaynak/profilim.jpg`)
2. Uygulama çalıştırıldığında "Fotoğraf yolu" sorulduğunda: `kaynak/profilim.jpg` yazın
3. Fotoğraf eklemek istemiyorsanız boş bırakın (Enter tuşuna basın)

**Desteklenen Formatlar:** JPG, JPEG, PNG

## ⚙️ IDE ile Kullanım

### IntelliJ IDEA

1. Projeyi açın: `File` → `Open` → Proje klasörünü seçin
2. Kütüphaneleri ekleyin: `File` → `Project Structure` → `Libraries` → `+` → `lib/` klasöründeki JAR'ları seçin
3. `Main.java` dosyasını açın ve çalıştırın (Run)

### Eclipse

1. Projeyi import edin: `File` → `Import` → `Existing Projects into Workspace`
2. Build Path ekleyin: Projeye sağ tık → `Build Path` → `Configure Build Path` → `Libraries` → `Add External JARs` → `lib/` klasöründeki JAR'ları seçin
3. `Main.java` dosyasını çalıştırın (Run)

### VS Code

1. Java Extension Pack yükleyin
2. Projeyi açın
3. `Java Projects` panelinden `Referenced Libraries` → `+` → JAR dosyalarını ekleyin
4. `Main.java` dosyasını çalıştırın

## 🔧 Sorun Giderme

### "NoClassDefFoundError: org/apache/commons/logging/LogFactory"

**Çözüm:** Commons Logging kütüphanesi eksik
- `commons-logging-1.2.jar` dosyasını indirin ve `lib/` klasörüne ekleyin
- VEYA `pdfbox-app-3.0.3.jar` kullanın (tüm bağımlılıkları içerir)

### "Türkçe karakterler düzgün görünmüyor"

**Çözüm:** Sistem fontları bulunamıyor
- Windows: `C:/Windows/Fonts/arial.ttf` dosyasının var olduğundan emin olun
- Linux: DejaVu Sans fontunu yükleyin: `sudo apt-get install fonts-dejavu`
- Mac: Sistem fontları otomatik bulunmalı

### "Fotoğraf bulunamadı"

**Çözüm:** 
- Fotoğraf yolunun doğru olduğundan emin olun
- Dosya adında Türkçe karakter kullanmayın
- Örnek: `kaynak/foto.jpg` (✅) vs `kaynak/fotoğraf.jpg` (❌)

## 📚 Kullanılan Teknolojiler

- **Java 11+** - Programlama dili
- **Apache PDFBox 3.0.3** - PDF oluşturma kütüphanesi
- **Commons Logging 1.2** - Loglama kütüphanesi

## 🤝 Katkıda Bulunma

1. Bu projeyi fork edin
2. Yeni bir branch oluşturun (`git checkout -b ozellik/yeniOzellik`)
3. Değişikliklerinizi commit edin (`git commit -m 'Yeni özellik eklendi'`)
4. Branch'inizi push edin (`git push origin ozellik/yeniOzellik`)
5. Pull Request oluşturun

## 📝 Lisans

Bu proje MIT lisansı altında lisanslanmıştır. Detaylar için [LICENSE](LICENSE) dosyasına bakın.

## 👤 Geliştirici

- GitHub: [@kullanici-adi](https://github.com/kullanici-adi)
- E-posta: your.email@example.com

## 🌟 Destek

Bu projeyi beğendiyseniz ⭐ vermeyi unutmayın!

## 📋 Değişiklik Günlüğü

### v1.0.0 (2024-10-08)
- ✅ İlk sürüm
- ✅ Türkçe karakter desteği
- ✅ Terminal üzerinden kullanıcı girişi
- ✅ Fotoğraf ekleme özelliği
- ✅ Özelleştirilebilir bölümler

---

**Not:** Sorularınız veya önerileriniz için lütfen [Issues](https://github.com/kullanici-adi/ozgecmis-olusturucu/issues) bölümünü kullanın.
