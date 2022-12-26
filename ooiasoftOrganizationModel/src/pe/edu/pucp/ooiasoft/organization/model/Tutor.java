/* 
 * Archivo: Tutor.java
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

import java.util.Date;
import pe.edu.pucp.ooiasoft.information.model.Especialidad;

public class Tutor extends PersonalCitas {

    //Atributos propios de la clase
    private Date fechaAsociacion;
    private Especialidad especialidad;

    //Constructores
    //Constructor vacio
    public Tutor() {
    }

    //Getters y setters
    public Date getFechaAsociacion() {
        return fechaAsociacion;
    }

    public void setFechaAsociacion(Date fechaAsociacion) {
        this.fechaAsociacion = fechaAsociacion;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

}
