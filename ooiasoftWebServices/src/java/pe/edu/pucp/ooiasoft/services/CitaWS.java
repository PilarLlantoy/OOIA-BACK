/* 
 * Archivo: CitaWS.java
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
import java.util.Date;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import pe.edu.pucp.ooiasoft.event.dao.CitaDAO;
import pe.edu.pucp.ooiasoft.event.dao.MotivoCitaDAO;
import pe.edu.pucp.ooiasoft.event.model.Cita;
import pe.edu.pucp.ooiasoft.event.model.MotivoCita;
import pe.edu.pucp.ooiasoft.event.mysql.CitaMySQL;
import pe.edu.pucp.ooiasoft.event.mysql.MotivoCitaMySQL;

@WebService(serviceName = "CitaWS")
public class CitaWS {

    private MotivoCitaDAO daoMotivo;
    private CitaDAO daoCita;
    
    public CitaWS(){
        daoMotivo = new MotivoCitaMySQL();
        daoCita = new CitaMySQL();
    }
    
    @WebMethod(operationName = "insertarMotivoCita")
    public int insertarMotivoCita(@WebParam(name = "motivoCita") MotivoCita motivoCita) {
        int resultado = 0;
        try{       
            resultado = daoMotivo.insertar(motivoCita);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarMotivosCita")
    public ArrayList<MotivoCita> listarMotivosCita() {
        ArrayList<MotivoCita> motivos = new ArrayList<>();
        try{       
            motivos = daoMotivo.listar();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return motivos;
    }
    
    @WebMethod(operationName = "modificarMotivoCita")
    public int modificarMotivoCita(@WebParam(name = "motivoCita") MotivoCita motivoCita) {
        int resultado = 0;
        try{       
            resultado = daoMotivo.modificar(motivoCita);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarMotivoCita")
    public int eliminarMotivoCita(@WebParam(name = "idMotivoCita") int idMotivoCita) {
        int resultado = 0;
        try{       
            resultado = daoMotivo.eliminar(idMotivoCita);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "insertarCita")
    public int insertarCita(@WebParam(name = "cita") Cita cita) {
        int resultado = 0;
        try{       
            resultado = daoCita.insertar(cita);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarCitasDeAlumnoPorCicloFechaPersonal")
    public ArrayList<Cita> listarCitasDeAlumnoPorCicloFechaPersonal(
    @WebParam(name = "idAlumno") int idAlumno, @WebParam(name = "ciclo") String ciclo,
    @WebParam(name = "fechaIni") Date fechaIni, @WebParam(name = "fechaFin") Date fechaFin,
    @WebParam(name = "nombrePersonal") String nombrePersonal) {
        ArrayList<Cita> citas = new ArrayList<>();
        try{       
            citas = daoCita.listarCitasDeAlumnoPorCicloFechaPersonal(idAlumno, ciclo, fechaIni, fechaFin, nombrePersonal);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return citas;
    }
    
    @WebMethod(operationName = "listarCitasDePersonalPorCicloFechaAlumno")
    public ArrayList<Cita> listarCitasDePersonalPorCicloFechaAlumno(
    @WebParam(name = "idPersonal") int idPersonal, @WebParam(name = "ciclo") String ciclo,
    @WebParam(name = "fechaIni") Date fechaIni, @WebParam(name = "fechaFin") Date fechaFin,
    @WebParam(name = "nombreAlumno") String nombreAlumno) {
        ArrayList<Cita> citas = new ArrayList<>();
        try{       
            citas = daoCita.listarCitasDePersonalPorCicloFechaAlumno(idPersonal, ciclo, fechaIni, fechaFin, nombreAlumno);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return citas;
    }
    
    @WebMethod(operationName = "modificarCita")
    public int modificarCita(@WebParam(name = "cita") Cita cita) {
        int resultado = 0;
        try{       
            resultado = daoCita.modificar(cita);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarCita")
    public int eliminarCita(@WebParam(name = "idCita") int idCita) {
        int resultado = 0;
        try{       
            resultado = daoCita.eliminar(idCita);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

}
