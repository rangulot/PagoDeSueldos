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
 * @author ROBERTO
 */
@Entity
@Table(name = "nominas", catalog = "pago_de_sueldos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nominas.findAll", query = "SELECT n FROM Nominas n")
    , @NamedQuery(name = "Nominas.findByIdnomina", query = "SELECT n FROM Nominas n WHERE n.idnomina = :idnomina")
    , @NamedQuery(name = "Nominas.findByEncargado", query = "SELECT n FROM Nominas n WHERE n.encargado = :encargado")
    , @NamedQuery(name = "Nominas.findByTotalapagar", query = "SELECT n FROM Nominas n WHERE n.totalapagar = :totalapagar")
    , @NamedQuery(name = "Nominas.findByCortemes", query = "SELECT n FROM Nominas n WHERE n.cortemes = :cortemes")
    , @NamedQuery(name = "Nominas.findByFechaActualizacion", query = "SELECT n FROM Nominas n WHERE n.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "Nominas.findByEstado", query = "SELECT n FROM Nominas n WHERE n.estado = :estado")})
public class Nominas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnomina", nullable = false)
    private Integer idnomina;
    @Column(name = "encargado", length = 255)
    private String encargado;
    @Basic(optional = false)
    @Column(name = "totalapagar", nullable = false)
    private double totalapagar;
    @Column(name = "cortemes", length = 255)
    private String cortemes;
    @Basic(optional = false)
    @Column(name = "fecha_actualizacion", nullable = false, length = 255)
    private String fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private int estado;
    @OneToMany(mappedBy = "idnomina")
    private List<Detallesnominas> detallesnominasList;

    public Nominas() {
    }

    public Nominas(Integer idnomina) {
        this.idnomina = idnomina;
    }

    public Nominas(Integer idnomina, double totalapagar, String fechaActualizacion, int estado) {
        this.idnomina = idnomina;
        this.totalapagar = totalapagar;
        this.fechaActualizacion = fechaActualizacion;
        this.estado = estado;
    }

    public Integer getIdnomina() {
        return idnomina;
    }

    public void setIdnomina(Integer idnomina) {
        this.idnomina = idnomina;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public double getTotalapagar() {
        return totalapagar;
    }

    public void setTotalapagar(double totalapagar) {
        this.totalapagar = totalapagar;
    }

    public String getCortemes() {
        return cortemes;
    }

    public void setCortemes(String cortemes) {
        this.cortemes = cortemes;
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
    public List<Detallesnominas> getDetallesnominasList() {
        return detallesnominasList;
    }

    public void setDetallesnominasList(List<Detallesnominas> detallesnominasList) {
        this.detallesnominasList = detallesnominasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnomina != null ? idnomina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nominas)) {
            return false;
        }
        Nominas other = (Nominas) object;
        if ((this.idnomina == null && other.idnomina != null) || (this.idnomina != null && !this.idnomina.equals(other.idnomina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Nominas[ idnomina=" + idnomina + " ]";
    }
    
}
