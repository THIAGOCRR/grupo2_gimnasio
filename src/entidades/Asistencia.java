/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;

/**
 *
 * @author crn70
 */
public class Asistencia {
    
    private int idAsistencia = -1;
    private int socio;
    private int clase;
    private LocalDate fechaAsistencia;

    public Asistencia(int idAsistencia, int socio, int clase, LocalDate fechaAsistencia) {
        this.idAsistencia = idAsistencia;
        this.socio = socio;
        this.clase = clase;
        this.fechaAsistencia = fechaAsistencia;
    }

    public Asistencia(int socio, int clase, LocalDate fechaAsistencia) {
        this.socio = socio;
        this.clase = clase;
        this.fechaAsistencia = fechaAsistencia;
    }

    public Asistencia() {
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public int getSocio() {
        return socio;
    }

    public void setSocio(int socio) {
        this.socio = socio;
    }

    public int getClase() {
        return clase;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }

    public LocalDate getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(LocalDate fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }
    
}
