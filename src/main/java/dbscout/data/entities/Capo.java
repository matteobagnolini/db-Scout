package dbscout.data.entities;

public class Capo extends Associato {

    private Branca brancaCapo;      // branca di cui è capo

    public Capo(int codAssociato, String tel, String mail, String nome, String cognome, String cf, int eta, String sesso) {
        super(codAssociato, tel, mail, nome, cognome, cf, eta, sesso);
    }

    public void setBrancaCapo(Branca branca) {
        this.brancaCapo = branca;
    }

    public Branca getBrancaCapo() {
        return brancaCapo;
    }

}
