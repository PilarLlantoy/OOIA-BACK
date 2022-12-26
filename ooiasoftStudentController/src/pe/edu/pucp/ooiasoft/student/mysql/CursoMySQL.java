/* 
 * Archivo: CursoMySQL.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.student.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.information.model.Especialidad;
import pe.edu.pucp.ooiasoft.student.config.DBManager;
import pe.edu.pucp.ooiasoft.student.dao.CursoDAO;
import pe.edu.pucp.ooiasoft.student.model.Curso;


public class CursoMySQL implements CursoDAO{
    
    //Atributos necesarios para establecer la conexion en cada operacion
    Connection con;
    CallableStatement cs;
    ResultSet rs;

    @Override
    public int insertar(Curso curso) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call (?,?,?)}");
            
            //Setear parametros
            cs.registerOutParameter("_id_curso", java.sql.Types.INTEGER);
            cs.setInt("_fid_especialidad", curso.getEspecialidad().getIdEspecialidad());
            cs.setString("_codigo", curso.getCodigo());
            cs.setString("_nombre", curso.getNombre());
            cs.setDouble("_creditos", curso.getCreditos());

            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            curso.setIdCurso(cs.getInt("_id_curso"));
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = curso.getIdCurso();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Curso> listarPorCodigoNombre(String codigoNombre) {
        ArrayList<Curso> cursos = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_CURSOS_X_CODIGO_NOMBRE(?)}");

            //Setear parametros de entrada y salida			
            cs.setString("_codigo_nombre", codigoNombre);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                Curso esp = llenarCurso(rs);
             
                //Agregarlo al arreglo
                cursos.add(esp);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return cursos;
    }
    
    private Curso llenarCurso(ResultSet rsCurso) throws Exception{
        Curso curso = new Curso();
        
        //Llenar
        curso.setIdCurso(rsCurso.getInt("id_curso"));
        curso.setCodigo(rsCurso.getString("codigo"));
        curso.setNombre(rsCurso.getString("nombre"));
        curso.setCreditos(rsCurso.getDouble("creditos"));
        curso.setEstado(true);
        
        //Llenar la especialidad
        Especialidad esp = new Especialidad();
        esp.setIdEspecialidad(rsCurso.getInt("fid_especialidad"));
        esp.setNombre(rsCurso.getString("especialidad"));
        esp.setDescripcion(rsCurso.getString("descripcion"));
        esp.setEstado(true);
        //Asociar
        curso.setEspecialidad(esp);
        
        //Retornar 
        return curso;
    }

    @Override
    public int modificar(Curso curso) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call MODIFICAR_CURSO(?,?,?,?,?) }");

            //Setear parametros
            cs.setInt("_id_curso", curso.getIdCurso());
            cs.setInt("_fid_especialidad", curso.getEspecialidad().getIdEspecialidad());
            cs.setString("_codigo", curso.getCodigo());
            cs.setString("_nombre", curso.getNombre());
            cs.setDouble("_creditos", curso.getCreditos());

            //Ejecutar operacion
            cs.executeUpdate();
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = 1;
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idCurso) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call ELIMINAR_CURSO(?) }");

            //Setear parametros
            cs.setInt("_id_curso", idCurso);

            //Ejecutar operacion
            cs.executeUpdate();
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = 1;
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

}
