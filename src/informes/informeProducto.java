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
package informes;

import inerfaceDAO.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author David López González
 */
public class informeProducto extends Conexion
{
    static JasperPrint jasperPrint = null;
    static JasperReport reporte = null;
    static Connection conexion = null;

    private static void conectar()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager
                    .getConnection("jdbc:mysql://localhost/BMExpendedora", "root", "root");

        } catch (ClassNotFoundException ex)
        {
            System.err.println("La clase no existe:" + ex.getMessage());
        } catch (SQLException e)
        {
            System.err.println("La conexión con la BBno se ha podido realizar:" + e.getMessage());
        }
    }

    private static void compilar()
    {
        try
        {
           reporte = JasperCompileManager.compileReport(
                    "./src/informes/InformeProducto.jrxml");
        } catch (JRException ex)
        {
            Logger.getLogger(InformesReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void imprimir()
    {
        try
        {
            jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
        } catch (JRException ex)
        {
            Logger.getLogger(InformesReports.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            JasperExportManager.exportReportToPdfFile(
                    jasperPrint, "./Informes/info_productos.pdf");
        } catch (JRException ex)
        {
            Logger.getLogger(InformesReports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws SQLException
    {
        conectar();
        compilar();
        imprimir();
    }
}