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

public class GUIVenta extends JFrame implements ActionListener {
    Ventana frame;
    JScrollPane mostron;
    JButton vender;
    JButton confirmar;
    JLabel presupuesto;
    JLabel delanteros;
    JLabel medioCampos;
    JLabel defensas;
    JLabel porteros;
    JComboBox posiciones;
    JList playerList;
    Mercado mercado = Aplicacion.temporadaActual.getMercado();
    JPanel jugadoresMenu;
    JScrollPane jcp = new JScrollPane();
    EquipoFantasia equipoFantasia = Aplicacion.user.getEquipo();

    public GUIVenta() {

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
        menuOpciones.setLayout(new GridLayout(8, 1, 5, 5));

        JPanel vacioS = new JPanel();
        vacioS.setBackground(new Color(7, 7, 33, 255));
        vacioS.setPreferredSize(new Dimension(0, 65));

        // Creacion de texto

        JLabel tituloTxt = new JLabel();
        tituloTxt.setText("Mercado");
        tituloTxt.setFont(new Font("Times New Roman", Font.PLAIN, 55));
        tituloTxt.setForeground(Color.WHITE);
        tituloTxt.setAlignmentX(Component.CENTER_ALIGNMENT);

        presupuesto = new JLabel();
        presupuesto.setText("Presupuesto : " + Integer.toString(equipoFantasia.getPresupuesto()) + "$");
        presupuesto.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        presupuesto.setForeground(Color.WHITE);
        presupuesto.setAlignmentX(Component.CENTER_ALIGNMENT);

        delanteros = new JLabel();
        String cantidadDelantero = new String();
        try {
            cantidadDelantero = Integer
                    .toString(3 - equipoFantasia.getJugadoresPosicion(Posicion.DELANTERO).size());
        } catch (Exception e) {

            cantidadDelantero = "3";

        }
        delanteros.setText("Delanteros que faltan : " + cantidadDelantero);
        delanteros.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        delanteros.setForeground(Color.WHITE);

        medioCampos = new JLabel();
        String cantidadMedioCampos = new String();
        try {
            cantidadMedioCampos = Integer
                    .toString(5 - equipoFantasia.getJugadoresPosicion(Posicion.MEDIOCAMPISTA).size());
        } catch (Exception e) {

            cantidadMedioCampos = "5";

        }
        medioCampos.setText("Medios que faltan : " + cantidadMedioCampos);
        medioCampos.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        medioCampos.setForeground(Color.WHITE);

        defensas = new JLabel();
        String cantidadDefensas = new String();
        try {
            cantidadDefensas = Integer
                    .toString(5 - equipoFantasia.getJugadoresPosicion(Posicion.DEFENSA).size());
        } catch (Exception e) {

            cantidadDefensas = "5";

        }
        defensas.setText("Defensas que faltan : " + cantidadDefensas);
        defensas.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        defensas.setForeground(Color.WHITE);

        porteros = new JLabel();
        String cantidadPortero = new String();
        try {
            cantidadPortero = Integer
                    .toString(2 - equipoFantasia.getJugadoresPosicion(Posicion.PORTERO).size());
        } catch (Exception e) {

            cantidadPortero = "2";

        }
        porteros.setText("Porteros que faltan : " + cantidadPortero);
        porteros.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        porteros.setForeground(Color.WHITE);

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

        vender = new JButton("Vender");
        vender.setFocusable(false);
        vender.setBackground(new Color(37, 32, 70, 255));
        vender.setBorder(BorderFactory.createEtchedBorder());
        vender.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        vender.setForeground(Color.WHITE);
        vender.addActionListener(this);
        vender.setPreferredSize(new Dimension(85, 35));

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
        menuOpciones.add(presupuesto);
        menuOpciones.add(posiciones);
        menuOpciones.add(vender);
        menuOpciones.add(porteros);
        menuOpciones.add(defensas);
        menuOpciones.add(medioCampos);
        menuOpciones.add(delanteros);
        menuOpciones.add(confirmar);

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

        if (e.getSource() == vender) {
            int index = playerList.getSelectedIndex();
            String jugador = (String) playerList.getSelectedValue();
            jugador = jugador.split("/")[0];
            Posicion pos = (Posicion) posiciones.getSelectedItem();
            Jugador jugadorVender = Aplicacion.user.getEquipo().getJugadoresPosicion(pos).get(index);

            if (pos == Posicion.PORTERO) {

                Aplicacion.user.venderJugador(jugadorVender, index, Aplicacion.user.getEquipo().getNombre());
                presupuesto.setText("Presupuesto : " + Integer.toString(equipoFantasia.getPresupuesto()) + "$");
                porteros.setText("Porteros que faltan : "
                        + Integer.toString(2 - equipoFantasia.getJugadoresPosicion(Posicion.PORTERO).size()));
                posiciones.actionPerformed(e);

            } else if (pos == Posicion.DEFENSA) {

                Aplicacion.user.venderJugador(jugadorVender, index, Aplicacion.user.getEquipo().getNombre());
                presupuesto.setText("Presupuesto : " + Integer.toString(equipoFantasia.getPresupuesto()) + "$");
                defensas.setText("Defensas que faltan : "
                        + Integer.toString(5 - equipoFantasia.getJugadoresPosicion(Posicion.DEFENSA).size()));
                posiciones.actionPerformed(e);
            } else if (pos == Posicion.MEDIOCAMPISTA) {

                Aplicacion.user.venderJugador(jugadorVender, index, Aplicacion.user.getEquipo().getNombre());
                presupuesto.setText("Presupuesto : " + Integer.toString(equipoFantasia.getPresupuesto()) + "$");
                defensas.setText("Medios que faltan : "
                        + Integer.toString(5 - equipoFantasia.getJugadoresPosicion(Posicion.MEDIOCAMPISTA).size()));
                posiciones.actionPerformed(e);
            } else if (pos == Posicion.DELANTERO) {

                Aplicacion.user.venderJugador(jugadorVender, index, Aplicacion.user.getEquipo().getNombre());
                presupuesto.setText("Presupuesto: " + Integer.toString(equipoFantasia.getPresupuesto()) + "$");
                defensas.setText("Delanteros que faltan : "
                        + Integer.toString(3 - equipoFantasia.getJugadoresPosicion(Posicion.DELANTERO).size()));
                posiciones.actionPerformed(e);
            }

        }

        else if (e.getSource() == confirmar)

        {

            if (equipoFantasia.getJugadoresPosicion(Posicion.DELANTERO).size() == 3
                    && equipoFantasia.getJugadoresPosicion(Posicion.MEDIOCAMPISTA).size() == 5
                    && equipoFantasia.getJugadoresPosicion(Posicion.DEFENSA).size() == 5
                    && equipoFantasia.getJugadoresPosicion(Posicion.PORTERO).size() == 2) {

                int input = JOptionPane.showConfirmDialog(null,
                        "No ha hecho cambios en su equipo, quiere salir y dejarlo asi?",
                        "Select an Option...",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
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
                new GUIMercado(false);
            }
        }

    }

}
