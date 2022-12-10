import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class GUIParticipante extends JFrame implements ActionListener {

    Ventana frame;
    JButton crearEquipo;
    JButton comprarJugador;
    JButton editarAlineacion;
    JButton estadisticas;
    JButton logOut;
    JLabel equipoSelect;
    JLabel puntaje;

    public GUIParticipante(String nombre) {

        JPanel titulo = new JPanel();
        titulo.setBackground(new Color(25, 24, 55, 255));
        titulo.setPreferredSize(new Dimension(0, 85));
        titulo.setBorder(BorderFactory.createEtchedBorder());

        JPanel menu = new JPanel();
        menu.setBackground(new Color(25, 24, 55, 255));
        menu.setLayout(new GridLayout(5, 3, 20, 20));
        menu.setBorder(BorderFactory.createEtchedBorder());

        JPanel vacioW = new JPanel();
        vacioW.setBackground(new Color(7, 7, 33, 255));
        vacioW.setPreferredSize(new Dimension(100, 100));

        JPanel vacioE = new JPanel();
        vacioE.setBackground(new Color(7, 7, 33, 255));
        vacioE.setPreferredSize(new Dimension(100, 100));

        JPanel vacioS = new JPanel();
        vacioS.setBackground(new Color(7, 7, 33, 255));
        vacioS.setPreferredSize(new Dimension(100, 85));

        // Creacion de texto

        JLabel tituloTxt = new JLabel();
        tituloTxt.setText("Bienvenido " + nombre);
        tituloTxt.setFont(new Font("Times New Roman", Font.PLAIN, 55));
        tituloTxt.setForeground(Color.WHITE);
        tituloTxt.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel equipo = new JLabel();
        equipo.setText("Equipo:  ");
        equipo.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        equipo.setForeground(Color.WHITE);
        equipo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel equipoSelect = new JLabel();
        try {
            equipoSelect.setText(Aplicacion.user.getEquipo().getNombre());
        } catch (Exception e) {
            equipoSelect.setText("No hay equipo seleccionado");
        }
        equipoSelect.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        equipoSelect.setForeground(Color.WHITE);
        equipoSelect.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel puntaje = new JLabel();
        try {
            puntaje.setText("puntaje: " + Aplicacion.user.getEquipo().getPuntos());
        } catch (Exception e) {
            puntaje.setText("Puntaje: 0");
        }
        puntaje.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        puntaje.setForeground(Color.WHITE);
        puntaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creacion de botones

        crearEquipo = new JButton("Gestionar equipo");
        crearEquipo.setFocusable(false);
        crearEquipo.setBackground(new Color(37, 32, 70, 255));
        crearEquipo.setBorder(BorderFactory.createEtchedBorder());
        crearEquipo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        crearEquipo.setForeground(Color.WHITE);
        crearEquipo.addActionListener(this);
        crearEquipo.setPreferredSize(new Dimension(100, 50));

        comprarJugador = new JButton("Comprar Jugadores");
        comprarJugador.setFocusable(false);
        comprarJugador.setBackground(new Color(37, 32, 70, 255));
        comprarJugador.setBorder(BorderFactory.createEtchedBorder());
        comprarJugador.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        comprarJugador.setForeground(Color.WHITE);
        comprarJugador.addActionListener(this);
        comprarJugador.setPreferredSize(new Dimension(100, 50));

        editarAlineacion = new JButton("Editar Alineacion");
        editarAlineacion.setFocusable(false);
        editarAlineacion.setBackground(new Color(37, 32, 70, 255));
        editarAlineacion.setBorder(BorderFactory.createEtchedBorder());
        editarAlineacion.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        editarAlineacion.setForeground(Color.WHITE);
        editarAlineacion.addActionListener(this);
        editarAlineacion.setPreferredSize(new Dimension(100, 50));

        estadisticas = new JButton("Estadisticas");
        estadisticas.setFocusable(false);
        estadisticas.setBackground(new Color(37, 32, 70, 255));
        estadisticas.setBorder(BorderFactory.createEtchedBorder());
        estadisticas.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        estadisticas.setForeground(Color.WHITE);
        estadisticas.addActionListener(this);
        estadisticas.setPreferredSize(new Dimension(100, 50));

        logOut = new JButton("Log out");
        logOut.setFocusable(false);
        logOut.setBackground(new Color(37, 32, 70, 255));
        logOut.setBorder(BorderFactory.createEtchedBorder());
        logOut.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        logOut.setForeground(Color.WHITE);
        logOut.addActionListener(this);
        logOut.setPreferredSize(new Dimension(100, 50));

        // agr`egar valores a la ventana

        frame = new Ventana();

        titulo.add(tituloTxt);

        menu.add(equipo);
        menu.add(equipoSelect);
        menu.add(puntaje);

        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(crearEquipo);
        menu.add(Box.createRigidArea(new Dimension(5, 0)));

        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(comprarJugador);
        menu.add(Box.createRigidArea(new Dimension(5, 0)));

        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(editarAlineacion);
        menu.add(Box.createRigidArea(new Dimension(5, 0)));

        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(estadisticas);
        menu.add(Box.createRigidArea(new Dimension(5, 0)));

        vacioS.add(logOut);

        frame.add(titulo, BorderLayout.NORTH);
        frame.add(vacioW, BorderLayout.WEST);
        frame.add(vacioE, BorderLayout.EAST);
        frame.add(vacioS, BorderLayout.SOUTH);
        frame.add(menu, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == crearEquipo) {
            frame.dispose();
            SerializarObjeto.serializarObjeto(System.getProperty("user.dir") + "/data/TemporadaActual.txt",
                    Aplicacion.getTemporadaActual());
            new GUICrearEquipo();
        }

        else if (e.getSource() == comprarJugador) {

            try {
                new GUIVenta();
                frame.dispose();
                SerializarObjeto.serializarObjeto(System.getProperty("user.dir") + "/data/TemporadaActual.txt",
                        Aplicacion.getTemporadaActual());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "No tiene un equipo",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (e.getSource() == editarAlineacion) {
            if (Aplicacion.user.getNombresEquiposFantasy() == null) {
                JOptionPane.showMessageDialog(null, "No tienes equipos",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                new GUIAlineacion();
                frame.dispose();
                SerializarObjeto.serializarObjeto(System.getProperty("user.dir") + "/data/TemporadaActual.txt",
                        Aplicacion.getTemporadaActual());
            }

        }

        else if (e.getSource() == logOut)

        {
            frame.dispose();
            SerializarObjeto.serializarObjeto(System.getProperty("user.dir") + "/data/TemporadaActual.txt",
                    Aplicacion.getTemporadaActual());
            new GUILogIn();
        }

    }

}
