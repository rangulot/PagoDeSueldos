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
 * @author ROBERTO
 */
@Entity
@Table(name = "contratos", catalog = "pago_de_sueldos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratos.findAll", query = "SELECT c FROM Contratos c")
    , @NamedQuery(name = "Contratos.findByIdcontrato", query = "SELECT c FROM Contratos c WHERE c.idcontrato = :idcontrato")
    , @NamedQuery(name = "Contratos.findByTipoContrato", query = "SELECT c FROM Contratos c WHERE c.tipoContrato = :tipoContrato")
    , @NamedQuery(name = "Contratos.findByFechaAlta", query = "SELECT c FROM Contratos c WHERE c.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Contratos.findByFechaBaja", query = "SELECT c FROM Contratos c WHERE c.fechaBaja = :fechaBaja")
    , @NamedQuery(name = "Contratos.findByFechaActualizacion", query = "SELECT c FROM Contratos c WHERE c.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "Contratos.findByEstado", query = "SELECT c FROM Contratos c WHERE c.estado = :estado")})
public class Contratos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontrato", nullable = false)
    private Integer idcontrato;
    @Basic(optional = false)
    @Column(name = "tipoContrato", nullable = false, length = 255)
    private String tipoContrato;
    @Basic(optional = false)
    @Column(name = "fecha_alta", nullable = false, length = 255)
    private String fechaAlta;
    @Basic(optional = false)
    @Column(name = "fecha_baja", nullable = false, length = 255)
    private String fechaBaja;
    @Basic(optional = false)
    @Column(name = "fecha_actualizacion", nullable = false, length = 255)
    private String fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private int estado;
    @OneToMany(mappedBy = "idcontrato")
    private List<Categorias> categoriasList;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")
    @ManyToOne
    private Empleados idempleado;
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne
    private Categorias idcategoria;
    @JoinColumn(name = "idcomplemento", referencedColumnName = "idcomplemento")
    @ManyToOne
    private Complementos idcomplemento;

    public Contratos() {
    }

    public Contratos(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Contratos(Integer idcontrato, String tipoContrato, String fechaAlta, String fechaBaja, String fechaActualizacion, int estado) {
        this.idcontrato = idcontrato;
        this.tipoContrato = tipoContrato;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.fechaActualizacion = fechaActualizacion;
        this.estado = estado;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
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
    public List<Categorias> getCategoriasList() {
        return categoriasList;
    }

    public void setCategoriasList(List<Categorias> categoriasList) {
        this.categoriasList = categoriasList;
    }

    public Empleados getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleados idempleado) {
        this.idempleado = idempleado;
    }

    public Categorias getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Categorias idcategoria) {
        this.idcategoria = idcategoria;
    }

    public Complementos getIdcomplemento() {
        return idcomplemento;
    }

    public void setIdcomplemento(Complementos idcomplemento) {
        this.idcomplemento = idcomplemento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrato != null ? idcontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contratos)) {
            return false;
        }
        Contratos other = (Contratos) object;
        if ((this.idcontrato == null && other.idcontrato != null) || (this.idcontrato != null && !this.idcontrato.equals(other.idcontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Contratos[ idcontrato=" + idcontrato + " ]";
    }
    
}
