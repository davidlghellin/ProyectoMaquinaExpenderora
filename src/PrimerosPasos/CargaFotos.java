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
package PrimerosPasos;

import DAO.ProductoDAO;
import dominio.Producto;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import utils.Imagen;

/**
 * Ejecutar este método una vez, al cargar el spript MySql. Se encargará de
 * poner las fotos a los productos
 *
 * @author David López González
 */
public class CargaFotos
{

    private static void cargarFoto(File file, Producto p)
    {
        byte[] buf = null;
        buf = Imagen.imagenArray(file);
        p.setImagen(buf);
    }

    private static void comprobarFoto(Producto p)
    {
        File file;
        ProductoDAO proDAO = new ProductoDAO();
        if (p.getNombre().equalsIgnoreCase("CocaCola"))
        {
            file = new File("./Fotos/cocacola.jpg");
            cargarFoto(file, p);
            proDAO.modificacion(p);
        }
        else if (p.getNombre().equalsIgnoreCase("Coca Zero"))
        {
            file = new File("./Fotos/coca_zero.jpg");
            cargarFoto(file, p);
            proDAO.modificacion(p);
        }
        else if (p.getNombre().equalsIgnoreCase("Fanta N."))
        {
            file = new File("./Fotos/fantaNaranja.jpg");
            cargarFoto(file, p);
            proDAO.modificacion(p);
        }
        else if (p.getNombre().equalsIgnoreCase("Fanta L."))
        {
            file = new File("./Fotos/fanta_limon.jpg");
            cargarFoto(file, p);
            proDAO.modificacion(p);
        }
        else if (p.getNombre().equalsIgnoreCase("Nestea"))
        {
            file = new File("./Fotos/nestea.jpg");
            cargarFoto(file, p);
            proDAO.modificacion(p);
        }
        else if (p.getNombre().equalsIgnoreCase("Sprite"))
        {
            file = new File("./Fotos/sprite.jpg");
            cargarFoto(file, p);
            proDAO.modificacion(p);
        }
        else if (p.getNombre().equalsIgnoreCase("RedBull"))
        {
            file = new File("./Fotos/red-bull.jpg");
            cargarFoto(file, p);
            proDAO.modificacion(p);
        }
        else if (p.getNombre().equalsIgnoreCase("Monster"))
        {
            file = new File("./Fotos/monster.jpg");
            cargarFoto(file, p);
            proDAO.modificacion(p);
        }
        else if (p.getNombre().equalsIgnoreCase("Agua"))
        {
            file = new File("./Fotos/solan.jpg");
            cargarFoto(file, p);
            proDAO.modificacion(p);
        }

    }

    public static void main(String[] args)
    {
        ProductoDAO proDAO = new ProductoDAO();
        ArrayList<Producto> muchosP = new ArrayList<Producto>();
        muchosP = proDAO.consultarAll();
        for (Producto p : muchosP)
        {
            System.out.println(p.getNombre());
            comprobarFoto(p);
        }
    }
}
