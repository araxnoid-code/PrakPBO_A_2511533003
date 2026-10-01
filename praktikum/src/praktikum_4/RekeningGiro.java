package praktikum_4;

public class RekeningGiro extends Rekening {
    double batasOverdraft;

    public RekeningGiro(String nomor, String nama, Double saldo, String pinAwal, double batasOverdraft){
        super(nomor, nama, saldo, pinAwal);
        this.batasOverdraft = batasOverdraft;
    }

    public double getBatasOverdraft(){
        return this.batasOverdraft;
    }
}
