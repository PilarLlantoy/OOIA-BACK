/* 
 * Archivo: CursoDAO.java
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
import pe.edu.pucp.ooiasoft.student.model.Curso;

public interface CursoDAO {
    public int insertar(Curso curso);
    public ArrayList<Curso> listarPorCodigoNombre(String codigoNombre);
    public int modificar(Curso curso);
    public int eliminar(int idCurso);
}
