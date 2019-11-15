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
@Table(name = "tc_centro")
public class TcCentro{
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCentro;

    @Column(length = 150)
    @NotNull
    private String nombre;

    @Column(length = 150)
    @NotNull
    private String direccion;

    @Column(length = 150)
    @NotNull
    private String correo;

    @Column(length = 20)
    @NotNull
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "idDepto")
    @NotNull
    private TcDepartamento tc_departamento;

    public Long getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(Long idCentro) {
        this.idCentro = idCentro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TcDepartamento getTc_departamento() {
        return tc_departamento;
    }

    public void setTc_departamento(TcDepartamento tc_departamento) {
        this.tc_departamento = tc_departamento;
    }
    
    

}