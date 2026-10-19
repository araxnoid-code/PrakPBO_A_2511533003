package praktikum_5;

public class KartuDebit extends Rekening {
    public KartuDebit(String nomor, String nama, Double saldo, String pinAwal){
        super(nomor, nama, saldo, pinAwal);
    }

    @Override
    public boolean tarikTunai(double nominal) {
        if (nominal >= 500000){
            System.out.println("Limit Penarikan pada sekali tarik adalah Rp500.000");
            return false;
        }
        super.tarikTunai(nominal);
        return true;
    }
}
