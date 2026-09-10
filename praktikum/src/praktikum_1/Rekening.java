package praktikum_1;

public class Rekening {
    String nomorRekening;
    String namaPemilik;
    double saldo;

    public Rekening(String nomor, String nama, double SaldoAwal){
        this.nomorRekening = nomor;
        this.namaPemilik = nama;
        this.saldo = SaldoAwal;
        System.out.println("Rekening atas nama " + this.namaPemilik + " Berhasil dibuat dengan saldo Rp" + this.saldo);
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
            System.out.println("Setor Tunai Rp" + nominal + " Berhasil. Saldo saat ini: Rp" + this.saldo);
        } else {
            System.out.println("Gagal: nominal setor harus lebih dari 0!");
        }

        return true;
    }

    public void cekInformasi(){
        System.out.println("--- INFORMASI REKENING ---");
        System.out.println("No. rekening    :" + this.nomorRekening);
        System.out.println("Nama Pemilik    :" + this.namaPemilik);
        System.out.println("Saldo Akhir     : Rp" + this.saldo);
        System.out.println("--------------------------");
    }

    public void tarikTunai(double nominal){
        if (nominal < 10000){
            System.out.println("Transaksi Gagal : Minimal nomonal penarikan 10.000");
        }
        if (this.saldo < nominal){
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + this.saldo);
        }

        this.saldo -= nominal;
        System.out.println("Penarikan Rp" + nominal + "Berhasil, saldo anda tersisa Rp" + this.saldo);
    }
}
