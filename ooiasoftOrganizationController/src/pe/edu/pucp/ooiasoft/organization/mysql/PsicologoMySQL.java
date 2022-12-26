/* 
 * Archivo: PsicologoMySQL.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.organization.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.organization.config.DBManager;
import pe.edu.pucp.ooiasoft.organization.dao.PsicologoDAO;
import pe.edu.pucp.ooiasoft.organization.model.Psicologo;

public class PsicologoMySQL implements PsicologoDAO{
    
    //Atributos necesarios para establecer la conexion en cada operacion
    Connection con;
    ResultSet rs;
    CallableStatement cs;

    @Override
    public int insertar(Psicologo psicologo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call INSERTAR_PSICOLOGO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            //Setear parametros
            cs.registerOutParameter("_fid_persona", java.sql.Types.INTEGER);
            cs.setString("_codigo_pucp", psicologo.getCodigoPUCP());
            cs.setString("_dni",psicologo.getDNI());
            cs.setString("_nombre_completo",psicologo.getNombreCompleto());
            cs.setDate("_fecha_nacimiento",new java.sql.Date(psicologo.getFechaNacimiento().getTime()));
            cs.setInt("_edad",psicologo.getEdad());
            cs.setString("_sexo",String.valueOf(psicologo.getSexo()));
            cs.setString("_telefono",psicologo.getTelefono());
            cs.setString("_correo",psicologo.getCorreo());
            cs.setString("_usuario",psicologo.getUsuario());
            cs.setString("_password",psicologo.getPassword());
            cs.setBytes("_foto",psicologo.getFoto());
            cs.setInt("_horas_semanales", psicologo.getHorasSemanales());
            cs.setDouble("_rendimiento", psicologo.getRendimiento());
            cs.setString("_colegiatura", psicologo.getColegiatura());

            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            psicologo.setIdPersona(cs.getInt("_fid_persona"));
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = psicologo.getIdPersona();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Psicologo> listarPorCodigoNombre(String codigoNombre) {
        ArrayList<Psicologo> psicologos = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call LISTAR_PSICOLOGOS_X_CODIGO_NOMBRE(?) }");

            //Setear parametros de entrada y salida			
            cs.setString("_codigo_nombre", codigoNombre);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                Psicologo psicologo = llenarPsicologo(rs);
             
                //Agregarlo al arreglo
                psicologos.add(psicologo);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return psicologos;
    }

    private Psicologo llenarPsicologo(ResultSet rsPsic) throws Exception{
        Psicologo psic = new Psicologo();
        
        //Llenar
        psic.setIdPersona(rsPsic.getInt("id_persona"));
        psic.setCodigoPUCP(rsPsic.getString("codigo_pucp"));
        psic.setDNI(rsPsic.getString("dni"));
        psic.setNombreCompleto(rsPsic.getString("nombre_completo"));
        psic.setFechaNacimiento(rsPsic.getDate("fecha_nacimiento"));
        psic.setEdad(rsPsic.getInt("edad"));
        psic.setSexo(rsPsic.getString("sexo").charAt(0));
        psic.setTelefono(rsPsic.getString("telefono"));
        psic.setCorreo(rsPsic.getString("correo"));
        psic.setEstado(true);
        psic.setUsuario(rsPsic.getString("usuario"));
        psic.setPassword(rsPsic.getString("password"));
        psic.setFoto(rsPsic.getBytes("foto"));
        psic.setHorasSemanales(rsPsic.getInt("horas_semanales"));
        psic.setRendimiento(rsPsic.getDouble("rendimiento"));
        psic.setColegiatura(rsPsic.getString("colegiatura"));
        
        //Retornar
        return psic;
    }
    
    @Override
    public int modificar(Psicologo psicologo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call MODIFICAR_PSICOLOGO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            //Setear parametros
            cs.setInt("_fid_persona", psicologo.getIdPersona());
            cs.setString("_codigo_pucp", psicologo.getCodigoPUCP());
            cs.setString("_dni",psicologo.getDNI());
            cs.setString("_nombre_completo",psicologo.getNombreCompleto());
            cs.setDate("_fecha_nacimiento",new java.sql.Date(psicologo.getFechaNacimiento().getTime()));
            cs.setInt("_edad",psicologo.getEdad());
            cs.setString("_sexo",String.valueOf(psicologo.getSexo()));
            cs.setString("_telefono",psicologo.getTelefono());
            cs.setString("_correo",psicologo.getCorreo());
            cs.setString("_usuario",psicologo.getUsuario());
            cs.setString("_password",psicologo.getPassword());
            cs.setBytes("_foto",psicologo.getFoto());
            cs.setInt("_horas_semanales", psicologo.getHorasSemanales());
            cs.setDouble("_rendimiento", psicologo.getRendimiento());
            cs.setString("_colegiatura", psicologo.getColegiatura());

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
    public int eliminar(int idPsicologo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call ELIMINAR_PSICOLOGO(?) }");

            //Setear parametros
            cs.setInt("_fid_persona", idPsicologo);

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
    public Psicologo buscarPsicologoPorID(int idPsicologo) {
        Psicologo psicologo = null;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call BUSCAR_PSICOLOGO_X_ID(?) }");

            //Setear parametros de entrada y salida			
            cs.setInt("_fid_persona", idPsicologo);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            if(rs.next())
                psicologo = llenarPsicologo(rs);

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return psicologo;
    }
    
}
