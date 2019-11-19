package gt.edu.umg.model;

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
@Table(name = "tc_curso")
public class TcCurso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCurso;

    @Column(length = 150)
    @NotNull
    private String nomCurso;

    @ManyToOne
    @JoinColumn(name = "idCiclo")
    @NotNull
    private TcCiclo tc_ciclo;
    
    @ManyToOne
    @JoinColumn(name = "idFacultad")
    @NotNull
    private TcFacultad tc_facultad;

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomCurso() {
        return nomCurso;
    }

    public void setNomCurso(String nomCurso) {
        this.nomCurso = nomCurso;
    }

    public TcCiclo getTc_ciclo() {
        return tc_ciclo;
    }

    public void setTc_ciclo(TcCiclo tc_ciclo) {
        this.tc_ciclo = tc_ciclo;
    }

    public TcFacultad getTc_facultad() {
        return tc_facultad;
    }

    public void setTc_facultad(TcFacultad tc_facultad) {
        this.tc_facultad = tc_facultad;
    }
   

}
