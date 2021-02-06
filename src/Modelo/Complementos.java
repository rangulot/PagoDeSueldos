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
@Table(name = "complementos")
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
    @Column(name = "idcomplemento")
    private Integer idcomplemento;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @OneToMany(mappedBy = "idcomplemento")
    private List<Contratos> contratosList;

    public Complementos() {
    }

    public Complementos(Integer idcomplemento) {
        this.idcomplemento = idcomplemento;
    }

    public Complementos(Integer idcomplemento, String nombre, String descripcion, double valor, String fechaActualizacion, int estado) {
        this.idcomplemento = idcomplemento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fechaActualizacion = fechaActualizacion;
        this.estado = estado;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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
