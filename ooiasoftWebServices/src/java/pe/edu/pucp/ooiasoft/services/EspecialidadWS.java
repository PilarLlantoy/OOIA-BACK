/* 
 * Archivo: EspecialidadWS.java
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
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import pe.edu.pucp.ooiasoft.information.dao.EspecialidadDAO;
import pe.edu.pucp.ooiasoft.information.model.Especialidad;
import pe.edu.pucp.ooiasoft.information.mysql.EspecialidadMySQL;

@WebService(serviceName = "EspecialidadWS")
public class EspecialidadWS {

    private EspecialidadDAO daoEspecialidad;
    
    public EspecialidadWS(){
        daoEspecialidad = new EspecialidadMySQL();
    }
    
    @WebMethod(operationName = "insertarEspecialidad")
    public int insertarEspecialidad(@WebParam(name = "especialidad") Especialidad especialidad) {
        int resultado = 0;
        try{       
            resultado = daoEspecialidad.insertar(especialidad);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarEspecialidadesPorNombre")
    public ArrayList<Especialidad> listarEspecialidadesPorNombre(
    @WebParam(name = "nombre") String nombre) {
        ArrayList<Especialidad> especialidades = new ArrayList<>();
        try{       
            especialidades = daoEspecialidad.listarPorNombre(nombre);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return especialidades;
    }
    
    @WebMethod(operationName = "modificarEspecialidad")
    public int modificarEspecialidad(@WebParam(name = "especialidad") Especialidad especialidad) {
        int resultado = 0;
        try{       
            resultado = daoEspecialidad.modificar(especialidad);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarEspecialidad")
    public int eliminarEspecialidad(@WebParam(name = "idEspecialidad") int idEspecialidad) {
        int resultado = 0;
        try{       
            resultado = daoEspecialidad.eliminar(idEspecialidad);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
}
