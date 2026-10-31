/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package views;
import java.sql.Connection;
import entidades.Asistencia;
import entidades.Entrenador;
import entidades.Socio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.AsistenciaData;
import modelo.EntrenadorData;
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
    
    /* Prueba de guardarAsistencia()    
    AsistenciaData asistenciaData = new AsistenciaData(conexion);
    
    Asistencia asistencia = new Asistencia();

    asistencia.setSocio(1);    
    asistencia.setClase(1);      
    asistencia.setFechaAsistencia(LocalDate.now());

    asistenciaData.guardarAsistencia(asistencia);
    System.out.println("Asistencia guardada correctamente");
    */    
      
    /* Prueba de buscarAsistencia()
    AsistenciaData ad=new AsistenciaData(conexion);
    Asistencia a=ad.buscarAsistencia(1);
    */    
       
    /* Prueba de listarAsistencia()
    AsistenciaData ad = new AsistenciaData(conexion);

    ArrayList<Asistencia> asistencias = ad.listarAsistencia();

    for (Asistencia a : asistencias) {
        System.out.println("ID: " + a.getIdAsistencia());
        System.out.println("Socio: " + a.getSocio());
        System.out.println("Clase: " + a.getClase());
        System.out.println("Fecha: " + a.getFechaAsistencia());
        System.out.println("-------------------------");
    }    
    */    
    
    /* PruebA 
    AsistenciaData ad=new AsistenciaData(conexion);
    
    ArrayList<Asistencia> asistencias = ad.listarAsistenciasPorSocio(1);
    
    for (Asistencia a : asistencias) {
        System.out.println("ID: " + a.getIdAsistencia());
        System.out.println("Socio: " + a.getSocio());
        System.out.println("Clase: " + a.getClase());
        System.out.println("Fecha: " + a.getFechaAsistencia());
        System.out.println("-------------------------");
    }  
    */

    /* Prueba de actualizarAsistencia
    AsistenciaData ad=new AsistenciaData(conexion);
    
    Asistencia asistencia = new Asistencia();
    
    asistencia.setIdAsistencia(1);
    asistencia.setSocio(1);
    asistencia.setClase(1);
    asistencia.setFechaAsistencia(LocalDate.now());

    ad.actualizarAsistencia(asistencia);
    */
    
    /* Prueba de borrarAsistencia
    AsistenciaData ad=new AsistenciaData(conexion);
    
    ad.borrarAsistencia(1);
    */

    /*
    AsistenciaData ad=new AsistenciaData(conexion);
    
    ArrayList<Asistencia> asistencias=ad.listarAsistenciasPorClase(1);
    
    for (Asistencia a : asistencias) {
        System.out.println("ID: " + a.getIdAsistencia());
        System.out.println("Socio: " + a.getSocio());
        System.out.println("Clase: " + a.getClase());
        System.out.println("Fecha: " + a.getFechaAsistencia());
        System.out.println("-------------------------");
    }
    */
    /*---------------------------------------------------------------------------------*/
    EntrenadorData en = new EntrenadorData(conexion);
    
     Entrenador entrenadorActualizar = new Entrenador();
entrenadorActualizar.setIdEntrenador(5);      
entrenadorActualizar.setDni(22222);
entrenadorActualizar.setNombre("Carlos");
entrenadorActualizar.setApellido("Perez");
entrenadorActualizar.setEspecialidad("Futbol"); 
entrenadorActualizar.setEstado(true);
    
    System.out.println("actualizando entrenador");
en.ActualizarEntrenador(entrenadorActualizar);
            
    
    /*---------------------------------------------------------------------------------*/
   
   
    
   

   
      
    
     /*---------------------------------------------------------------------------------*/
    
    
  /*
     Eliminar Entrenadores: 

EntrenadorData en = new EntrenadorData(conexion);
en.eliminarEntrenador(4);
    */
   
     
      /*---------------------------------------------------------------------------------*/
 /*
      EntrenadorData en = new EntrenadorData(conexion);
     int idEntrenador = 5; 

      Entrenador encontrado = en.buscarEntrenador(idEntrenador);

     
     
        System.out.println("Buscando entrenador con ID = " + idEntrenador + " "+ encontrado.getNombre() +" "+ encontrado.getApellido() );
        
   */    
  /*---------------------------------------------------------------------------------*/
 /*
 
 Listar Entrenadores: 
 EntrenadorData en = new EntrenadorData(conexion);
 List<Entrenador> activos = en.listarEntrenadores();
   for (Entrenador e : activos) {
    System.out.println("ID: " + e.getIdEntrenador());
    System.out.println("Nombre: " + e.getNombre() + " " + e.getApellido());
    System.out.println("----------------------");
}
    
    
   */ 
  /*---------------------------------------------------------------------------------*/   
    
     /* 
  Agregar Socio: 
     SociosData so = new SociosData(conexion);
        Socio nuevo = new Socio( 45123777,"Carla", "Díaz",28, "carla@email.com", 266111222, true);

       
        so.agregarSocio(nuevo);
 */ 
    
    
    
      /*---------------------------------------------------------------------------------*/   
    
      
        /* 
      Eliminar Socio: 
      
     SociosData so = new SociosData(conexion);
      int id = 1; 

       
        Socio encontrado = so.buscarSocio(id);
    so.eliminarSocio(id);
      
     */ 
    
    /*---------------------------------------------------------------------------------*/   
    
     /* 
      SociosData so = new SociosData(conexion);

        Socio actualizarSocio = new Socio();
        actualizarSocio.setIdSocio(2);       
        actualizarSocio.setDni(22222);
        actualizarSocio.setNombre("Roberto");
        actualizarSocio.setApellido("Lopez");
        actualizarSocio.setEdad(35);
        actualizarSocio.setCorreo("roberto@email.com");
        actualizarSocio.setTelefono(266111222);
        actualizarSocio.setEstado(true);

        System.out.println("actualizando socio");
        so.ActualizarSocio(actualizarSocio);
   
      */ 
     /*---------------------------------------------------------------------------------*/  
    
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    
    }
}
