package inerfaceDAO;

import dominio.Dinero;
import java.util.ArrayList;

/**
 *
 * @author David López González
 */
public interface interfaceDineroDAO
{
    public void alta(Dinero d);
    public void baja(int codigo);
    public void modificacion(Dinero d);
    public Dinero consultar(String texto);
    public Dinero consultar(int num);
    public ArrayList<Dinero> consultarAll();
}
