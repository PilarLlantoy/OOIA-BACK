/* 
 * Archivo: EventoCicloWS.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   04/07/2021
 */


package pe.edu.pucp.ooiasoft.services;

import java.util.ArrayList;
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import pe.edu.pucp.ooiasoft.event.dao.EventoCicloDAO;
import pe.edu.pucp.ooiasoft.event.model.EventoCiclo;
import pe.edu.pucp.ooiasoft.event.mysql.EventoCicloMySQL;

@WebService(serviceName = "EventoCicloWS")
public class EventoCicloWS {

    private EventoCicloDAO daoEventoCiclo;
    
    public EventoCicloWS(){
        daoEventoCiclo = new EventoCicloMySQL();
    }
        
    @WebMethod(operationName = "insertarEventoCiclo")
    public int insertarEventoCiclo(@WebParam(name = "eventoCiclo") EventoCiclo eventoCiclo) {
        int resultado = 0;
        try{       
            resultado = daoEventoCiclo.insertar(eventoCiclo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarEventoCiclosNoInscritosParaAlumno")
    public ArrayList<EventoCiclo> listarEventoCiclosNoInscritosParaAlumno(
    @WebParam(name = "idAlumno") int idAlumno, @WebParam(name = "idCiclo") int idCiclo, @WebParam(name = "nombre") String nombre,
    @WebParam(name = "fechaIni") Date fechaIni, @WebParam(name = "fechaFin") Date fechaFin) {
        ArrayList<EventoCiclo> eventos = new ArrayList<>();
        try{       
            eventos = daoEventoCiclo.listarPorAlumnoCicloNoInscrito(idAlumno,idCiclo,nombre,fechaIni,fechaFin);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return eventos;
    }
    
    @WebMethod(operationName = "listarEventoCiclosInscritosParaAlumno")
    public ArrayList<EventoCiclo> listarEventoCiclosInscritosParaAlumno(
    @WebParam(name = "idAlumno") int idAlumno, @WebParam(name = "ciclo") String ciclo, @WebParam(name = "nombre") String nombre,
    @WebParam(name = "fechaIni") Date fechaIni, @WebParam(name = "fechaFin") Date fechaFin, @WebParam(name = "estado") String estado) {
        ArrayList<EventoCiclo> eventos = new ArrayList<>();
        try{       
            eventos = daoEventoCiclo.listarPorAlumnoCicloNombreFechaEstado(idAlumno,ciclo,nombre,fechaIni,fechaFin,estado);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return eventos;
    }
    
    @WebMethod(operationName = "listarEventoCiclosParaAdministrativo")
    public ArrayList<EventoCiclo> listarEventoCiclosParaAdministrativo(
    @WebParam(name = "ciclo") String ciclo, @WebParam(name = "nombre") String nombre,
    @WebParam(name = "fechaIni") Date fechaIni, @WebParam(name = "fechaFin") Date fechaFin, @WebParam(name = "estado") String estado) {
        ArrayList<EventoCiclo> eventos = new ArrayList<>();
        try{       
            eventos = daoEventoCiclo.listarTodosPorCicloNombre(ciclo,nombre,fechaIni,fechaFin,estado);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return eventos;
    }
    
    @WebMethod(operationName = "inscribirAlumnoEventoCiclo")
    public int inscribirAlumnoEventoCiclo(@WebParam(name = "idAlumno") int idAlumno,@WebParam(name = "idEventoCiclo") int idEventoCiclo) {
        int resultado = -1;
        try{       
            resultado = daoEventoCiclo.inscribirAlumno(idAlumno,idEventoCiclo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "desinscribirAlumnoEventoCiclo")
    public int desinscribirAlumnoEventoCiclo(@WebParam(name = "idAlumno") int idAlumno,@WebParam(name = "idEventoCiclo") int idEventoCiclo) {
        int resultado = -1;
        try{       
            resultado = daoEventoCiclo.desinscribirAlumno(idAlumno,idEventoCiclo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "modificarEventoCiclo")
    public int modificarEventoCiclo(@WebParam(name = "eventoCiclo") EventoCiclo eventoCiclo) {
        int resultado = 0;
        try{       
            resultado = daoEventoCiclo.modificar(eventoCiclo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarEventoCiclo")
    public int eliminarEventoCiclo(@WebParam(name = "idEventoCiclo") int idEventoCiclo) {
        int resultado = 0;
        try{       
            resultado = daoEventoCiclo.eliminar(idEventoCiclo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
}
