/* 
 * Archivo: DBManager.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.information.config;

public abstract class DBManager {
    public static String url = "jdbc:mysql:"
            + "//bd-ooia-2021-1-mysql.chuixdkbzjpg.us-east-1.rds.amazonaws.com"
            + ":3306/OOIABD";
    public static String user = "admin";
    public static String password = "tebajastelabd";
}
