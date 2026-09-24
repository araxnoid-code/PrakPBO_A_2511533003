package praktikum_3;

import java.util.ArrayList;

public class Rekening {
    private String nomorRekening;
    private String namaPemilik;
    private double saldo;
    private String pin;
    int coba;

    private ArrayList<Transaksi> riwayatTransaksi;

    public Rekening(String nomor, String nama, double SaldoAwal, String pinAwal){
        this.nomorRekening = nomor;
        this.namaPemilik = nama;
        this.saldo = SaldoAwal;

        if (pinAwal.length() == 6){
            this.pin = "pinAwal";
        } else {
            System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
            this.pin = "123456";
        }

        this.riwayatTransaksi = new ArrayList<Transaksi>();
        System.out.println("Rekening atas nama " + this.namaPemilik + " Berhasil dibuat dengan saldo Rp" + this.saldo);
    }

    public String getNomorRekening(){
        return this.nomorRekening;
    }

    public String getNamaPemilik(){
        return this.namaPemilik;
    }

    public boolean otentikasi(String pinInput){
        return this.pin.equals(pinInput);
    }

    public boolean setorTunai(String nominal){
        double n;
        try{
            n = Double.parseDouble(nominal);
        } catch(NumberFormatException e){
            return false;
        }

        if (n > 0){
            this.saldo += n;

            String idTrx = "TRX-S-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx, "Kredit", n);
            this.riwayatTransaksi.add(trxBaru);
            System.out.println("Setor Tunai Rp" + nominal + " Berhasil. Saldo saat ini: Rp" + this.saldo);
        } else {
            System.out.println("Gagal: nominal setor harus lebih dari 0!");
        }

        return true;
    }

    public void tarikTunai(double nominal){
        if (nominal < 10000){
            System.out.println("Transaksi Gagal : Minimal nomonal penarikan 10.000");
        }
        if (this.saldo < nominal){
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + this.saldo);
        }

        this.saldo -= nominal;

        String idTrx = "TRX-T-" + System.currentTimeMillis();
        Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
        this.riwayatTransaksi.add(trxBaru);
        System.out.println("Penarikan Rp" + nominal + "Berhasil, saldo anda tersisa Rp" + this.saldo);
    }

    public void cekInformasi(){
        System.out.println("--- INFORMASI REKENING ---");
        System.out.println("No. rekening    :" + this.nomorRekening);
        System.out.println("Nama Pemilik    :" + this.namaPemilik);
        System.out.println("Saldo Akhir     : Rp" + this.saldo);
        System.out.println("--------------------------");
    }

    void PengeluaranTerbesarTerkecil(){
        Transaksi terbesar = null;
        Transaksi terkecil = null;
        int len = this.riwayatTransaksi.size();

        for(int i = 0; i < len; i++){
            Transaksi transaksi = this.riwayatTransaksi.get(i);
            if (!transaksi.getJenis().equals("Debit")){
                continue;
            }

            if (terbesar == null){
                terbesar = transaksi;
            } else {
                if (transaksi.getNominal() > terbesar.getNominal()){
                    terbesar = transaksi;
                }
            }

            if (terkecil == null){
                terkecil = transaksi;
            } else {
                if (transaksi.getNominal() < terkecil.getNominal()){
                    terkecil = transaksi;
                }
            }
        }

        System.out.println("===================");
        System.out.println("Pengeluaran Terbesar");
        if (terbesar == null){
            System.out.println("Tidak Ada Transaksi");
        } else {
            terbesar.cetakDetail();
        }

        System.out.println("Pengeluaran Terkecil");
        if (terkecil == null){
            System.out.println("Tidak Ada Transaksi");
        } else {
            terkecil.cetakDetail();
        }
    }

    void PemasukanTerbesarTerkecil(){
        Transaksi terbesar = null;
        Transaksi terkecil = null;
        int len = this.riwayatTransaksi.size();

        for(int i = 0; i < len; i++){
            Transaksi transaksi = this.riwayatTransaksi.get(i);
            if (!transaksi.getJenis().equals("Kredit")){
                continue;
            }

            if (terbesar == null){
                terbesar = transaksi;
            } else {
                if (transaksi.getNominal() > terbesar.getNominal()){
                    terbesar = transaksi;
                }
            }

            if (terkecil == null){
                terkecil = transaksi;
            } else {
                if (transaksi.getNominal() < terkecil.getNominal()){
                    terkecil = transaksi;
                }
            }
        }

        System.out.println("===================");
        System.out.println("Pemasukan Terbesar");
        if (terbesar == null){
            System.out.println("Tidak Ada Transaksi");
        } else {
            terbesar.cetakDetail();
        }

        System.out.println("Pemasukan Terkecil");
        if (terkecil == null){
            System.out.println("Tidak Ada Transaksi");
        } else {
            terkecil.cetakDetail();
        }
    }

    public void cetakMutasi(){
        int len = this.riwayatTransaksi.size();
        if (len == 0) {
            System.out.println("Belum ada transaksi pada rekening ini");
            return;
        }

        if (len <= 3){
            System.out.println("\nMutasi Transaksi: ");
            for(int i = 0; i < len; i++){
                Transaksi transaksi = this.riwayatTransaksi.get(i);
                transaksi.cetakDetail();
            }

            this.PemasukanTerbesarTerkecil();
            this.PengeluaranTerbesarTerkecil();
            return;
        }

        System.out.println("\nMutasi Transaksi: ");
        for(int i = len - 1; i > len - 4; i--){
            Transaksi transaksi = this.riwayatTransaksi.get(i);
            transaksi.cetakDetail();
        }

        this.PemasukanTerbesarTerkecil();
        this.PengeluaranTerbesarTerkecil();
    }
}
