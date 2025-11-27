/*
 * Ad Soyad: Aylin Akagündüz
 * Ogrenci No: 250541011
 * Tarih: 27 Kasim 2025
 * Aciklama: Hafta 6 - Proje 3: Akilli Restoran Siparis Sistemi
 */

import java.util.Scanner;
import java.util.Locale;

public class RestoranSistemi {

    // --- Menü Fiyatları (Switch-Case) ---
    public static double anaYemekFiyat(int secim) {
        switch (secim) {
            case 1: return 85;  // Izgara Tavuk
            case 2: return 120; // Adana Kebap
            case 3: return 110; // Levrek
            case 4: return 65;  // Manti
            default: return 0;
        }
    }

    public static double baslangicFiyat(int secim) {
        switch (secim) {
            case 1: return 25; // Corba
            case 2: return 45; // Humus
            case 3: return 55; // Sigara Boregi
            default: return 0;
        }
    }

    public static double icecekFiyat(int secim) {
        switch (secim) {
            case 1: return 15; // Kola
            case 2: return 12; // Ayran
            case 3: return 35; // Meyve Suyu
            case 4: return 25; // Limonata
            default: return 0;
        }
    }

    public static double tatliFiyat(int secim) {
        switch (secim) {
            case 1: return 65; // Kunefe
            case 2: return 55; // Baklava
            case 3: return 35; // Sutlac
            default: return 0;
        }
    }

    // --- Kontrol Metotları ---
    public static boolean comboMi(double ana, double icecek, double tatli) {
        // Hepsi seçildiyse (fiyatları 0'dan büyükse) Combo olur
        return ana > 0 && icecek > 0 && tatli > 0;
    }

    public static boolean happyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // --- Hesaplama Metotları ---
    public static double indirimHesapla(double toplam, boolean combo, boolean ogrenci, int saat, double icecek, int gun) {
        double toplamIndirim = 0;
        double kalanPara = toplam; // İndirimler yapıldıkça bu tutar azalacak

        // 1. Combo İndirimi (%15)
        if (combo) {
            double ind = toplam * 0.15;
            toplamIndirim += ind;
            kalanPara -= ind; // Fiyattan düşüyoruz
        } 
        else if (toplam > 200) {
            double ind = toplam * 0.10;
            toplamIndirim += ind;
            kalanPara -= ind;
        }

        // 2. Happy Hour İndirimi (%20 - İçecekten)
        if (happyHour(saat)) {
            double ind = icecek * 0.20;
            toplamIndirim += ind;
            kalanPara -= ind; // Fiyattan düşüyoruz
        }

        // 3. Öğrenci İndirimi (%10)
        if (ogrenci && gun <= 5) {
            double ind = kalanPara * 0.10; 
            toplamIndirim += ind;
        }

        return toplamIndirim;
    }

    public static double bahsis(double tutar) {
        return tutar * 0.10;
    }

    // --- MAIN ---
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("=== AKILLI RESTORAN SIPARIS ===");

        // Girdiler
        System.out.print("Ana Yemek (1-4): ");
        int ana = sc.nextInt();

        System.out.print("Baslangic (0-3): ");
        int bas = sc.nextInt();

        System.out.print("Icecek (0-4): ");
        int icecek = sc.nextInt();

        System.out.print("Tatli (0-3): ");
        int tatli = sc.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = sc.nextInt();

        System.out.print("Ogrenci misiniz? (E/H): ");
        boolean ogrenci = sc.next().equalsIgnoreCase("E");

        System.out.print("Hangi gun? (1-7): ");
        int gun = sc.nextInt();

        // Fiyatları al
        double fAna = anaYemekFiyat(ana);
        double fBas = baslangicFiyat(bas);
        double fIcecek = icecekFiyat(icecek);
        double fTatli = tatliFiyat(tatli);

        // Hesaplamalar
        double araToplam = fAna + fBas + fIcecek + fTatli;
        boolean combo = comboMi(fAna, fIcecek, fTatli);

        double indirim = indirimHesapla(araToplam, combo, ogrenci, saat, fIcecek, gun);
        double odenecek = araToplam - indirim;
        double bahsisOnerisi = bahsis(odenecek);

        // Çıktı (Fiş)
        System.out.println("\n--- HESAP DETAYI ---");
        System.out.println("Ara Toplam    : " + araToplam + " TL");
        
        if (combo) System.out.println("* Combo indirimi uygulandi");
        if (happyHour(saat)) System.out.println("* Happy Hour indirimi var");
        if (ogrenci && gun <= 5) System.out.println("* Ogrenci indirimi uygulandi");
        
        System.out.println("-------------------------");
        System.out.printf(Locale.US, "Toplam Indirim: -%.2f TL%n", indirim);
        System.out.printf(Locale.US, "Odenecek Tutar: %.2f TL%n", odenecek);
        System.out.printf(Locale.US, "Bahsis Onerisi: %.2f TL%n", bahsisOnerisi);

        sc.close();
    }
}