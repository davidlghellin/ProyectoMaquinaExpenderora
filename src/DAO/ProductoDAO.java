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

import dominio.Producto;
import inerfaceDAO.interfaceProductoDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David López González
 */
public class ProductoDAO implements interfaceProductoDAO
{

    private Connection Conectar()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/BMExpendedora", "root", "root");
        } catch (SQLException ex)
        {
            Logger.getLogger(DineroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DineroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void Desconectar(Connection conx)
    {
        try
        {
            conx.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(DineroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void alta(Producto p)
    {
        Connection miConex = null;

        try
        {
            miConex = Conectar();

            String misql = "INSERT INTO TProducto (Nombre,Descripcion,Precio,Existencias,Imagen) VALUES(?,?,?,?,?);";
            PreparedStatement ps = miConex.prepareStatement(misql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getNombre());
            ps.setFloat(3, p.getPrecio());
            ps.setInt(4, p.getExistencias());
            ps.setBlob(5, p.getImagen());

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
    public void baja(int codigo)
    {
        Connection miConex = null;

        try
        {
            miConex = Conectar();
            String misql = "DELETE FROM TProducto WHERE Codigo = ?;";
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
    public void modificacion(Producto p)
    {
        Connection miConex = null;

        try
        {
            miConex = Conectar();

            String misql = "UPDATE TProducto SET Nombre=?,Descripcion=?,Precio=?,Existencias=?,Imagen=? WHERE Codigo=?;";
            PreparedStatement ps = miConex.prepareStatement(misql);

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setFloat(3, p.getPrecio());
            ps.setInt(4, p.getExistencias());
            ps.setBlob(5, p.getImagen());
            ps.setInt(6, p.getCodigo());

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
    public Producto consultar(String texto)
    {
        Connection miConex = null;
        Producto producto = null;

        try
        {
            producto = new Producto();
            miConex = Conectar();
            String misql = ("SELECT * FROM TProducto WHERE Nombre=?;");
            PreparedStatement ps = miConex.prepareStatement(misql);
            ps.setString(1, texto);
            ResultSet consulta = ps.executeQuery();

            if (consulta.next())
            {
                producto.setCodigo(consulta.getInt("Codigo"));
                producto.setNombre(consulta.getString("Nombre"));
                producto.setDescripcion(consulta.getString("Descripcion"));
                producto.setPrecio(consulta.getFloat("Precio"));
                producto.setExistencias(consulta.getInt("Existencias"));
                producto.setImagen(consulta.getBlob("Imagen"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DineroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            Desconectar(miConex);
        }
        return producto;
    }

    @Override
    public Producto consultar(int num)
    {
        Connection miConex = null;
        Producto producto = null;

        try
        {
            producto = new Producto();
            miConex = Conectar();
            String misql = ("SELECT * FROM TProducto WHERE Codigo=?;");
            PreparedStatement ps = miConex.prepareStatement(misql);
            ps.setInt(1, num);
            ResultSet consulta = ps.executeQuery();

            if (consulta.next())
            {
                producto.setCodigo(consulta.getInt("Codigo"));
                producto.setNombre(consulta.getString("Nombre"));
                producto.setDescripcion(consulta.getString("Descripcion"));
                producto.setPrecio(consulta.getFloat("Precio"));
                producto.setExistencias(consulta.getInt("Existencias"));
                producto.setImagen(consulta.getBlob("Codigo"));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DineroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            Desconectar(miConex);
        }
        return producto;
    }

    @Override
    public ArrayList<Producto> consultarAll()
    {
        Connection miConex = null;
        ArrayList<Producto> muchosproductos = null;
        try
        {
            String misql = ("SELECT * FROM TDinero ;");
            muchosproductos = new ArrayList<Producto>();
            miConex = Conectar();
            PreparedStatement ps = miConex.prepareStatement(misql);
            ResultSet consulta = ps.executeQuery();
            while (consulta.next())
            {
                Producto producto = new Producto();
                producto.setCodigo(consulta.getInt("Codigo"));
                producto.setNombre(consulta.getString("Nombre"));
                producto.setDescripcion(consulta.getString("Descripcion"));
                producto.setPrecio(consulta.getFloat("Precio"));
                producto.setExistencias(consulta.getInt("Existencias"));
                producto.setImagen(consulta.getBlob("Codigo"));
                muchosproductos.add(producto);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DineroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            Desconectar(miConex);
        }
        return muchosproductos;
    }

}
