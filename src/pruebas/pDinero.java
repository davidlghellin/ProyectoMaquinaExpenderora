package pruebas;

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
        // ActualizarDinero.pagar(5.1f);
        ActualizarDinero.retirar02E(10);
        System.out.println(ActualizarDinero.caja());
    }
}
