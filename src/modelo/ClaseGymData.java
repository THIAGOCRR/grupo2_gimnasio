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
import java.util.List;

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
    
            String sql="INSERT INTO clases(Disciplina, ID_Entrenador, Horario, Capacidad, Estado) VALUES (?, ?, ?, ?, ?)";
    
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, claseGym.getNombre());
            ps.setInt(2, claseGym.getEntrenador());
            ps.setTime(3, java.sql.Time.valueOf(claseGym.getHorario()));
            ps.setInt(4, claseGym.getCapacidad());
            ps.setBoolean(5, claseGym.isEstado());
            
            int filas=ps.executeUpdate();
            
            if (filas > 0) {
            
                JOptionPane.showMessageDialog(null, "Clase guardada correctamente");
            
            } else {
            
                JOptionPane.showMessageDialog(null, "No se pudo guardar la clase");
            
            }
        
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al intentar guardar la clase " + ex.getMessage());
            
        }
    }
    
    public void actualizarClase(ClaseGym claseGym) {
    
        String sql="UPDATE clases SET Disciplina = ?, ID_Entrenador = ?, Horario = ?, Capacidad = ?, Estado = ? WHERE ID_Clase = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, claseGym.getNombre());
            ps.setInt(2, claseGym.getEntrenador());
            ps.setTime(3, java.sql.Time.valueOf(claseGym.getHorario()));
            ps.setInt(4, claseGym.getCapacidad());
            ps.setBoolean(5, claseGym.isEstado());
            ps.setInt(6, claseGym.getIdClase());
            
            int filas=ps.executeUpdate();
            
            if (filas > 0) {
            
                JOptionPane.showMessageDialog(null, "Clase actualizada correctamente");
                
            } else if (filas == 0) {
            
                JOptionPane.showMessageDialog(null, "No se encontro el ID");
            
            } else {
            
                JOptionPane.showMessageDialog(null, "No se pudo actualizar la clase");
                
            }
        
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al intentar actualizar la clase " + ex.getMessage());
            
        }
    
    }
    
    public void desactivarClase(int idClase) {
    
        String sql="UPDATE clases SET Estado = ? WHERE ID_Clase = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, idClase);
            
            int filas=ps.executeUpdate();
            
            if (filas > 0) {
            
                JOptionPane.showMessageDialog(null, "Clase desactivada correctamente");
                
            } else if (filas == 0) {
            
                JOptionPane.showMessageDialog(null, "No se encontró la clase");
            
            } else {
            
                JOptionPane.showMessageDialog(null, "No se pudo desactivar la clase");
                
            }
            
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al intentar borrar la clase " + ex.getMessage());
            
        }
        
    }
    
    public void activarClase(int idClase) {

        String sql = "UPDATE clases SET Estado = ? WHERE ID_Clase = ?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setBoolean(1, true);
            ps.setInt(2, idClase);

            int filas = ps.executeUpdate();

            if (filas > 0) {

            JOptionPane.showMessageDialog(null, "Clase activada correctamente");

        } else {

            JOptionPane.showMessageDialog(null, "No se encontró la clase inactiva");
        }

        ps.close();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al intentar activar la clase: " + ex.getMessage());
        }
    }
    
    public List<ClaseGym> buscarClasesInactivas(String nombre) {

        List<ClaseGym> clasesEncontradas = new ArrayList<>();

        String sql = "SELECT * FROM clases WHERE Disciplina = ? AND Estado = false";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            ClaseGym cg = new ClaseGym();

            cg.setIdClase(rs.getInt("ID_Clase"));
            cg.setNombre(rs.getString("Disciplina"));
            cg.setEntrenador(rs.getInt("ID_Entrenador"));
            cg.setHorario(rs.getTime("Horario").toLocalTime());
            cg.setCapacidad(rs.getInt("Capacidad"));
            cg.setEstado(rs.getBoolean("Estado"));

            clasesEncontradas.add(cg);
        }

        rs.close();
        ps.close();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Error al buscar clases inactivas: " + ex.getMessage());
        }

        return clasesEncontradas;
    }
    
    public List<ClaseGym> buscarClases(String nombre) {
    
        List<ClaseGym> clasesEncontradas = new ArrayList<>();
        
        
        
        String sql = "SELECT * FROM clases WHERE Disciplina = ? AND Estado = true";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, nombre);
            
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()) {
            
                ClaseGym cg = new ClaseGym();
                
                cg.setIdClase(rs.getInt("ID_Clase"));
                cg.setNombre(rs.getString("Disciplina"));
                cg.setEntrenador(rs.getInt("ID_Entrenador"));
                
                cg.setHorario(rs.getTime("Horario").toLocalTime());
                cg.setCapacidad(rs.getInt("Capacidad"));
                cg.setEstado(rs.getBoolean("Estado"));

                clasesEncontradas.add(cg);
            }
        
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al buscar la clase " + ex.getMessage());
            
        }
        return clasesEncontradas;
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
