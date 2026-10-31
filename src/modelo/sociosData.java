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
public class SociosData {
    
    
    
    
     private Connection con = null;
       
       
       
       public SociosData(miConexion conexion){
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
        ps.setInt(6, socio.getTelefono());     
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
          ps.setInt(4, socio.getTelefono());
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
       
       public Socio buscarSocio(int id) {
    String sql = "SELECT ID_Socio, DNI, Nombre, Apellido, Edad, Correo, Telefono, Estado " +
                 "FROM socios WHERE ID_Socio = ?";

    Socio socio = null;

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            socio = new Socio();
            socio.setIdSocio(rs.getInt("ID_Socio"));
            socio.setDni(rs.getInt("DNI"));
            socio.setNombre(rs.getString("Nombre"));
            socio.setApellido(rs.getString("Apellido"));
            socio.setEdad(rs.getInt("Edad"));
            socio.setCorreo(rs.getString("Correo"));
            socio.setTelefono(rs.getInt("Telefono"));
            socio.setEstado(rs.getBoolean("Estado"));
        } else {
            JOptionPane.showMessageDialog(null, "No existe ese socio");
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
            socio.setTelefono(rs.getInt("Telefono"));
            socio.setEstado(rs.getBoolean("Estado"));
            lista.add(socio);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al listar Socios");
    }
    return lista;
}
       
       
       
       
       
       
       
       
       
       
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
