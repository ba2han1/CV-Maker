public class KisiselBilgiler {
    private String adSoyad;
    private String email;
    private String telefon;
    private String adres;
    private String baslik;

    public KisiselBilgiler(String adSoyad, String email, String telefon,
                           String adres, String baslik) {
        this.adSoyad = adSoyad;
        this.email = email;
        this.telefon = telefon;
        this.adres = adres;
        this.baslik = baslik;
    }

    public String getAdSoyad() { return adSoyad; }
    public String getEmail() { return email; }
    public String getTelefon() { return telefon; }
    public String getAdres() { return adres; }
    public String getBaslik() { return baslik; }
}