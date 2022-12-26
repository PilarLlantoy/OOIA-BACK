/* 
 * Archivo: DetalleHorario.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.organization.model;

public class DetalleHorario {

    //Atributos de clase
    /*
     * Da soporte a varios fragmentos de horas en un dia y para varios dias.
     * Por ejemplo, Lunes (0) de 8 am a 10 am (horaInicio = 8, horaFin = 10),
     * Martes (1) de 10 am a 2 pm (horaInicio = 10, horaFin = 14)
     */
    private int idDetalle;
    private int dia;
    private int horaInicio;
    private int horaFin;
    private boolean estado;

    //Constructores
    
    //Constructor vacio
    public DetalleHorario(){}
    
    //Constructor con parametros
    public DetalleHorario(int dia, int horaInicio, int horaFin, boolean estado) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }

    //Getters y setters
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
