/* 
 * Archivo: Ciclo.java
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

import java.util.Date;

public class Ciclo {
    //Atributos propios de la clase
    private int idCiclo;
    private int anho;
    private int periodo;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean estado;

    //Constructores
    
    //Constructor vacio
    public Ciclo(){}
    
    //El constructor debe recibir todos los parametros para existir (menos el ID)
    public Ciclo(int idCiclo, int anho, int periodo, Date fechaInicio, Date fechaFin, boolean estado) {
        this.idCiclo = idCiclo;
        this.anho = anho;
        this.periodo = periodo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public String consultarDatosCiclo(){
        return "Ciclo: " + anho + " - " + periodo;
    }
    
}
