package gt.com.actividades.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tc_detalle")
public class TcDetalle{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCorrelativo;
    
    @Column
    @NotNull
    private int mes;

    @ManyToOne
    @JoinColumn(name = "idTema")
    @NotNull
    private TcTema tcTema;

    @Column
    @NotNull
    private String subtema;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesarrollar;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRevision;
    
    @Column
    private int porcAvanceSemanal;

    @Column
    private int porcAvanceMensual;

    @Column
    private String estado;

    public Long getIdCorrelativo() {
        return idCorrelativo;
    }

    public void setIdCorrelativo(Long idCorrelativo) {
        this.idCorrelativo = idCorrelativo;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public TcTema getTcTema() {
        return tcTema;
    }

    public void setTcTema(TcTema tcTema) {
        this.tcTema = tcTema;
    }

    public String getSubtema() {
        return subtema;
    }

    public void setSubtema(String subtema) {
        this.subtema = subtema;
    }

    public Date getFechaDesarrollar() {
        return fechaDesarrollar;
    }

    public void setFechaDesarrollar(Date fechaDesarrollar) {
        this.fechaDesarrollar = fechaDesarrollar;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public int getPorcAvanceSemanal() {
        return porcAvanceSemanal;
    }

    public void setPorcAvanceSemanal(int porcAvanceSemanal) {
        this.porcAvanceSemanal = porcAvanceSemanal;
    }

    public int getPorcAvanceMensual() {
        return porcAvanceMensual;
    }

    public void setPorcAvanceMensual(int porcAvanceMensual) {
        this.porcAvanceMensual = porcAvanceMensual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}