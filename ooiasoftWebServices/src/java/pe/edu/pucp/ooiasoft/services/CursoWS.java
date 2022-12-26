/* 
 * Archivo: CursoWS.java
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
import pe.edu.pucp.ooiasoft.student.dao.CursoDAO;
import pe.edu.pucp.ooiasoft.student.model.Curso;
import pe.edu.pucp.ooiasoft.student.mysql.CursoMySQL;

@WebService(serviceName = "CursoWS")
public class CursoWS {

    private CursoDAO daoCurso;
    
    public CursoWS(){
        daoCurso = new CursoMySQL();
    }
    
    @WebMethod(operationName = "insertarCurso")
    public int insertarCurso(@WebParam(name = "curso") Curso curso) {
        int resultado = 0;
        try{       
            resultado = daoCurso.insertar(curso);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarCursosPorCodigoNombre")
    public ArrayList<Curso> listarCursosPorCodigoNombre(
    @WebParam(name = "codigoNombre") String codigoNombre) {
        ArrayList<Curso> cursos = new ArrayList<>();
        try{       
            cursos = daoCurso.listarPorCodigoNombre(codigoNombre);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return cursos;
    }
    
    @WebMethod(operationName = "modificarCurso")
    public int modificarCurso(@WebParam(name = "curso") Curso curso) {
        int resultado = 0;
        try{       
            resultado = daoCurso.modificar(curso);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarCurso")
    public int eliminarCurso(@WebParam(name = "idCurso") int idCurso) {
        int resultado = 0;
        try{       
            resultado = daoCurso.eliminar(idCurso);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
}
