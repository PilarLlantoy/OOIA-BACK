/* 
 * Archivo: EventoCiclo.java
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

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.ooiasoft.student.model.Alumno;
import pe.edu.pucp.ooiasoft.information.model.Ciclo;

public class EventoCiclo {

    //Atributos propios de la clase
    private int idEventoCiclo;
    private Date fechaRegistro;
    private Date fechaEvento;
    private int capacidadMax;
    private int cantAsistentes;
    private String aula;
    private String titulo;
    private String tituloUTF;
    private String descripcion;
    private String descripcionUTF;
    private String organizadores;
    private String linkEncuestaSatisfaccion;
    private String linkVideo;
    private byte[] foto;
    private int horaIni;
    private int duracionHoras;
    //A diferencia de EstadoEvento, estado permite ver si fue eliminado de la base
    private boolean estado;

    //Atributos de relaciones
    private Ciclo ciclo;
    private EstadoEvento estadoEvento;
    private ArrayList<Alumno> alumnosInscritos;

    public EventoCiclo() {
        this.alumnosInscritos = new ArrayList<>();
    }

    //Getters y setters

    public int getIdEventoCiclo() {
        return idEventoCiclo;
    }

    public void setIdEventoCiclo(int idEventoCiclo) {
        this.idEventoCiclo = idEventoCiclo;
    }

    public int getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(int horaIni) {
        this.horaIni = horaIni;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }
        
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public int getCantAsistentes() {
        return cantAsistentes;
    }

    public void setCantAsistentes(int cantAsistentes) {
        this.cantAsistentes = cantAsistentes;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
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

    public String getLinkEncuestaSatisfaccion() {
        return linkEncuestaSatisfaccion;
    }

    public void setLinkEncuestaSatisfaccion(String linkEncuestaSatisfaccion) {
        this.linkEncuestaSatisfaccion = linkEncuestaSatisfaccion;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public EstadoEvento getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(EstadoEvento estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public ArrayList<Alumno> getAlumnosInscritos() {
        return alumnosInscritos;
    }

    public void setAlumnosInscritos(ArrayList<Alumno> alumnosInscritos) {
        this.alumnosInscritos = alumnosInscritos;
    }

    public void agregarAlumno(Alumno alumno) {
        this.alumnosInscritos.add(alumno);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTituloUTF() {
        return tituloUTF;
    }

    public void setTituloUTF(String tituloUTF) {
        this.tituloUTF = tituloUTF;
    }

    public String getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(String organizadores) {
        this.organizadores = organizadores;
    }

}
