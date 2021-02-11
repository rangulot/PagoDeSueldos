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
@Table(name = "complementos", catalog = "pago_de_sueldos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Complementos.findAll", query = "SELECT c FROM Complementos c")
    , @NamedQuery(name = "Complementos.findByIdcomplemento", query = "SELECT c FROM Complementos c WHERE c.idcomplemento = :idcomplemento")
    , @NamedQuery(name = "Complementos.findByNombre", query = "SELECT c FROM Complementos c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Complementos.findByDescripcion", query = "SELECT c FROM Complementos c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Complementos.findByValor", query = "SELECT c FROM Complementos c WHERE c.valor = :valor")
    , @NamedQuery(name = "Complementos.findByFechaActualizacion", query = "SELECT c FROM Complementos c WHERE c.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "Complementos.findByEstado", query = "SELECT c FROM Complementos c WHERE c.estado = :estado")})
public class Complementos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomplemento", nullable = false)
    private Integer idcomplemento;
    @Column(name = "nombre", length = 255)
    private String nombre;
    @Column(name = "descripcion", length = 255)
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor", precision = 22)
    private Double valor;
    @Column(name = "fecha_actualizacion", length = 255)
    private String fechaActualizacion;
    @Column(name = "estado")
    private Integer estado;
    @OneToMany(mappedBy = "idcomplemento")
    private List<Contratos> contratosList;

    public Complementos() {
    }

    public Complementos(Integer idcomplemento) {
        this.idcomplemento = idcomplemento;
    }

    public Integer getIdcomplemento() {
        return idcomplemento;
    }

    public void setIdcomplemento(Integer idcomplemento) {
        this.idcomplemento = idcomplemento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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
        hash += (idcomplemento != null ? idcomplemento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Complementos)) {
            return false;
        }
        Complementos other = (Complementos) object;
        if ((this.idcomplemento == null && other.idcomplemento != null) || (this.idcomplemento != null && !this.idcomplemento.equals(other.idcomplemento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Complementos[ idcomplemento=" + idcomplemento + " ]";
    }
    
}
