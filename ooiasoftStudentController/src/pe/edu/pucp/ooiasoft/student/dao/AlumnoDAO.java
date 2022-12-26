/* 
 * Archivo: AlumnoDAO.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.student.dao;

import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.student.model.Alumno;

public interface AlumnoDAO {
    public int insertar(Alumno alumno);
    
    //Esto lo utilizara el administrativo, lista con todos sus datos
    public ArrayList<Alumno> listarCompletoPorCodigoONombre(String codigoNombre);
    public Alumno buscarAlumnoPorID(int idAlumno);
    
    //Estos los utilizara el administrativo
    public int modificarDatosPersonales(Alumno alumno);
    public int modificarCiclosCursosNotas(Alumno alumno);
    public int eliminar(int idAlumno);
}
