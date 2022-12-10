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

import javax.swing.*;

public class GUICrearEquipo extends JFrame implements ActionListener {

    Ventana frame;
    JButton volver;
    JButton crear;
    JTextField nombre;
    JButton escoger;

    public GUICrearEquipo() {

        JPanel titulo = new JPanel();
        titulo.setBackground(new Color(25, 24, 55, 255));
        titulo.setPreferredSize(new Dimension(0, 85));
        titulo.setBorder(BorderFactory.createEtchedBorder());

        JPanel menu = new JPanel();
        menu.setBackground(new Color(25, 24, 55, 255));
        menu.setLayout(new GridLayout(4, 3, 20, 20));
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
        tituloTxt.setText("Cree Su Equipo");
        tituloTxt.setFont(new Font("Times New Roman", Font.PLAIN, 55));
        tituloTxt.setForeground(Color.WHITE);
        tituloTxt.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creacion de Jbuttons

        crear = new JButton("Crear Equipo");
        crear.setFocusable(false);
        crear.setBackground(new Color(37, 32, 70, 255));
        crear.setBorder(BorderFactory.createEtchedBorder());
        crear.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        crear.setForeground(Color.WHITE);
        crear.addActionListener(this);
        crear.setPreferredSize(new Dimension(100, 50));

        escoger = new JButton("Escoger Equipo");
        escoger.setFocusable(false);
        escoger.setBackground(new Color(37, 32, 70, 255));
        escoger.setBorder(BorderFactory.createEtchedBorder());
        escoger.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        escoger.setForeground(Color.WHITE);
        escoger.addActionListener(this);
        escoger.setPreferredSize(new Dimension(100, 50));

        volver = new JButton("Volver");
        volver.setFocusable(false);
        volver.setBackground(new Color(37, 32, 70, 255));
        volver.setBorder(BorderFactory.createEtchedBorder());
        volver.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        volver.setForeground(Color.WHITE);
        volver.addActionListener(this);
        volver.setPreferredSize(new Dimension(100, 50));

        // creacion JText

        nombre = new JTextField();
        nombre.setText("Nombre del equipo");
        nombre.setFont(new Font("Consolas", Font.PLAIN, 18));
        nombre.setForeground(new Color(143, 138, 167, 255));

        frame = new Ventana();

        titulo.add(tituloTxt);

        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(Box.createRigidArea(new Dimension(5, 0)));

        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(nombre);
        menu.add(Box.createRigidArea(new Dimension(5, 0)));

        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(crear);
        menu.add(Box.createRigidArea(new Dimension(5, 0)));

        menu.add(Box.createRigidArea(new Dimension(5, 0)));
        menu.add(escoger);
        menu.add(Box.createRigidArea(new Dimension(5, 0)));

        vacioS.add(volver);

        frame.add(titulo, BorderLayout.NORTH);
        frame.add(vacioW, BorderLayout.WEST);
        frame.add(vacioE, BorderLayout.EAST);
        frame.add(vacioS, BorderLayout.SOUTH);
        frame.add(menu, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == crear) {

            if (Aplicacion.temporadaActual == null) {
                JOptionPane.showMessageDialog(null, "No existe una tempoarada para jugar", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }

            else {
                Boolean igual = false;
                try {
                    for (Object s : Aplicacion.user.getNombresEquiposFantasy()) {
                        if (nombre.getText().equals((String) s)) {
                            igual = true;
                        }
                    }
                    if (igual) {
                        JOptionPane.showMessageDialog(null, "Ya tienes un equipo con ese nombre", "Error",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        EquipoFantasia equipoFantasia = Aplicacion.user.crearEquipoFantasia(nombre.getText(),
                                Aplicacion.temporadaActual);
                        Aplicacion.temporadaActual.addEquipoFantasy(equipoFantasia);
                        frame.dispose();
                        SerializarObjeto.serializarObjeto(System.getProperty("user.dir") + "/data/TemporadaActual.txt",
                                Aplicacion.getTemporadaActual());
                        new GUIMercado(true);
                    }
                } catch (Exception e1) {
                    EquipoFantasia equipoFantasia = Aplicacion.user.crearEquipoFantasia(nombre.getText(),
                            Aplicacion.temporadaActual);
                            Aplicacion.temporadaActual.addEquipoFantasy(equipoFantasia);
                    frame.dispose();
                    SerializarObjeto.serializarObjeto(System.getProperty("user.dir") + "/data/TemporadaActual.txt",
                            Aplicacion.getTemporadaActual());
                    new GUIMercado(true);
                }
            }

        } else if (e.getSource() == escoger) {
            Object[] nombresEquipos = Aplicacion.user.getNombresEquiposFantasy();
            String nombreEquipo = (String) (JOptionPane.showInputDialog(null,
                    "Seleccione uno de sus equipos ya creados: ",
                    "Equipos", JOptionPane.PLAIN_MESSAGE, null, nombresEquipos, "Selecciona"));
            try {
                Aplicacion.user.setEquipo(Aplicacion.user.getEquipoPorNombre(nombreEquipo));
                frame.dispose();
                SerializarObjeto.serializarObjeto(System.getProperty("user.dir") + "/data/TemporadaActual.txt",
                        Aplicacion.getTemporadaActual());
                new GUIParticipante(Aplicacion.user.getNombre());

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Escoga un equipo Valido", "Error escogiendo",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        else if (e.getSource() == volver) {
            frame.dispose();
            SerializarObjeto.serializarObjeto(System.getProperty("user.dir") + "/data/TemporadaActual.txt",
                    Aplicacion.getTemporadaActual());
            new GUIParticipante(Aplicacion.user.getNombre());
        }

    }

}
