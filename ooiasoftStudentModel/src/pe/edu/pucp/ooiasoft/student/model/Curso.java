/* 
 * Archivo: Curso.java
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

import pe.edu.pucp.ooiasoft.information.model.Especialidad;

public class Curso {
    //Atributos de clase
    private int idCurso;
    private String codigo;
    private String nombre;
    private double creditos;
    private boolean estado;
    
    //Atributos de relaciones
    private Especialidad especialidad;
    
    //Constructores
    
    // Constructor vacío para usarlo en SQL
    public Curso(){}
    
    //Este constructor debe tambien recibir la especialidad, no puede existir un curso sin especialidad
    public Curso(int idCurso, String codigo, String nombre, double creditos, Especialidad especialidad, boolean estado) {
        this.idCurso = idCurso;
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    //Getters y setters
    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCreditos() {
        return creditos;
    }

    public void setCreditos(double creditos) {
        this.creditos = creditos;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    
    public String consultarDatosCurso(){
        return "Curso: " + codigo + " - Nombre: " + nombre + " - Especialidad: " + especialidad.getNombre() + " - Creditos: " + creditos;
    }
}
