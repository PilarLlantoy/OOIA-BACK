/* 
 * Archivo: CicloMySQL.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.information.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.information.config.DBManager;
import pe.edu.pucp.ooiasoft.information.dao.CicloDAO;
import pe.edu.pucp.ooiasoft.information.model.Ciclo;

public class CicloMySQL implements CicloDAO {

    //Atributos necesarios para establecer la conexion en cada operacion
    Connection con;
    ResultSet rs;
    CallableStatement cs;

    @Override
    public int insertar(Ciclo ciclo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_CICLO(?,?,?,?,?)}");

            //Setear parametros
            cs.registerOutParameter("_id_ciclo", java.sql.Types.INTEGER);
            cs.setInt("_anho", ciclo.getAnho());
            cs.setInt("_periodo", ciclo.getPeriodo());
            cs.setDate("_fecha_inicio", new java.sql.Date(ciclo.getFechaInicio().getTime()));
            cs.setDate("_fecha_fin", new java.sql.Date(ciclo.getFechaFin().getTime()));

            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            ciclo.setIdCiclo(cs.getInt("_id_ciclo"));
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = ciclo.getIdCiclo();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Ciclo> listar() {
        ArrayList<Ciclo> ciclos = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_CICLOS()}");
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                Ciclo ciclo = llenarCiclo(rs);
             
                //Agregarlo al arreglo
                ciclos.add(ciclo);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return ciclos;
    }

    private Ciclo llenarCiclo(ResultSet rsCiclo) throws Exception{
        Ciclo ciclo = new Ciclo();
        
        //Llenar
        ciclo.setIdCiclo(rsCiclo.getInt("id_ciclo"));
        ciclo.setAnho(rsCiclo.getInt("anho"));
        ciclo.setPeriodo(rsCiclo.getInt("periodo"));
        ciclo.setFechaInicio(rsCiclo.getDate("fecha_inicio"));
        ciclo.setFechaFin(rsCiclo.getDate("fecha_fin"));
        ciclo.setEstado(true);
        
        //Retornar 
        return ciclo;
    }
    
    @Override
    public int modificar(Ciclo ciclo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call MODIFICAR_CICLO(?,?,?,?,?)}");

            //Setear parametros
            cs.setInt("_id_ciclo", ciclo.getIdCiclo());
            cs.setInt("_anho", ciclo.getAnho());
            cs.setInt("_periodo", ciclo.getPeriodo());
            cs.setDate("_fecha_inicio", new java.sql.Date(ciclo.getFechaInicio().getTime()));
            cs.setDate("_fecha_fin", new java.sql.Date(ciclo.getFechaFin().getTime()));

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
    public int eliminar(int idCiclo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_CICLO(?)}");

            //Setear parametros
            cs.setInt("_id_ciclo", idCiclo);

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