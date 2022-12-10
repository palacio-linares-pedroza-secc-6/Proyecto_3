import java.util.*;
import java.io.Serializable;

public class Alineacion implements Serializable {
    private HashMap<Posicion, ArrayList<Jugador>> jugadores = new HashMap<Posicion, ArrayList<Jugador>>();
    private EquipoFantasia equipo;
    private Jugador capitan;

    public Alineacion(ArrayList<Jugador> listajugadores, EquipoFantasia equipo) {
        Aplicacion.crearMapa(jugadores, listajugadores);
        this.equipo = equipo;
    }

    /**
     * Confirma si la alineacion de un equipo tiene el numero de jugadores esperados
     * en las posiciones esperadas
     * <b> pre: </b> La alineacion tiene jugadores añadidos <br>
     * 
     * @return Valor booleano depictando el estado de la alineacion. False
     *         significando incompleta y True significando completa
     */
    public Boolean checkAlineacioncompleta() {
        Object[] posiciones = jugadores.keySet().toArray();
        for (int i = 0; i < 4; i++) {
            Posicion posicion = (Posicion) posiciones[i];
            ArrayList<Jugador> listaporposicion = jugadores.get(posicion);
            if (capitan == null) {
                return false;
            }
            if (posicion == Posicion.PORTERO) {
                if (listaporposicion.size() != 1) {
                    return false;
                }
            } else if (posicion == Posicion.DELANTERO) {
                if (listaporposicion.size() != 2) {
                    return false;
                }
            } else {
                if (listaporposicion.size() != 4) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Utiliza la alineacion para jugar el partido especificado, sustituyendo
     * jugadores si es necesario y calculando los puntos para los jugadores de dicha
     * alineacion
     * <b> pre:</b> La alineacion debe estar inicializada antes de entrar.
     * <b> post:</b> La alineacion es modificada para se realizen las sustituciones
     * necesarias
     * 
     * @param partido
     */
    public void jugarPartido(Partido partido, Fecha fecha) {
        Object[] posiciones = jugadores.keySet().toArray();
        HashMap<Posicion, Integer> sustituciones = new HashMap<Posicion, Integer>();
        for (int i = 0; i < 4; i++) {
            Posicion posicion = (Posicion) posiciones[i];
            sustituciones.put(posicion, 0);
            ArrayList<Jugador> listaporposicion = jugadores.get(posicion);
            for (int j = 0; j < listaporposicion.size(); j++) {
                Jugador jugadoractual = listaporposicion.get(j);
                ReporteJugador reporte = jugadoractual.getReporte(partido.getNombre());
                int numsustituciones = sustituciones.get(posicion);
                if (reporte == null) {
                    if (numsustituciones < 1) {
                        Sustituir(jugadoractual);
                        numsustituciones += 1;
                        sustituciones.put(posicion, numsustituciones);
                    } else {
                        continue;
                    }
                } else {
                    int minutosJugados = reporte.getminutosJugados();
                    if (minutosJugados == 0 && numsustituciones < 1) {
                        Sustituir(jugadoractual);
                        numsustituciones += 1;
                    }
                }
            }
        }
        ReporteJugador.calcularPuntosPartido(partido, this);
        equipo.addFechaJugadas(fecha, this);
    }

    public void Sustituir(Jugador jugadoractual) {
        Posicion posicion = jugadoractual.getPosicion();
        ArrayList<Jugador> players = jugadores.get(posicion);
        if (posicion == Posicion.PORTERO) {
            Jugador sustituto = equipo.getSusArquero();
            equipo.setSusArquero(jugadoractual);
            players.remove(jugadoractual);
            if (sustituto != null) {
                players.add(sustituto);
            }
        } else if (posicion == Posicion.DEFENSA) {
            Jugador sustituto = equipo.getSusDefensa();
            equipo.setSusDefensa(jugadoractual);
            players.remove(jugadoractual);
            if (sustituto != null) {
                players.add(sustituto);
            }
        } else if (posicion == Posicion.MEDIOCAMPISTA) {
            Jugador sustituto = equipo.getSusMedio();
            equipo.setSusMedio(jugadoractual);
            players.remove(jugadoractual);
            if (sustituto != null) {
                players.add(sustituto);
            }
        } else {
            Jugador sustituto = equipo.getSusDelantero();
            equipo.setSusDelantero(jugadoractual);
            players.remove(jugadoractual);
            if (sustituto != null) {
                players.add(sustituto);
            }
        }
    }

    public EquipoFantasia getEquipo() {
        return equipo;
    }

    /**
     * Devuelve el capitan del equipo
     * <b> pre </b> Se debe haber inicializado la alineacion antes <br>
     * 
     * @return El capitan del equipo, null si el capitan no ha sido puesto
     */
    public Jugador getCapitan() {
        return capitan;
    }

    /**
     * Pone el capitan del equipo
     * <b> post:</b> La alineacion queda con el jugador indicado como capitan
     * 
     * @param capitan El jugador cual quiere poner como capitan
     */
    public void setCapitan(Jugador capitan) {
        this.capitan = capitan;
    }

    /**
     * Devuelve una lista de todos los jugadores de la alineacion
     * <b> pre </b> Se debe haber inicializado la alineacion primero. Debe haber
     * minimo un jugador en cada posicion <br>
     * 
     * @return La lista de jugadores que se tiene en la alineacion
     */
    public ArrayList<Jugador> getJugadores() {

        ArrayList<Jugador> jugadoresList = new ArrayList<Jugador>();
        Object[] posiciones = jugadores.keySet().toArray();

        for (int i = 0; i < 4; i++) {
            Posicion posicion = (Posicion) posiciones[i];
            ArrayList<Jugador> listaporposicion = jugadores.get(posicion);
            jugadoresList.addAll(listaporposicion);
        }
        return jugadoresList;
    }
}
