/*
 * Copyright (C) 2015 David López González
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package parteGrafica;

import DAO.ProductoDAO;
import dominio.Producto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author David López González
 */
public class MaquinaJFrame extends JFrame
{

    ProductoDAO proDAO = new ProductoDAO();

    // Componentes gráficos referentes al menu
    JMenuBar barraMenu = new JMenuBar();
    JMenu f = new JMenu("File");
    JMenu s = new JMenu("Save");
    JMenuItem menuRoot = new JMenuItem("Root");
    JMenuItem menuInforme = new JMenuItem("Informe");
    JMenuItem menuSalir = new JMenuItem("Salir");

    // Paneles que dividirán el frame
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel(new GridLayout(16, 1));
    // Area de texto para mostrar las salidas 
    JTextArea info = new JTextArea(5, 20);
    // 16 Botones como máximo, solamente mostraremos los que contengan elemento
    JButton[] botones = new JButton[16];

    /**
     * Evento que nos servira para añadir a los botones para obtener
     * el nombre del produco, y así hacer lo correspondiente
     */
    ActionListener mostrarPrecio = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            Producto p = (proDAO.consultar(Integer.parseInt(((JButton) ae.getSource()).getName())));
            info.setText("" + p.getNombre());
            info.append("\n" + p.getPrecio());
        }
    };

    public MaquinaJFrame()
    {
        //ponemos el JMenu y sus componentes asociados en el JFrame
        setJMenuBar(barraMenu);
        barraMenu.add(f);
        f.add(menuRoot);
        f.add(menuInforme);
        f.add(menuSalir);
        
        //establemos el layout para añadir dos paneles 
        setLayout(new GridLayout(1, 2));
        add(panel);
        add(panel2);
        
        // Propiedades del JTextArea
        info.setEditable(false);
        info.setBackground(Color.GREEN);
        info.setText("Escoja su producto");
        panel.add(BorderLayout.NORTH, info);

        // Creamos los botones y le añadimos los ActionListener
        for (int i = 0; i < botones.length; i++)
        {
            botones[i] = new JButton();
            botones[i].addActionListener(mostrarPrecio);
        }
        MostrarBotones.pintarBotones(botones, panel2);

    }

    

    public static void main(String[] args)
    {
        MaquinaJFrame maquina = new MaquinaJFrame();
        
        // Opciones para su visualización por defecto
        maquina.setBounds(600, 300, 500, 500);
        maquina.setVisible(true);
        maquina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
