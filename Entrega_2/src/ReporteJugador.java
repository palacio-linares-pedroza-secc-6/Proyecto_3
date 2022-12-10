import java.io.Serializable;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class ReporteJugador implements Serializable {

    private String nombrePartido;
    private int minutosJugados;
    private int minutoIngresado;
    private int minutoSalido;
    private int goles;
    private int golesPenlatis;
    private int autogoles;
    private int asistencias;
    private int golesRecibidos;
    private int penaltisDetenidos;
    private int penaltisErrados;
    private int amarillas;
    private int rojas;
    private int tiros_libres;
    private int manos;
    private int goles_de_tiro_libre;

    public ReporteJugador(String partidoBus, int minJugados, int minIngresado, int minSalido, int goles,
            int golesPenaltis, int autogoles, int asistencias, int golesRecibidos, int penaltisDetenidos,
            int penaltisErrados, int tarjetasAmarillas, int tarjetasRojas, int tiros_libres, int manos,
            int goles_de_tiro_libre) {

        this.nombrePartido = partidoBus;
        this.minutosJugados = minJugados;
        this.minutoIngresado = minIngresado;
        this.minutoSalido = minSalido;
        this.goles = goles;
        this.golesPenlatis = golesPenaltis;
        this.autogoles = autogoles;
        this.asistencias = asistencias;
        this.golesRecibidos = golesRecibidos;
        this.penaltisDetenidos = penaltisDetenidos;
        this.penaltisErrados = penaltisErrados;
        this.amarillas = tarjetasAmarillas;
        this.rojas = tarjetasRojas;
        this.tiros_libres = tiros_libres;
        this.manos = manos;
        this.goles_de_tiro_libre = goles_de_tiro_libre;
    }

    /**
     * Retorna el nombre del partido del cual es el reporte
     * 
     * @return Nombre del partido relacionado al reporte
     */
    public String getNombrePartido() {
        return nombrePartido;
    }

    /**
     * Retorna los minutos jugados por el jugador asociado
     * 
     * @return Numero de minutos jugados por el jugador asociado
     */
    public int getminutosJugados() {
        return minutosJugados;
    }

    /**
     * 
     * @return
     */
    public int getminutoIngresado() {
        return minutoIngresado;
    }

    public int getminutoSalido() {
        return minutoSalido;
    }

    public int getGoles() {
        return goles;
    }

    public int getGolesPenaltis() {
        return golesPenlatis;
    }

    public int getAutogoles() {
        return autogoles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public int getGolesRecibidos() {
        return golesRecibidos;
    }

    public int getPenaltisDetenidos() {
        return penaltisDetenidos;
    }

    public int getPenaltisErrados() {
        return penaltisErrados;
    }

    public int getTarjetasAmarillas() {
        return amarillas;
    }

    public int getTarjetasRojas() {
        return rojas;
    }

    public int getTirosLibres() {
        return tiros_libres;
    }

    public int getManos() {
        return manos;
    }

    public int getGolesdeTiroLibre() {
        return goles_de_tiro_libre;
    }

    public static void calcularPuntosPartido(Partido partido, Alineacion alineacion) {
        if (alineacion.checkAlineacioncompleta()) {
            EquipoFantasia equipo = alineacion.getEquipo();
            Fecha fecha = partido.getFecha();
            ArrayList<Jugador> jugadores = alineacion.getJugadores();
            for (Jugador jugador : jugadores) {
                int puntos = 0;
                ReporteJugador reporte = jugador.getReporte(partido.getNombre());
                if (reporte != null) {
                    if (reporte.getminutosJugados() > 0) {
                        System.out.println("ASDASDASDASDASD"+jugador.getNombre());
                        if (fecha.getIndexPartido(partido) >= 2) {
                            System.out.println("ENTRO1");
                            int index = fecha.getIndexPartido(partido);
                            Partido partido1 = fecha.getPartidoporIndex(index - 2);
                            int goles1 = jugador.getReporte(partido1.getNombre()).getGoles();
                            int minutos1 = jugador.getReporte(partido1.getNombre()).getminutosJugados();
                            Partido partido2 = fecha.getPartidoporIndex(index - 1);
                            int goles2 = jugador.getReporte(partido2.getNombre()).getGoles();
                            int minutos2 = jugador.getReporte(partido2.getNombre()).getminutosJugados();
                            if (goles1 > 1 && goles2 > 1 && reporte.getGoles() > 1) {
                                puntos = puntos + 10;
                            }
                            if (minutos1 > 60 && minutos2 > 60 && reporte.getminutosJugados() > 60) {
                                puntos = puntos + 5;
                            }
                        }
                            if (jugador.equals(alineacion.getCapitan())) {
                                if (partido.getLocal().equals(jugador.getEquipo())) {
                                    if (partido.getMarcador().getKey() > (int) partido.getMarcador().getValue()) {
                                        puntos += 5;
                                    }
                                } else {
                                    if (partido.getMarcador().getKey() < (int) partido.getMarcador().getValue()) {
                                        puntos += 5;
                                    }
                                }
                            }
                        System.out.println(reporte.getManos());
                        puntos = puntos + reporte.getAsistencias() * 3;
                        puntos = puntos - reporte.getAutogoles() * 2;
                        puntos = puntos - reporte.getPenaltisErrados() * 2;
                        puntos = puntos - reporte.getTarjetasRojas() * 3;
                        puntos = puntos - reporte.getTarjetasAmarillas();
                        if (reporte.getminutosJugados() <= 60) {
                            puntos += 1;
                        } else {
                            puntos += 2;
                        }
                        if (jugador.getPosicion().equals(Posicion.DELANTERO)) {
                            puntos = puntos + reporte.getGoles() * 4;
                        } else if (jugador.getPosicion().equals(Posicion.MEDIOCAMPISTA)) {
                            puntos = puntos + reporte.getGoles() * 5;
                        } else {
                            puntos = puntos + reporte.getGoles() * 6;
                            if (partido.getLocal().equals(jugador.getEquipo())) {
                                if ((int) partido.getMarcador().getValue() == 0) {
                                    puntos += 4;
                                }
                            } else {
                                if (partido.getMarcador().getKey() == 0) {
                                    puntos += 4;
                                }
                            }
                            if (jugador.getPosicion().equals(Posicion.PORTERO)) {
                                puntos = puntos + reporte.getPenaltisDetenidos() * 5;
                            }
                        }
                    }
                    
                }
                Pair playerpuntos = new Pair(puntos, jugador);
                equipo.addJugadorRanking(playerpuntos);
            }
        }
    }

    public static void calcularPuntosFecha(Fecha fecha) {
        Temporada temporada = Aplicacion.getTemporadaActual();
        ArrayList<EquipoFantasia> equipos = temporada.getEquiposFantasy();
        PriorityQueue<Pair> ranking = temporada.getRankingEquipoFantasia();
        for (Partido partido : fecha.getPartidos()) {
            for (EquipoFantasia equipo : equipos) {
                boolean perdieron = false;
                boolean empataron = false;
                ArrayList<Alineacion> alineaciones_fecha = equipo.getFechaJugada(fecha);
                for (Alineacion alineacion : alineaciones_fecha) {
                    for (Jugador jugador : alineacion.getJugadores()) {
                        ReporteJugador reporte = jugador.getReporte(partido.getNombre());
                        if (reporte != null) {
                            if (reporte.getminutosJugados() > 0) {
                                if (partido.getMarcador().getKey() == (int) partido.getMarcador().getValue()) {
                                    empataron = true;
                                }
                                if (partido.getLocal() == jugador.getEquipo()) {
                                    if (partido.getMarcador().getKey() < (int) partido.getMarcador().getValue()) {
                                        perdieron = true;
                                    }
                                } else {
                                    if (partido.getMarcador().getKey() > (int) partido.getMarcador().getValue()) {
                                        perdieron = true;
                                    }
                                }
                            }
                        }
                    }
                }
                if (!perdieron && !empataron) {
                    equipo.addPuntos(15);
                } else if (!perdieron && empataron) {
                    equipo.addPuntos(10);
                }
            }
        }
        EquipoFantasia mejor_equipo = (EquipoFantasia) ranking.peek().getValue();
        for (EquipoFantasia equipo : equipos) {

            if (mejor_equipo.equals(equipo)) {
                if (temporada.esUltimaFecha(fecha)) {
                    mejor_equipo = (EquipoFantasia) ranking.poll().getValue();
                    equipo.addPuntos(10);
                    EquipoFantasia segundo_mejor = (EquipoFantasia) ranking.poll().getValue();
                    segundo_mejor.addPuntos(7);
                    EquipoFantasia tercero = (EquipoFantasia) ranking.poll().getValue();
                    tercero.addPuntos(5);
                    ranking.add(new Pair(equipo.getPuntos(), equipo));
                    ranking.add(new Pair(segundo_mejor.getPuntos(), segundo_mejor));
                    ranking.add(new Pair(tercero.getPuntos(), tercero));

                } else {
                    equipo.addPuntos(10);
                    ranking.add(new Pair(equipo.getPuntos(), equipo));
                }
            }

        }
    }
}
