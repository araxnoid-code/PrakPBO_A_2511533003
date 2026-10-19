package praktikum_5;

public class RekeningGiro extends Rekening {
    double batasOverdraft;

    public RekeningGiro(String nomor, String nama, Double saldo, String pinAwal, double batasOverdraft){
        super(nomor, nama, saldo, pinAwal);
        this.batasOverdraft = batasOverdraft;
    }

    public double getBatasOverdraft(){
        return this.batasOverdraft;
    }

    @Override
    public boolean tarikTunai(double nominal) {
        if (nominal <= 0){
            System.out.println("Gagal: Nominal tarik Giro harus lebih dari 0");
            return false;
        } else if(nominal > (super.saldo + this.batasOverdraft)){
            System.out.println("Transaksi Gagal: Melebihi Batas Overdraft! Maksimal Penarikan: Rp" + (super.saldo + this.batasOverdraft));
            return false;
        } else {
            super.saldo -= nominal;

            String idTrx = "TRX-G-" + System.currentTimeMillis();
            super.riwayatTransaksi.add(new Transaksi(idTrx, "Debit Giro", nominal));

            System.out.println("Tarik Tunai Giro Rp" + nominal + " Berhasil Saldo Saat Ini: Rp" + super.saldo);
            return true;
        }
    }
}
