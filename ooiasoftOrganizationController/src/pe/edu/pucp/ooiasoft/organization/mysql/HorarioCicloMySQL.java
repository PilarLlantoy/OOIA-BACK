/* 
 * Archivo: HorarioCicloMySQL.java
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
import pe.edu.pucp.ooiasoft.information.model.Ciclo;
import pe.edu.pucp.ooiasoft.organization.config.DBManager;
import pe.edu.pucp.ooiasoft.organization.dao.HorarioCicloDAO;
import pe.edu.pucp.ooiasoft.organization.model.DetalleHorario;
import pe.edu.pucp.ooiasoft.organization.model.HorarioCiclo;
import pe.edu.pucp.ooiasoft.organization.model.PersonalCitas;
import pe.edu.pucp.ooiasoft.organization.model.Psicologo;
import pe.edu.pucp.ooiasoft.organization.model.Tutor;


public class HorarioCicloMySQL implements HorarioCicloDAO{
    
    //Atributos necesarios para establecer la conexion en cada operacion
    Connection con;
    ResultSet rs;
    CallableStatement cs;

    @Override
    public int insertar(HorarioCiclo horario) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call INSERTAR_HORARIO_CICLO(?,?,?,?,?) }");

            //Setear parametros
            cs.registerOutParameter("_id_horario_ciclo", java.sql.Types.INTEGER);
            cs.setInt("_fid_personal_citas", horario.getPersonal().getIdPersona());
            cs.setInt("_fid_ciclo", horario.getCiclo().getIdCiclo());
            cs.setInt("_horas_disponibles", horario.getHorasDisponible());
            cs.setInt("_horas_atendidas", horario.getHorasAtendidas());            
            
            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            horario.setIdHorarioCiclo(cs.getInt("_id_horario_ciclo"));
            
            //Insertar su detalle de horario
            insertarDetalleHorario(horario);
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = horario.getIdHorarioCiclo();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    private void insertarDetalleHorario(HorarioCiclo horario) throws Exception{
        for(DetalleHorario dh : horario.getHorario()){
            //Preparar llamado
            cs = con.prepareCall("{ call INSERTAR_DETALLE_HORARIO(?,?,?,?,?)}");
            cs.registerOutParameter("_id_detalle_horario", java.sql.Types.INTEGER);
            cs.setInt("_fid_horario_ciclo", horario.getIdHorarioCiclo());
            cs.setInt("_dia", dh.getDia());
            cs.setInt("_hora_inicio", dh.getHoraInicio());
            cs.setInt("_hora_fin", dh.getHoraFin());
            
            //Ejecutar llamado
            cs.executeUpdate();
            
            //Recuperar id
            dh.setIdDetalle(cs.getInt("_id_detalle_horario"));
        }
    }
    
    @Override
    public ArrayList<HorarioCiclo> listarPorPersonalCitas(int idPersonalCitas) {
        ArrayList<HorarioCiclo> horarios = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call LISTAR_HORARIOS_CICLOS_X_ID_PERSONAL_CITAS(?) }");

            //Setear parametros de entrada y salida			
            cs.setInt("_id_personal_citas", idPersonalCitas);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo	
            while(rs.next()){
                HorarioCiclo hC = llenarHorarioCiclo(rs);
             
                //Llenar su detalle
                llenarDetalleHorarioCiclo(hC);
                
                //Agregarlo al arreglo
                horarios.add(hC);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return horarios;
    }

    private HorarioCiclo llenarHorarioCiclo(ResultSet rsHC) throws Exception{
        HorarioCiclo hC = new HorarioCiclo();
        
        //Llenar
        hC.setIdHorarioCiclo(rsHC.getInt("id_horario_ciclo"));
        hC.setHorasDisponible(rsHC.getInt("horas_disponibles"));
        hC.setHorasAtendidas(rsHC.getInt("horas_atendidas"));
        hC.setEstado(true);
        
        //Llenar el personal citas
        PersonalCitas pC;
        char tipo = rsHC.getString("tipo").charAt(0);
        if(tipo == 'T') pC = new Tutor();
        else pC = new Psicologo();
        
        pC.setIdPersona(rsHC.getInt("id_persona"));
        pC.setCorreo(rsHC.getString("codigo_pucp"));
        pC.setDNI(rsHC.getString("dni"));
        pC.setNombreCompleto(rsHC.getString("nombre_completo"));
        pC.setTelefono(rsHC.getString("telefono"));
        pC.setCorreo(rsHC.getString("correo"));
        pC.setFoto(rsHC.getBytes("foto"));
        pC.setEstado(true);
        //Asociarlo
        hC.setPersonal(pC);
        
        //Llenar el ciclo
        Ciclo ciclo = new Ciclo();
        ciclo.setIdCiclo(rsHC.getInt("id_ciclo"));
        ciclo.setAnho(rsHC.getInt("anho"));
        ciclo.setPeriodo(rsHC.getInt("periodo"));
        ciclo.setEstado(true);
        //Asociarlo
        hC.setCiclo(ciclo);
                
        //Retornar
        return hC;
    }
    
    private void llenarDetalleHorarioCiclo(HorarioCiclo hC) throws Exception{
        //Prepara llamada
        cs = con.prepareCall("{ call LISTAR_DETALLE_HORARIO_X_HORARIO_CICLO(?) }");
        cs.setInt("_id_horario_ciclo", hC.getIdHorarioCiclo());
        
        //Ejecutar llamada
        ResultSet rsDHC = cs.executeQuery();
        
        //Llenar detalle
        while(rsDHC.next()){
            DetalleHorario dh = new DetalleHorario();
            dh.setIdDetalle(rsDHC.getInt("id_detalle_horario"));
            dh.setDia(rsDHC.getInt("dia"));
            dh.setHoraInicio(rsDHC.getInt("hora_inicio"));
            dh.setHoraFin(rsDHC.getInt("hora_fin"));
            dh.setEstado(true);
            
            //Agregarlo al arreglo
            hC.agregarDetalle(dh);
        }
        
        //Cerrar recurso
        rsDHC.close();
    }
    
    @Override
    public int modificar(HorarioCiclo horario) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call MODIFICAR_HORARIO_CICLO(?,?,?,?,?) }");

            //Setear parametros
            cs.setInt("_id_horario_ciclo", horario.getIdHorarioCiclo());
            cs.setInt("_fid_personal_citas", horario.getPersonal().getIdPersona());
            cs.setInt("_fid_ciclo", horario.getCiclo().getIdCiclo());
            cs.setInt("_horas_disponibles", horario.getHorasDisponible());
            cs.setInt("_horas_atendidas", horario.getHorasAtendidas());            
            
            //Ejecutar operacion
            cs.executeUpdate();
            
            //Eliminar su detalle anterior para luego insertar el detalle nuevo
            cs = con.prepareCall("{ call ELIMINAR_DETALLES_HORARIO_X_HORARIO_CICLO(?) }");
            cs.setInt("_id_horario_ciclo", horario.getIdHorarioCiclo());
            cs.executeUpdate();
            
            //Insertar su detalle de horario actualizado
            insertarDetalleHorario(horario);
            
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
    public int eliminar(int idHorarioCiclo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call ELIMINAR_HORARIO_CICLO(?) }");

            //Setear parametros
            cs.setInt("_id_horario_ciclo", idHorarioCiclo);

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
