import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import javax.swing.*;

public class Aplicacion {
	// atributos clase
	static DataDam dataDam = new DataDam();
	static Administrador admin;
	static Participante user;
	private HashMap<String, Temporada> historialTemporadas;
	static Temporada temporadaActual;
	private static ArrayList<Administrador> administradores = new ArrayList<Administrador>();
	private static ArrayList<Participante> participantes = new ArrayList<Participante>();
	private static HashMap<String, Administrador> findAdministrador = new HashMap<>();
	private static HashMap<String, Participante> findParticipantes = new HashMap<>();

	/**
	 * Crea un usuario y lo guarda en la aplicación
	 * 
	 * @param nombre     Nombre con el que desea guardar al usuario
	 * @param contrasena Contraseña con la que quiere relacionar al usuario
	 * @param tipo       Tipo de Usuario (Administrador o Participante)
	 * @return Retorna un string dependiendo del caso. Si ya existe el nombre de
	 *         usuario devuelve "Ese nombre de usuario ya existe"
	 * @throws IOException
	 */
	public static String CrearUsuario(String nombre, String contrasena, Tipo_Usuario tipo)
			throws IOException {

		if (tipo == Tipo_Usuario.ADMINISTRADOR) {
			for (int i = 0; i < administradores.size(); i++) {
				Administrador adminlista = administradores.get(i);
				if (adminlista.getNombre().equals(nombre)) {
					return "ya existe";
				}
			}
			Administrador Admin = new Administrador(nombre, contrasena);
			administradores.add(Admin);
			findAdministrador.put(nombre, Admin);
			dataDam.addAdministrador(nombre, contrasena);

			return "Se ha creado";
		} else if (tipo == Tipo_Usuario.PARTICIPANTE) {
			System.out.println("=================");
			for (int i = 0; i < participantes.size(); i++) {
				Participante userlista = participantes.get(i);
				System.out.println(userlista.getNombre());
				if (userlista.getNombre().equals(nombre)) {
					return "ya existe";
				}
			}
			Participante User = new Participante(nombre, contrasena);
			participantes.add(User);
			findParticipantes.put(nombre, User);
			dataDam.addParticipante(nombre, contrasena);
			temporadaActual.addParticipante(User);

			return "Se ha creado";
		} else {
			return null;
		}
	}

	public static void reCrearUsuarios(String nombre, String contrasena, Tipo_Usuario tipo) {

		if (tipo == Tipo_Usuario.ADMINISTRADOR) {

			Administrador Admin = new Administrador(nombre, contrasena);
			administradores.add(Admin);
			findAdministrador.put(nombre, Admin);
		} else if (tipo == Tipo_Usuario.PARTICIPANTE) {
			Participante User = new Participante(nombre, contrasena);
			participantes.add(User);
			findParticipantes.put(nombre, User);
			try {
				temporadaActual.addParticipante(User);
			} catch (Exception e) {

			}
		}
	}

	public static String logIn(String nombre, String contrasena, Tipo_Usuario tipo) {

		if (tipo == Tipo_Usuario.ADMINISTRADOR) {
			for (int i = 0; i < administradores.size(); i++) {
				Administrador adminlista = administradores.get(i);

				if (adminlista.getNombre().equals(nombre) && adminlista.getContrasena().equals(contrasena)) {
					admin = adminlista;
					return "LogIn Valido";

				}
			}
			return "LogIn NO Valido";
		} else if (tipo == Tipo_Usuario.PARTICIPANTE) {
			for (int i = 0; i < participantes.size(); i++) {
				Participante userlista = participantes.get(i);

				if (userlista.getNombre().equals(nombre) && userlista.getContrasena().equals(contrasena)) {
					user = userlista;
					return "LogIn Valido";
				}
			}
			return "LogIn NO Valido";
		} else {
			return "Ingrese un tipo Valido";
		}
	}

	public static Temporada getTemporadaActual() {
		return temporadaActual;
	}

	public static void setTemporada(Temporada temporada) {
		temporadaActual = temporada;
	}

	public void cerrarTemporadaActual(Temporada temporadaNueva) {
		historialTemporadas.put(temporadaActual.getNombreTemporada(), temporadaActual);
		setTemporada(temporadaNueva);
	}

	public ArrayList<EquipoFantasia> QueuetoList(PriorityQueue<Pair> ganadores) {
		ArrayList<EquipoFantasia> lista = new ArrayList<EquipoFantasia>(ganadores.size());
		while (!ganadores.isEmpty()) {
			lista.add((EquipoFantasia) ganadores.poll().getValue());
		}
		return lista;
	}

	public static void crearMapa(HashMap<Posicion, ArrayList<Jugador>> hashmap, ArrayList<Jugador> lista) {
		for (int i = 0; i < lista.size(); i++) {
			Jugador jugadoract = lista.get(i);
			if (hashmap.containsKey(jugadoract.getPosicion())) {
				ArrayList<Jugador> listaposicion = hashmap.get(jugadoract.getPosicion());
				listaposicion.add(jugadoract);
			} else {
				ArrayList<Jugador> listanuevaposicion = new ArrayList<Jugador>();
				listanuevaposicion.add(jugadoract);
				hashmap.put(jugadoract.getPosicion(), listanuevaposicion);
			}
		}

	}

	public ArrayList<EquipoFantasia> getGanadores() {
		PriorityQueue<Pair> ganadores = temporadaActual.getRankingEquipoFantasia();
		ArrayList<EquipoFantasia> listaganadores = QueuetoList(ganadores);
		return listaganadores;

	}

	public ArrayList<EquipoFantasia> getGanadoresPasados(String nombreTemporada) {
		Temporada temporadavieja = historialTemporadas.get(nombreTemporada);
		PriorityQueue<Pair> ganadores = temporadavieja.getRankingEquipoFantasia();
		ArrayList<EquipoFantasia> Ganadores = QueuetoList(ganadores);
		return Ganadores;

	}

	public String getHora() {
		String hora = LocalTime.now().toString();
		return hora;
	}

	public Aplicacion() throws IOException {

		Temporada temp = SerializarObjeto
				.deserializarObjeto(System.getProperty("user.dir") + "/data/TemporadaActual.txt", Temporada.class);
		if (temp != null) {
			temporadaActual = temp;
			if (temp.getParticipantes() == null) {
				System.out.println("Vacio");
			} else {
				Aplicacion.participantes = temp.getParticipantes();
			}

		}
		dataDam.loadUsuarios();
		dataDam.creatFilesUsuarios();
	}

}