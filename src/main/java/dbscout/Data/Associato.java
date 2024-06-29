package dbscout.Data;

public class Associato {
    private int codAssociato;
    private String tel;
    private String mail;
    private String nome;
    private String cognome;
    private String cf;
    private int eta;
    private char sesso;
    private Branca branca;

    public Associato(int codAssociato, String tel, String mail, String nome, 
                     String cognome, String cf, int eta, char sesso) {
        this.codAssociato = codAssociato;
        this.tel = tel;
        this.mail = mail;
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.eta = eta;
        this.sesso = sesso;
    }

    public int getCodAssociato() {
        return codAssociato;
    }

    public void setCodAssociato(int codAssociato) {
        this.codAssociato = codAssociato;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public char getSesso() {
        return sesso;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public Branca getBranca() {
        return branca;
    }

    public void setBranca(Branca branca) {
        this.branca = branca;
    }

}

