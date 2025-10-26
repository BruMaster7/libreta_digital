package main.model;

import java.sql.Timestamp;

public class Visado {
    private int visadoId;
    private int usuarioIdAdministrador;
    private Timestamp fechaVisado;
    private int idCurso;
    private boolean planificacion;
    private boolean parciales;
    private boolean desarrollo;
    private boolean promedios;

    public Visado() {}

    public Visado(int usuarioIdAdministrador, Timestamp fechaVisado,
                  boolean planificacion, boolean parciales, boolean desarrollo, boolean promedios, int idCurso) {
        this.usuarioIdAdministrador = usuarioIdAdministrador;
        this.fechaVisado = fechaVisado;
        this.planificacion = planificacion;
        this.parciales = parciales;
        this.desarrollo = desarrollo;
        this.promedios = promedios;
        this.idCurso = idCurso;
    }

    public int getVisadoId() { return visadoId; }
    public void setVisadoId(int visadoId) { this.visadoId = visadoId; }

    public int getUsuarioIdAdministrador() { return usuarioIdAdministrador; }
    public void setUsuarioIdAdministrador(int usuarioIdAdministrador) { this.usuarioIdAdministrador = usuarioIdAdministrador; }

    public Timestamp getFechaVisado() { return fechaVisado; }
    public void setFechaVisado(Timestamp fechaVisado) { this.fechaVisado = fechaVisado; }

    public boolean isPlanificacion() { return planificacion; }
    public void setPlanificacion(boolean planificacion) { this.planificacion = planificacion; }

    public boolean isParciales() { return parciales; }
    public void setParciales(boolean parciales) { this.parciales = parciales; }

    public boolean isDesarrollo() { return desarrollo; }
    public void setDesarrollo(boolean desarrollo) { this.desarrollo = desarrollo; }

    public boolean isPromedios() { return promedios; }
    public void setPromedios(boolean promedios) { this.promedios = promedios; }
    
    public int getCursoId () {
    	return this.idCurso;
    }
    
    public void setCursoId (int cursoid) {
    	this.idCurso = cursoid;
    }
}
