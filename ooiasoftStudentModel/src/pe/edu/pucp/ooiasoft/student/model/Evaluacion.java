/* 
 * Archivo: Evaluacion.java
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

public class Evaluacion {
    //Atributos clase
    private int idEvaluacion;
    private int nota;
    private String comentarios;
    private int numEvaluacion;
    private boolean estado;
    
    //Atributos relaciones
    private TipoEvaluacion tipo;
    
    //Constructores
    
    //Constructor vacio
    public Evaluacion(){}
    
    //El constructor debe recibir todos los parametros para existir (menos el ID)
    public Evaluacion(int nota, String comentarios, TipoEvaluacion tipo, int numEvaluacion, boolean estado) {
        this.nota = nota;
        this.comentarios = comentarios;
        this.tipo = tipo;
        this.numEvaluacion = numEvaluacion;
        this.estado = estado;
    }

    //Getters y setters
     public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }
    
    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public TipoEvaluacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvaluacion tipo) {
        this.tipo = tipo;
    }

    public int getNumEvaluacion() {
        return numEvaluacion;
    }

    public void setNumEvaluacion(int numEvaluacion) {
        this.numEvaluacion = numEvaluacion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
