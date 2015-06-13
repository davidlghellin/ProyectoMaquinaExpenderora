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
package parteGrafica;

import DAO.DineroDAO;
import DAO.ProductoDAO;
import dominio.Dinero;
import dominio.Producto;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import publico.ActualizarDinero;
import publico.ActualizarProductos;

/**
 *
 * @author David López González
 */
public class MaquinaJFrame extends JFrame
{

    private static final int MAX_NUMERO_PRODUCTOS = 16;
    private static final ProductoDAO proDAO = new ProductoDAO();
    private static final DineroDAO dineroDao = new DineroDAO();

    // Componentes gráficos referentes al menu
    private static final JMenuBar barraMenu = new JMenuBar();
    private static final JMenu f = new JMenu("File");
    private static final JMenu s = new JMenu("Save");
    private static final JMenuItem menuRoot = new JMenuItem("Root");
    private static final JMenuItem menuInforme = new JMenuItem("Informe");
    private static final JMenuItem menuSalir = new JMenuItem("Salir");

    // Paneles que dividirán el frame
    private static final JPanel panel = new JPanel();
    private static final JPanel panel2 = new JPanel(new GridLayout(MAX_NUMERO_PRODUCTOS, 1));
    // Area de texto para mostrar las salidas 
    private static final JPanel panelinfo = new JPanel(new GridLayout(1, 2));
    private static final JTextArea info = new JTextArea();
    private static final JTextArea info2 = new JTextArea();
    // 16 Botones como máximo, solamente mostraremos los que contengan elemento
    private static final JButton[] botones = new JButton[MAX_NUMERO_PRODUCTOS];
    // Botones de monedas y cancelar
    private static JButton[] botonesMonedas;

    // Precio del producto
    private static int precioProducto = 0;
    private static int dineroPagado = 0;
    // Boolean que indica si a escogido el producto y si además quedan en la máquina
    private static boolean boolProducto = false;
    // Numero de monedas
    private static int[] cantidadMonedas = new int[6];
    // Codigo del producto selecionado
    private static int codigoPoducto;

    // Informes
    JasperReport jasperReport;
    JasperPrint jasperPrint;

