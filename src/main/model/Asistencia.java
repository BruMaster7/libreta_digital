package main.model;

import java.util.Date;

public class Asistencia {
    private int asistenciaId;
    private int usuarioId;
    private int cursoId;
    private Date fechaClase;
    private String estadoAsistencia; // "Presente", "Ausente", o null

    // Getters y setters
    public int getAsistenciaId() { return asistenciaId; }
    public void setAsistenciaId(int asistenciaId) { this.asistenciaId = asistenciaId; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getCursoId() { return cursoId; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }

    public Date getFechaClase() { return fechaClase; }
    public void setFechaClase(Date fechaClase) { this.fechaClase = fechaClase; }

    public String getEstadoAsistencia() { return estadoAsistencia; }
    public void setEstadoAsistencia(String estadoAsistencia) { this.estadoAsistencia = estadoAsistencia; }
}

