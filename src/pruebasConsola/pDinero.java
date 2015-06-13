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

import DAO.DineroDAO;
import dominio.Dinero;
import publico.ActualizarDinero;

/**
 *
 * @author David López González
 */
public class pDinero
{

    public static void main(String[] args)
    {
        //DineroDAO dineroDao=new DineroDAO();
        //dineroDao.alta(new Dinero( "dolar",3, 0.3f));
        //dineroDao.baja(8);
        //Dinero di=dineroDao.consultar(2);
        //System.out.println(di.getNombre());
        // ActualizarDinero.pagar(2);
        //ActualizarDinero.retirar2E(10);
        //System.out.println(ActualizarDinero.caja());
        ActualizarDinero.pagarCaja(1.2f,1.4f);
    }
}
