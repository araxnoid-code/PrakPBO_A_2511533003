package praktikum_4;

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
            System.out.println("6. Cetak Mutasi(riwayat)");
            System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan");
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
                        System.out.println("Masukkan Pin: ");
                        String pin = input.nextLine().trim();
                        System.out.println("Masukkan Saldo Awal: ");

                        if (pin.length() != 6){
                            System.out.println("Pin Harus sepanjang 6 Digit Angka");
                            break;
                        }

                        try {
                            Integer.parseInt(pin);
                        } catch (NumberFormatException e){
                            System.out.println("Pin Harus Berisi Angka");
                            break;
                        }

                        //


                        int a = -1;
                        Boolean[] check = {false, false, false, false, false, false, false, false, false, false};
                        boolean error = false;
                        String msg = "";
                        for (int i = 0; i < pin.length(); i++){
                            int index = Integer.parseInt(pin.substring(i, i + 1));
                            if (check[index]){
                                error = true;
                                msg = "Tidak Boleh Menggunakan Angka Berulang";
                                break;
                            }
                            check[index] = true;

                            if (a == -1){
                                a = index;
                            } else {
                                if ((index - 1) == a){
                                    error = true;
                                    msg = "Tidak Boleh Berurutan";
                                } else if ((index + 1) == a){
                                    error = true;
                                    msg = "Tidak Boleh Berurutan";
                                }
                                a = index;
                            }

                        }

                        if (error){
                            System.out.println(msg);
                            break;
                        }

                        try{
                            double saldoAwal = input.nextDouble();
                            if (saldoAwal < 50000){
                                System.out.println("Saldo minimal Rp50K");
                            } else {
                                System.out.println("Pilih Product");
                                System.out.println("0. Tabungan Umum");
                                System.out.println("1. Giro Bisnis");
                                int jenisTabungan = input.nextInt();

                                if (jenisTabungan == 0 ){
                                    System.out.println("Input Suku Bunga");
                                    double sukuBunga = input.nextDouble();
                                    Rekening tabungan = new RekeningTabungan(noRekening, namaPemilik, saldoAwal, pin, sukuBunga);
                                    listAkunAktif.add(tabungan);
                                } else if (jenisTabungan==1){
                                    System.out.println("Input batasOverdraft");
                                    double batasOverdraft = input.nextDouble();
                                    Rekening tabungan = new RekeningGiro(noRekening, namaPemilik, saldoAwal, pin, batasOverdraft);
                                    listAkunAktif.add(tabungan);
                                } else {
                                    System.out.println("Maaf. Opsi Tidak Ada Pada Pembuatan Rekening");
                                    break;
                                }

                                // listAkunAktif.add(new Rekening(noRekening, namaPemilik, saldoAwal, pin));
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
                            if (listAkunAktif.get(idx).coba >= 3){
                                System.out.println("Maaf Akun Terblokir");
                            } else {
                            System.out.println("Masukkan Nominal Setor: ");
                            String setor = input.nextLine();

                            if (!listAkunAktif.get(idx).setorTunai(setor)){
                                System.out.println("Nominal harus ANGKA");
                                input = new Scanner(System.in);
                            };
                            }
                        }
                        break;

                    case 3:

                        if (listAkunAktif.get(idx).coba >= 3){
                            System.out.println("Akun Terblokir");
                        } else {
                        System.out.println("Masukkan Pin: ");
                        String checkPin = input.nextLine().trim();
                        if (!listAkunAktif.get(idx).otentikasi(checkPin)){
                            listAkunAktif.get(idx).coba += 1;
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                        } else {
                        System.out.println("Masukkan Nominal Penarikan: ");
                        Double tarik = input.nextDouble();
                        listAkunAktif.get(idx).tarikTunai(tarik);
                        }
                        }
                        break;

                    case 4:
                        if (listAkunAktif.isEmpty()){
                            System.out.println("Error: Anda belum memiliki rekening!");
                        } else {
                            if (listAkunAktif.get(idx).coba >= 3){
                                System.out.println("AKUN TERBLOKIR");
                            } else {

                            listAkunAktif.get(idx).cekInformasi();
                            }
                        }
                        break;

                    case 5:
                        if (listAkunAktif.isEmpty()){
                            System.out.println("Error: Anda belum memiliki rekening!");
                        } else {
                            System.out.println("index   Nama");
                            for (int i = 0; i < listAkunAktif.size(); i++){
                                System.out.println(i + "    " + listAkunAktif.get(i).getNamaPemilik());
                            }
                            System.out.println("Pilih Index Rekening: ");
                            int opsi = input.nextInt();
                            idx = opsi;
                        }
                        break;

                    case 6:
                    if (listAkunAktif.get(idx).coba >= 3){
                        System.out.println("Akun Terblokir");
                    } else {
                        System.out.println("Masukkan Pin: ");
                        String checkPinMutasi = input.nextLine().trim();

                        if (!listAkunAktif.get(idx).otentikasi(checkPinMutasi)){
                            listAkunAktif.get(idx).coba += 1;
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                        } else {


                            listAkunAktif.get(idx).cetakMutasi();
                            }
                        }
                        break;
                    case 7:
                        Rekening akun = listAkunAktif.get(idx);
                        Boolean check_tabungan = akun instanceof RekeningTabungan;
                        if (!check_tabungan){
                            System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan.");
                            break;
                        }
                        RekeningTabungan tabungan = (RekeningTabungan) akun;
                        tabungan.tambahBungaAkhirBulan();
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
