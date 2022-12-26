/* 
 * Archivo: InformacionSubtemaCicloDAO.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.information.dao;

import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.information.model.InformacionTemaCiclo;

public interface InformacionTemaCicloDAO {
    public int insertar(InformacionTemaCiclo info);
    public ArrayList<InformacionTemaCiclo> listarPorCicloTema(int idCiclo, int idTema);
    public int modificar(InformacionTemaCiclo info);
    public int eliminar(int idInformacionTemaCiclo);
}
