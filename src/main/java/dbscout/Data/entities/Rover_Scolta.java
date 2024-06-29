package dbscout.data.entities;

public class Rover_Scolta extends Associato {

    private Servizio servizio;
    
    public Rover_Scolta(int codAssociato, String tel, String mail, String nome, String cognome, String cf, int eta,
            char sesso) {
        super(codAssociato, tel, mail, nome, cognome, cf, eta, sesso);
    }

    public void setServizio(Servizio servizio) {
        this.servizio = servizio;
    }

    public Servizio getServizio() {
        return this.servizio;
    }
    
}
