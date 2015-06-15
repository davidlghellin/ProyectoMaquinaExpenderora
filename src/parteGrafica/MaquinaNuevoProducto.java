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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import utils.Imagen;

/**
 * @see
 * <a href="https://github.com/davidlghellin/ProyectoMaquinaExpenderora">https://github.com/davidlghellin/ProyectoMaquinaExpenderora</a>
 * @version 0.9 13 de Junio de 2015
 * @author David López González
 */
public class MaquinaNuevoProducto extends JFrame
{

    private static final ProductoDAO productoDAO = new ProductoDAO();
    private static final JLabel jblNombre = new JLabel("Nombre");
    private static final JTextField jtfNombre = new JTextField("xxx");
    private static final JLabel jblCantidad = new JLabel("Cantidad");
    private static final JTextField jtfCantidad = new JTextField("xxx");
    private static final JLabel jblPrecio = new JLabel("Precio");
    private static final JTextField jtfPrecio = new JTextField("xxx");
    private static final JLabel jblDescripcion = new JLabel("Descripcion");
    private static final JTextArea txtDescripcion = new JTextArea("xxx");
    private static final JButton bSelecionarImagen = new JButton("Selecione imagen");
    private static final JButton bAceptar = new JButton("Aceptar");
    private static final JButton bCancelar = new JButton("Cancelar");

    private static JFileChooser jf = new JFileChooser();
    private static File f;

    MaquinaNuevoProducto()
    {
        setLayout(new GridLayout(7, 2));
        add(jblNombre);
        add(jtfNombre);
        add(jblCantidad);
        add(jtfCantidad);
        add(jblPrecio);
        add(jtfPrecio);
        add(jblDescripcion);
        add(txtDescripcion);
        add(bSelecionarImagen);
        add(bAceptar);
        add(bCancelar);

        // funcionalidad de botones
        anyadirFuncionalidad();

        // opciones de visibilidad
        setBounds(600, 300, 500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private static void anyadirFuncionalidad()
    {
        // Seteamos a las opciones por defecto
        bCancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                jtfNombre.setText("xxx");
                jtfCantidad.setText("xxx");
                jtfPrecio.setText("xxx");
                txtDescripcion.setText("xxx");
                f = null;
            }
        });
        // Cargamos la imagen
        bSelecionarImagen.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                jf.showOpenDialog(null);
                f = jf.getSelectedFile();
            }
        });
        // Aceptamos los valores pasados
        bAceptar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String nombre = jtfNombre.getText();
                int cantidad = Integer.parseInt(jtfCantidad.getText());
                float precio = Float.parseFloat(jtfPrecio.getText());
                String descrip = txtDescripcion.getText();
                try
                {
                    if (f != null)
                    {
                        byte[] imagen = Imagen.imagenArray(f);
                        productoDAO.alta(new Producto(nombre, descrip, precio, cantidad, imagen));
                    }
                } catch (Exception e)
                {
                }
            }
        });
    }
}
