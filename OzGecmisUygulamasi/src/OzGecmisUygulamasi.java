import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

class Main {
    private static final float MARGIN = 50;
    private static final float FONT_SIZE_TITLE = 24;
    private static final float FONT_SIZE_SUBTITLE = 18;
    private static final float FONT_SIZE_HEADING = 14;
    private static final float FONT_SIZE_NORMAL = 11;
    private static final float LEADING = 15;

    private static PDType0Font turkceFont;
    private static PDType0Font turkceFontBold;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println("==============================================");
            System.out.println("         ÖZGEÇMİŞ OLUŞTURUCU");
            System.out.println("==============================================\n");

            // Kişisel bilgileri al
            System.out.println("--- KİŞİSEL BİLGİLER ---");
            System.out.print("Ad Soyad: ");
            String adSoyad = scanner.nextLine();

            System.out.print("E-posta: ");
            String email = scanner.nextLine();

            System.out.print("Telefon: ");
            String telefon = scanner.nextLine();

            System.out.print("Adres: ");
            String adres = scanner.nextLine();

            System.out.print("Başlık (örn: Java Developer): ");
            String baslik = scanner.nextLine();

            KisiselBilgiler kisiselBilgiler = new KisiselBilgiler(
                    adSoyad, email, telefon, adres, baslik
            );

            // İş deneyimlerini al
            System.out.println("\n--- İŞ DENEYİMLERİ ---");
            System.out.print("Kaç iş deneyimi eklemek istiyorsunuz? ");
            int deneyimSayisi = Integer.parseInt(scanner.nextLine());

            IsDeneyimi[] deneyimler = new IsDeneyimi[deneyimSayisi];

            for (int i = 0; i < deneyimSayisi; i++) {
                System.out.println("\n" + (i + 1) + ". İş Deneyimi:");
                System.out.print("  Pozisyon: ");
                String pozisyon = scanner.nextLine();

                System.out.print("  Şirket: ");
                String sirket = scanner.nextLine();

                System.out.print("  Tarih (örn: Ocak 2022 - Günümüz): ");
                String tarih = scanner.nextLine();

                System.out.print("  Açıklama: ");
                String aciklama = scanner.nextLine();

                deneyimler[i] = new IsDeneyimi(pozisyon, sirket, tarih, aciklama);
            }

            // Eğitim bilgisi al
            System.out.println("\n--- EĞİTİM ---");
            System.out.print("Eğitim bilgisi (örn: Bilgisayar Mühendisliği, İTÜ 2015-2019): ");
            String egitim = scanner.nextLine();

            // Yetenekleri al
            System.out.println("\n--- YETENEKLER ---");
            System.out.print("Kaç yetenek eklemek istiyorsunuz? ");
            int yetenekSayisi = Integer.parseInt(scanner.nextLine());

            String[] yetenekler = new String[yetenekSayisi];

            for (int i = 0; i < yetenekSayisi; i++) {
                System.out.print("  " + (i + 1) + ". Yetenek: ");
                yetenekler[i] = scanner.nextLine();
            }

            // Fotoğraf yolu al
            System.out.println("\n--- FOTOĞRAF ---");
            System.out.print("Fotoğraf yolu (boş bırakabilirsiniz): ");
            String fotografYolu = scanner.nextLine();
            if (fotografYolu.trim().isEmpty()) {
                fotografYolu = null;
            }

            // Çıktı dosya adı
            System.out.print("\nÇıktı dosya adı (örn: ozgecmis.pdf): ");
            String ciktiDosyasi = scanner.nextLine();
            if (ciktiDosyasi.trim().isEmpty()) {
                ciktiDosyasi = "ozgecmis.pdf";
            }

            // PDF oluştur
            System.out.println("\n✓ PDF oluşturuluyor...");
            pdfOlustur(
                    ciktiDosyasi,
                    kisiselBilgiler,
                    deneyimler,
                    egitim,
                    yetenekler,
                    fotografYolu
            );

