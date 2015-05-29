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
import DAO.ProductoDAO;
import dominio.Movimientos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import publico.ActualizarProductos;

/**
 *
 * @author David López González
 */
public class pMovimientos
{

    public static void main(String[] args)
    {
        MovimientosDAO dao = new MovimientosDAO();
        ProductoDAO pdao = new ProductoDAO();
        //dao.alta(new Movimientos(pdao.consultar(2), 55));
        Movimientos m = dao.consultar(1);
        System.out.println(m.getProducto());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaConFormato = sdf.format(m.getFecha());
        System.out.println(fechaConFormato);

        ArrayList<Movimientos> mis = new ArrayList<Movimientos>();
        mis = dao.consultarAll(m.getProducto());
        for (Movimientos mm : mis)
        {
            System.out.println(mm.getProducto().getExistencias());
        }
        System.out.println("____");
        System.out.println(pdao.consultar(2).getExistencias());
        ActualizarProductos.sacarProducto(pdao.consultar(2));
        System.out.println(pdao.consultar(2).getExistencias());
        System.out.println("____");
        System.out.println("____");
        ActualizarProductos.meterProducto(pdao.consultar(2), 10);
        System.out.println(pdao.consultar(2).getExistencias());
    }

}
