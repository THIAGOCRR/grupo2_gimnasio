/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import entidades.Asistencia;
import entidades.Entrenador;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crn70
 */
public class EntrenadorData {
    
    
     private Connection con = null;

   public EntrenadorData(miConexion conexion){
        this.con = conexion.buscarConexion();
    }
    
     public List<Entrenador> listarEntrenadores() {
    List<Entrenador> lista = new ArrayList<>();
    String sql = "SELECT ID_Entrenador, DNI, Nombre, Apellido, Especialidad, Estado FROM entrenadores";

    try (PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Entrenador entrenador = new Entrenador();
            entrenador.setIdEntrenador(rs.getInt("ID_Entrenador"));
            entrenador.setDni(rs.getInt("DNI")); 
            entrenador.setNombre(rs.getString("Nombre"));
            entrenador.setApellido(rs.getString("Apellido"));
            entrenador.setEspecialidad(rs.getString("Especialidad"));
            entrenador.setEstado(rs.getBoolean("Estado"));
            lista.add(entrenador);
        }
        System.out.println("Entrenadores cargados:" );

    } catch (SQLException e) {
        System.err.println("Error al listar:");
    }
    return lista;
}
    
    public Entrenador buscarEntrenador(int id ){
    
       String sql = "SELECT ID_Entrenador, DNI, Nombre, Apellido, Especialidad, Estado " +
                 "FROM entrenadores WHERE ID_Entrenador = ? AND Estado = 1";
    
    Entrenador entrenador = null;
    
    try{
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, id);
    ResultSet rs= ps.executeQuery();
    
    
    if(rs.next()){
    
    
      entrenador = new Entrenador();
      entrenador.setIdEntrenador(id);
      entrenador.setDni(rs.getInt("DNI"));
      entrenador.setApellido(rs.getString("Apellido"));
      entrenador.setNombre(rs.getString("Nombre"));
      entrenador.setEspecialidad(rs.getString("Especialidad"));
      entrenador.setEstado(rs.getBoolean("Estado"));
    
        
        
        
    
    }
    else{ JOptionPane.showMessageDialog(null, "No existe ese entrenador");}
    ps.close();
    
    }catch(SQLException ex){
    
    
    JOptionPane.showMessageDialog(null, "Error al acceder a la tabla entrenadores");
    
    
    }
    
    return entrenador;
    
    
    
    
    }
     
     
     public void eliminarEntrenador(int id){
     /*
         
         Borrar de manera logica (es decir que no sea visible pero sigue ahi tan solo que no con el estado en 1 sino en 0) como lo hizo el profe en su video: 
         
     String sql = "UPDATE entrenadores SET Estado = 0 WHERE ID_Entrenador = ?";
     
     
     try{
     
     PreparedStatement ps = con.prepareStatement(sql);
     ps.setInt(1, id);
     int exito = ps.executeUpdate();
     if (exito ==1){
     
         JOptionPane.showMessageDialog(null, "Entrenador Eliminado");
         
     }
     }catch(SQLException ex ){
             
             
             JOptionPane.showMessageDialog(null, "Error al acceder a la tabla entrenador");
             
             
             
             
             
             
     }
*/
    /* Eliminar de manera permanente haciendo que desaparezca de la tabla entrenadores:
     
     */
    String sql = "DELETE FROM entrenadores WHERE ID_Entrenador = ?";

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Entrenador Eliminado");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla entrenador");
    }
}
     
             
     
     public void ActualizarEntrenador(Entrenador entrenador){
     
     String sql = "UPDATE entrenadores SET dni = ?, apellido = ?, nombre = ?, especialidad = ? WHERE ID_Entrenador = ?  ";
         
         
     
     
     
     
     
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             
             ps.setInt(1, entrenador.getDni());
          ps.setString(2, entrenador.getApellido());
          ps.setString(3, entrenador.getNombre());
          ps.setString(4, entrenador.getEspecialidad());
          ps.setInt(5,entrenador.getIdEntrenador());
          int exito =   ps.executeUpdate();
             
             if (exito == 1 ){
             
             JOptionPane.showMessageDialog(null, "Entrenador Actualizado");
             
             }
             
             
             
         } catch (SQLException ex) {

             
             JOptionPane.showMessageDialog(null, "Error al acceder a la tabla entrenador");
         }
     
     
     
     }
     
     
   public void agregarEntrenador(Entrenador entrenador){
   
   
    String sql = "INSERT INTO entrenadores (DNI, Nombre, Apellido, Especialidad, Estado) " +
                 "VALUES (?, ?, ?, ?, ?)";

    
    
    
    
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, entrenador.getDni());
        ps.setString(2, entrenador.getNombre());
        ps.setString(3, entrenador.getApellido());
        ps.setString(4, entrenador.getEspecialidad());
        ps.setBoolean(5, entrenador.isEstado());

        int exito = ps.executeUpdate();

        if (exito == 1) {
            JOptionPane.showMessageDialog(null, "Entrenador agregado correctamente");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al agregar entrenador");
    }
   
   
   
       
       
       
   
   
   
   
   }
     
   
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     

     
     }
     
     
     
     
     
     
     

     
     
     
    

