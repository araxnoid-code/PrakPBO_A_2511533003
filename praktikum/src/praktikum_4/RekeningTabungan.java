package praktikum_4;

public class RekeningTabungan extends Rekening {
    private double sukuBunga;

    public RekeningTabungan(String nomor, String nama, double saldo, String pinAwal, double sukuBunga){
        super(nomor, nama, saldo, pinAwal);
        this.sukuBunga = sukuBunga;
    }

    public void tambahBungaAkhirBulan(){
        double nominalBunga = super.saldo * (this.sukuBunga / 100);
        super.saldo += nominalBunga;

        String idTrx = "TRX-B-"+System.currentTimeMillis();
        super.riwayatTransaksi.add(new Transaksi(idTrx, "Bunga", nominalBunga));

        System.out.println("Bunga " + this.sukuBunga + "% Berhasil Ditambahkan: Rp" + nominalBunga);
    }
}
