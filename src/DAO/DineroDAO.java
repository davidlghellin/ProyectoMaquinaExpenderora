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
package DAO;

import dominio.Dinero;
import inerfaceDAO.Conexion;
import inerfaceDAO.interfaceDineroDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class DineroDAO extends Conexion implements interfaceDineroDAO
{
    
    @Override
    public void alta(Dinero d)
    {
        Connection miConex = null;

        try
        {
            miConex = Conectar();

            String misql = "INSERT INTO TDinero (Nombre,Existencias,Valor) VALUES(?,?,?);";
            PreparedStatement ps = miConex.prepareStatement(misql);
            ps.setString(1, d.getNombre());
            ps.setInt(2, d.getExistencias());
            ps.setFloat(3, d.getValor());

            ps.executeUpdate();
        } catch (SQLException ex)
        {
            System.out.println("errr");
            Logger.getLogger(DineroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            Desconectar(miConex);
        }
    }

    @Override
    public void baja(int codigo)
    {
        Connection miConex = null;

        try
        {
            miConex = Conectar();
            String misql = "DELETE FROM TDinero WHERE Codigo = ?;";
            PreparedStatement ps = miConex.prepareStatement(misql);
            ps.setInt(1, codigo);

            ps.executeUpdate();
        } catch (SQLException ex)
        {
            Logger.getLogger(DineroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            Desconectar(miConex);
        }
    }

    @Override
    public void modificacion(Dinero d)
    {
        Connection miConex = null;

        try
        {
            miConex = Conectar();

            String misql = "UPDATE TDinero SET Nombre=?,Existencias=?,Valor=? WHERE Codigo=?;";
            PreparedStatement ps = miConex.prepareStatement(misql);

            ps.setString(1, d.getNombre());
            ps.setInt(2, d.getExistencias());
            ps.setFloat(3, d.getValor());
            ps.setInt(4, d.getCodigo());

            ps.executeUpdate();
        } catch (SQLException ex)
        {
            Logger.getLogger(DineroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            Desconectar(miConex);
        }
    }

    @Override
    public Dinero consultar(String texto)
    {
        Connection miConex = null;
        Dinero dinero = null;

        try
        {
            dinero = new Dinero();
            miConex = Conectar();
            String misql = ("SELECT * FROM TDinero WHERE Nombre=?;");
            PreparedStatement ps = miConex.prepareStatement(misql);
            ps.setString(1, texto);
            ResultSet consulta = ps.executeQuery();

            if (consulta.next())
            {
                dinero.setCodigo(consulta.getInt("Codigo"));
                dinero.setNombre(consulta.getString("Nombre"));
                dinero.setExistencias(consulta.getInt("Existencias"));
                dinero.setValor(consulta.getFloat("Valor"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DineroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            Desconectar(miConex);
        }
        return dinero;
    }
    
    @Override
    public Dinero consultar(int num)
    {
        Connection miConex = null;
        Dinero dinero = null;

        try
        {
            dinero = new Dinero();
            miConex = Conectar();
            String misql = ("SELECT * FROM TDinero WHERE Codigo=?;");
            PreparedStatement ps = miConex.prepareStatement(misql);
            ps.setInt(1, num);
            ResultSet consulta = ps.executeQuery();

            if (consulta.next())
            {
                dinero.setCodigo(consulta.getInt("Codigo"));
                dinero.setNombre(consulta.getString("Nombre"));
                dinero.setExistencias(consulta.getInt("Existencias"));
                dinero.setValor(consulta.getFloat("Valor"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DineroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            Desconectar(miConex);
        }
        return dinero;
    }

    @Override
    public ArrayList<Dinero> consultarAll()
    {
        Connection miConex = null;
        ArrayList<Dinero> muchosdineros = null;

        try
        {
            String misql = ("SELECT * FROM TDinero ;");
            muchosdineros = new ArrayList<Dinero>();
            miConex = Conectar();
            PreparedStatement ps = miConex.prepareStatement(misql);
            ResultSet consulta = ps.executeQuery();
            while (consulta.next())
            {
                Dinero dinero = new Dinero();
                dinero.setCodigo(consulta.getInt("Codigo"));
                dinero.setNombre(consulta.getString("Nombre"));
                dinero.setExistencias(consulta.getInt("Existencias"));
                dinero.setValor(consulta.getFloat("Valor"));
                muchosdineros.add(dinero);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DineroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            Desconectar(miConex);
        }
        return muchosdineros;
    }

}
