package dbscout.Data;

public class Capo extends Associato {

    private Branca brancaCapo;

    public Capo(int codAssociato, String tel, String mail, String nome, String cognome, String cf, int eta, char sesso) {
        super(codAssociato, tel, mail, nome, cognome, cf, eta, sesso);
    }

    /**
     * Setta la branca di cui deve essere capobranca.
     * @param branca
     */
    public void setBrancaCapo(Branca branca) {
        this.brancaCapo = branca;
    }

    public Branca getBranca() {
        return brancaCapo;
    }

}
