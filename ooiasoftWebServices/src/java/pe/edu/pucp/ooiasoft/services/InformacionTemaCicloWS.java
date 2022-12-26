/* 
 * Archivo: InformacionTemaCicloWS.java
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
import pe.edu.pucp.ooiasoft.information.dao.TemaDAO;
import pe.edu.pucp.ooiasoft.information.model.InformacionTemaCiclo;
import pe.edu.pucp.ooiasoft.information.model.Tema;
import pe.edu.pucp.ooiasoft.information.dao.InformacionTemaCicloDAO;
import pe.edu.pucp.ooiasoft.information.mysql.InformacionTemaCicloMySQL;
import pe.edu.pucp.ooiasoft.information.mysql.TemaMySQL;

@WebService(serviceName = "InformacionTemaCicloWS")
public class InformacionTemaCicloWS {

    private TemaDAO daoTema;
    private InformacionTemaCicloDAO daoInfo;
    
    public InformacionTemaCicloWS(){
        daoTema = new TemaMySQL();
        daoInfo = new InformacionTemaCicloMySQL();
    }
    
    @WebMethod(operationName = "insertarTema")
    public int insertarTema(@WebParam(name = "tema") Tema tema) {
        int resultado = 0;
        try{       
            resultado = daoTema.insertar(tema);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarTemas")
    public ArrayList<Tema> listarTemas() {
        ArrayList<Tema> temas = new ArrayList<>();
        try{       
            temas = daoTema.listar();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return temas;
    }
    
    @WebMethod(operationName = "modificarTema")
    public int modificarTema(@WebParam(name = "tema") Tema tema) {
        int resultado = 0;
        try{       
            resultado = daoTema.modificar(tema);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarTema")
    public int eliminarTema(@WebParam(name = "idTema") int idTema) {
        int resultado = 0;
        try{       
            resultado = daoTema.eliminar(idTema);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
       
    @WebMethod(operationName = "insertarInformacionTemaCiclo")
    public int insertarInformacionTemaCiclo(@WebParam(name = "infoTemaCiclo") InformacionTemaCiclo infoTemaCiclo) {
        int resultado = 0;
        try{       
            resultado = daoInfo.insertar(infoTemaCiclo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarInformacionesTemaCiclo")
    public ArrayList<InformacionTemaCiclo> listarInformacionesTemaCiclo(@WebParam(name = "idCiclo") int idCiclo,@WebParam(name = "idTema") int idTema) {
        ArrayList<InformacionTemaCiclo> infos = new ArrayList<>();
        try{       
            infos = daoInfo.listarPorCicloTema(idCiclo, idTema);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return infos;
    }
    
    @WebMethod(operationName = "modificarInformacionTemaCiclo")
    public int modificarInformacionTemaCiclo(@WebParam(name = "infoTemaCiclo") InformacionTemaCiclo infoTemaCiclo) {
        int resultado = 0;
        try{       
            resultado = daoInfo.modificar(infoTemaCiclo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarInformacionTemaCiclo")
    public int eliminarInformacionTemaCiclo(@WebParam(name = "idInfoTemaCiclo") int idInfoTemaCiclo) {
        int resultado = 0;
        try{       
            resultado = daoInfo.eliminar(idInfoTemaCiclo);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
}
