/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import entidades.Asistencia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author crn70
 */
public class AsistenciaData {
    
    private Connection con = null;

    public AsistenciaData(miConexion conexion){
        this.con = conexion.buscarConexion();
    }

    public AsistenciaData() {
    }
    
    public void guardarAsistencia(Asistencia asistencia) {
        String sql="INSERT INTO asistencias(ID_Socio, ID_Clase, Fecha_Asistencia) VALUES (?, ?, ?)";
        
        try { 
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, asistencia.getSocio());
            ps.setInt(2, asistencia.getClase());
            ps.setDate(3, Date.valueOf(asistencia.getFechaAsistencia()));
            
            int filas = ps.executeUpdate();
            if(filas > 0) {
                System.out.println("Asistencia guardada correctamente");
            } else {
                System.out.println("No se pudo guardar la asistencia");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar la asistencia" + ex.getMessage());
        }

    }
    
    public Asistencia buscarAsistencia(int idAsistencia) {
        
        Asistencia a=null;
        
        String sql = "SELECT * FROM asistencias WHERE ID_Asistencia= ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAsistencia);
            
            ResultSet rs=ps.executeQuery();
            
            if (rs.next()) {
                a = new Asistencia();
                a.setSocio(rs.getInt("ID_Socio"));
                a.setClase(rs.getInt("ID_Clase"));
                a.setFechaAsistencia(rs.getDate("Fecha_Asistencia").toLocalDate());
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al buscar la asistencia: " + ex.getMessage());
            
        }
        return a;
    }
    
    public ArrayList<Asistencia> listarAsistencia() {
        
        String sql="SELECT * FROM asistencias";
        
        ArrayList<Asistencia> asistencias=new ArrayList<>();
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()) {
                Asistencia a = new Asistencia();
                a.setIdAsistencia(rs.getInt("ID_Asistencia"));
                a.setSocio(rs.getInt("ID_Socio"));
                a.setClase(rs.getInt("ID_Clase"));
                a.setFechaAsistencia(rs.getDate("Fecha_Asistencia").toLocalDate());
                asistencias.add(a);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al listar las asistencias" + ex.getMessage());
            
        }
                
        return asistencias;
    }
    
    public ArrayList<Asistencia> listarAsistenciasPorSocio(int socio) {
        
        ArrayList<Asistencia> asistenciasPorSocio=new ArrayList<>();
        
        String sql="SELECT * FROM asistencias WHERE ID_Socio = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, socio);
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()) {
                Asistencia a = new Asistencia();
                a.setIdAsistencia(rs.getInt("ID_Asistencia"));
                a.setSocio(rs.getInt("ID_Socio"));
                a.setClase(rs.getInt("ID_Clase"));
                a.setFechaAsistencia(rs.getDate("Fecha_Asistencia").toLocalDate());
                asistenciasPorSocio.add(a);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al listar las asistencias del socio: " + ex.getMessage());
            
        }
       return asistenciasPorSocio; 
    }
    
    public void actualizarAsistencia(Asistencia asistencia) {
    
        String sql="UPDATE asistencias SET ID_Socio = ?, ID_Clase = ?, Fecha_Asistencia = ? WHERE ID_Asistencia = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, asistencia.getSocio());
            ps.setInt(2, asistencia.getClase());
            ps.setDate(3, Date.valueOf(asistencia.getFechaAsistencia()));
            ps.setInt(4, asistencia.getIdAsistencia());
            
            int filas = ps.executeUpdate();
            
            if(filas>0) {
                System.out.println("Asistencia actualizada correctamente");
            } else if(filas == 0) {
                System.out.println("No se pudo encontrar el ID");
            } else {
                System.out.println("No se pudo actualizar la asistencia");
            }
            
            ps.close();
        } catch (SQLException ex) {
         
            JOptionPane.showMessageDialog(null, "Error al actualizar la asistencia: " + ex.getMessage());
            
        }
        
    }
    
    public void borrarAsistencia(int idAsistencia) {
    
        String sql="DELETE FROM asistencias WHERE ID_Asistencia = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAsistencia);
            
            int filas = ps.executeUpdate();
            
            if(filas > 0) {
                System.out.println("Asistencia borrada correctamente");
            } else {
                System.out.println("No se pudo borrar la asistencia");
            }
        
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "No se pudo borrar la asistencia: " + ex.getMessage());
            
        }
        
    }

    public ArrayList<Asistencia> listarAsistenciasPorClase(int clase) {
    
        ArrayList<Asistencia> asistenciasPorClase=new ArrayList<>();
        
        String sql="SELECT * FROM asistencias WHERE ID_Clase = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, clase);
        
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()) {
                Asistencia a = new Asistencia();
                a.setIdAsistencia(rs.getInt("ID_Asistencia"));
                a.setSocio(rs.getInt("ID_Socio"));
                a.setClase(rs.getInt("ID_Clase"));
                a.setFechaAsistencia(rs.getDate("Fecha_Asistencia").toLocalDate());
                asistenciasPorClase.add(a);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al listar las asistencias por clase: " + ex.getMessage());
            
        }
        return asistenciasPorClase;
    }
    
}
