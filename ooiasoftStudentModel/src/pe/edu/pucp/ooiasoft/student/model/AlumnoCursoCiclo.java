/* 
 * Archivo: AlumnoCursoCiclo.java
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

public class AlumnoCursoCiclo {
    //Atributos clases
    private int idAlumnoCursoCiclo;
    private double notaFinal;
    private boolean estado;
    
    //Atributos de relaciones
    private Curso curso;
    private TipoECTS escala;
    private ArrayList<Evaluacion> evaluaciones;

    //Constructores
    // Constructor vacío para listarlo en SQL
    public AlumnoCursoCiclo() {
        evaluaciones = new ArrayList<>();
    }
    
    //La clase para existir debe recibir un curso y estado para poder existir, los demas atributos son calculados
    public AlumnoCursoCiclo(Curso curso, boolean estado) {
        this.curso = curso;
        this.estado = estado;
        evaluaciones = new ArrayList<>();   //Inicializar arreglo
    }

    //Getters y setters

    public int getIdAlumnoCursoCiclo() {
        return idAlumnoCursoCiclo;
    }

    public void setIdAlumnoCursoCiclo(int idAlumnoCursoCiclo) {
        this.idAlumnoCursoCiclo = idAlumnoCursoCiclo;
    }
    
    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public TipoECTS getEscala() {
        return escala;
    }

    public void setEscala(TipoECTS escala) {
        this.escala = escala;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
    
    public void agregarEvaluacion(Evaluacion ev){
        evaluaciones.add(ev);
    }
   
    //Retorna en formato reporte las evaluaciones que contiene
    public String consultarEvaluaciones(){
        String reporte = "";
        for (int i = 0; i < evaluaciones.size(); i++) {
            reporte += evaluaciones.get(i).getTipo() + " "
                    + evaluaciones.get(i).getNumEvaluacion() + " - "
                    + evaluaciones.get(i).getNota() + "\n";
        }
        return reporte;
    }   
    
}
