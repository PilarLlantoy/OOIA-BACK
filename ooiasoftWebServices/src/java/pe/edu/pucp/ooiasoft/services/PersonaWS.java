/* 
 * Archivo: PersonaWS.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   08/06/2021
 */

package pe.edu.pucp.ooiasoft.services;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import pe.edu.pucp.ooiasoft.organization.dao.AdministrativoDAO;
import pe.edu.pucp.ooiasoft.organization.dao.PersonaDAO;
import pe.edu.pucp.ooiasoft.organization.dao.PsicologoDAO;
import pe.edu.pucp.ooiasoft.organization.dao.TutorDAO;
import pe.edu.pucp.ooiasoft.organization.model.Administrativo;
import pe.edu.pucp.ooiasoft.organization.model.Persona;
import pe.edu.pucp.ooiasoft.organization.model.Psicologo;
import pe.edu.pucp.ooiasoft.organization.model.Tutor;
import pe.edu.pucp.ooiasoft.organization.mysql.AdministrativoMySQL;
import pe.edu.pucp.ooiasoft.organization.mysql.PersonaMySQL;
import pe.edu.pucp.ooiasoft.organization.mysql.PsicologoMySQL;
import pe.edu.pucp.ooiasoft.organization.mysql.TutorMySQL;
import pe.edu.pucp.ooiasoft.student.dao.AlumnoDAO;
import pe.edu.pucp.ooiasoft.student.dao.AsignadosCicloDAO;
import pe.edu.pucp.ooiasoft.student.model.Alumno;
import pe.edu.pucp.ooiasoft.student.model.AsignadosCiclo;
import pe.edu.pucp.ooiasoft.student.mysql.AlumnoMySQL;
import pe.edu.pucp.ooiasoft.student.mysql.AsignadosCicloMySQL;

@WebService(serviceName = "PersonaWS")
public class PersonaWS {

    private PersonaDAO daoPersona;
    private AdministrativoDAO daoAdministrativo;
    private PsicologoDAO daoPsicologo;
    private TutorDAO daoTutor;
    private AlumnoDAO daoAlumno;
    private AsignadosCicloDAO daoAsignados;
    
    public PersonaWS(){
        daoPersona = new PersonaMySQL();
        daoAdministrativo = new AdministrativoMySQL();
        daoPsicologo = new PsicologoMySQL();
        daoTutor = new TutorMySQL();
        daoAlumno = new AlumnoMySQL();
        daoAsignados = new AsignadosCicloMySQL();
    }
    
