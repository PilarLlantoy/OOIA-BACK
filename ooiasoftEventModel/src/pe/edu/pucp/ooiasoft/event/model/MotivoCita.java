/* 
 * Archivo: MotivoCita.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.event.model;

public class MotivoCita {

    //Atributos propios de la clase
    private int idMotivo;
    private char abreviatura;
    private String descripcion;
    private boolean estado;

    //Atributos de relaciones
    private TipoCita tipoCita;

    //Constructores
    
    //Constructor vacio
    public MotivoCita() { }
    
    //Recibe todo menos el ID
    public MotivoCita(char abreviatura, String descripcion, TipoCita tipoCita, boolean estado) {
        this.abreviatura = abreviatura;
        this.descripcion = descripcion;
        this.tipoCita = tipoCita;
        this.estado = estado;
    }
    
    //Getters y setters
    public int getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
    }

    public char getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(char abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public TipoCita getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(TipoCita tipoCita) {
        this.tipoCita = tipoCita;
    }

}
