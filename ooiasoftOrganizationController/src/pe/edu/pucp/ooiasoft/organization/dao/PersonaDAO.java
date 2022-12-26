/* 
 * Archivo: PersonaDAO.java
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

import pe.edu.pucp.ooiasoft.organization.model.Persona;

public interface PersonaDAO {
    //Este metodo busca el tipo de una pesona, se usara para el inicio de sesion en el sistema
    public char buscarTipoPersonaPorIDPersona(int idPersona);
    
    //Verifica credenciales
    public int buscarIDPersonaPorUsuarioPassword(String usuario, String password);
    
    public int buscarIDPersonaPorCodigoCorreo(String codigoPUCP, String correo);
    
    //Modificar los datos personales
    public int modificarPersona(Persona persona);
    
}
