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
@Table(name = "tc_facultad")
public class TcFacultad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFacultad;

    @Column(length = 150)
    @NotNull
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idCentro")
    private TcCentro tc_centro;

    public Long getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Long idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TcCentro getTc_centro() {
        return tc_centro;
    }

    public void setTc_centro(TcCentro tc_centro) {
        this.tc_centro = tc_centro;
    }

    
}