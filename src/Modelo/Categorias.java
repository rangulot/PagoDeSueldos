/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hacke
 */
@Entity
@Table(name = "categorias", catalog = "pago_de_sueldos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c")
    , @NamedQuery(name = "Categorias.findByIdcategoria", query = "SELECT c FROM Categorias c WHERE c.idcategoria = :idcategoria")
    , @NamedQuery(name = "Categorias.findByNombre", query = "SELECT c FROM Categorias c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Categorias.findBySueldobasico", query = "SELECT c FROM Categorias c WHERE c.sueldobasico = :sueldobasico")
    , @NamedQuery(name = "Categorias.findByPuestodestino", query = "SELECT c FROM Categorias c WHERE c.puestodestino = :puestodestino")
    , @NamedQuery(name = "Categorias.findByFechaActualizacion", query = "SELECT c FROM Categorias c WHERE c.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "Categorias.findByEstado", query = "SELECT c FROM Categorias c WHERE c.estado = :estado")})
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcategoria", nullable = false)
    private Integer idcategoria;
    @Column(name = "nombre", length = 255)
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sueldobasico", precision = 22)
    private Double sueldobasico;
    @Column(name = "puestodestino", length = 255)
    private String puestodestino;
    @Column(name = "fecha_actualizacion", length = 255)
    private String fechaActualizacion;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne
    private Contratos idcontrato;
    @OneToMany(mappedBy = "idcategoria")
    private List<Contratos> contratosList;

    public Categorias() {
    }

    public Categorias(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSueldobasico() {
        return sueldobasico;
    }

    public void setSueldobasico(Double sueldobasico) {
        this.sueldobasico = sueldobasico;
    }

    public String getPuestodestino() {
        return puestodestino;
    }

    public void setPuestodestino(String puestodestino) {
        this.puestodestino = puestodestino;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Contratos getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Contratos idcontrato) {
        this.idcontrato = idcontrato;
    }

    @XmlTransient
    public List<Contratos> getContratosList() {
        return contratosList;
    }

    public void setContratosList(List<Contratos> contratosList) {
        this.contratosList = contratosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategoria != null ? idcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        if ((this.idcategoria == null && other.idcategoria != null) || (this.idcategoria != null && !this.idcategoria.equals(other.idcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Categorias[ idcategoria=" + idcategoria + " ]";
    }
    
}
