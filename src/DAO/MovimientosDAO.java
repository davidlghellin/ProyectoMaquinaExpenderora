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

import dominio.Movimientos;
import dominio.Producto;
import inerfaceDAO.Conexion;
import inerfaceDAO.interfaceMovimientoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @see
 * <a href="https://github.com/davidlghellin/ProyectoMaquinaExpenderora">https://github.com/davidlghellin/ProyectoMaquinaExpenderora</a>
 * @version 0.9 13 de Junio de 2015
 * @author David López González
 */
public class MovimientosDAO extends Conexion implements interfaceMovimientoDAO
{

    /**
     * Alta en la BBDD
     *
     * @param m Objeto movimiento que se dará de alta
     */
    @Override
    public void alta(Movimientos m)
    {
        Connection miConex = null;

        try
        {
            miConex = Conectar();

            String misql = "INSERT INTO TMovimientos (IdProducto,Cantidad) VALUES (?,?);";
            PreparedStatement ps = miConex.prepareStatement(misql);
            ps.setInt(1, m.getProducto().getCodigo());
            ps.setInt(2, m.getCantidad());

            ps.executeUpdate();
        } catch (SQLException ex)
        {
            System.err.println("Error en el alta:" + ex.getMessage());
        } finally
        {
            Desconectar(miConex);
        }
    }

    /**
     * Baja de una tupla movimiento en la BBDD
     *
     * @param codigo Entero que es el id que eliminamos
     */
    @Override
    public void baja(int codigo)
    {
        Connection miConex = null;

        try
        {
            miConex = Conectar();
            String misql = "DELETE FROM TMovimientos WHERE IdMovimiento = ?;";
            PreparedStatement ps = miConex.prepareStatement(misql);
            ps.setInt(1, codigo);

            ps.executeUpdate();
        } catch (SQLException ex)
        {
            System.err.println("Error al borrar:" + ex.getMessage());
        } finally
        {
            Desconectar(miConex);
        }
    }

    /**
     * Método para eliminar los movimientos asociados a un producto
     *
     * @param producto
     */
    public void baja(Producto producto)
    {
        Connection miConex = null;

        try
        {
            miConex = Conectar();
            String misql = "DELETE FROM TMovimientos WHERE IdProducto = ?;";
            PreparedStatement ps = miConex.prepareStatement(misql);
            ps.setInt(1, producto.getCodigo());

            ps.executeUpdate();
        } catch (SQLException ex)
        {
            System.err.println("Error al borrar:" + ex.getMessage());
        } finally
        {
            Desconectar(miConex);
        }
    }

    /**
     * Modificación de los valores de una tupla en la BBDD
     *
     * @param m Objeto movimiento que queremos modificar
     */
    @Override
    public void modificacion(Movimientos m)
    {
        Connection miConex = null;

        try
        {
            miConex = Conectar();

            String misql = "UPDATE TMovimientos SET Cantidad=? WHERE IdMovimiento=?;";
            PreparedStatement ps = miConex.prepareStatement(misql);

            ps.setInt(1, m.getCantidad());
            ps.setInt(2, m.getId());

            ps.executeUpdate();
        } catch (SQLException ex)
        {
            System.err.println("Error al actualizar" + ex.getMessage());
        } finally
        {
            Desconectar(miConex);
        }
    }

    /**
     * Consulta a la BBDD
     *
     * @param idMovientos Entero por el cuál haremos la consulta a la BBDD
     * @return Movimiento
     */
    @Override
    public Movimientos consultar(int idMovientos)
    {
        Connection miConex = null;
        Movimientos movimientos = null;

        try
        {
            movimientos = new Movimientos();
            miConex = Conectar();
            String misql = ("SELECT * FROM TMovimientos WHERE IdMovimiento=?;");
            PreparedStatement ps = miConex.prepareStatement(misql);
            ps.setInt(1, idMovientos);
            ResultSet consulta = ps.executeQuery();

            if (consulta.next())
            {
                movimientos.setId(consulta.getInt("IdMovimiento"));
                movimientos.setFecha(consulta.getDate("Fecha"));
                ProductoDAO pdao = new ProductoDAO();
                Producto p = pdao.consultar(consulta.getInt("IdProducto"));
                movimientos.setProducto((Producto) p);

                movimientos.setCantidad(consulta.getInt("Cantidad"));
            }
        } catch (SQLException ex)
        {
            System.err.println("Error en la consulta:" + ex.getMessage());
        } finally
        {
            Desconectar(miConex);
        }
        return movimientos;
    }

    /**
     * Consulta a la BBDD
     *
     * @return ArrayList de Movimientos
     */
    @Override
    public ArrayList<Movimientos> consultarAll(Producto producto)
    {
        Connection miConex = null;
        ArrayList<Movimientos> muchosMovimientos = null;

        try
        {
            String misql = ("SELECT * FROM TMovimientos ;");
            muchosMovimientos = new ArrayList<Movimientos>();
            miConex = Conectar();
            PreparedStatement ps = miConex.prepareStatement(misql);
            ResultSet consulta = ps.executeQuery();
            while (consulta.next())
            {
                Movimientos movimientos = new Movimientos();
                movimientos.setFecha(consulta.getDate("Fecha"));
                movimientos.setId(consulta.getInt("IdMovimiento"));
                movimientos.setCantidad(consulta.getInt("Cantidad"));
                movimientos.setProducto(producto);
                muchosMovimientos.add(movimientos);
            }
        } catch (SQLException ex)
        {
            System.err.println("Error en la consulta de todos:" + ex.getMessage());
        } finally
        {
            Desconectar(miConex);
        }
        return muchosMovimientos;
    }

}
