/* 
 * Archivo: CicloWS.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.services;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import pe.edu.pucp.ooiasoft.information.dao.CicloDAO;
import pe.edu.pucp.ooiasoft.information.model.Ciclo;
import pe.edu.pucp.ooiasoft.information.mysql.CicloMySQL;

@WebService(serviceName = "CicloWS")
public class CicloWS {
    
    CicloDAO daoCiclo;
    
    public CicloWS(){
        daoCiclo = new CicloMySQL();
    }
    
    @WebMethod(operationName = "insertarCiclo")
    public int insertarCiclo(@WebParam(name = "ciclo") Ciclo ciclo) {
        int resultado = 0;
        try{       
            resultado = daoCiclo.insertar(ciclo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarCiclos")
    public ArrayList<Ciclo> listarCiclos() {
        ArrayList<Ciclo> ciclos = new ArrayList<>();
        try{       
            ciclos = daoCiclo.listar();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return ciclos;
    }
    
    @WebMethod(operationName = "modificarCiclo")
    public int modificarCiclo(@WebParam(name = "ciclo") Ciclo ciclo) {
        int resultado = 0;
        try{       
            resultado = daoCiclo.modificar(ciclo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarCiclo")
    public int eliminarCiclo(@WebParam(name = "idCiclo") int idCiclo) {
        int resultado = 0;
        try{       
            resultado = daoCiclo.eliminar(idCiclo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
       
}
