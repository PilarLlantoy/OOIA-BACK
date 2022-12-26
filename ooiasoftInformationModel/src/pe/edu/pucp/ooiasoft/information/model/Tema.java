/* 
 * Archivo: Tema.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.information.model;

public class Tema {
    //Atributos de clase
    private int idTema;
    private String nombre;
    private boolean estado;
    
    //Constructores
    
    //Constructor vacio
    public Tema(){}
    
    //Necesita de todos los atributos para existir
    public Tema(int idTema, String nombre, boolean estado) {
        this.idTema = idTema;
        this.nombre = nombre;
        this.estado = estado;
    }
    
    //Getters y setters
    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
