/*
 * Ad Soyad: Aylin Akagündüz
 * Ogrenci No: 250541011
 * Tarih: 22 Kasim 2025
 * Aciklama: Hafta 6 - Proje 1
 */

import java.util.Scanner;
import java.util.Locale;

public class OgrenciNotSistemi {

    public static double calculateAverage(double v, double f, double o) {
        return v * 0.30 + f * 0.40 + o * 0.30;
    }

    public static boolean isPassingGrade(double ort) {
        return ort >= 50;
    }

    public static String getLetterGrade(double ort) {
        if (ort >= 90) return "A";
        else if (ort >= 80) return "B";
        else if (ort >= 70) return "C";
        else if (ort >= 60) return "D";
        else return "F";
    }

    public static boolean isHonorList(double ort, double v, double f, double o) {
        return (ort >= 85 && v >= 70 && f >= 70 && o >= 70);
    }

    public static boolean hasRetakeRight(double ort) {
        return (ort >= 40 && ort < 50);
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("OGRENCI NOT SISTEMI");
        
        System.out.print("Vize: ");
        double vize = s.nextDouble();

        System.out.print("Final: ");
        double fin = s.nextDouble();

        System.out.print("Odev: ");
        double odev = s.nextDouble();

        double ort = calculateAverage(vize, fin, odev);
        String harf = getLetterGrade(ort);

        boolean gecti = isPassingGrade(ort);
        boolean onur = isHonorList(ort, vize, fin, odev);
        boolean butunleme = hasRetakeRight(ort);

        // --- ÇIKTIYI ÖRNEKTEKİ GİBİ YAPTIM ---
        System.out.println("\n=== OGRENCI NOT RAPORU ===");
        System.out.println("Vize Notu    : " + vize);
        System.out.println("Final Notu   : " + fin);
        System.out.println("Odev Notu    : " + odev);
        System.out.println("--------------------------");
        System.out.printf(Locale.US, "Ortalama     : %.1f%n", ort);
        System.out.println("Harf Notu    : " + harf);
        System.out.println("Durum        : " + (gecti ? "GECTI" : "KALDI"));
        System.out.println("Onur Listesi : " + (onur ? "EVET" : "HAYIR"));
        System.out.println("Butunleme    : " + (butunleme ? "VAR" : "YOK"));

        s.close();
    }
}