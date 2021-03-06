package gt.edu.umg.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    
    @ManyToOne
    @NotNull
    @JoinColumn(name = "idCorrelativo")
    private TcHeaders tcHeaders;
    
    @Transient
    private List<TcDetalle> detalle = new ArrayList<>();
    
    public List<TcDetalle> getDetalle(){
        return this.detalle;
    }
    
    public void setDetalle(List<TcDetalle> detalle){
        this.detalle=detalle;
    }

    public void addDetalle(TcDetalle detalle){
        this.detalle.add(detalle);
        detalle.setTcTema(null);
    }

    public void removeDetalle(TcDetalle detalle){
        this.detalle.remove(detalle);
        detalle.setTcTema(null);
    }

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

    public TcHeaders getTcHeaders() {
        return tcHeaders;
    }

    public void setTcHeaders(TcHeaders tcHeaders) {
        this.tcHeaders = tcHeaders;
    }

       

}