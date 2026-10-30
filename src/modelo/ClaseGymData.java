/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import entidades.ClaseGym;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author crn70
 */
public class ClaseGymData {
    
    private Connection con = null;
    
    public ClaseGymData(miConexion conexion){
        this.con = conexion.buscarConexion();
    }

    public ClaseGymData() {
    }
    
    public void guardarClaseGym(ClaseGym claseGym) {
    
        String sql="SELECT INTO clases(Disciplina, ID_Entrenador, Horario, Capacidad, Estado) VALUES (?, ?, ?, ?, ?)";
    
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, claseGym.getNombre());
            ps.setInt(2, claseGym.getEntrenador());
            ps.setTime(3, java.sql.Time.valueOf(claseGym.getHorario()));
            ps.setInt(4, claseGym.getCapacidad());
            ps.setBoolean(5, claseGym.isEstado());
            
            int filas=ps.executeUpdate();
            
            if (filas > 0) {
            
                System.out.println("Clase guardada correctamente");
            
            } else {
            
                System.out.println("No se pudo guardar la clase");
            
            }
        
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al intentar guardar la clase " + ex.getMessage());
            
        }
    }
    
    public void actualizarClase(ClaseGym claseGym) {
    
        String sql="UPDATE clases SET Disciplina = ?, ID_Entrenador = ?, Horario = ?, Capacidad = ? WHERE ID_Clase = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, claseGym.getNombre());
            ps.setInt(2, claseGym.getEntrenador());
            ps.setTime(3, java.sql.Time.valueOf(claseGym.getHorario()));
            ps.setInt(4, claseGym.getCapacidad());
            
            int filas=ps.executeUpdate();
            
            if (filas > 0) {
            
                System.out.println("Clase actualizada correctamente");
                
            } else if (filas == 0) {
            
                System.out.println("No se encontro el ID");
            
            } else {
            
                System.out.println("No se pudo actualizar la clase");
                
            }
        
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al intentar actualizar la clase " + ex.getMessage());
            
        }
    
    }
    
    public void borrarClase(int idClase) {
    
        String sql="DELETE FROM clases WHERE ID_Clase = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idClase);
            
            int filas=ps.executeUpdate();
            
            if (filas > 0) {
            
                System.out.println("Clase borrada correctamente");
                
            } else if (filas == 0) {
            
                System.out.println("No se encontro el ID");
            
            } else {
            
                System.out.println("No se pudo borrar la clase");
                
            }
            
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al intentar borrar la clase " + ex.getMessage());
            
        }
        
    }
    
    public ClaseGym buscarClase(int idClase) {
    
        ClaseGym cg=null;
        
        String sql="SELECT * FROM clases WHERE ID_Clase = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idClase);
            
            ResultSet rs=ps.executeQuery();
            
            if (rs.next()) {
            
                cg=new ClaseGym();
                cg.setNombre(rs.getString("Capacidad"));
                cg.setEntrenador(rs.getInt("ID_Entrenador"));
                cg.setHorario(rs.getTime("Horario").toLocalTime());
                cg.setCapacidad(rs.getInt("Capacidad"));
                cg.setEstado(rs.getBoolean("Estado"));

                System.out.println("Clase encontrada");
            }
        
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al buscar la clase " + ex.getMessage());
            
        }
        return cg;
    }
    
    public ArrayList<ClaseGym> listarClase() {
    
        String sql="SELECT * FROM clases";
        
        ArrayList<ClaseGym> clases=new ArrayList<>();
    
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()) {
            
                ClaseGym cg=new ClaseGym();
                cg.setIdClase(rs.getInt("ID_Clase"));
                cg.setNombre(rs.getString("Capacidad"));
                cg.setEntrenador(rs.getInt("ID_Entrenador"));
                cg.setHorario(rs.getTime("Horario").toLocalTime());
                cg.setCapacidad(rs.getInt("Capacidad"));
                cg.setEstado(rs.getBoolean("Estado"));
                
                clases.add(cg);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al listar las clases " + ex.getMessage());
            
        }
        return clases;
    }
    
}
