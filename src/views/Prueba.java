/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package views;

import entidades.Asistencia;
import entidades.Socio;
import java.time.LocalDate;
import modelo.AsistenciaData;
import modelo.SociosData;
import modelo.miConexion;

/**
 *
 * @author crn70
 */
public class Prueba {
   private Asistencia asistenciaData;
    private miConexion conexion;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        
         
               miConexion conexion = new miConexion();
        AsistenciaData asistenciaData = new AsistenciaData(conexion);
    
            Asistencia asistencia = new Asistencia();

    asistencia.setSocio(1);    
        asistencia.setClase(1);      
    asistencia.setFechaAsistencia(LocalDate.now());

      asistenciaData.guardarAsistencia(asistencia);
    System.out.println("Asistencia guardada correctamente");
        
        
        
        
        
        
        
        
        
        
    }
    
}
