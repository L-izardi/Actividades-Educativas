package gt.edu.umg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tc_ciclo")
public class TcCiclo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCiclo;

    @Column(length = 150)
    @NotNull
    private String ciclo1;

    public Long getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Long idCiclo) {
        this.idCiclo = idCiclo;
    }

    public String getCiclo1() {
        return ciclo1;
    }

    public void setCiclo1(String ciclo1) {
        this.ciclo1 = ciclo1;
    }

    
}