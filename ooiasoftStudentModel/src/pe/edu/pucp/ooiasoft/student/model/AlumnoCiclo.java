/* 
 * Archivo: AlumnoCiclo.java
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

import pe.edu.pucp.ooiasoft.information.model.Ciclo;
import java.util.ArrayList;

public class AlumnoCiclo {
    //Atributos de clase
    private int idAlumnoCiclo;
    private double CRAEST;
    private double promedioPonderado;
    private double creditosAprobados;
    private double creditosTotales;
    private int cantidadCursosAprobados;
    private boolean estado;
    
    //Atributos de relaciones
    private Ciclo ciclo;
    private ArrayList<AlumnoCursoCiclo> cursos;
    
    public AlumnoCiclo(){
        this.cursos = new ArrayList<>(); 
    } // Constructor vacío para llenarlo en el MySQL
    
    //La clase necesita de un ciclo y estado para poder existir, por ello recibe este como parametro
    public AlumnoCiclo(Ciclo ciclo, boolean estado){
        this.ciclo = ciclo;
        this.estado = estado;
        this.cursos = new ArrayList<>();    //Inicializar arreglo de cursos en un ciclo
    }
    
    //Getters y setters
    public int getIdAlumnoCiclo() {
        return idAlumnoCiclo;
    }

    public void setIdAlumnoCiclo(int idAlumnoCiclo) {
        this.idAlumnoCiclo = idAlumnoCiclo;
    }
  
    public double getCRAEST() {
        return CRAEST;
    }

    public void setCRAEST(double CRAEST) {
        this.CRAEST = CRAEST;
    }

    public double getPromedioPonderado() {
        return promedioPonderado;
    }

    public void setPromedioPonderado(double promedioPonderado) {
        this.promedioPonderado = promedioPonderado;
    }

    public double getCreditosAprobados() {
        return creditosAprobados;
    }

    public void setCreditosAprobados(double creditosAprobados) {
        this.creditosAprobados = creditosAprobados;
    }

    public double getCreditosTotales() {
        return creditosTotales;
    }

    public void setCreditosTotales(double creditosTotales) {
        this.creditosTotales = creditosTotales;
    }

    public int getCantidadCursosAprobados() {
        return cantidadCursosAprobados;
    }

    public void setCantidadCursosAprobados(int cantidadCursosAprobados) {
        this.cantidadCursosAprobados = cantidadCursosAprobados;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public ArrayList<AlumnoCursoCiclo> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<AlumnoCursoCiclo> cursos) {
        this.cursos = cursos;
    }
    
    public void agregarCurso(AlumnoCursoCiclo curso){
        cursos.add(curso);
    }

    //Completa los datos de un curso en un ciclo
    public void completarDatosCurso(Curso curso, AlumnoCursoCiclo aCC){
        aCC.setCurso(curso);
        cursos.add(aCC);
    }
    
    //Agregar evaluacion luego de buscar el curso en el arreglo
    public void agregarEvaluacionCurso(Curso curso, Evaluacion ev){
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCurso().getIdCurso() == curso.getIdCurso()) {
                cursos.get(i).agregarEvaluacion(ev);
            }
        }
        
    }
    
    //Retorna en formato reporte las evaluaciones de los cursos que contiene
    public String consultarCursosEvaluaciones(){
        String reporte = "";
        for (int i = 0; i < cursos.size(); i++) {
            reporte += "Codigo: " + cursos.get(i).getCurso().getCodigo() + "\n"
                    + "Nombre: " + cursos.get(i).getCurso().getNombre() + "\n"
                    + cursos.get(i).consultarEvaluaciones() + "\n";                    
        }
        return reporte;
    }
    
    //Retorna en formato reporte los cursos que contiene
    public String consultarCursos(){
        String reporte = "";
        for (int i = 0; i < cursos.size(); i++) {
            reporte += "Codigo: " + cursos.get(i).getCurso().getCodigo() + "\n"
                    + "Nombre: " + cursos.get(i).getCurso().getNombre() + "\n";
        }
        return reporte;
    }
        
}
