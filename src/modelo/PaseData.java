/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import entidades.Pase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author crn70
 */
public class PaseData {
    
    private Connection con = null;
    
    public PaseData(miConexion conexion){
        this.con = conexion.buscarConexion();
    }

    public PaseData() {
    }
    
    public void guardarPase(Pase pase) {
        
        String sql="SELECT INTO pases(ID_Socio, CantidadEntradas, Fecha_Inicio, Fecha_Fin, Costo, Estado) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, pase.getSocio());
            ps.setInt(2, pase.getCantidadPases());
            ps.setDate(3, java.sql.Date.valueOf(pase.getFechaInicio()));
            ps.setDate(4, java.sql.Date.valueOf(pase.getFechaFin()));
            ps.setDouble(5, pase.getCosto());
            ps.setBoolean(6, pase.isEstado());
            
            int filas=ps.executeUpdate();
            
            if (filas > 0) {
            
                System.out.println("Pase guardado correctamente");
                
            } else {
            
                System.out.println("No se ha podido guardar el pase");
            
            }
            
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al intentar guardar pase " + ex.getMessage());
        
        }
        
    }
    
    public void actualizarPase(Pase pase) {
    
        String sql="UPDATE pases SET CantidadEntradas, Fecha_Inicio, Fecha_Fin, Costo WHERE ID_Pase = ?";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, pase.getCantidadPases());
            ps.setDate(2, java.sql.Date.valueOf(pase.getFechaInicio()));
            ps.setDate(3, java.sql.Date.valueOf(pase.getFechaFin()));
            ps.setDouble(4, pase.getCosto());
            
            int filas=ps.executeUpdate();
            
            if (filas > 0) {
            
                System.out.println("Pase actualizado correctamente");
            
            } else if (filas==0) {
            
                System.out.println("No se encontró el ID");
            
            } else {
            
                System.out.println("No se pudo actualizar el pase");
            
            }
            
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al intentar actualizar el pase " + ex.getMessage());
            
        }
        
        
    }
    
    public void borrarPase(int idPase) {
    
    String sql="DELETE FROM pases WHERE ID_Pse = ?";
    
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idPase);
            
            int filas=ps.executeUpdate();
            
            if (filas > 0) {
            
                System.out.println("Pase borrado correctamente");
            
            } else {
            
                System.out.println("No se pudo borrar el pase");
                
            }
            
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al intentar borrar el Pase " + ex.getMessage());
            
        }
    
    }
    
    public ArrayList<Pase> listarPases() {
    
        String sql="SELECT * FROM pases";
        
        ArrayList<Pase> pases=new ArrayList<>();
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()) {
            
            Pase p=new Pase();
            p.setIdPase(rs.getInt("ID_Pase"));
            p.setSocio(rs.getInt("ID_Socio"));
            p.setCantidadPases(rs.getInt("CantidadEntradas"));
            p.setFechaInicio(rs.getDate("Fecha_Inicio").toLocalDate());
            p.setFechaFin(rs.getDate("Fecha_Fin").toLocalDate());
            p.setCosto(rs.getDouble("Costo"));
            p.setEstado(rs.getBoolean("Estado"));
            
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al listar los pases " + ex.getMessage());
        }
        
        return pases;
    }
    
    public ArrayList<Pase> buscarPasesPorSocio(int socio) {
    
        String sql="SELECT * FROM pases WHERE ID_Socio = ?";
        
        ArrayList<Pase> pases=new ArrayList<>();
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, socio);
        
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()) {
            
            Pase p=new Pase();
            p.setIdPase(rs.getInt("ID_Pase"));
            p.setSocio(rs.getInt("ID_Socio"));
            p.setCantidadPases(rs.getInt("CantidadEntradas"));
            p.setFechaInicio(rs.getDate("Fecha_Inicio").toLocalDate());
            p.setFechaFin(rs.getDate("Fecha_Fin").toLocalDate());
            p.setCosto(rs.getDouble("Costo"));
            p.setEstado(rs.getBoolean("Estado"));
            
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        
            JOptionPane.showMessageDialog(null, "Error al intentar buscar pases por socio " + ex.getMessage());
            
        }
        
        return pases;
    }
    
}
