package praktikum_4;

public class RekeningVIP extends Rekening {
    public RekeningVIP(String nomor, String nama, Double saldo, String pinAwal){
        super(nomor, nama, saldo + 100000, pinAwal);

    }
}
