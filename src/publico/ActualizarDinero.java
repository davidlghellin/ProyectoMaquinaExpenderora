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

import DAO.DineroDAO;
import dominio.Dinero;
import java.util.ArrayList;

/**
 * @see
 * <a href="https://github.com/davidlghellin/ProyectoMaquinaExpenderora">https://github.com/davidlghellin/ProyectoMaquinaExpenderora</a>
 * @version 0.9 13 de Junio de 2015
 * @author David López González
 */
public class ActualizarDinero
{

    private static DineroDAO dineroDao = new DineroDAO();

    /**
     * Método que calcula y devuelve el total de € en la máquina
     *
     * @return total dinero
     */
    public static float caja()
    {
        float total = 0f;

        ArrayList<Dinero> cartera = new ArrayList<Dinero>();
        cartera = dineroDao.consultarAll();
        for (Dinero c : cartera)
        {
            total += c.getExistencias() * c.getValor();
        }

        return total;
    }

    /**
     * Método que actualiza la tabla TDinero en la base de datos
     *
     * @param n cantidad de monedas a retirar
     * @param midinero tupla que actualizaremos en la base de datos
     */
    private static void retirar(int n, Dinero midinero)
    {
        if (n <= midinero.getExistencias())
        {
            midinero.setExistencias(midinero.getExistencias() - n);
            dineroDao.modificacion(midinero);
        }
    }

    public static void retirar5E(int n)
    {
        Dinero midinero = dineroDao.consultar("Billete 5");
        retirar(n, midinero);
    }

    public static void retirar2E(int n)
    {
        Dinero midinero = dineroDao.consultar("Moneda 2");
        retirar(n, midinero);
    }

    public static void retirar1E(int n)
    {
        Dinero midinero = dineroDao.consultar("Moneda 1");
        retirar(n, midinero);
    }

    public static void retirar05E(int n)
    {
        Dinero midinero = dineroDao.consultar("Moneda 0.5");
        retirar(n, midinero);
    }

    public static void retirar02E(int n)
    {
        Dinero midinero = dineroDao.consultar("Moneda 0.2");
        retirar(n, midinero);
    }

    public static void retirar01E(int n)
    {
        Dinero midinero = dineroDao.consultar("Moneda 0.1");
        retirar(n, midinero);
    }

    /**
     * Depositamos dinero en la máquina, sirve tanto para dejar dinero como para
     * pagar
     *
     * @param text nombre de la moneda
     * @param num cantidad
     */
    private static void auxpagar(String text, int num)
    {
        Dinero midinero = dineroDao.consultar(text);
        midinero.setExistencias(midinero.getExistencias() + num);
        dineroDao.modificacion(midinero);
    }

    /**
     * Depositamos dinero en la máquina, sirve tanto para dejar dinero como para
     * pagar
     *
     * @param n identificador de la moneneda en la BBDD
     * @param num cantidad
     */
    private static void auxpagar(int n, int num)
    {
        Dinero midinero = dineroDao.consultar(n);
        midinero.setExistencias(midinero.getExistencias() + num);
        dineroDao.modificacion(midinero);
    }

    /**
     * Método que comprueba el dinero del producto y lo que se introduce en la
     * caja para devolver el menor número de monedas, para ello debemos tener en
     * cuenta la cantidad de monedas que hay en la máquina expendedora
     *
     * @param precio precio del producto
     * @param dinero dinero insertado en la máquina
     */
    public static void pagarCaja(float precio, float dinero)
    {
        // Trabajaremos con enteros, ya que con float se pierde precisión
        // Precio del producto
        int intPrecio = (int) (precio * 100);
        // Dinero introducido en la máquina
        int intDinero = (int) (dinero * 100);
        pagarCaja(intPrecio, intDinero);
    }

