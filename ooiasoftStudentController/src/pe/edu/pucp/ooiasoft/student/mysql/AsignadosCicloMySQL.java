/* 
 * Archivo: AsignadosCicloMySQL.java
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
import pe.edu.pucp.ooiasoft.information.model.Ciclo;
import pe.edu.pucp.ooiasoft.information.model.Especialidad;
import pe.edu.pucp.ooiasoft.organization.model.Tutor;
import pe.edu.pucp.ooiasoft.student.config.DBManager;
import pe.edu.pucp.ooiasoft.student.dao.AsignadosCicloDAO;
import pe.edu.pucp.ooiasoft.student.model.Alumno;
import pe.edu.pucp.ooiasoft.student.model.AlumnoCiclo;
import pe.edu.pucp.ooiasoft.student.model.AlumnoCursoCiclo;
import pe.edu.pucp.ooiasoft.student.model.AsignadosCiclo;
import pe.edu.pucp.ooiasoft.student.model.Curso;
import pe.edu.pucp.ooiasoft.student.model.Evaluacion;
import pe.edu.pucp.ooiasoft.student.model.TipoAlumno;
import pe.edu.pucp.ooiasoft.student.model.TipoECTS;
import pe.edu.pucp.ooiasoft.student.model.TipoEvaluacion;

public class AsignadosCicloMySQL implements AsignadosCicloDAO{
    
    //Atributos necesarios para establecer la conexion en cada operacion
    Connection con;
    ResultSet rs;
    CallableStatement cs;
    
    @Override
    public int insertarAsignadosCiclo(AsignadosCiclo asignados) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call INSERTAR_ASIGNADOS_CICLO(?,?,?,?) }");

            //Setear parametros
            cs.registerOutParameter("_id_asignados_ciclo", java.sql.Types.INTEGER);
            cs.setInt("_fid_tutor", asignados.getTutor().getIdPersona());
            cs.setInt("_fid_ciclo", asignados.getCiclo().getIdCiclo());
            cs.setInt("_cantidad_asignados", asignados.getCantidadAsignados());
       
            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            asignados.setIdAsignadosCiclo(cs.getInt("_id_asignados_ciclo"));
            
            //Insertar su detalle de horario
            insertarDetalleAsignados(asignados);
            
            //Asignar exito en operacion y cerrado de recursos
            resultado = asignados.getIdAsignadosCiclo();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    private void insertarDetalleAsignados(AsignadosCiclo asignados) throws Exception{
        for(Alumno alum : asignados.getAsignados()){
            //Preparar llamada
            cs = con.prepareCall("{ call INSERTAR_ASIGNADOS_CICLO_ALUMNO(?,?,?) }");
            cs.registerOutParameter("_id_asignados_ciclo_alumno", java.sql.Types.INTEGER);
            cs.setInt("_fid_asignados_ciclo", asignados.getIdAsignadosCiclo());
            cs.setInt("_fid_alumno", alum.getIdPersona());
            
            //Ejecutar llamado
            cs.executeUpdate();
        }
    }
    
    @Override
    public ArrayList<AsignadosCiclo> listarPorIDTutor(int idTutor) {
        ArrayList<AsignadosCiclo> asignadosCiclos = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_ASIGNADOS_CICLO_X_TUTOR(?)}");

            //Setear parametros de entrada y salida
            cs.setInt("_id_tutor", idTutor);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo, este listado solo muestra la informacion basica del alumno
            //Sera necesario llamar a otro metodo para llenar sus detalles de notas
            while(rs.next()){
                AsignadosCiclo ac = new AsignadosCiclo();
                ac.setIdAsignadosCiclo(rs.getInt("id_asignados_ciclo"));
                ac.setCantidadAsignados(rs.getInt("cantidad_asignados"));
                ac.setEstado(true);
                
                //Llenar al tutor
                Tutor tutor = new Tutor();
                tutor.setIdPersona(rs.getInt("fid_tutor"));
                //Asociar
                ac.setTutor(tutor);
                
                //Llenar ciclo
                Ciclo ciclo = new Ciclo();
                ciclo.setIdCiclo(rs.getInt("id_ciclo"));
                ciclo.setAnho(rs.getInt("anho"));
                ciclo.setPeriodo(rs.getInt("periodo"));
                ciclo.setEstado(true);
                //Asociar
                ac.setCiclo(ciclo);
                
                //Llenar sus alumnos
                llenarAlumnosAsignados(ac);

                //Agregarlo al arreglo
                asignadosCiclos.add(ac);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return asignadosCiclos;
    }

    private void llenarAlumnosAsignados(AsignadosCiclo ac) throws Exception{
        cs = con.prepareCall("{call LISTAR_ASIGNADOS_CICLO_X_CODIGO_NOMBRE(?,?)}");

        //Setear parametros de entrada y salida
        cs.setInt("_id_asignados_ciclo", ac.getIdAsignadosCiclo());
        cs.setString("_codigo_nombre", "");

        //Ejecutar llamado
        ResultSet rsAlum = cs.executeQuery();

        //Llenar arreglo, este listado solo muestra la informacion basica del alumno
        //Sera necesario llamar a otro metodo para llenar sus detalles de notas
        while(rsAlum.next()){
            Alumno alumno = llenarDatosBasicosAlumno(rsAlum);

            //Llenar sus ciclos
            llenarCiclos(alumno);

            //Agregarlo al arreglo
            ac.agregarAlumno(alumno);
        }

        //Cerrar recursos
        rsAlum.close();
    }
    
    private Alumno llenarDatosBasicosAlumno(ResultSet rsAlum) throws Exception{
        Alumno alum = new Alumno();
        alum.setIdPersona(rsAlum.getInt("id_persona"));
        alum.setCodigoPUCP(rsAlum.getString("codigo_pucp"));
        alum.setDNI(rsAlum.getString("dni"));
        alum.setNombreCompleto(rsAlum.getString("nombre_completo"));
        alum.setFechaNacimiento(rsAlum.getDate("fecha_nacimiento"));
        alum.setEdad(rsAlum.getInt("edad"));
        alum.setSexo(rsAlum.getString("sexo").charAt(0));
        alum.setTelefono(rsAlum.getString("telefono"));
        alum.setCorreo(rsAlum.getString("correo"));
        alum.setEstado(true);
        alum.setUsuario(rsAlum.getString("usuario"));
        alum.setPassword(rsAlum.getString("password"));
        alum.setFoto(rsAlum.getBytes("foto"));
        alum.setEscalaPago(rsAlum.getInt("escala_pago"));
        alum.setCRAEST(rsAlum.getDouble("craest"));
        alum.setPromedioPonderado(rsAlum.getDouble("promedio_ponderado"));
        alum.setCantCiclos(rsAlum.getInt("cant_ciclos"));
        alum.setCantCursosAprobados(rsAlum.getInt("cantidad_cursos_aprobados"));
        alum.setCantidadCreditosAprobados(rsAlum.getDouble("cantidad_creditos_aprobados"));
        alum.setTotalCreditos(rsAlum.getDouble("total_creditos"));
        alum.setTipo(TipoAlumno.valueOf(rsAlum.getString("tipo")));
        
        //Llenar la especialidad
        Especialidad esp = new Especialidad();
        esp.setIdEspecialidad(rsAlum.getInt("fid_especialidad"));
        esp.setNombre(rsAlum.getString("nombre"));
        //Asociar
        alum.setEspecialidad(esp);
        
        //Retornar
        return alum;
    }
    
    public void llenarCiclos(Alumno alumno) throws Exception{
        //Llenar los ciclos para ese alumno
        cs = con.prepareCall("{ call LISTAR_ALUMNO_CICLOS_X_ALUMNO(?) }");
        cs.setInt("_id_alumno", alumno.getIdPersona());
        ResultSet rsAC = cs.executeQuery();
        
        //Llenar
        while(rsAC.next()){
            AlumnoCiclo ac = llenarDatosAlumnoCiclo(rsAC);
            
            //Llenar los cursos para ese ciclo
            llenarCursosCiclo(ac);
            
            //Agregar al arreglo de ciclos
            alumno.agregarCiclo(ac);
        }
        
        //Cerrar el recurso
        rsAC.close();
    }
    private AlumnoCiclo llenarDatosAlumnoCiclo(ResultSet rsAC) throws Exception{
        AlumnoCiclo ac = new AlumnoCiclo();
        
        ac.setIdAlumnoCiclo(rsAC.getInt("id_alumno_ciclo"));
        ac.setCRAEST(rsAC.getDouble("craest"));
        ac.setPromedioPonderado(rsAC.getDouble("promedio_ponderado"));
        ac.setCantidadCursosAprobados(rsAC.getInt("cantidad_cursos_aprobados"));
        ac.setCreditosTotales(rsAC.getDouble("creditos_totales"));
        ac.setEstado(true);
        
        //Llenar el ciclo
        Ciclo ciclo = new Ciclo();
        ciclo.setIdCiclo(rsAC.getInt("id_ciclo"));
        ciclo.setAnho(rsAC.getInt("anho"));
        ciclo.setPeriodo(rsAC.getInt("periodo"));
        //Asociar
        ac.setCiclo(ciclo);
        
        //Retornar
        return ac;
    }
    
    private void llenarCursosCiclo(AlumnoCiclo ac) throws Exception{
        //Preparar llamado
        cs = con.prepareCall("{ call LISTAR_ALUMNO_CURSO_CICLOS_X_ALUMNO_CICLO(?) }");
        cs.setInt("_id_alumno_ciclo", ac.getIdAlumnoCiclo());
     
        //Ejecutar llamado
        ResultSet rsACC = cs.executeQuery();
        
        //Llenar los cursos por ciclo
        while(rsACC.next()){
            AlumnoCursoCiclo acc = llenarDatosAlumnoCursoCiclo(rsACC);
            
            //Llenar las evaluaciones del curso para el ciclo
            llenarEvaluacionesCursoCiclo(acc);
            
            ac.agregarCurso(acc);
        }
        
        //Cerrar el recurso
        rsACC.close();
    }
    
    private AlumnoCursoCiclo llenarDatosAlumnoCursoCiclo(ResultSet rsACC) throws Exception{
        AlumnoCursoCiclo acc = new AlumnoCursoCiclo();
        acc.setIdAlumnoCursoCiclo(rsACC.getInt("id_alumno_curso_ciclo"));
        acc.setNotaFinal(rsACC.getDouble("nota_final"));
        acc.setEscala(TipoECTS.valueOf(rsACC.getString("tipo")));
        acc.setEstado(true);
        
        //Llenar el curso
        Curso curso = new Curso();
        curso.setIdCurso(rsACC.getInt("fid_curso"));
        curso.setCodigo(rsACC.getString("codigo"));
        curso.setNombre(rsACC.getString("nombre"));
        curso.setCreditos(rsACC.getDouble("creditos"));
        curso.setEstado(true);
        //Asociar
        acc.setCurso(curso);
        
        //Retornar 
        return acc;
    }
    
    private void llenarEvaluacionesCursoCiclo(AlumnoCursoCiclo acc) throws Exception{
        //Preparar llamada
        cs = con.prepareCall("{ call LISTAR_EVALUACIONES_X_ALUMNO_CURSO_CICLO(?) }");
        cs.setInt("_id_alumno_curso_ciclo", acc.getIdAlumnoCursoCiclo());
        
        //Ejecutar llamado
        ResultSet rsEv = cs.executeQuery();
        
        //Llenar
        
        while(rsEv.next()){
            Evaluacion ev = llenarDatosEvaluacion(rsEv);
            
            //Agregar al arreglo
            acc.agregarEvaluacion(ev);
        }
        
        //Cerrar el recurso
        rsEv.close();
    }
    
    private Evaluacion llenarDatosEvaluacion(ResultSet rsEv) throws Exception{
        Evaluacion ev = new Evaluacion();
        
        //Llenar
        ev.setIdEvaluacion(rs.getInt("id_evaluacion"));
        ev.setNumEvaluacion(rs.getInt("num_evaluacion"));
        ev.setNota(rs.getInt("nota"));
        ev.setComentarios(rs.getString("comentarios"));
        ev.setTipo(TipoEvaluacion.valueOf(rs.getString("tipo")));
        ev.setEstado(true);
        
        //Retornar
        return ev;
    }
    
    @Override
    public int modificar(AsignadosCiclo asignados) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call MODIFICAR_ASIGNADOS_CICLO(?,?,?,?) }");

            //Setear parametros
            cs.setInt("_id_asignados_ciclo", asignados.getIdAsignadosCiclo());
            cs.setInt("_fid_tutor", asignados.getTutor().getIdPersona());
            cs.setInt("_fid_ciclo", asignados.getCiclo().getIdCiclo());
            cs.setInt("_cantidad_asignados", asignados.getCantidadAsignados());
       
            //Ejecutar operacion
            cs.executeUpdate();

            //Eliminar su detalle de asignados
            cs = con.prepareCall("{ call ELIMINAR_ASIGNADOS_CICLO_ALUMNO(?) }");
            cs.setInt("_id_asignados_ciclo_alumno", asignados.getIdAsignadosCiclo());
                        
            //Insertar su detalle de horario actualizado
            insertarDetalleAsignados(asignados);
            
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
    public int eliminar(int idAsignadosCiclo) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call ELIMINAR_ASIGNADOS_CICLO(?) }");

            //Setear parametros
            cs.setInt("_id_asignados_ciclo", idAsignadosCiclo);
       
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
