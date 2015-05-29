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
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import pruebas.pProducto;
import utils.Imagen;

/**
 *
 * @author David López González
 */
public class MostrarBotones
{

    public static void pintarBotones(JButton[] botones, JPanel panel2)
    {

        ProductoDAO proDAO = new ProductoDAO();
        ArrayList<Producto> misProductos = proDAO.consultarAll();
        System.out.println(misProductos.size());
        try
        {
            for (int j = 0; j < misProductos.size()&&j<botones.length; j++)
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
