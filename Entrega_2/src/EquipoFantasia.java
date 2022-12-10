import java.util.*;

public class EquipoFantasia extends Equipo {
	private String nombre;
	private int puntos;
	private int presupuesto;
	private Alineacion alineacionpasada;
	private HashMap<Fecha, ArrayList<Alineacion>> fechasJugadas;
	private HashMap<Posicion, ArrayList<Jugador>> Jugadores;
	private PriorityQueue<Pair> rankingJugadores = new PriorityQueue<>(new Comparador());
	private Jugador susMedio;
	private Jugador susDelantero;
	private Jugador susArquero;
	private Jugador susDefensa;

	public EquipoFantasia(String nombre, Temporada temporada) {
		super(nombre, temporada);
		this.presupuesto = temporada.getPresupuesto();
		this.Jugadores = new HashMap<Posicion, ArrayList<Jugador>>();
		this.fechasJugadas = new HashMap<Fecha, ArrayList<Alineacion>>();
		Aplicacion.temporadaActual.addEquipoFantasy(this);

	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public HashMap<Posicion, ArrayList<Jugador>> getMapa() {
		return Jugadores;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public ArrayList<Alineacion> getFechaJugada(Fecha fecha) {
		return fechasJugadas.get(fecha);
	}

	public void addFechaJugadas(Fecha fechajugada, Alineacion alineacion) {
		if (fechasJugadas.containsKey(fechajugada)) {
			ArrayList<Alineacion> lista_ali = getFechaJugada(fechajugada);
			lista_ali.add(alineacion);
			fechasJugadas.put(fechajugada, lista_ali);
		} else {
			ArrayList<Alineacion> lista_ali = new ArrayList<Alineacion>();
			fechasJugadas.put(fechajugada, lista_ali);
		}

	}

	public ArrayList<Alineacion> getAlineaciones() {
		ArrayList<Alineacion> alinaciones = new ArrayList<Alineacion>();
		for (Fecha fecha : (Fecha[]) fechasJugadas.keySet().toArray()) {
			ArrayList<Alineacion> all = getFechaJugada(fecha);
			alinaciones.addAll(all);
		}
		return alinaciones;
	}

	public void addPuntos(int puntosadd) {
		puntos = puntos + puntosadd;
	}

	public int getPuntos() {
		return puntos;
	}

	public Jugador getSusMedio() {
		return susMedio;
	}

	public void setSusMedio(Jugador susMedio) {
		this.susMedio = susMedio;
	}

	public Jugador getSusDelantero() {
		return susDelantero;
	}

	public void setSusDelantero(Jugador susDelantero) {
		this.susDelantero = susDelantero;
	}

	public Jugador getSusArquero() {
		return susArquero;
	}

	public void setSusArquero(Jugador susArquero) {
		this.susArquero = susArquero;
	}

	public Jugador getSusDefensa() {
		return susDefensa;
	}

	public void setSusDefensa(Jugador susDefensa) {
		this.susDefensa = susDefensa;
	}

	public void crearAlineacion(ArrayList<Jugador> listajugadores) {
		Alineacion alineacion = new Alineacion(listajugadores, this);
		this.alineacionpasada = alineacion;
	}

	public PriorityQueue<Pair> getRankingJugadores() {
		return rankingJugadores;
	}

	public Alineacion getAlineacion() {
		return alineacionpasada;
	}

	public Mercado crearMercado() {
		Mercado mercado = new Mercado();
		return mercado;
	}

	public void removeJugador(Jugador jugador, int index) {
		Posicion pos = jugador.getPosicion();
		ArrayList<Jugador> players = this.getJugadoresPosicion(pos);
		setPresupuesto(presupuesto + jugador.getValorVenta());
		players.remove(index);
		Jugadores.put(pos, players);
	}

	public void addJugadorRanking(Pair playerpuntos) {
		Jugador jugador = (Jugador) playerpuntos.getValue();
		int puntos = playerpuntos.getKey();
		Iterator<Pair> value = rankingJugadores.iterator();
		boolean playerfound = false;
		while (value.hasNext()) {
			Pair pair = value.next();
			Jugador player = (Jugador) pair.getValue();
			if (jugador == player) {
				int puntos1 = pair.getKey();
				puntos1 = puntos1 + puntos;
				pair.setKey(puntos1);
				playerfound = true;
			}
		}
		if (playerfound == false) {
			rankingJugadores.add(playerpuntos);
		}

	}

}
