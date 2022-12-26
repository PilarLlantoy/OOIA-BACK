/* 
 * Archivo: AlumnoMySQL.java
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
import pe.edu.pucp.ooiasoft.student.config.DBManager;
import pe.edu.pucp.ooiasoft.student.dao.AlumnoDAO;
import pe.edu.pucp.ooiasoft.student.model.Alumno;
import pe.edu.pucp.ooiasoft.student.model.AlumnoCiclo;
import pe.edu.pucp.ooiasoft.student.model.AlumnoCursoCiclo;
import pe.edu.pucp.ooiasoft.student.model.Curso;
import pe.edu.pucp.ooiasoft.student.model.Evaluacion;
import pe.edu.pucp.ooiasoft.student.model.TipoAlumno;
import pe.edu.pucp.ooiasoft.student.model.TipoECTS;
import pe.edu.pucp.ooiasoft.student.model.TipoEvaluacion;


public class AlumnoMySQL implements AlumnoDAO{
    
    //Atributos necesarios para establecer la conexion en cada operacion
    Connection con;
    CallableStatement cs;
    ResultSet rs;

    @Override
    public int insertar(Alumno alumno) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{ call INSERTAR_ALUMNO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

            //Setear parametros
            cs.registerOutParameter("_id_alumno", java.sql.Types.INTEGER);
            cs.setString("_codigo_pucp",alumno.getCodigoPUCP());
            cs.setString("_dni",alumno.getDNI());
            cs.setString("_nombre_completo",alumno.getNombreCompleto());
            cs.setDate("_fecha_nacimiento",new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            cs.setInt("_edad",alumno.getEdad());
            cs.setString("_sexo",String.valueOf(alumno.getSexo()));
            cs.setString("_telefono",alumno.getTelefono());
            cs.setString("_correo",alumno.getCorreo());
            cs.setString("_usuario",alumno.getUsuario());
            cs.setString("_password",alumno.getPassword());
            cs.setBytes("_foto",alumno.getFoto());
            cs.setInt("_fid_especialidad",alumno.getEspecialidad().getIdEspecialidad());
            cs.setString("_tipo_alumno",alumno.getTipo().toString());
            cs.setInt("_escala_pago",alumno.getEscalaPago());
            cs.setDouble("_craest",alumno.getCRAEST());
            cs.setDouble("_promedio_ponderado",alumno.getPromedioPonderado());
            cs.setInt("_cant_ciclos",alumno.getCantCiclos());
            cs.setInt("_cantidad_cursos_aprobados",alumno.getCantCursosAprobados());
            cs.setDouble("_cantidad_creditos_aprobados",alumno.getCantidadCreditosAprobados());
            cs.setDouble("_total_creditos",alumno.getTotalCreditos());
            
            //Ejecutar operacion
            cs.executeUpdate();

            //Obtener parametro de salida
            alumno.setIdPersona(cs.getInt("_id_alumno"));
                       
            //Asignar exito en operacion y cerrado de recursos
            resultado = alumno.getIdPersona();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    private void insertarCiclos(int idAlumno, ArrayList<AlumnoCiclo> ciclos) throws Exception{
        for(AlumnoCiclo aCiclo : ciclos){
            //Preparar llamada
            cs = con.prepareCall("{ call INSERTAR_ALUMNO_CICLO(?,?,?,?,?,?,?,?) }");
            cs.registerOutParameter("_id_alumno_ciclo", java.sql.Types.INTEGER);
            cs.setInt("_fid_alumno", idAlumno);
            cs.setInt("_fid_ciclo", aCiclo.getCiclo().getIdCiclo());
            cs.setDouble("_craest", aCiclo.getCRAEST());
            cs.setDouble("_promedio_ponderado", aCiclo.getPromedioPonderado());
            cs.setInt("_cantidad_cursos_aprobados", aCiclo.getCantidadCursosAprobados());
            cs.setDouble("_cantidad_creditos_aprobados", aCiclo.getCreditosAprobados());
            cs.setDouble("_creditos_totales", aCiclo.getCreditosTotales());
            
            //Ejecutar llamada
            cs.executeUpdate();
            
            //Obtener parametro de salida
            aCiclo.setIdAlumnoCiclo(cs.getInt("_id_alumno_ciclo"));
            
            //Insertar los cursos del ciclo
            insertarCursosCiclo(aCiclo.getIdAlumnoCiclo(), aCiclo.getCursos());
        }
    }
    
    private void insertarCursosCiclo(int idAlumnoCiclo, ArrayList<AlumnoCursoCiclo> accs) throws Exception{
        for(AlumnoCursoCiclo acc : accs){
            //Preparar llamada
            cs = con.prepareCall("{ call INSERTAR_ALUMNO_CURSO_CICLO(?,?,?,?,?) }");
            cs.registerOutParameter("_id_alumno_curso_ciclo", java.sql.Types.INTEGER);
            cs.setInt("_fid_alumno_ciclo", idAlumnoCiclo);
            cs.setInt("_fid_curso", acc.getCurso().getIdCurso());
            cs.setString("_tipo_ECTS", acc.getEscala().toString());
            cs.setDouble("_nota_final", acc.getNotaFinal());
            
            //Ejecutar llamada
            cs.executeUpdate();
            
            //Obtener parametro de salida
            acc.setIdAlumnoCursoCiclo(cs.getInt("_id_alumno_curso_ciclo"));
            
            //Insertar las evaluaciones de ese curso en ese ciclo
            insertarEvaluacionesCursoCiclo(acc.getIdAlumnoCursoCiclo(), acc.getEvaluaciones());
        }
    }
    
    private void insertarEvaluacionesCursoCiclo(int idAlumnoCursoCiclo, ArrayList<Evaluacion> evaluaciones) throws Exception{
        for(Evaluacion evaluacion : evaluaciones){
            //Preparar llamada
            cs = con.prepareCall("{ call INSERTAR_EVALUACION(?,?,?,?,?,?) }");
            cs.registerOutParameter("_id_evaluacion", java.sql.Types.INTEGER);
            cs.setInt("_fid_alumno_curso_ciclo", idAlumnoCursoCiclo);
            cs.setString("_tipo_evaluacion", evaluacion.getTipo().toString());
            cs.setInt("_num_evaluacion", evaluacion.getNumEvaluacion());
            cs.setInt("_nota", evaluacion.getNota());
            cs.setString("_comentarios", evaluacion.getComentarios());
            
            //Ejecutar llamada
            cs.executeUpdate();
            
            //Obtener parametro de salida
            evaluacion.setIdEvaluacion(cs.getInt("_id_evaluacion"));
        }
    }
    
    @Override
    public ArrayList<Alumno> listarCompletoPorCodigoONombre(String codigoNombre) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call LISTAR_ALUMNOS_X_CODIGO_NOMBRE(?)}");

            //Setear parametros de entrada y salida			
            cs.setString("_codigo_nombre", codigoNombre);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo, este listado solo muestra la informacion basica del alumno
            //Sera necesario llamar a otro metodo para llenar sus detalles de notas
            while(rs.next()){
                Alumno alumno = llenarDatosBasicosAlumno(rs);
                
                //Llenar sus ciclos
                llenarCiclos(alumno);
                
                //Agregarlo al arreglo
                alumnos.add(alumno);
            }

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return alumnos;
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
        ev.setIdEvaluacion(rsEv.getInt("id_evaluacion"));
        ev.setNumEvaluacion(rsEv.getInt("num_evaluacion"));
        ev.setNota(rsEv.getInt("nota"));
        ev.setComentarios(rsEv.getString("comentarios"));
        ev.setTipo(TipoEvaluacion.valueOf(rsEv.getString("tipo")));
        ev.setEstado(true);
        
        //Retornar
        return ev;
    }
    
    @Override
    public int modificarDatosPersonales(Alumno alumno) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call MODIFICAR_ALUMNO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            //Setear parametros
            cs.setInt("_id_alumno", alumno.getIdPersona());
            cs.setString("_codigo_pucp",alumno.getCodigoPUCP());
            cs.setString("_dni",alumno.getDNI());
            cs.setString("_nombre_completo",alumno.getNombreCompleto());
            cs.setDate("_fecha_nacimiento",new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            cs.setInt("_edad",alumno.getEdad());
            cs.setString("_sexo",String.valueOf(alumno.getSexo()));
            cs.setString("_telefono",alumno.getTelefono());
            cs.setString("_correo",alumno.getCorreo());
            cs.setString("_usuario",alumno.getUsuario());
            cs.setString("_password",alumno.getPassword());
            cs.setBytes("_foto",alumno.getFoto());
            cs.setInt("_fid_especialidad",alumno.getEspecialidad().getIdEspecialidad());
            cs.setString("_tipo_alumno",alumno.getTipo().toString());
            cs.setInt("_escala_pago",alumno.getEscalaPago());
            cs.setDouble("_craest",alumno.getCRAEST());
            cs.setDouble("_promedio_ponderado",alumno.getPromedioPonderado());
            cs.setInt("_cant_ciclos",alumno.getCantCiclos());
            cs.setInt("_cantidad_cursos_aprobados",alumno.getCantCursosAprobados());
            cs.setDouble("_cantidad_creditos_aprobados",alumno.getCantidadCreditosAprobados());
            cs.setDouble("_total_creditos",alumno.getTotalCreditos());

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
    public int modificarCiclosCursosNotas(Alumno alumno) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            
            //Eliminar sus detalles
            cs = con.prepareCall("{ call ELIMINAR_ALUMNOS_CICLOS_X_AlUMNO(?) }");
            cs.setInt("_id_alumno", alumno.getIdPersona());
            
            //Volver a insertar sus ciclos, cursos y evaluaciones actualizadas
            insertarCiclos(alumno.getIdPersona(), alumno.getCiclos());
        
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
    public int eliminar(int idAlumno) {
        int resultado = 0;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call ELIMINAR_ALUMNO(?)}");

            //Setear parametros
            cs.setInt("_id_alumno", idAlumno);

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
    public Alumno buscarAlumnoPorID(int idAlumno) {
        Alumno alumno = null;
        try{
            //Abrir conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DBManager.url, DBManager.user, DBManager.password);
            cs = con.prepareCall("{call BUSCAR_ALUMNO_X_ID(?)}");

            //Setear parametros de entrada y salida			
            cs.setInt("_fid_persona", idAlumno);
            
            //Ejecutar llamado
            rs = cs.executeQuery();

            //Llenar arreglo, este listado solo muestra la informacion basica del alumno
            if(rs.next())
                alumno = llenarDatosBasicosAlumno(rs);

            //Cerrar recursos
            rs.close();
            cs.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return alumno;
    }

}
