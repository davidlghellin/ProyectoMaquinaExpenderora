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
public class cf
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

        file = new File("./Fotos/" + p.getCodigo() + ".jpg");
        if (file != null)
        {
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
