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
package dominio;

import java.sql.Blob;
import java.util.ArrayList;

/**
 *
 * @author David López González
 */
public class Producto
{ 
    private int codigo;
    private String nombre;
    private String descripcion;
    private float precio;
    private int existencias;
    private byte[] imagen;
    private ArrayList<Movimientos> movimientos=new ArrayList();

    public Producto(){}
    public Producto(String nombre, String descripcion, float precio, int existencias)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
    }
    
    public Producto(String nombre, String descripcion, float precio, int existencias, byte[] imagen)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
        this.imagen = imagen;
    }

    public int getCodigo()      {return codigo;}
    public String getNombre()   {return nombre;}
    public String getDescripcion(){return descripcion;}
    public float getPrecio()    {return precio;}
    public int getExistencias() {return existencias;}
    public byte[] getImagen()   {return imagen;}
    public ArrayList<Movimientos> getMovimientos(){return movimientos;}
    
    public void setCodigo(int codigo){this.codigo = codigo;}
    public void setNombre(String nombre){this.nombre = nombre;}   
    public void setDescripcion(String descripcion){this.descripcion = descripcion;}
    public void setPrecio(float precio){this.precio = precio;}
    public void setExistencias(int existencias){this.existencias = existencias;}
    public void setImagen(byte[] imagen){this.imagen = imagen;}    
    public void setMovimientos(ArrayList<Movimientos> movimientos){this.movimientos = movimientos;}   
    
    @Override
    public String toString()
    {
        return nombre;
                //"Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", existencias=" + existencias +  '}';
    }
}
