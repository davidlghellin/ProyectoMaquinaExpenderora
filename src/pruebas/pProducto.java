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

import DAO.ProductoDAO;
import dominio.Producto;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author David López González
 */
public class pProducto
{

    public static void main(String[] args)
    {
        ProductoDAO proDAO = new ProductoDAO();
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(null);
        File file = jf.getSelectedFile();
        byte[] buf = null;
        try
        {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[(int) file.length()];
            try
            {
                for (int readNum; (readNum = fis.read(buf)) != -1;)
                {
                    bos.write(buf, 0, readNum);
                    System.out.println("read " + readNum + " bytes,");
                }
            } catch (IOException ex)
            {
                Logger.getLogger(pProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e)
        {
            System.out.println("eee");
        }
        Producto p = new Producto("cocas", "cocalcaolas", 1.2f, 3, buf);
        
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(300, 300, 300, 333);
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(Color.red);
    }
}
