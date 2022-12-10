import java.io.Serializable;
import java.util.*;

@SuppressWarnings("unchecked")
public class Fecha implements Serializable {

    String fecha;
    PriorityQueue<Pair> rankingJugadores;
    PriorityQueue<Pair> rankingEquipoFantasia;
    HashMap<String, Partido> partidos = new HashMap<String, Partido>();

    public Fecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean esUltimoPartido(Partido partido) {
        String[] llaves = partidos.keySet().toArray(new String[0]);
        if (llaves[llaves.length - 1].equals(partido.getNombre())) {
            return true;
        }
        return false;
    }

    public Partido crearPartido(String hora, Equipo local, Equipo visitante) {
        Partido partido = new Partido(hora, local, visitante, this);
        return partido;
    }

    public void addPartido(String hora, Equipo local, Equipo visitante) {
        String nombrelocal = local.getNombreShort();
        String nombrePartido = hora + nombrelocal;
        if (!partidos.containsKey(nombrePartido)) {
            Partido partido = crearPartido(hora, local, visitante);
            partidos.put(nombrePartido, partido);
        }
    }

    public Partido getPartido(String nombrePartido) {
        return partidos.get(nombrePartido);
    }

    public ArrayList<Partido> getPartidos() {
        List<Partido> list = Arrays.asList(partidos.values().toArray(new Partido[0]));
        ArrayList<Partido> lista_partidos = new ArrayList<Partido>(list);
        return lista_partidos;
    }

    public int getIndexPartido(Partido partido) {
        for (int i = 0; i < partidos.values().toArray().length; i++) {
            if (partido.equals((Partido) partidos.values().toArray()[i])) {
                return i;
            }
        }
        return -1;
    }

    public Partido getPartidoporIndex(int index) {
        return (Partido) partidos.values().toArray()[index];
    }

    public Pair[] calcularRankingEquipos() {
        ArrayList<EquipoFantasia> equipos = Aplicacion.getTemporadaActual().getEquiposFantasy();
        Iterable<Pair> pares = (Iterable<Pair>) rankingJugadores.iterator();
        for (EquipoFantasia equipo : equipos) {
            int puntosEquipo = 0;
            for (Jugador jugador : equipo.getAlineacion().getJugadores()) {
                while (((Iterator<Pair>) pares).hasNext()) {
                    if (((Iterator<Pair>) pares).next().getValue() == jugador) {
                        puntosEquipo += ((Iterator<Pair>) pares).next().getKey();
                    }
                }
            }

            Pair equipoFantasia = new Pair(puntosEquipo, equipo);
            rankingEquipoFantasia.add(equipoFantasia);
        }

        return null;
    }

    public void addJugadoresRanking(PriorityQueue<Pair> jugadores) {
        rankingJugadores.addAll(jugadores);
    }

    public String getFecha() {
        return fecha;
    }

}
