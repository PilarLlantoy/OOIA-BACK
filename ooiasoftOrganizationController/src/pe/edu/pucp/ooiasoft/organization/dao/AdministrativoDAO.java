/* 
 * Archivo: AdministrativoDAO.java
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
import pe.edu.pucp.ooiasoft.organization.model.Administrativo;

public interface AdministrativoDAO {
    public int insertar(Administrativo admin);
    public ArrayList<Administrativo> listarPorCodigoNombre(String codigoNombre);
    public Administrativo buscarAdministrativoPorID(int idAdmin);
    public int modificar(Administrativo admin);
    public int eliminar(int idAdmin);
}
