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
    private Blob imagen;

    public Producto(){}
    public Producto(String nombre, String descripcion, float precio, int existencias)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
    }
    public Producto(String nombre, String descripcion, float precio, int existencias, Blob imagen)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
        this.imagen = imagen;
    }
    public Producto(String nombre, String descripcion, float precio, Blob imagen)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = 0;
        this.imagen = imagen;
    }

    public int getCodigo()      {return codigo;}
    public String getNombre()   {return nombre;}
    public String getDescripcion(){return descripcion;}
    public float getPrecio()    {return precio;}
    public int getExistencias() {return existencias;}
    public Blob getImagen()   {return imagen;}
    public void setCodigo(int codigo){this.codigo = codigo;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setDescripcion(String descripcion){this.descripcion = descripcion;}
    public void setPrecio(float precio){this.precio = precio;}
    public void setExistencias(int existencias){this.existencias = existencias;}
    public void setImagen(Blob imagen){this.imagen = imagen;}    
    
}