    /**
     * Método que comprueba el dinero del producto y lo que se introduce en la
     * caja para devolver el menor número de monedas, para ello debemos tener en
     * cuenta la cantidad de monedas que hay en la máquina expendedora
     *
     * @param precio precio del producto
     * @param dinero dinero insertado en la máquina
     */
    public static int[] pagarCaja(int precio, int dinero)
    {
        ArrayList<Dinero> cartera = new ArrayList<Dinero>();
        cartera = dineroDao.consultarAll();
        // Monedas que se devolveran
        int monedas[] = new int[6];
        // Monedas que hay en caja
        int monedasCaja[] = new int[6];
        int i = 0;
        for (Dinero n : cartera)
        {
            monedasCaja[i++] = n.getExistencias();
        }

        // Trabajaremos con enteros, ya que con float se pierde precisión
        // Precio del producto
        int intPrecio = precio;
        // Dinero introducido en la máquina
        int intDinero = dinero;
        int aux = intDinero - intPrecio;
        if (aux > 0)    // Hay que devolver 
        {
            // Saber los billetes de 5€ que tenemos y podemos devolver
            while (aux - 500 >= 0 && monedasCaja[0] > 0)
            {
                aux -= 500;         // Restamos 5€
                monedasCaja[0]--;   // Quitamos una moneda de la caja
                monedas[0]++;       // Incrementamos las que hay que devolver
            }
            // Saber las monedas de 2€ que tenemos y podemos devolver
            while (aux - 200 >= 0 && monedasCaja[1] > 0)
            {
                aux -= 200;         // Restamos 2€
                monedasCaja[1]--;   // Quitamos una moneda de la caja
                monedas[1]++;       // Incrementamos las que hay que devolver
            }
            // Saber las monedas de 1€ que tenemos y podemos devolver
            while (aux - 100 >= 0 && monedasCaja[2] > 0)
            {
                aux -= 100;         // Restamos 1€
                monedasCaja[2]--;   // Quitamos una moneda de la caja
                monedas[2]++;       // Incrementamos las que hay que devolver
            }
            // Saber las monedas de 0.5€ que tenemos y podemos devolver
            while (aux - 50 >= 0 && monedasCaja[3] > 0)
            {
                aux -= 50;           // Restamos 0.5€
                monedasCaja[3]--;   // Quitamos una moneda de la caja
                monedas[3]++;       // Incrementamos las que hay que devolver
            }
            // Saber las monedas de 0.2€ que tenemos y podemos devolver
            while (aux - 20 >= 0 && monedasCaja[4] > 0)
            {
                aux -= 20;          // Restamos 0.2€
                monedasCaja[4]--;   // Quitamos una moneda de la caja
                monedas[4]++;       // Incrementamos las que hay que devolver
            }
            // Saber las monedas de 0.1€ que tenemos y podemos devolver
            while (aux - 10 >= 0 && monedasCaja[5] > 0)
            {
                aux -= 10;          // Restamos 0.1€
                monedasCaja[5]--;   // Quitamos una moneda de la caja
                monedas[5]++;       // Incrementamos las que hay que devolver
            }
        }
        // Actualización del pago y de la BBDD
        i = 1;
        for (int n : monedas)
        {
            auxpagar(i++, -n);
        }
        return monedas;
    }

    /**
     * Método que sirve para saber el número de monedas que tendríamos que
     * devolver No usaremos este ya que necesitamos saber tanto el número de
     * monedas que hay en la caja, como el valor del precio del producto
     *
     * @deprecated No tiene en cuenta las monedas en caja, y usa float por lo
     * que pierde en en rededondeo
     * @param num
     */
    public static void pagar(float num)
    {
        // XXX pasar el dinero a int para no tener problemas con redondeo
        int dosE = 0, unE = 0, cincuetaC = 0, veintC = 0, diezC = 0, cincoC = 0;
        //float aux = num;
        int aux = (int) (num * 100);
        System.out.println("total " + aux);
        dosE = ((int) aux / 200);
        System.out.println(dosE);
        if (aux >= 200)
        {
            System.out.println(dosE + " 2€");
            aux = ((int) num * 100) % 200;
            auxpagar("Moneda 2", dosE);
        }

        if (aux >= 100)
        {
            unE = 1;
            aux--;
            auxpagar("Moneda 1", unE);
        }

        if (aux >= 50)
        {
            cincuetaC = 1;
            aux -= 50;
            auxpagar("Moneda 0.5", cincuetaC);
        }

        if (aux >= 20)
        {
            veintC = (int) (aux / 20);
            aux = aux % 20;
            auxpagar("Moneda 0.2", veintC);

        }
        if (aux >= 10)
        {
            diezC = (int) (aux / 10);
            auxpagar("Moneda 0.1", diezC);
        }
    }
}