    @WebMethod(operationName = "buscarTipoPersonaPorIDPersona")
    public char buscarTipoPersonaPorIDPersona(@WebParam(name = "idPersona") int idPersona) {
        
        char tipo = 'N';
        try{
            tipo = daoPersona.buscarTipoPersonaPorIDPersona(idPersona);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return tipo;
    }
    
    @WebMethod(operationName = "buscarIDPersonaPorUsuarioPassword")
    public int buscarIDPersonaPorUsuarioPassword(@WebParam(name = "usuario") String usuario,
            @WebParam(name = "password") String password) {
        int idPersona = -1;
        try{
            idPersona = daoPersona.buscarIDPersonaPorUsuarioPassword(usuario, password);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return idPersona;
    }
    
    @WebMethod(operationName = "buscarIDPersonaPorCodigoCorreo")
    public int buscarIDPersonaPorCodigoCorreo(@WebParam(name = "codigoPUCP") String codigoPUCP,
            @WebParam(name = "correo") String correo) {
        int idPersona = -1;
        try{
            idPersona = daoPersona.buscarIDPersonaPorCodigoCorreo(codigoPUCP, correo);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return idPersona;
    }
    
    @WebMethod(operationName = "modificarPersona")
    public int modificarPersona(@WebParam(name = "persona") Persona persona) {
        int resultado = 0;
        try{
            resultado = daoPersona.modificarPersona(persona);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "insertarAdministrativo")
    public int insertarAdministrativo(@WebParam(name = "admin") Administrativo admin) {
        int resultado = 0;
        try{
            resultado = daoAdministrativo.insertar(admin);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarAdministrativosPorCodigoNombre")
    public ArrayList<Administrativo> listarAdministrativosPorCodigoNombre(@WebParam(name = "codigoNombre") String codigoNombre) {
        ArrayList<Administrativo> administrativos = new ArrayList<>();
        try{
            administrativos = daoAdministrativo.listarPorCodigoNombre(codigoNombre);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return administrativos;
    }
    
    @WebMethod(operationName = "buscarAdministrativoPorID")
    public Administrativo buscarAdministrativoPorID(@WebParam(name = "idAdmin") int idAdmin) {
        Administrativo administrativo = null;
        try{
            administrativo = daoAdministrativo.buscarAdministrativoPorID(idAdmin);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return administrativo;
    }
    
    @WebMethod(operationName = "modificarAdministrativo")
    public int modificarAdministrativo(@WebParam(name = "admin") Administrativo admin) {
        int resultado = 0;
        try{
            resultado = daoAdministrativo.modificar(admin);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarAdministrativo")
    public int eliminarAdministrativo(@WebParam(name = "idAdministrativo") int idAdministrativo) {
        int resultado = 0;
        try{
            resultado = daoAdministrativo.eliminar(idAdministrativo);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "insertarPsicologo")
    public int insertarPsicologo(@WebParam(name = "psicologo") Psicologo psicologo) {
        int resultado = 0;
        try{
            resultado = daoPsicologo.insertar(psicologo);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarPsicologosPorCodigoNombre")
    public ArrayList<Psicologo> listarPsicologosPorCodigoNombre(@WebParam(name = "codigoNombre") String codigoNombre) {
        ArrayList<Psicologo> psicologos = new ArrayList<>();
        try{
            psicologos = daoPsicologo.listarPorCodigoNombre(codigoNombre);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return psicologos;
    }
    
    @WebMethod(operationName = "buscarPsicologoPorID")
    public Psicologo buscarPsicologoPorID(@WebParam(name = "idPsicologo") int idPsicologo) {
        Psicologo psicologo = null;
        try{
            psicologo = daoPsicologo.buscarPsicologoPorID(idPsicologo);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return psicologo;
    }
    
    @WebMethod(operationName = "modificarPsicologo")
    public int modificarPsicologo(@WebParam(name = "psicologo") Psicologo psicologo) {
        int resultado = 0;
        try{
            resultado = daoPsicologo.modificar(psicologo);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarPsicologo")
    public int eliminarPsicologo(@WebParam(name = "idPsicologo") int idPsicologo) {
        int resultado = 0;
        try{
            resultado = daoPsicologo.eliminar(idPsicologo);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "insertarTutor")
    public int insertarTutor(@WebParam(name = "tutor") Tutor tutor) {
        int resultado = 0;
        try{
            resultado = daoTutor.insertar(tutor);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "buscarTutorPorID")
    public Tutor buscarTutorPorID(@WebParam(name = "idTutor") int idTutor) {
        Tutor tutor = null;
        try{
            tutor = daoTutor.buscarTutorPorID(idTutor);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return tutor;
    }
    
    @WebMethod(operationName = "listarTutoresPorCodigoNombre")
    public ArrayList<Tutor> listarTutoresPorCodigoNombre(@WebParam(name = "codigoNombre") String codigoNombre) {
        ArrayList<Tutor> tutores = new ArrayList<>();
        try{
            tutores = daoTutor.listarPorCodigoONombre(codigoNombre);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return tutores;
    }
    
    @WebMethod(operationName = "modificarTutor")
    public int modificarTutor(@WebParam(name = "tutor") Tutor tutor) {
        int resultado = 0;
        try{
            resultado = daoTutor.modificar(tutor);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarTutor")
    public int eliminarTutor(@WebParam(name = "idTutor") int idTutor) {
        int resultado = 0;
        try{
            resultado = daoTutor.eliminar(idTutor);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "insertarAlumno")
    public int insertarAlumno(@WebParam(name = "alumno") Alumno alumno) {
        int resultado = 0;
        try{
            resultado = daoAlumno.insertar(alumno);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarAlumnosPorCodigoONombre")
    public ArrayList<Alumno> listarAlumnosPorCodigoONombre(@WebParam(name = "codigoNombre") String codigoNombre) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        try{
            alumnos = daoAlumno.listarCompletoPorCodigoONombre(codigoNombre);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return alumnos;
    }
    
    @WebMethod(operationName = "buscarAlumnoPorID")
    public Alumno buscarAlumnoPorID(@WebParam(name = "idAlumno") int idAlumno) {
        Alumno alumno = null;
        try{
            alumno = daoAlumno.buscarAlumnoPorID(idAlumno);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return alumno;
    }
    
    @WebMethod(operationName = "modificarDatosPersonales")
    public int modificarDatosPersonales(@WebParam(name = "alumno") Alumno alumno) {
        int resultado = 0;
        try{
            resultado = daoAlumno.modificarDatosPersonales(alumno);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "modificarCiclosCursosNotas")
    public int modificarCiclosCursosNotas(@WebParam(name = "alumno") Alumno alumno) {
        int resultado = 0;
        try{
            resultado = daoAlumno.modificarCiclosCursosNotas(alumno);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarAlumno")
    public int eliminarAlumno(@WebParam(name = "idAlumno") int idAlumno) {
        int resultado = 0;
        try{
            resultado = daoAlumno.eliminar(idAlumno);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "insertarAsignadosCiclo")
    public int insertarAsignadosCiclo(@WebParam(name = "asignados") AsignadosCiclo asignados) {
        int resultado = 0;
        try{
            resultado = daoAsignados.insertarAsignadosCiclo(asignados);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarAsignadosPorIDTutor")
    public ArrayList<AsignadosCiclo> listarAsignadosPorIDTutor(@WebParam(name = "codigoNombre") int idTutor) {
        ArrayList<AsignadosCiclo> asignados = new ArrayList<>();
        try{
            asignados = daoAsignados.listarPorIDTutor(idTutor);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return asignados;
    }
    
    @WebMethod(operationName = "modificarAsignadosCiclo")
    public int modificarAsignadosCiclo(@WebParam(name = "asignados") AsignadosCiclo asignados) {
        int resultado = 0;
        try{
            resultado = daoAsignados.modificar(asignados);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarAsignadosCiclo")
    public int eliminarAsignadosCiclo(@WebParam(name = "idAsignadosCiclo") int idAsignadosCiclo) {
        int resultado = 0;
        try{
            resultado = daoAsignados.eliminar(idAsignadosCiclo);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

}
