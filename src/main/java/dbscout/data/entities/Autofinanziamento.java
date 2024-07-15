package dbscout.data.entities;

public class Autofinanziamento {
    
    private String data;
    private String luogo;
    private float guadagno;
    private String tipo;

    public String getData() {
        return data;
    }

    public String getLuogo() {
        return luogo;
    }

    public float getGuadagno() {
        return guadagno;
    }

    public String getTipo() {
        return tipo;
    }

    public Autofinanziamento(String data, String luogo, String tipo, float guadagno) {
        this.data = data;
        this.luogo = luogo;
        this.guadagno = guadagno;
        this.tipo = tipo;
    }

}
