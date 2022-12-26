/* 
 * Archivo: HorarioCiclo.java
 * Grupo:   Grave Error de Proyecto
 * Integrantes: 
 *  - Oscar Dueñas
 *  - Christian Carhuancho
 *  - Franccesco Jaimes
 *  - Pilar Llantoy
 *  - Cesar Rafael
 * Fecha:   07/06/2021
 */

package pe.edu.pucp.ooiasoft.organization.model;

import java.util.ArrayList;
import pe.edu.pucp.ooiasoft.information.model.Ciclo;

public class HorarioCiclo {

    //Atributos de la clase
    private int idHorarioCiclo;
    private int horasDisponible;
    private int horasAtendidas;
    private boolean estado;

    //Atributos de relaciones
    private PersonalCitas personal;
    private Ciclo ciclo;
    private ArrayList<DetalleHorario> horario;

    //Constructores
    
    //Constructor vacio
    public HorarioCiclo() {
        horario = new ArrayList<>();
    }
    
    //Para poder existir, debe estar asociado a un ciclo, un personal de citas, las horas teoricas disponibles y el estado
    public HorarioCiclo(PersonalCitas personal, Ciclo ciclo, int horasDisponible, boolean estado) {
        this.personal = personal;
        this.ciclo = ciclo;
        this.horasDisponible = horasDisponible;
        this.estado = estado;
        horario = new ArrayList<>();    //Inicializar arreglo de detalle
    }
    
    //Getters y setters
    public int getIdHorarioCiclo() {
        return idHorarioCiclo;
    }

    public void setIdHorarioCiclo(int idHorarioCiclo) {    
        this.idHorarioCiclo = idHorarioCiclo;
    }

    public int getHorasDisponible() {
        return horasDisponible;
    }

    public void setHorasDisponible(int horasDisponible) {
        this.horasDisponible = horasDisponible;
    }

    public int getHorasAtendidas() {
        return horasAtendidas;
    }

    public void setHorasAtendidas(int horasAtendidas) {
        this.horasAtendidas = horasAtendidas;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public PersonalCitas getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalCitas personal) {
        this.personal = personal;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public ArrayList<DetalleHorario> getHorario() {
        return horario;
    }

    public void setHorario(ArrayList<DetalleHorario> horario) {
        this.horario = horario;
    }

    public void agregarDetalle(DetalleHorario det) {
        horario.add(det);
    }

    //Recorrer el arreglo de DetalleHorario y armar el calendario para mostrarlo.
    //Luego esto se implementara utilizando una interfaz grafica.
    public String mostrarHorario() {
        String aux = "           L  M  M  J  V  S  D\n";
        int flag;
        for(int i=8;i<22;i++){    //Horas
            aux += String.format("%-9s| ", i + " - " + (i+1));
            for(int j=0;j<7;j++){   //Dias
                flag = 0;
                for(DetalleHorario dh : horario){
                    if(dh.getDia() == j && (i>=dh.getHoraInicio() && i<dh.getHoraFin())){
                        aux += "*  ";
                        flag = 1;
                        break;
                    }
                }
                if(flag == 0) aux += "-  ";
            }
            aux += "\n";
        }
        return aux;
    }

}
