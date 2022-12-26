/* 
 * Archivo: AsignadosCiclo.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.student.model;

import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.information.model.Ciclo;
import pe.edu.pucp.ooiasoft.organization.model.Tutor;

public class AsignadosCiclo {
    //Atributos de clase
    private int idAsignadosCiclo;
    private int cantidadAsignados;
    private boolean estado;
    
    //Atributos de relaciones
    private Tutor tutor;
    private Ciclo ciclo;
    private ArrayList<Alumno> asignados;
    
    //Constructores
    //Constructor vacio
    public AsignadosCiclo(){
        asignados = new ArrayList<>();
    }
    
    //Getters y setters
    public int getIdAsignadosCiclo() {
        return idAsignadosCiclo;
    }

    public void setIdAsignadosCiclo(int idAsignadosCiclo) {
        this.idAsignadosCiclo = idAsignadosCiclo;
    }    
    
    public int getCantidadAsignados() {
        return cantidadAsignados;
    }

    public void setCantidadAsignados(int cantidadAsignados) {
        this.cantidadAsignados = cantidadAsignados;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public ArrayList<Alumno> getAsignados() {
        return asignados;
    }

    public void setAsignados(ArrayList<Alumno> asignados) {
        this.asignados = asignados;
    }
    
    public void agregarAlumno(Alumno alum){
        asignados.add(alum);
    }
    
    //Retorna en formato reporte los datos de sus alumnos asignados
    public String consultarAlumnosAsignados(){
        return "";
    }
    
}
