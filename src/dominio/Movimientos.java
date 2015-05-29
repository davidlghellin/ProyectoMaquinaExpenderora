/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Date;

/**
 *
 * @author David López González
 */
public class Movimientos
{
    private int id;
    private Date fecha;
    private Producto producto;
    private int cantidad;

    public Movimientos(){}
    public Movimientos(Producto producto, int cantidad)
    {
        this.producto = producto;
        this.cantidad=cantidad;
    }
    
    public int getId(){return id;}
    public Date getFecha(){return fecha;}
    public Producto getProducto(){return producto;}
    public int getCantidad(){return cantidad;}
    
    public void setId(int id){this.id=id;}
    public void setFecha(Date fecha){this.fecha = fecha;}
    public void setProducto(Producto producto){this.producto = producto;}
    public void setCantidad(int cantidad){this.cantidad = cantidad;}    
}
