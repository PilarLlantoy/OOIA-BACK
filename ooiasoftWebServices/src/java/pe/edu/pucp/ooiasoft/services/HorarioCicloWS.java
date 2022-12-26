/* 
 * Archivo: HorarioCicloWS.java
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
import pe.edu.pucp.ooiasoft.organization.dao.HorarioCicloDAO;
import pe.edu.pucp.ooiasoft.organization.model.HorarioCiclo;
import pe.edu.pucp.ooiasoft.organization.mysql.HorarioCicloMySQL;

@WebService(serviceName = "HorarioCicloWS")
public class HorarioCicloWS {

    private HorarioCicloDAO daoHorario;
    
    public HorarioCicloWS(){
        daoHorario = new HorarioCicloMySQL();
    }
    
    @WebMethod(operationName = "insertarHorarioCiclo")
    public int insertarHorarioCiclo(@WebParam(name = "horario") HorarioCiclo horario) {
        int resultado = 0;
        try{       
            resultado = daoHorario.insertar(horario);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarHorarioCiclos")
    public ArrayList<HorarioCiclo> listarHorarioCiclos(
    @WebParam(name = "idPersonalCitas") int idPersonalCitas) {
        ArrayList<HorarioCiclo> horarios = new ArrayList<>();
        try{       
            horarios = daoHorario.listarPorPersonalCitas(idPersonalCitas);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return horarios;
    }
    
    @WebMethod(operationName = "modificarHorarioCiclo")
    public int modificarHorarioCiclo(@WebParam(name = "horario") HorarioCiclo horario) {
        int resultado = 0;
        try{       
            resultado = daoHorario.modificar(horario);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarHorarioCiclo")
    public int eliminarHorarioCiclo(@WebParam(name = "idHorarioCiclo") int idHorarioCiclo) {
        int resultado = 0;
        try{       
            resultado = daoHorario.eliminar(idHorarioCiclo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
}
