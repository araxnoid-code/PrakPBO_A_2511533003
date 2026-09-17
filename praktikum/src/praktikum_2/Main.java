package praktikum_2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        // Rekening akunAktif = null;
        ArrayList<Rekening> listAkunAktif = new ArrayList<Rekening>();
        int idx = 0;
        boolean isRunning = true;

        System.out.println("=== SISTEM PERBANKAN MINI ===");
        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun");
            System.out.println("0. keluar");
            System.out.println("Pilih Menu: ");

            try{
                int pilihan = input.nextInt();
                input.nextLine();

                switch (pilihan){
                    case 1:
                        System.out.println("Masukkan Nomor Rekening: ");
                        String noRekening = input.nextLine();
                        System.out.println("Masukkan Nama Pemilik: ");
                        String namaPemilik = input.nextLine();
                        System.out.println("Masukkan Saldo Awal: ");

                        try{
                            double saldoAwal = input.nextDouble();
                            if (saldoAwal < 50000){
                                System.out.println("Saldo minimal Rp50K");
                            } else {
                                listAkunAktif.add(new Rekening(noRekening, namaPemilik, saldoAwal));
                            }
                        } catch(InputMismatchException e){
                            System.out.println("Saldo Harus Angka");
                            input = new Scanner(System.in);
                        }
                        break;

                    case 2:
                        if (listAkunAktif.isEmpty()){
                            System.out.println("Error: Mohon Maaf, Anda Belum Memiliki Nomor Rekening!");
                        } else {
                            System.out.println("Masukkan Nominal Setor: ");
                            String setor = input.nextLine();

                            if (!listAkunAktif.get(idx).setorTunai(setor)){
                                System.out.println("Nominal harus ANGKA");
                                input = new Scanner(System.in);
                            };
                        }
                        break;

                    case 3:
                        System.out.println("Masukkan Nominal Penarikan: ");
                        Double tarik = input.nextDouble();
                        listAkunAktif.get(idx).tarikTunai(tarik);
                        break;

                    case 4:
                        if (listAkunAktif.isEmpty()){
                            System.out.println("Error: Anda belum memiliki rekening!");
                        } else {
                            listAkunAktif.get(idx).cekInformasi();
                        }
                        break;

                    case 5:
                        if (listAkunAktif.isEmpty()){
                            System.out.println("Error: Anda belum memiliki rekening!");
                        } else {
                            System.out.println("index   Nama");
                            for (int i = 0; i < listAkunAktif.size(); i++){
                                System.out.println(i + "    " + listAkunAktif.get(i).namaPemilik);
                            }
                            System.out.println("Pilih Index Rekening: ");
                            int opsi = input.nextInt();
                            idx = opsi;
                        }
                        break;

                    case 0:
                        isRunning = false;
                        System.out.println("Sistem Ditutup. Terimakasih");
                        break;

                    default:
                        System.out.println("Pilihan Tidak Valid!");

                }
            } catch(InputMismatchException e){
                input = new Scanner(System.in);
                System.out.println("Pilihan Opsi Harus Angka");
            };

        }
        input.close();
    }
}