            System.out.println("✓ Özgeçmiş başarıyla oluşturuldu: " + ciktiDosyasi);
            System.out.println("✓ PDF dosyası proje klasöründe bulunmaktadır.");

        } catch (Exception e) {
            System.err.println("✗ Hata oluştu: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void pdfOlustur(String dosyaAdi, KisiselBilgiler kisiselBilgiler,
                                   IsDeneyimi[] deneyimler, String egitim,
                                   String[] yetenekler, String fotografYolu) throws IOException {

        try (PDDocument document = new PDDocument()) {

            // Türkçe font yükle
            try {
                File fontFile = new File("C:/Windows/Fonts/arial.ttf");
                if (!fontFile.exists()) {
                    fontFile = new File("/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf");
                }
                if (!fontFile.exists()) {
                    fontFile = new File("/System/Library/Fonts/Supplemental/Arial.ttf");
                }

                if (fontFile.exists()) {
                    turkceFont = PDType0Font.load(document, fontFile);
                    File boldFile = new File("C:/Windows/Fonts/arialbd.ttf");
                    if (!boldFile.exists()) {
                        boldFile = new File("/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf");
                    }
                    if (!boldFile.exists()) {
                        boldFile = new File("/System/Library/Fonts/Supplemental/Arial Bold.ttf");
                    }
                    turkceFontBold = boldFile.exists() ? PDType0Font.load(document, boldFile) : turkceFont;
                } else {
                    System.out.println("⚠ Uyarı: Türkçe font bulunamadı, varsayılan font kullanılacak");
                    turkceFont = null;
                    turkceFontBold = null;
                }
            } catch (Exception e) {
                System.out.println("⚠ Uyarı: Font yüklenemedi: " + e.getMessage());
                turkceFont = null;
                turkceFontBold = null;
            }

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float yPosition = page.getMediaBox().getHeight() - MARGIN;

            // Fotoğraf ekle
            if (fotografYolu != null && !fotografYolu.trim().isEmpty()) {
                yPosition = fotografEkle(contentStream, fotografYolu, page, yPosition, document);
            }

            // Kişisel bilgiler bölümü
            yPosition = kisiselBilgilerEkle(contentStream, kisiselBilgiler, yPosition);

            yPosition -= 20;

            // İş deneyimleri bölümü
            yPosition = baslikEkle(contentStream, "İŞ DENEYİMLERİ", yPosition, document);
            yPosition = cizgiEkle(contentStream, yPosition, page.getMediaBox().getWidth());
            yPosition -= 10;

            for (IsDeneyimi deneyim : deneyimler) {
                yPosition = deneyimEkle(contentStream, deneyim, yPosition, document);
                yPosition -= 15;
            }

            // Eğitim bölümü
            yPosition = baslikEkle(contentStream, "EĞİTİM", yPosition, document);
            yPosition = cizgiEkle(contentStream, yPosition, page.getMediaBox().getWidth());
            yPosition -= 10;
            yPosition = metinEkle(contentStream, egitim, yPosition, FONT_SIZE_NORMAL, false);
            yPosition -= 20;

            // Yetenekler bölümü
            yPosition = baslikEkle(contentStream, "YETENEKLER", yPosition, document);
            yPosition = cizgiEkle(contentStream, yPosition, page.getMediaBox().getWidth());
            yPosition -= 10;
            yPosition = yeteneklerEkle(contentStream, yetenekler, yPosition);

            contentStream.close();
            document.save(dosyaAdi);
        }
    }

    private static float fotografEkle(PDPageContentStream contentStream, String fotografYolu,
                                      PDPage page, float yPosition, PDDocument document) throws IOException {
        try {
            File fotografDosya = new File(fotografYolu);
            if (fotografDosya.exists()) {
                PDImageXObject image = PDImageXObject.createFromFile(fotografYolu, document);
                float imageWidth = 100;
                float imageHeight = 100;
                float xPosition = page.getMediaBox().getWidth() - MARGIN - imageWidth;

                contentStream.drawImage(image, xPosition, yPosition - imageHeight,
                        imageWidth, imageHeight);
            } else {
                System.out.println("⚠ Uyarı: Fotoğraf bulunamadı: " + fotografYolu);
            }
        } catch (Exception e) {
            System.out.println("⚠ Uyarı: Fotoğraf yüklenemedi: " + e.getMessage());
        }
        return yPosition;
    }

    private static float kisiselBilgilerEkle(PDPageContentStream contentStream,
                                             KisiselBilgiler bilgiler, float yPosition) throws IOException {
        // Ad Soyad
        yPosition = metinEkle(contentStream, bilgiler.getAdSoyad(), yPosition,
                FONT_SIZE_TITLE, true);
        yPosition -= 5;

        // Başlık
        yPosition = metinEkle(contentStream, bilgiler.getBaslik(), yPosition,
                FONT_SIZE_SUBTITLE, false);
        yPosition -= 15;

        // İletişim bilgileri
        yPosition = metinEkle(contentStream, "E-posta: " + bilgiler.getEmail(),
                yPosition, FONT_SIZE_NORMAL, false);
        yPosition = metinEkle(contentStream, "Telefon: " + bilgiler.getTelefon(),
                yPosition, FONT_SIZE_NORMAL, false);
        yPosition = metinEkle(contentStream, "Adres: " + bilgiler.getAdres(),
                yPosition, FONT_SIZE_NORMAL, false);

        return yPosition;
    }

    private static float baslikEkle(PDPageContentStream contentStream, String baslik,
                                    float yPosition, PDDocument document) throws IOException {
        return metinEkle(contentStream, baslik, yPosition, FONT_SIZE_HEADING, true);
    }

    private static float cizgiEkle(PDPageContentStream contentStream, float yPosition,
                                   float pageWidth) throws IOException {
        contentStream.setLineWidth(1f);
        contentStream.moveTo(MARGIN, yPosition - 5);
        contentStream.lineTo(pageWidth - MARGIN, yPosition - 5);
        contentStream.stroke();
        return yPosition - 10;
    }

    private static float deneyimEkle(PDPageContentStream contentStream, IsDeneyimi deneyim,
                                     float yPosition, PDDocument document) throws IOException {
        // Pozisyon ve Şirket
        yPosition = metinEkle(contentStream, deneyim.getPozisyon() + " - " +
                deneyim.getSirket(), yPosition, FONT_SIZE_NORMAL, true);

        // Tarih
        yPosition = metinEkle(contentStream, deneyim.getTarih(), yPosition,
                FONT_SIZE_NORMAL - 1, false);
        yPosition -= 5;

        // Açıklama (çok satırlı)
        yPosition = cokSatirliMetinEkle(contentStream, deneyim.getAciklama(),
                yPosition, FONT_SIZE_NORMAL - 1);

        return yPosition;
    }

    private static float yeteneklerEkle(PDPageContentStream contentStream, String[] yetenekler,
                                        float yPosition) throws IOException {
        for (String yetenek : yetenekler) {
            yPosition = metinEkle(contentStream, "• " + yetenek, yPosition,
                    FONT_SIZE_NORMAL, false);
        }
        return yPosition;
    }

    private static float metinEkle(PDPageContentStream contentStream, String metin,
                                   float yPosition, float fontSize, boolean bold) throws IOException {
        contentStream.beginText();

        // Türkçe font varsa onu kullan
        if (turkceFont != null) {
            if (bold && turkceFontBold != null) {
                contentStream.setFont(turkceFontBold, fontSize);
            } else {
                contentStream.setFont(turkceFont, fontSize);
            }
        } else {
            // Varsayılan font
            if (bold) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), fontSize);
            } else {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), fontSize);
            }
        }

        contentStream.newLineAtOffset(MARGIN, yPosition);
        contentStream.showText(metin);
        contentStream.endText();

        return yPosition - LEADING;
    }

    private static float cokSatirliMetinEkle(PDPageContentStream contentStream, String metin,
                                             float yPosition, float fontSize) throws IOException {
        float maxWidth = 450;
        String[] kelimeler = metin.split(" ");
        StringBuilder satir = new StringBuilder();

        for (String kelime : kelimeler) {
            String testSatir = satir.length() > 0 ? satir + " " + kelime : kelime;

            // Genişlik hesaplama
            float genislik;
            if (turkceFont != null) {
                genislik = turkceFont.getStringWidth(testSatir) / 1000 * fontSize;
            } else {
                PDType1Font font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
                genislik = font.getStringWidth(testSatir) / 1000 * fontSize;
            }

            if (genislik > maxWidth) {
                yPosition = metinEkle(contentStream, satir.toString(), yPosition,
                        fontSize, false);
                satir = new StringBuilder(kelime);
            } else {
                satir = new StringBuilder(testSatir);
            }
        }

        if (satir.length() > 0) {
            yPosition = metinEkle(contentStream, satir.toString(), yPosition,
                    fontSize, false);
        }

        return yPosition;
    }
}