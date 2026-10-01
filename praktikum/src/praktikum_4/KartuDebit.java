package praktikum_4;

public class KartuDebit extends Rekening {
    public KartuDebit(String nomor, String nama, Double saldo, String pinAwal){
        super(nomor, nama, saldo, pinAwal);
    }

    @Override
    public void tarikTunai(double nominal) {
        if (nominal >= 500000){
            System.out.println("Limit Penarikan pada sekali tarik adalah Rp500.000");
            return;
        }
        super.tarikTunai(nominal);
    }
}
