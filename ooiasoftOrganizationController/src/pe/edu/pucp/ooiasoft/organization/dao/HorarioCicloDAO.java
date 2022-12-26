/* 
 * Archivo: HorarioCicloDAO.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.organization.dao;

import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.organization.model.HorarioCiclo;

public interface HorarioCicloDAO {
    public int insertar(HorarioCiclo horario);
    public ArrayList<HorarioCiclo> listarPorPersonalCitas(int idPersonalCitas);
    public int modificar(HorarioCiclo horario);
    public int eliminar(int idHorarioCiclo);
}
