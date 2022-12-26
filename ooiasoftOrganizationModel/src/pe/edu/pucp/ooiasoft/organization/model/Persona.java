/* 
 * Archivo: Persona.java
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

//Clase abstracta, no se instanciara de ella
public abstract class Persona{

    //Atributos propios de la clase
    private int idPersona;
    private String codigoPUCP;
    private String DNI;
    private String nombreCompleto;
    private Date fechaNacimiento;
    private int edad;
    private char sexo;
    private String telefono;
    private String correo;
    private boolean estado;
    private String usuario;
    private byte[] foto;
    private String password;

    //Constructores (ninguno recibe idPersona ya que este lo crea la base de datos)
    
    //Constructor vacio
    public Persona(){}
    
    //Soporta un constructor que reciba todos los datos menos los de inicio de sesion
    //Crea el usuario y password automaticamente
    public Persona(String codigoPUCP, String DNI, String nombreCompleto,
            Date fechaNacimiento, int edad, char sexo, String telefono, String correo, boolean estado) {
        this.codigoPUCP = codigoPUCP;
        this.DNI = DNI;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.sexo = sexo;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
        //Esta zona permite crear automaticamente los datos de inicio de sesion
        this.usuario = codigoPUCP;
        this.password = "PUCP" + DNI;
    }

    //Tambien se soporta un constructor que reciba todos los parametros incluyendo los datos de inicio de sesion
    public Persona(String codigoPUCP, String DNI, String nombreCompleto,
            Date fechaNacimiento, int edad, char sexo, String telefono, String correo,
            String usuario, String password, boolean estado) {
        this.codigoPUCP = codigoPUCP;
        this.DNI = DNI;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.sexo = sexo;
        this.telefono = telefono;
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
        this.estado = estado;
    }

    //Getters y setters
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getCodigoPUCP() {
        return codigoPUCP;
    }

    public void setCodigoPUCP(String codigoPUCP) {
        this.codigoPUCP = codigoPUCP;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
