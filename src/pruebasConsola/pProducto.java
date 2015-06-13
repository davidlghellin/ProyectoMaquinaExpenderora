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
package pruebasConsola;

import DAO.ProductoDAO;
import dominio.Producto;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utils.Imagen;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

/**
 *
 * @author David López González
 */
public class pProducto
{

    public static void main(String[] args) throws IOException
    {
        final ProductoDAO proDAO = new ProductoDAO();

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(600, 300, 500, 500);

        JMenuBar barraMenu = new JMenuBar();
        JMenu f = new JMenu("File");
        JMenu s = new JMenu("Save");
        JMenuItem menuRoot = new JMenuItem("Root");
        JMenuItem menuInforme = new JMenuItem("Informe");
        JMenuItem menuSalir = new JMenuItem("Salir");
        // poner la barra de menu arriba
        frame.setJMenuBar(barraMenu);
        barraMenu.add(f);
        f.add(menuRoot);
        f.add(menuInforme);
        f.add(menuSalir);

        frame.setLayout(new GridLayout(1, 2));

        JPanel panel = new JPanel();
        frame.add(panel);
        JPanel panel2 = new JPanel(new GridLayout(16, 1));
        frame.add(panel2);
        final JTextArea info = new JTextArea(5, 20);
        info.setEditable(false);
        info.setBackground(Color.GREEN);
        info.setText("Esc el producto");
        panel.add(BorderLayout.NORTH, info);
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
        JButton[] botones = new JButton[16];
        for (int i = 0; i < botones.length; i++)
        {
            botones[i] = new JButton();
            botones[i].addActionListener(mostrarPrecio);

        }

        pintarBotones(botones, panel2);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(Color.GRAY);

    }

    public static void pintarBotones(JButton[] botones, JPanel panel2)
    {

        ProductoDAO proDAO = new ProductoDAO();
        ArrayList<Producto> misProductos = proDAO.consultarAll();
        System.out.println(misProductos.size());
        try
        {
            for (int j = 0; j < misProductos.size(); j++)
            {
                botones[j].setName(misProductos.get(j).getCodigo() + "");
                System.out.println(botones[j].getName() + "");
                Producto pb = proDAO.consultar(misProductos.get(j).getCodigo());
                System.out.println(pb.getNombre());
                try
                {
                    Image img = Imagen.getImage(pb.getImagen());
                    if (img != null)
                    {
                        Icon icon = new ImageIcon(img);
                        botones[j].setIcon(icon);
                    }
                } catch (IOException ex)
                {
                    Logger.getLogger(pProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
                panel2.add(botones[j]);
            }
        } catch (Exception e)
        {
            Logger.getLogger(pProducto.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
