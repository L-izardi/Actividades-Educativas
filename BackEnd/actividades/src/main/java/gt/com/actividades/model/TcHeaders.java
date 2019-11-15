package gt.com.actividades.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="header")
public class TcHeaders{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCorrelativo;
    
    @ManyToOne
    @JoinColumn(name = "idCentro")
    @NotNull
    private TcCentro tc_centro;

    @ManyToOne
    @JoinColumn(name = "idCurso")
    @NotNull
    private TcCurso tc_curso;

    @ManyToOne
    @JoinColumn(name = "idCatedratico")
    @NotNull
    private TcCatedratico tc_catedratico;

    
    @Column
    @NotNull
    private int semestre;

    @Column
    @NotNull
    private String seccion;

    @Column
    @NotNull
    private String horario;

    @Column
    @NotNull
    private String estado;

    public Long getIdCorrelativo() {
        return idCorrelativo;
    }

    public void setIdCorrelativo(Long idCorrelativo) {
        this.idCorrelativo = idCorrelativo;
    }

    public TcCentro getTc_centro() {
        return tc_centro;
    }

    public void setTc_centro(TcCentro tc_centro) {
        this.tc_centro = tc_centro;
    }

    public TcCurso getTc_curso() {
        return tc_curso;
    }

    public void setTc_curso(TcCurso tc_curso) {
        this.tc_curso = tc_curso;
    }

    public TcCatedratico getTc_catedratico() {
        return tc_catedratico;
    }

    public void setTc_catedratico(TcCatedratico tc_catedratico) {
        this.tc_catedratico = tc_catedratico;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}