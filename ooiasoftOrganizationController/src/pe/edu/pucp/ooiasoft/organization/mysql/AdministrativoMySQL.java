/* 
 * Archivo: AdministrativoMySQL.java
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
import pe.edu.pucp.ooiasoft.organization.dao.AdministrativoDAO;
import pe.edu.pucp.ooiasoft.organization.model.Administrativo;
import pe.edu.pucp.ooiasoft.organization.model.TipoAdministrativo;

public class AdministrativoMySQL implements AdministrativoDAO{
    
    //Atributos necesarios para establecer la conexion en cada operacion
    Connection con;
    ResultSet rs;
    CallableStatement cs;

    @Override
    public int insertar(Administrativo admin) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call INSERTAR_ADMINISTRATIVO(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            //Setear parametros
            cs.registerOutParameter("_fid_persona", java.sql.Types.INTEGER);
            cs.setString("_codigo_pucp", admin.getCodigoPUCP());
            cs.setString("_dni",admin.getDNI());
            cs.setString("_nombre_completo",admin.getNombreCompleto());
            cs.setDate("_fecha_nacimiento",new java.sql.Date(admin.getFechaNacimiento().getTime()));
            cs.setInt("_edad",admin.getEdad());
            cs.setString("_sexo",String.valueOf(admin.getSexo()));
            cs.setString("_telefono",admin.getTelefono());
            cs.setString("_correo",admin.getCorreo());
            cs.setString("_usuario",admin.getUsuario());
            cs.setString("_password",admin.getPassword());
            cs.setBytes("_foto",admin.getFoto());
            cs.setString("_tipo", admin.getTipo().toString());

            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            admin.setIdPersona(cs.getInt("_fid_persona"));
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = admin.getIdPersona();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Administrativo> listarPorCodigoNombre(String codigoNombre) {
        ArrayList<Administrativo> admins = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_ADMINISTRATIVOS_X_CODIGO_NOMBRE(?)}");

            //Setear parametros de entrada y salida			
            cs.setString("_codigo_nombre", codigoNombre);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                Administrativo admin = llenarAdministrativo(rs);
             
                //Agregarlo al arreglo
                admins.add(admin);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return admins;
    }
    
    private Administrativo llenarAdministrativo(ResultSet rsAdmin) throws Exception{
        Administrativo admin = new Administrativo();
        
        //Llenar
        admin.setIdPersona(rsAdmin.getInt("id_persona"));
        admin.setCodigoPUCP(rsAdmin.getString("codigo_pucp"));
        admin.setDNI(rsAdmin.getString("dni"));
        admin.setNombreCompleto(rsAdmin.getString("nombre_completo"));
        admin.setFechaNacimiento(rsAdmin.getDate("fecha_nacimiento"));
        admin.setEdad(rsAdmin.getInt("edad"));
        admin.setSexo(rsAdmin.getString("sexo").charAt(0));
        admin.setTelefono(rsAdmin.getString("telefono"));
        admin.setCorreo(rsAdmin.getString("correo"));
        admin.setEstado(true);
        admin.setUsuario(rsAdmin.getString("usuario"));
        admin.setPassword(rsAdmin.getString("password"));
        admin.setFoto(rsAdmin.getBytes("foto"));
        admin.setTipo(TipoAdministrativo.valueOf(rs.getString("tipo_admin")));
        
        //Retornar
        return admin;
    }
    
    @Override
    public int modificar(Administrativo admin) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call MODIFICAR_ADMINISTRATIVO(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            //Setear parametros
            cs.setInt("_fid_persona", admin.getIdPersona());
            cs.setString("_codigo_pucp", admin.getCodigoPUCP());
            cs.setString("_dni",admin.getDNI());
            cs.setString("_nombre_completo",admin.getNombreCompleto());
            cs.setDate("_fecha_nacimiento",new java.sql.Date(admin.getFechaNacimiento().getTime()));
            cs.setInt("_edad",admin.getEdad());
            cs.setString("_sexo",String.valueOf(admin.getSexo()));
            cs.setString("_telefono",admin.getTelefono());
            cs.setString("_correo",admin.getCorreo());
            cs.setString("_usuario",admin.getUsuario());
            cs.setString("_password",admin.getPassword());
            cs.setBytes("_foto",admin.getFoto());
            cs.setString("_tipo", admin.getTipo().toString());

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
    public int eliminar(int idAdmin) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_ADMINISTRATIVO(?)}");

            //Setear parametros
            cs.setInt("_fid_persona", idAdmin);

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
    public Administrativo buscarAdministrativoPorID(int idAdmin) {
        Administrativo admin = null;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call BUSCAR_ADMINISTRATIVO_POR_ID(?)}");

            //Setear parametros de entrada y salida			
            cs.setInt("_fid_persona", idAdmin);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            if(rs.next())
                admin = llenarAdministrativo(rs);

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return admin;
    }
    
}
