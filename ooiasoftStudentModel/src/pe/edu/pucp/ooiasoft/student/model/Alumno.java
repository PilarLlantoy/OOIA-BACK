/* 
 * Archivo: Alumno.java
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
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.ooiasoft.organization.model.Persona;

public class Alumno extends Persona{
    //Atributos propios de la clase
    private int escalaPago;
    private double CRAEST;
    private double promedioPonderado;
    private int cantCiclos;
    private int cantCursosAprobados;
    private double cantidadCreditosAprobados;
    private double totalCreditos;
    
    //Atributos de relacion
    private Especialidad especialidad;
    private TipoAlumno tipo;
    private ArrayList<AlumnoCiclo> ciclos;

    // Constructor vacío para listarlo en SQL
    public Alumno(){
        this.ciclos = new ArrayList<>();
        cantCiclos = cantCursosAprobados = 0;
        CRAEST = promedioPonderado = cantidadCreditosAprobados = totalCreditos = 0.0;   
    }
    
    
    //El constructor recibe los datos propios de persona, la escala de pago, la especialidad y que tipo de alumno es
    public Alumno(String codigoPUCP, String DNI, String nombreCompleto,
            Date fechaNacimiento, int edad, char sexo, String telefono, String correo,
            int escalaPago, Especialidad especialidad, TipoAlumno tipo, boolean estado) {
        super(codigoPUCP, DNI, nombreCompleto, fechaNacimiento, edad, sexo, telefono, correo, estado);
        this.escalaPago = escalaPago;
        this.especialidad = especialidad;
        this.tipo = tipo;
        this.ciclos = new ArrayList<>();    //Inicializar el arreglo de ciclos
    }

    //Getters y setters
    public int getEscalaPago() {
        return escalaPago;
    }

    public void setEscalaPago(int escalaPago) {
        this.escalaPago = escalaPago;
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

    public int getCantCiclos() {
        return cantCiclos;
    }

    public void setCantCiclos(int cantCiclos) {
        this.cantCiclos = cantCiclos;
    }

    public int getCantCursosAprobados() {
        return cantCursosAprobados;
    }

    public void setCantCursosAprobados(int cantCursosAprobados) {
        this.cantCursosAprobados = cantCursosAprobados;
    }

    public double getCantidadCreditosAprobados() {
        return cantidadCreditosAprobados;
    }

    public void setCantidadCreditosAprobados(double cantidadCreditosAprobados) {
        this.cantidadCreditosAprobados = cantidadCreditosAprobados;
    }

    public double getTotalCreditos() {
        return totalCreditos;
    }

    public void setTotalCreditos(double totalCreditos) {
        this.totalCreditos = totalCreditos;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public TipoAlumno getTipo() {
        return tipo;
    }

    public void setTipo(TipoAlumno tipo) {
        this.tipo = tipo;
    }

    public ArrayList<AlumnoCiclo> getCiclos() {
        return ciclos;
    }

    public void setCiclos(ArrayList<AlumnoCiclo> ciclos) {
        this.ciclos = ciclos;
    }
    
    public void agregarCiclo(AlumnoCiclo ciclo){
        ciclos.add(ciclo);
    }
    
}