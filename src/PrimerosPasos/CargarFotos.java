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
import java.io.IOException;
import java.util.ArrayList;
import utils.Imagen;

/**
 * Ejecutar este método una vez, al cargar el spript MySql. Se encargará de
 * poner las fotos a los productos
 *
 * @see
 * <a href="https://github.com/davidlghellin/ProyectoMaquinaExpenderora">https://github.com/davidlghellin/ProyectoMaquinaExpenderora</a>
 * @version 0.9 13 de Junio de 2015
 * @author David López González
 */
public class CargarFotos
{

    /**
     * Método que carga una foto y la asocia a un producto
     *
     * @param file fichero que contiene la foto
     * @param p Producto al que le pondremos la foto
     */
    private static void cargarFoto(File file, Producto p)
    {
        byte[] buf = null;
        buf = Imagen.imagenArray(file);
        p.setImagen(buf);
    }

    /**
     * Método que comprueba las fotos contenidas en la carpeta "Fotos" que
     * tendrán un número y las carga en la BBDD a los productos que tengán ese
     * número
     *
     * @param p
     */
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

    /**
     * Método ejecutable, que comprueba el listado de productos que tenemos en
     * la BBDD y le pone la foto correspondiente
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        ProductoDAO proDAO = new ProductoDAO();
        ArrayList<Producto> muchosP = new ArrayList<Producto>();
        muchosP = proDAO.consultarAll();
        for (Producto p : muchosP)
        {
            comprobarFoto(p);
        }
    }
}
