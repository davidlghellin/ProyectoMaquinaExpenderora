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
package publico;

import DAO.MovimientosDAO;
import DAO.ProductoDAO;
import dominio.Movimientos;
import dominio.Producto;
import java.io.IOException;

/**
 *
 * @author David López González
 */
public class ActualizarProductos
{

    /**
     * Retiramos una unidad del producto p de la base de datos
     * @param p Producto a retirar una unidad de la base de datos
     */
    public static void sacarProducto(Producto p)
    {
        MovimientosDAO moviDAO = new MovimientosDAO();
        ProductoDAO proDAO = new ProductoDAO();
        moviDAO.alta(new Movimientos(p, 1));
        int n = p.getExistencias();
        p.setExistencias(--n);
        proDAO.modificacion(p);
    }

    /**
     * Método que introduce una cantidad de productos en la base de datos
     * @param p
     * @param cantidad
     * @throws IOException 
     */
    public static void meterProducto(Producto p, int cantidad) throws IOException
    {
        MovimientosDAO moviDAO = new MovimientosDAO();
        ProductoDAO proDAO = new ProductoDAO();
        Movimientos m = new Movimientos(p, cantidad);
        log.CrearLog.log(m);
        moviDAO.alta(m);
        int n = p.getExistencias() + cantidad;
        p.setExistencias(n);
        proDAO.modificacion(p);
    }
}
