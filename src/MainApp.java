/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Map<String, Integer> jenisBuku = new HashMap<>();
        jenisBuku.put("pelajaran", 2000);
        jenisBuku.put("novel", 5000);
        jenisBuku.put("skripsi", 10000);

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("======================================================");
        System.out.println("Selamat Datang DI Perpustakaan Binus Online");
        System.out.println("======================================================");
        System.out.println("Bebas Meminjam buku tanpa biaya (Gratis)");
        System.out.println("Selama tidak melewati tenggat");
        System.out.println("Waktu pinjamnya selama 10 hari");
        System.out.println("Melebihi tenggak akan dikenakan denda");
        System.out.println("======================================================");
        System.out.println("");

        System.out.print("Masukkan jenis buku (pelajaran/novel/skripsi): ");
        String jenis = scanner.nextLine();

        System.out.print("Masukkan tanggal pinjam (yyyy-mm-dd): ");
        String tanggalPinjamStr = scanner.nextLine();
        LocalDate tanggalPinjam = LocalDate.parse(tanggalPinjamStr);

        //digunakan untuk memberi tenggat 10 hari pinjam
        LocalDate tanggalJatuhTempo = tanggalPinjam.plusDays(10);
        LocalDate tanggalKembali;

        do {
            System.out.print("Masukkan tanggal kembali (yyyy-mm-dd): ");
            String tanggalKembaliStr = scanner.nextLine();
            tanggalKembali = LocalDate.parse(tanggalKembaliStr);

            if (tanggalKembali.isBefore(tanggalJatuhTempo)) {
                System.out.println("Tanggal kembali harus setelah atau pada tanggal jatuh tempo (" + tanggalJatuhTempo + ")");
            }
        } while (tanggalKembali.isBefore(tanggalJatuhTempo));

        long jumlahHariTerlambat = ChronoUnit.DAYS.between(tanggalJatuhTempo, tanggalKembali);
        jumlahHariTerlambat = Math.max(jumlahHariTerlambat, 0); 

        int tarifDendaPerHari = jenisBuku.getOrDefault(jenis, 0);
        int denda = tarifDendaPerHari * (int) jumlahHariTerlambat;

        System.out.println("Jumlah hari terlambat: " + jumlahHariTerlambat);
        System.out.println("Denda yang harus dibayarkan: Rp" + denda);
    }
}