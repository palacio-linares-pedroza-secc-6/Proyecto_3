import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

public class GUIAlineacion extends JFrame implements ActionListener {

    Ventana frame;
    JScrollPane mostron;
    JButton sustituir;
    JButton confirmar;
    JButton susCapitan;
    JLabel delantero;
    JLabel medioCampo;
    JLabel defensa;
    JLabel portero;
    JLabel capitan;
    JComboBox posiciones;
    JList playerList;
    JPanel jugadoresMenu;
    JScrollPane jcp = new JScrollPane();
    EquipoFantasia equipoFantasia = Aplicacion.user.getEquipo();
    Alineacion alineacion;

    public GUIAlineacion() {

        if (equipoFantasia.getAlineacion() == null) {
            equipoFantasia.crearAlineacion(equipoFantasia.getJugadores());
            JOptionPane.showMessageDialog(null, "Se creo una alinacion",
                    "Creacion de la alinacion", JOptionPane.INFORMATION_MESSAGE);
        }
        this.alineacion = equipoFantasia.getAlineacion();

        JPanel titulo = new JPanel();
        titulo.setBackground(new Color(25, 24, 55, 255));
        titulo.setPreferredSize(new Dimension(0, 85));
        titulo.setBorder(BorderFactory.createEtchedBorder());

        JPanel menu = new JPanel();
        menu.setBackground(new Color(25, 24, 55, 255));
        menu.setLayout(new GridLayout(4, 3, 20, 20));
        menu.setBorder(BorderFactory.createEtchedBorder());

        jugadoresMenu = new JPanel();
        jugadoresMenu.setBackground(new Color(25, 24, 55, 255));
        jugadoresMenu.setPreferredSize(new Dimension(450, 100));
        jugadoresMenu.setLayout(new GridLayout(1, 1));

        JPanel menuOpciones = new JPanel();
        menuOpciones.setBackground(new Color(25, 24, 55, 255));
        menuOpciones.setPreferredSize(new Dimension(350, 100));
        menuOpciones.setLayout(new GridLayout(7, 1, 5, 5));

        JPanel vacioS = new JPanel();
        vacioS.setBackground(new Color(7, 7, 33, 255));
        vacioS.setPreferredSize(new Dimension(85, 85));
        vacioS.setLayout(new GridLayout(1, 3, 5, 5));

        // Creacion de texto

        JLabel tituloTxt = new JLabel();
        tituloTxt.setText("Alineacion");
        tituloTxt.setFont(new Font("Times New Roman", Font.PLAIN, 55));
        tituloTxt.setForeground(Color.WHITE);
        tituloTxt.setAlignmentX(Component.CENTER_ALIGNMENT);

        delantero = new JLabel();
        String susDelantero = new String();
        try {
            susDelantero = equipoFantasia.getSusDelantero().getNombre();
        } catch (Exception e) {
            susDelantero = "Missing";
        }
        delantero.setText("Delantero suplente: " + susDelantero);
        delantero.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        delantero.setForeground(Color.WHITE);

        medioCampo = new JLabel();
        String susMedioCampo = new String();
        try {
            susMedioCampo = equipoFantasia.getSusMedio().getNombre();
        } catch (Exception e) {
            susMedioCampo = "Missing";
        }
        medioCampo.setText("Medio suplente: " + susMedioCampo);
        medioCampo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        medioCampo.setForeground(Color.WHITE);

        defensa = new JLabel();
        String susDefensa = new String();
        try {
            susDefensa = equipoFantasia.getSusDefensa().getNombre();
        } catch (Exception e) {
            susDefensa = "Missing";
        }
        defensa.setText("Defensa suplente: " + susDefensa);
        defensa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        defensa.setForeground(Color.WHITE);

        portero = new JLabel();
        String susPortero = new String();
        try {
            susPortero = equipoFantasia.getSusArquero().getNombre();
        } catch (Exception e) {
            susPortero = "Missing";
        }
        portero.setText("Portero suplente: " + susPortero);
        portero.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        portero.setForeground(Color.WHITE);

        capitan = new JLabel();
        String capitanE = new String();
        try {
            capitanE = alineacion.getCapitan().getNombre();
        } catch (Exception e) {
            capitanE = "Missing";
        }
        capitan.setText("Capitan: " + capitanE);
        capitan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        capitan.setForeground(Color.WHITE);

        // JComboBox

        Posicion[] posicionesList = { Posicion.PORTERO, Posicion.DEFENSA, Posicion.MEDIOCAMPISTA, Posicion.DELANTERO };
        posiciones = new JComboBox(posicionesList);
        posiciones.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Jugador> jugadores = equipoFantasia
                        .getJugadoresPosicion((Posicion) posiciones.getSelectedItem());
                String[] list = new String[jugadores.size()];

                for (int a = 0; a < jugadores.size(); a++) {
                    Jugador jugador = jugadores.get(a);
                    String nombre = jugador.getNombre();
                    String equipoShort = jugador.getEquipo().getNombreShort();
                    int valor = jugador.getValor();
                    list[a] = (nombre + "/" + equipoShort + "/" + valor);
                }

                playerList = new JList<String>(list);
                jcp.setViewportView(playerList);

            }
        });

        sustituir = new JButton("Escoger Suplente");
        sustituir.setFocusable(false);
        sustituir.setBackground(new Color(37, 32, 70, 255));
        sustituir.setBorder(BorderFactory.createEtchedBorder());
        sustituir.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        sustituir.setForeground(Color.WHITE);
        sustituir.addActionListener(this);
        sustituir.setPreferredSize(new Dimension(85, 35));

        susCapitan = new JButton("Escoger Capitan");
        susCapitan.setFocusable(false);
        susCapitan.setBackground(new Color(37, 32, 70, 255));
        susCapitan.setBorder(BorderFactory.createEtchedBorder());
        susCapitan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        susCapitan.setForeground(Color.WHITE);
        susCapitan.addActionListener(this);
        susCapitan.setPreferredSize(new Dimension(85, 35));

        confirmar = new JButton("Confirmar");
        confirmar.setFocusable(false);
        confirmar.setBackground(new Color(37, 32, 70, 255));
        confirmar.setBorder(BorderFactory.createEtchedBorder());
        confirmar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        confirmar.setForeground(Color.WHITE);
        confirmar.addActionListener(this);
        confirmar.setPreferredSize(new Dimension(100, 50));

        frame = new Ventana();

        titulo.add(tituloTxt);
        menuOpciones.add(posiciones);
        menuOpciones.add(capitan);
        menuOpciones.add(portero);
        menuOpciones.add(defensa);
        menuOpciones.add(medioCampo);
        menuOpciones.add(delantero);
        menuOpciones.add(confirmar);

        vacioS.add(sustituir);
        vacioS.add(susCapitan);

        jugadoresMenu.add(jcp);

        frame.add(titulo, BorderLayout.NORTH);
        frame.add(jugadoresMenu, BorderLayout.WEST);
        frame.add(menuOpciones, BorderLayout.EAST);
        frame.add(vacioS, BorderLayout.SOUTH);

        // frame.add(menu, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == sustituir) {
            int index = playerList.getSelectedIndex();
            String jugador = (String) playerList.getSelectedValue();
            jugador = jugador.split("/")[0];
            Posicion pos = (Posicion) posiciones.getSelectedItem();
            Jugador jugadorSus = equipoFantasia.getJugadoresPosicion(pos).get(index);

            if (jugadorSus == alineacion.getCapitan()) {
                JOptionPane.showMessageDialog(null,
                        "Ese jugador es su capitan, cambie de capitan o escoga otro suplente", "Error capiran",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                alineacion.Sustituir(jugadorSus);
                if (pos == Posicion.PORTERO) {
                    portero.setText("Portero suplente: " + equipoFantasia.getSusArquero().getNombre());
                } else if (pos == Posicion.DEFENSA) {
                    defensa.setText("Defensa suplente: " + equipoFantasia.getSusDefensa().getNombre());
                } else if (pos == Posicion.MEDIOCAMPISTA) {
                    medioCampo.setText("Medio suplente: " + equipoFantasia.getSusMedio().getNombre());
                } else if (pos == Posicion.DELANTERO) {
                    delantero.setText("Delantero suplente: " + equipoFantasia.getSusDelantero().getNombre());
                }
            }
        }

        else if (e.getSource() == susCapitan) {

            int index = playerList.getSelectedIndex();
            String jugador = (String) playerList.getSelectedValue();
            jugador = jugador.split("/")[0];
            Posicion pos = (Posicion) posiciones.getSelectedItem();
            Jugador jugadorSus = equipoFantasia.getJugadoresPosicion(pos).get(index);

            if (equipoFantasia.getSusArquero() == jugadorSus || equipoFantasia.getSusDefensa() == jugadorSus
                    || equipoFantasia.getSusMedio() == jugadorSus || equipoFantasia.getSusDelantero() == jugadorSus) {
                JOptionPane.showMessageDialog(null, "Ese jugador esta de suplente", "Error capiran",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                alineacion.setCapitan(jugadorSus);
                capitan.setText("Capitan: " + alineacion.getCapitan().getNombre());
            }

        } else if (e.getSource() == confirmar)

            if (equipoFantasia.getSusArquero() == null || equipoFantasia.getSusDefensa() == null
                    || equipoFantasia.getSusMedio() == null || equipoFantasia.getSusDelantero() == null
                    || alineacion.getCapitan() == null) {

                int input = JOptionPane.showConfirmDialog(null,
                        "Su alineacion no esta completa seguro quiere dejarla asi?",
                        "Select an Option...",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                // 0=yes, 1=no,
                if (input == 0) {
                    frame.dispose();
                    SerializarObjeto.serializarObjeto(System.getProperty("user.dir") + "/data/TemporadaActual.txt",
                            Aplicacion.getTemporadaActual());
                    new GUIParticipante(Aplicacion.user.getNombre());
                }

            } else {
                frame.dispose();
                SerializarObjeto.serializarObjeto(System.getProperty("user.dir") + "/data/TemporadaActual.txt",
                        Aplicacion.getTemporadaActual());
                new GUIParticipante(Aplicacion.user.getNombre());
            }

    }

}