    /*
     * Evento que nos servira para añadir a los botones para obtener
     * el nombre del produco, y así hacer lo correspondiente
     */
    ActionListener mostrarPrecio = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            Producto p = (proDAO.consultar(Integer.parseInt(((JButton) ae.getSource()).getName())));
            if (p.getExistencias() > 0)
            {
                info.setText("" + p.getNombre());
                codigoPoducto = p.getCodigo();
                info.append("\n" + p.getPrecio());
                precioProducto = (int) (p.getPrecio() * 100);
                info.append("\n" + precioProducto);
                boolProducto = true;
            }
            else
            {
                info.setText("Producto agotado");
            }
        }
    };
    ActionListener mostrarDinero = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (boolProducto)
            {   // Si tenemos producto seleccionado y además quedan existencias. 
                System.out.println(Integer.parseInt(((JButton) ae.getSource()).getName()) + 1);
                Dinero d = (dineroDao.consultar(Integer.parseInt(((JButton) ae.getSource()).getName()) + 1));
                dineroPagado += (int) (d.getValor() * 100);
                cantidadMonedas[Integer.parseInt(((JButton) ae.getSource()).getName())]++;

                info2.setText("Total pagado:\n" + (float) (dineroPagado / 100f) + " €");
                if (dineroPagado < precioProducto) // Todavía falta dinero
                {
                    info2.append("\nFalta: " + (float) ((precioProducto - dineroPagado) / 100f) + "€");
                }
                else    // Se efectua la venta decrementando el producto 
                {
                    for (int i = 0; i < botonesMonedas.length - 1; i++)
                    {
                        botonesMonedas[i].setEnabled(false);
                    }
                    // XXXX Actualizar dineros
                    int monedas[] = ActualizarDinero.pagarCaja(precioProducto, dineroPagado);
                    botonesMonedas[botonesMonedas.length - 1].setText("Aceptar");
                    info2.setText("Recoja su cambio: " + (float) ((precioProducto - dineroPagado) / 100f) + "€\n");
                    if (monedas[1] > 0)
                    {
                        info2.append(monedas[1] + "monedas de 2€, ");
                    }
                    if (monedas[2] > 0)
                    {
                        info2.append(monedas[2] + "monedas de 1€, ");
                    }
                    if (monedas[3] > 0)
                    {
                        info2.append(monedas[3] + "monedas de 0.5€, ");
                    }
                    if (monedas[4] > 0)
                    {
                        info2.append(monedas[4] + "monedas de 0.2€, ");
                    }
                    if (monedas[5] > 0)
                    {
                        info2.append(monedas[5] + "monedas de 0.1€, ");
                    }
                }
            }
        }
    };

    public MaquinaJFrame()
    {
        //ponemos el JMenu y sus componentes asociados en el JFrame
        setJMenuBar(barraMenu);
        barraMenu.add(f);
        f.add(menuRoot);
        f.add(menuInforme);
        f.add(menuSalir);
        ponerActionMenu();
        informes();

        //establemos el layout para añadir dos paneles 
        setLayout(new GridLayout(1, 2));
        add(panel);
        add(panel2);

        // Propiedades del JTextArea
        info.setEditable(false);
        info.setBackground(Color.GREEN);
        info.setText("Escoja su producto");
        info2.setText("Inserte la moneda");
        panelinfo.add(info);
        panelinfo.add(info2);
        panel.add(panelinfo);
        panel.setLayout(new GridLayout(8, 1));

        // Botones para las monedas
        botonesMonedas = new JButton[7];
        // Creamos los botones
        for (int i = 0; i < botonesMonedas.length; i++)
        {
            botonesMonedas[i] = new JButton();
        }
        // Pintamos los botones
        MostrarBotones.ponerBotonesMondeas(panel, botonesMonedas);
        // Añadimos a los botones de las monedas la funcionalidad de pulsar
        for (int i = 0; i < botonesMonedas.length - 1; i++)
        {
            botonesMonedas[i].addActionListener(mostrarDinero);
        }
        // Boton de cancelar de las monedas, ponemos el dinero que se a  introducido a 0
        botonesMonedas[botonesMonedas.length - 1].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                dineroPagado = 0;
                for (int m : cantidadMonedas)
                {
                    m = 0;
                }
                // Seteamos a la configuración inicial
                info2.setText("Inserte la moneda");
                if (((JButton) ae.getSource()).getText().equals("Aceptar"))
                {   // Se ha producido la venta debemos devolver e insertar en la maquina
                    for (int i = 0; i < botonesMonedas.length - 1; i++)
                    {
                        botonesMonedas[i].setEnabled(true);
                    }
                    botonesMonedas[botonesMonedas.length - 1].setText("Cancelar");
                    // Valores por defecto
                    info.setText("Escoja su producto");
                    boolProducto = false;
                    // Actualirzamos producto
                    ProductoDAO pDAO = new ProductoDAO();
                    Producto pro = pDAO.consultar(codigoPoducto);
                    ActualizarProductos.sacarProducto(pro);

                }
            }
        });

        // Creamos los botones de las bebidas y le añadimos los ActionListener
        for (int i = 0; i < botones.length; i++)
        {
            botones[i] = new JButton();
            botones[i].addActionListener(mostrarPrecio);

        }
        /*
         * Pintamos los botones de las bebidas, mostraremos los botones que contengan solamente imagen
         */
        MostrarBotones.pintarBotonesProductos(botones, panel2);
    }

    public static void informes()
    {
        menuInforme.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String contraseña = JOptionPane.showInputDialog("Intoduzca la contraseña");
                if (contraseña.equals("root"))
                {
                    try
                    {
                        informes.informeMovimiento.main(null);
                        informes.informeProducto.main(null);
                    } catch (SQLException ex)
                    {
                        Logger.getLogger(MaquinaJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public static void ponerActionMenu()
    {
        menuRoot.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String contraseña = JOptionPane.showInputDialog("Intoduzca la contraseña");
                System.out.println(contraseña);
                if (contraseña.equals("root"))
                {
                    new MaquinaRoot();
                }
            }
        });
        // TODO Generar informe
        menuInforme.addActionListener(null);

        menuSalir.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args)
    {
        MaquinaJFrame maquina = new MaquinaJFrame();

        // Opciones para su visualización por defecto
        maquina.setBounds(600, 300, 500, 500);
        maquina.setVisible(true);
        maquina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
