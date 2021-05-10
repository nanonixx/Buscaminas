package desplazao;

import java.io.Serializable;

public class Casilla implements Serializable {
    public boolean mina;
    public String marca;

    public Casilla(String marca) {
        this.marca = marca;
        this.mina = false;
    }
}
