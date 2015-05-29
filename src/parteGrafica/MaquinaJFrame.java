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

import DAO.DineroDAO;
import DAO.ProductoDAO;
import dominio.Dinero;
import dominio.Producto;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static parteGrafica.MaquinaRoot.dineroDao;

/**
 *
 * @author David López González
 */
public class MaquinaJFrame extends JFrame
{

    private static final int MAX_NUMERO_PRODUCTOS = 16;
    private static final ProductoDAO proDAO = new ProductoDAO();
    private static final DineroDAO dineroDao = new DineroDAO();

    // Componentes gráficos referentes al menu
    private static final JMenuBar barraMenu = new JMenuBar();
    private static final JMenu f = new JMenu("File");
    private static final JMenu s = new JMenu("Save");
    private static final JMenuItem menuRoot = new JMenuItem("Root");
    private static final JMenuItem menuInforme = new JMenuItem("Informe");
    private static final JMenuItem menuSalir = new JMenuItem("Salir");

    // Paneles que dividirán el frame
    private static final JPanel panel = new JPanel();
    private static final JPanel panel2 = new JPanel(new GridLayout(MAX_NUMERO_PRODUCTOS, 1));
    // Area de texto para mostrar las salidas 
    private static final JTextArea info = new JTextArea(5, 20);
    // 16 Botones como máximo, solamente mostraremos los que contengan elemento
    private static final JButton[] botones = new JButton[MAX_NUMERO_PRODUCTOS];

    /*
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
        ponerActionMenu();

        //establemos el layout para añadir dos paneles 
        setLayout(new GridLayout(1, 2));
        add(panel);
        add(panel2);

        // Propiedades del JTextArea
        info.setEditable(false);
        info.setBackground(Color.GREEN);
        info.setText("Escoja su producto");
        //panel.add(BorderLayout.NORTH, info);
        panel.add(info);
        panel.setLayout(new GridLayout(8, 1));
        JButton[] botonesMonedas = new JButton[7];
        ponerBotonesMondeas(panel, botonesMonedas);

        // Creamos los botones y le añadimos los ActionListener
        for (int i = 0; i < botones.length; i++)
        {
            botones[i] = new JButton();
            botones[i].addActionListener(mostrarPrecio);
        }
        /*
         * Pintamos los botones, mostraremos los botones que contengan solamente imagen
         */
        MostrarBotones.pintarBotones(botones, panel2);
    }

    private static void ponerBotonesMondeas(JPanel panel, JButton[] botones)
    {
        int i = 0;
        ArrayList<Dinero> dineros = dineroDao.consultarAll();
        try
        {
            for (JButton b : botones)
            {
                b = new JButton();
                b.setText(dineros.get(i).getNombre() + "");
                b.setName((i++) + "");
                panel.add(b);
            }
        } catch (Exception e)
        {

        }
        botones[botones.length - 1] = new JButton();
        botones[botones.length - 1].setText("Cancelar");
        panel.add(botones[botones.length - 1]);
    }

    public static void ponerActionMenu()
    {
        menuRoot.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String contraseña = JOptionPane.showInputDialog("Intoduzca la contraseña");
                System.out.println(contraseña);
//                if (contraseña.equals("root"))
//                {
//                    new MaquinaRoot();
//                }
                new MaquinaRoot();
            }
        });
        menuSalir.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent ae)
            {
                System.out.println("sal");
                System.exit(0);
            }
        });
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
