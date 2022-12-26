/* 
 * Archivo: InformacionSubtemaCiclo.java
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

public class InformacionTemaCiclo {
    //Atributos de clase
    private int idInformacionTemaCiclo;
    private String titulo;
    private String descripcion;
    private String descripcionUTF;
    private Date fechaRegistro;
    private Date fechaVisible;
    private byte[] foto;
    private boolean estado;
    
    //Atributos de relaciones
    private Ciclo ciclo;
    private Tema tema;

    //Constructor vacio
    public InformacionTemaCiclo(){}

    //Getters y setters

    public int getIdInformacionTemaCiclo() {
        return idInformacionTemaCiclo;
    }

    public void setIdInformacionTemaCiclo(int idInformacionTemaCiclo) {
        this.idInformacionTemaCiclo = idInformacionTemaCiclo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionUTF() {
        return descripcionUTF;
    }

    public void setDescripcionUTF(String descripcionUTF) {
        this.descripcionUTF = descripcionUTF;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaVisible() {
        return fechaVisible;
    }

    public void setFechaVisible(Date fechaVisible) {
        this.fechaVisible = fechaVisible;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public boolean isEstado() {
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

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

}
