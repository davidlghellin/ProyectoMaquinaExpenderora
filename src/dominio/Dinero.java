/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 * @see
 * <a href="https://github.com/davidlghellin/ProyectoMaquinaExpenderora">https://github.com/davidlghellin/ProyectoMaquinaExpenderora</a>
 * @version 0.9 13 de Junio de 2015
 * @author David López González
 */
public class Dinero
{

    private int codigo;
    private String nombre;
    private int existencias;
    private float valor;
    
    public Dinero(){}
    public Dinero( String nombre, int existencias,float valor)
    {
        this.nombre = nombre;
        this.existencias = existencias;
        this.valor=valor;
    }
    
    public int getCodigo(){return codigo;}
    public String getNombre(){return nombre;}
    public int getExistencias(){return existencias;}
    public float getValor(){return valor;}
    
    public void setCodigo(int codigo){this.codigo = codigo;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setExistencias(int existencias){this.existencias = existencias;}
    public void setValor(float valor){this.valor = valor;}
}
