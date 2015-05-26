package publico;

import DAO.DineroDAO;
import dominio.Dinero;
import java.util.ArrayList;

/**
 *
 * @author David López González
 */
public class ActualizarDinero
{

    private static DineroDAO dineroDao = new DineroDAO();
private int a;
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
//        if (n <= midinero.getExistencias())
//        {
//            midinero.setExistencias(midinero.getExistencias() - n);
//            dineroDao.modificacion(midinero);
//        }
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
     * Con este método depositamos dinero en la máquina, sirve tanto para dejar
     * dinero como para pagar
     *
     * @param num
     */
    public static void pagar(float num)
    {
        int dosE, unE = 0, cincuetaC = 0, veintC = 0, diezC = 0, cincoC = 0;
        float aux = num;
        System.out.println("total" + num);
        dosE = (int) (num / 2);
        if (dosE > 0)
        {
            System.out.println(dosE + " 2€");
            aux = num % 2;
            Dinero midinero = dineroDao.consultar("Moneda 2");
            midinero.setExistencias(midinero.getExistencias() + dosE);
            dineroDao.modificacion(midinero);
        }

        if (aux >= 1)
        {
            unE = 1;
            aux--;
            Dinero midinero = dineroDao.consultar("Moneda 1");
            midinero.setExistencias(midinero.getExistencias() + unE);
            dineroDao.modificacion(midinero);
        }
        System.out.println(unE + " 1€");
        if (aux >= 0.5f)
        {
            cincuetaC = 1;
            aux -= 0.5f;
            Dinero midinero = dineroDao.consultar("Moneda 0.5");
            midinero.setExistencias(midinero.getExistencias() + cincuetaC);
            dineroDao.modificacion(midinero);
        }
        System.out.println(cincuetaC + " modenads de 50 centimos €");
        if (aux >= 0.2)
        {
            veintC = (int) (aux / 0.2f);
            System.out.println(veintC + " modenads de 20 centimos €");
            aux = aux % 0.2f;
            Dinero midinero = dineroDao.consultar("Moneda 0.2");
            midinero.setExistencias(midinero.getExistencias() + veintC);
            dineroDao.modificacion(midinero);

        }
        if (aux >= 0.1f)
        {
            diezC = (int) (aux / 0.1f);
            System.out.println(veintC + " modenads de 10 centimos €");
            Dinero midinero = dineroDao.consultar("Moneda 0.1");
            midinero.setExistencias(midinero.getExistencias() + diezC);
            dineroDao.modificacion(midinero);
        }
    }

}
