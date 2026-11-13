/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import entidades.Asistencia;
import entidades.Entrenador;
import entidades.Socio;
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
public class sociosData {
    
    
    
    
     private Connection con = null;
       
       
       
       public sociosData(miConexion conexion){
        this.con = conexion.buscarConexion();
    }
    
    public void agregarSocio(Socio socio) {
    String sql = "INSERT INTO Socios (DNI, Nombre, Apellido, Edad, Correo, Telefono, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";   

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, socio.getDni());
        ps.setString(2, socio.getNombre());
        ps.setString(3, socio.getApellido());
        ps.setInt(4, socio.getEdad());       
        ps.setString(5, socio.getCorreo());    
        ps.setLong(6, socio.getTelefono());     
        ps.setBoolean(7, socio.isEstado());   

        int exito = ps.executeUpdate();

        if (exito == 1) {
            JOptionPane.showMessageDialog(null, "Socio agregado correctamente");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Socios");
    }
}
    
    public void eliminarSocio(int id){
    
           
          String sql = "DELETE FROM socios WHERE ID_Socio = ?";

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1,id );
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Socio Eliminado");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Socios");
    }
}
       
    public void ActualizarSocio(Socio socio){
     
     String sql = "UPDATE socios SET DNI = ?, Apellido = ?, Nombre = ?, Telefono = ? , Edad = ? , Correo = ? WHERE ID_Socio = ?  ";
         
         
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             
             ps.setInt(1, socio.getDni());
          ps.setString(2, socio.getApellido());
          ps.setString(3, socio.getNombre());
          ps.setLong(4, socio.getTelefono());
          ps.setInt(5, socio.getEdad());
          ps.setString(6, socio.getCorreo());
          ps.setInt(7,socio.getIdSocio());
          int exito =   ps.executeUpdate();
             
             if (exito == 1 ){
             
             JOptionPane.showMessageDialog(null, "Socio Actualizado");
             
             }
             
             
             
         } catch (SQLException ex) {

             
             JOptionPane.showMessageDialog(null, "Error al acceder a la tabla socios");
         }
     
     
     
     }
       
    public Socio buscarSocio(String dni) {
    String sql = "SELECT ID_Socio, DNI, Nombre, Apellido, Edad, Correo, Telefono, Estado FROM socios WHERE DNI = ?";

    Socio socio = null;

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            socio = new Socio();
            socio.setIdSocio(rs.getInt("ID_Socio"));
            socio.setDni(rs.getInt("DNI"));
            socio.setNombre(rs.getString("Nombre"));
            socio.setApellido(rs.getString("Apellido"));
            socio.setEdad(rs.getInt("Edad"));
            socio.setCorreo(rs.getString("Correo"));
            socio.setTelefono(rs.getLong("Telefono"));
            socio.setEstado(rs.getBoolean("Estado"));
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Socios");
    }

    return socio;
}
    /*hice otro Buscar Socio por Int en vez de String para no sabotear la idea de teo, porque al cambiar
    el metodo este afecta otras partes del codigo que hizo teo y ahora lo necesito de tipo dni para el metodo que estoy armando en paseData*/
     public Socio buscarSocioInt(int dni) {
    String sql = "SELECT ID_Socio, DNI, Nombre, Apellido, Edad, Correo, Telefono, Estado FROM socios WHERE DNI = ?";

    Socio socio = null;

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, dni);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            socio = new Socio();
            socio.setIdSocio(rs.getInt("ID_Socio"));
            socio.setDni(rs.getInt("DNI"));
            socio.setNombre(rs.getString("Nombre"));
            socio.setApellido(rs.getString("Apellido"));
            socio.setEdad(rs.getInt("Edad"));
            socio.setCorreo(rs.getString("Correo"));
            socio.setTelefono(rs.getLong("Telefono"));
            socio.setEstado(rs.getBoolean("Estado"));
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Socios");
    }

    return socio;
}
    
    
    
       
    public List<Socio> listarSocios() {
    List<Socio> lista = new ArrayList<>();
    String sql = "SELECT ID_Socio, DNI, Nombre, Apellido, Edad, Correo, Telefono, Estado FROM socios";

    try (PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Socio socio = new Socio();
            socio.setIdSocio(rs.getInt("ID_Socio"));
            socio.setDni(rs.getInt("DNI"));
            socio.setNombre(rs.getString("Nombre"));
            socio.setApellido(rs.getString("Apellido"));
            socio.setEdad(rs.getInt("Edad"));
            socio.setCorreo(rs.getString("Correo"));
            socio.setTelefono(rs.getLong("Telefono"));
            socio.setEstado(rs.getBoolean("Estado"));
            lista.add(socio);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al listar Socios");
    }
    return lista;
}
       
    public void darDeBaja (int dni) {
       
        String sql = "UPDATE socios SET Estado = 0 WHERE DNI = ?";
       
        try {
             
            PreparedStatement ps=con.prepareStatement(sql);    
            ps.setInt(1, dni);
            
            int exito = ps.executeUpdate();
            
            if (exito == 1) {
            
                JOptionPane.showMessageDialog(null, "Dado de baja correctamente");
            
            } else {
            
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja");
                
            }
            
        } catch (SQLException ex) {
           
            JOptionPane.showMessageDialog(null, "Error al dar de baja " + ex.getMessage());
            
        }
       
    }
    
    public void darDeAlta (int dni) {
       
        String sql = "UPDATE socios SET Estado = 1 WHERE DNI = ?";
       
        try {
             
            PreparedStatement ps=con.prepareStatement(sql);    
            ps.setInt(1, dni);
            
            int exito = ps.executeUpdate();
            System.out.println("Filas afectadas: " + exito);
            
            if (exito == 1) {
            
                JOptionPane.showMessageDialog(null, "Dado de alta correctamente");
            
            } else {
            
                JOptionPane.showMessageDialog(null, "No se pudo dar de alta");
                
            }
            
        } catch (SQLException ex) {
           
            JOptionPane.showMessageDialog(null, "Error al dar de alta " + ex.getMessage());
            
        }
       
    }
       
}