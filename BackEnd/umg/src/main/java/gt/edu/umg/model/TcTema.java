package gt.edu.umg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tc_tema")
public class TcTema{
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTema;

    @Column(length = 150)
    @NotNull
    private String nombre;

    @Column(length = 150)
    @NotNull
    private String estado;

    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

       

}