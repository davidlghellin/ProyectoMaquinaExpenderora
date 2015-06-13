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
package log;

import dominio.Movimientos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class CrearLog
{

    private static RandomAccessFile fichero;

    public static void iniLog() throws IOException
    {
        try
        {
            fichero = new RandomAccessFile(new File("./log/logReponedor.log"), "rw");
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(CrearLog.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuilder sb = new StringBuilder();
        //new Date-->Wed Jun 03 17:37:31 CEST 2015
        //sql-->2015-06-01 20:56:00 
        //DateTimeDateSimpleDateFormatFormat sdf=new SimpleDateFormat("yyyy-MM-dd 'T' HH:mm:ss");
        Date fecha = new Date();
        // Formato
        // SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMM d HH:mm:ss z yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss d/MM/yyyy");
        System.out.println(dateFormat.format(fecha));
        sb.append("[").append(dateFormat.format(fecha)).append("]\n");
        try
        {
            fichero.seek(fichero.length());
            String s = sb.toString();
            System.out.println(s);
            fichero.writeBytes(s);

        } catch (IOException ex)
        {
            Logger.getLogger(CrearLog.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            fichero.close();
        }

    }

    public static void log(Movimientos m) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        // Formato
        // SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE MMMM d HH:mm:ss z yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss d/MM/yyyy");
        sb.append("[").append(dateFormat.format(new Date())).append("] ")
                .append(m.getProducto().getNombre() + ": ")
                .append(m.getProducto().getCodigo() + " Unidades: ").append(m.getCantidad()).append("\n");
        try
        {
            fichero = new RandomAccessFile(new File("./log/logReponedor.log"), "rw");
            fichero.seek(fichero.length());
            String s = sb.toString();
            System.out.println(s);
            fichero.writeBytes(s);
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(CrearLog.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            fichero.close();
        }
    }

    public static void main(String[] args) throws IOException
    {
        iniLog();
    }
}
