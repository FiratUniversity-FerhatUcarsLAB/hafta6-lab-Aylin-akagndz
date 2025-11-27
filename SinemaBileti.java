/*
 * Ad Soyad: Aylin Akagündüz
 * Ogrenci No: 250541011
 * Tarih: 25 Kasim 2025
 * Aciklama: Hafta 6 - Proje 2: Sinema Bileti Sistemi
 */

import java.util.Scanner;
import java.util.Locale;

public class SinemaBileti { 

    // Hafta sonu kontrolu
    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7);
    }

    // Matine kontrolu
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // Temel Fiyat Hesaplama
    public static double calculateBasePrice(int gun, int saat) {
        if (isWeekend(gun)) {
            return isMatinee(saat) ? 55.0 : 85.0;
        } else {
            return isMatinee(saat) ? 45.0 : 65.0;
        }
    }

    // Indirim Hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun) {
        if (yas >= 65) return 0.30;
        if (yas < 12) return 0.25;

        if (meslek == 1) { // Ogrenci
            return (gun >= 1 && gun <= 4) ? 0.20 : 0.15;
        }

        if (meslek == 2) { // Ogretmen
            if (gun == 3) return 0.35;
        }

        return 0.0;
    }

    // Format Ekstra Ucreti
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0.0;   // 2D
            case 2: return 25.0;  // 3D
            case 3: return 35.0;  // IMAX
            case 4: return 50.0;  // 4DX
            default: return 0.0;
        }
    }

    // Toplam Fiyat Hesaplama
    public static double calculateFinalPrice(double temel, double indirimOrani, double ekstra) {
        double indirimTutari = temel * indirimOrani;
        return (temel - indirimTutari) + ekstra;
    }

    // Bilet Bilgisi Yazdirma
    public static void generateTicketInfo(double temel, double indirimTutari, double ekstra, double toplam) {
        System.out.println("\n=== HESAP DETAYI ===");
        System.out.printf(Locale.US, "Temel Fiyat    : %.2f TL%n", temel);
        System.out.printf(Locale.US, "Indirim Tutari : -%.2f TL%n", indirimTutari);
        System.out.printf(Locale.US, "Format Ekstra  : +%.2f TL%n", ekstra);
        System.out.println("-------------------------");
        System.out.printf(Locale.US, "TOPLAM TUTAR   : %.2f TL%n", toplam);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("SINEMA BILETI HESAPLAYICI");

        System.out.print("Gun (1-Pzt ... 7-Paz): ");
        int gun = s.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = s.nextInt();

        System.out.print("Yas: ");
        int yas = s.nextInt();

        System.out.print("Meslek (1=Ogr, 2=Ogrt, 3=Diger): ");
        int meslek = s.nextInt();

        System.out.print("Film Turu (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int tur = s.nextInt();

        double temelFiyat = calculateBasePrice(gun, saat);
        double indirimOrani = calculateDiscount(yas, meslek, gun);
        double ekstraUcret = getFormatExtra(tur);
        double indirimTutari = temelFiyat * indirimOrani;
        double toplam = calculateFinalPrice(temelFiyat, indirimOrani, ekstraUcret);

        generateTicketInfo(temelFiyat, indirimTutari, ekstraUcret, toplam);

        s.close();
    }
}