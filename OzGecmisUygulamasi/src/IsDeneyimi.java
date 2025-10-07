public class IsDeneyimi {
    private String pozisyon;
    private String sirket;
    private String tarih;
    private String aciklama;

    public IsDeneyimi(String pozisyon, String sirket, String tarih, String aciklama) {
        this.pozisyon = pozisyon;
        this.sirket = sirket;
        this.tarih = tarih;
        this.aciklama = aciklama;
    }

    public String getPozisyon() { return pozisyon; }
    public String getSirket() { return sirket; }
    public String getTarih() { return tarih; }
    public String getAciklama() { return aciklama; }
}