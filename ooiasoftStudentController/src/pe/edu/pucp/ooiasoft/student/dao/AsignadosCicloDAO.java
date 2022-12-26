/* 
 * Archivo: AsignadosCicloDAO.java
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
import pe.edu.pucp.ooiasoft.student.model.AsignadosCiclo;

public interface AsignadosCicloDAO {
    public int insertarAsignadosCiclo(AsignadosCiclo asignados);
    public ArrayList<AsignadosCiclo> listarPorIDTutor(int idTutor);
    public int modificar(AsignadosCiclo asignados);
    public int eliminar(int idAsignadosCiclo);
}
