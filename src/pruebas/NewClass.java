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
package pruebas;

import DAO.MovimientosDAO;
import dominio.Movimientos;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author David López González
 */
public class NewClass
{

    public static void main(String[] args)
    {
        //System.out.println(new Date());
//        ProductoDAO productoDAO=new ProductoDAO();
//        Producto p=productoDAO.consultar(1);
//        ActualizarProductos.meterProducto(p, 2);
        MovimientosDAO movDAO = new MovimientosDAO();
        Movimientos m = movDAO.consultar(12);
        //SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMM d HH:mm:ss z yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMMM yyyy");
        System.out.println(dateFormat.format(m.getFecha()));

    }

}
