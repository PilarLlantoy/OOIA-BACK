/* 
 * Archivo: Cita.java
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

import java.util.Date;
import pe.edu.pucp.ooiasoft.organization.model.PersonalCitas;
import pe.edu.pucp.ooiasoft.student.model.Alumno;
import pe.edu.pucp.ooiasoft.information.model.Ciclo;

public class Cita {

    //Atributos propios de la clase
    private int idCita;
    private Date fechaRegistro;
    private Date fechaAtencion;
    private int hora;
    private boolean asistio;
    private boolean estado;

    //Atributos de relaciones
    private MotivoCita motivoCita;
    private PersonalCitas personalCitas;
    private Alumno alumno;
    private Ciclo ciclo;

    //Constructores
    //Recibe todos los datos menos si el alumno asistio ya que esto se llena posterior a la cita
    public Cita(Date fechaRegistro, Date fechaAtencion, int hora, boolean estado, MotivoCita motivoCita, PersonalCitas personalCitas, Alumno alumno, Ciclo ciclo) {
        this.fechaRegistro = fechaRegistro;
        this.fechaAtencion = fechaAtencion;
        this.hora = hora;
        this.estado = estado;
        this.motivoCita = motivoCita;
        this.personalCitas = personalCitas;
        this.alumno = alumno;
        this.ciclo = ciclo;
    }

    public Cita() {
        
    }

    //Getters y setters
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public boolean getAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public MotivoCita getMotivoCita() {
        return motivoCita;
    }

    public void setMotivoCita(MotivoCita motivoCita) {
        this.motivoCita = motivoCita;
    }

    public PersonalCitas getPersonalCitas() {
        return personalCitas;
    }

    public void setPersonalCitas(PersonalCitas personalCitas) {
        this.personalCitas = personalCitas;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }
    
    //Completa los datos restantes como asistio y el motivo. Esto lo llama el tutor
    public void completarDatosFaltantes(Boolean asistio, MotivoCita motivoCita) {
        this.asistio = asistio;
        this.motivoCita = motivoCita;
    }

    //Retorna en formato de cadena los datos de la cita (incluyendo los de la relacion)
    public String consultarDatosCita() {
        String reporte = "";
        reporte += "DATOS DE LA CITA:\n";
        reporte += "Fecha de Atencion: " + this.getFechaAtencion() + "\n";
        reporte += "Hora de atencion: " + this.getHora() + "\n";
        reporte += "Alumno: " + this.getAlumno().getCodigoPUCP() + " - " + this.getAlumno().getNombreCompleto() + "\n";
        reporte += "Tutor/Psicologo a cargo: " + this.getPersonalCitas().getNombreCompleto() + "\n";
        reporte += "Motivo de la cita: " + this.getMotivoCita().getDescripcion() + "\n";
        return reporte;
    }

}
