import java.io.Serializable;
import java.util.*;

public class Mercado implements Serializable {

    HashMap<Posicion, ArrayList<Jugador>> mercadoPosiciones = new HashMap<Posicion, ArrayList<Jugador>>();
    HashMap<String, Jugador> mercadoJugadores = new HashMap<String, Jugador>();

    public Mercado() {
        mercadoPosiciones.put(Posicion.PORTERO, null);
        mercadoPosiciones.put(Posicion.DEFENSA, null);
        mercadoPosiciones.put(Posicion.MEDIOCAMPISTA, null);
        mercadoPosiciones.put(Posicion.DELANTERO, null);
    }

    public ArrayList<Jugador> getJugadoresporPosicion(Posicion pos) {
        ArrayList<Jugador> lista = mercadoPosiciones.get(pos);
        return lista;
    }
}