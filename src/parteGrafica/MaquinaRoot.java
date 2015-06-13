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
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static parteGrafica.MaquinaRoot.listxt;
import static parteGrafica.MaquinaRoot.retirarDinero;
import publico.ActualizarProductos;

/**
 *
 * @author David López González
 */
public class MaquinaRoot extends JFrame
{

    private static DineroDAO dineroDAO = new DineroDAO();
    private static ProductoDAO productoDAO = new ProductoDAO();

    private static JPanel pdinero = new JPanel();
    private static JPanel pproducto = new JPanel();
    private static JTextField tx5 = new JTextField();
    private static JTextField tx2 = new JTextField();
    private static JTextField tx1 = new JTextField();
    private static JTextField tx05 = new JTextField();
    private static JTextField tx02 = new JTextField();
    private static JTextField tx01 = new JTextField();
    static ArrayList<JTextField> listxt = new ArrayList();

    static JButton retirarDinero = new JButton("Retirar");
    private static JButton consuDinero = new JButton("Consultar");

    public MaquinaRoot()
    {
        setLayout(new GridLayout(1, 2));
        add(pdinero);
        pdinero.setBackground(Color.red);
        add(pproducto);
        pproducto.setBackground(Color.black);
        // Panel añadir productos 
        final JComboBox jcb = new JComboBox();
        consultarProductos(jcb);
        pproducto.add(jcb);
        final JTextField jtfNumProd = new JTextField(5);
        pproducto.add(jtfNumProd);
        JButton btnIntroProd = new JButton("Reponer");
        btnIntroProd.addActionListener(new ActionListener()
        {
            // Reponemos productos seleccionados
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                Producto p = (Producto) jcb.getSelectedItem();
                try
                {
                    ActualizarProductos.meterProducto(p, Integer.parseInt(jtfNumProd.getText()));
                } catch (IOException ex)
                {
                    Logger.getLogger(MaquinaRoot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        pproducto.add(btnIntroProd);

        // Boton para crear nuevo producto
        JButton bNuevoProducto = new JButton("Nuevo Producto");
        pproducto.add(bNuevoProducto);
        bNuevoProducto.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                new MaquinaNuevoProducto();
            }
        });

        // Panel para el dinero
        pdinero.setLayout(new GridLayout(7, 2));
        pdinero.add(tx5);
        pdinero.add(new JLabel("Billetes de 5 €"));
        pdinero.add(tx2);
        pdinero.add(new JLabel("Monedas de 2 €"));
        pdinero.add(tx1);
        pdinero.add(new JLabel("Monedas de 1 €"));
        pdinero.add(tx05);
        pdinero.add(new JLabel("Monedas de 05 €"));
        pdinero.add(tx02);
        pdinero.add(new JLabel("Monedas de 02 €"));
        pdinero.add(tx01);
        pdinero.add(new JLabel("Monedas de 01 €"));
        pdinero.add(retirarDinero);
        pdinero.add(consuDinero);
        anyadirFuncionalidadDinero();

        // opciones de visibilidad
        setBounds(600, 300, 500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        /*addWindowListener(new WindowAdapter()
         {
         public void windowClosing(WindowEvent evt)
         {
         dispose();
         }
         });*/
    }

    private static void anyadirFuncionalidadDinero()
    {
        // añadimos los JTextField al ArrayList para trabajar más comodo
        listxt.add(tx5);
        listxt.add(tx2);
        listxt.add(tx1);
        listxt.add(tx05);
        listxt.add(tx02);
        listxt.add(tx01);

        // Le ponemos el nombre para que hagan luego de indice en la actualizacion a la BBDD
        int i = 1;
        for (JTextField l : listxt)
        {
            l.setName((i++) + "");
        }

        ActionListener comprobarMonedas = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // Consultamos la BBDD para saber cuantas monedas hay, 
                // para añadir o retirar
                ArrayList<Dinero> dineros = dineroDAO.consultarAll();

                for (int j = 0; j < listxt.size(); j++)
                {
                    try
                    {
                        int num = Integer.parseInt(listxt.get(j).getText());
                        // Metemos dinero en la máquina
                        if (num > 0)
                        {
                            num += (dineros.get(j).getExistencias());
                            dineros.get(j).setExistencias(num);
                            dineroDAO.modificacion(dineros.get(j));
                        }
                        // Retiramos dinero
                        else if (num < 0)
                        {
                            // Retiramos alguna moneda o todas
                            if (Math.abs(num) <= dineros.get(j).getExistencias())
                            {
                                num += (dineros.get(j).getExistencias());
                                dineros.get(j).setExistencias(num);
                                dineroDAO.modificacion(dineros.get(j));
                            }
                        }

                    } catch (Exception e)
                    {
                        System.out.println(e.getStackTrace().getClass());
                    }

                }
            }
        };
        retirarDinero.addActionListener(comprobarMonedas);
        consuDinero.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                ArrayList<Dinero> dineros = dineroDAO.consultarAll();
                StringBuilder text = new StringBuilder();
                for (Dinero d : dineros)
                {
                    System.out.println(d.getNombre());
                    text.append(d.getNombre() + "€:   \t").append(d.getExistencias() + " uds\n");
                }
                JOptionPane.showMessageDialog(null, text);
            }
        });

    }

    private static void consultarProductos(JComboBox jcb)
    {

        ArrayList<Producto> misProductos = productoDAO.consultarAll();
        for (Producto p : misProductos)
        {
            jcb.addItem(p);
        }
    }
}
