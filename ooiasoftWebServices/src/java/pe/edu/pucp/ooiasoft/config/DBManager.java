/* 
 * Archivo: DBManager.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   04/07/2021
 */

package pe.edu.pucp.ooiasoft.config; 
import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    private static DBManager dbManager = new DBManager();
    private Connection con;
    private String url = "jdbc:mysql:"
            + "//bd-ooia-2021-1-mysql.chuixdkbzjpg.us-east-1.rds.amazonaws.com"
            + ":3306/OOIABD";
    private String user = "admin";
    private String password = "tebajastelabd";
    
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.user,this.password);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
    
    public static DBManager getInstance(){
        return dbManager;
    }
}
