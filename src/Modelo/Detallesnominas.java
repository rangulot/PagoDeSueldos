/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ROBERTO
 */
@Entity
@Table(name = "detallesnominas", catalog = "pago_de_sueldos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallesnominas.findAll", query = "SELECT d FROM Detallesnominas d")
    , @NamedQuery(name = "Detallesnominas.findByIddetallesnominas", query = "SELECT d FROM Detallesnominas d WHERE d.iddetallesnominas = :iddetallesnominas")
    , @NamedQuery(name = "Detallesnominas.findByResponsable", query = "SELECT d FROM Detallesnominas d WHERE d.responsable = :responsable")
    , @NamedQuery(name = "Detallesnominas.findByFechaActualizacion", query = "SELECT d FROM Detallesnominas d WHERE d.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "Detallesnominas.findByEstado", query = "SELECT d FROM Detallesnominas d WHERE d.estado = :estado")})
public class Detallesnominas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallesnominas", nullable = false)
    private Integer iddetallesnominas;
    @Basic(optional = false)
    @Column(name = "responsable", nullable = false, length = 255)
    private String responsable;
    @Basic(optional = false)
    @Column(name = "fecha_actualizacion", nullable = false, length = 255)
    private String fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private int estado;
    @JoinColumn(name = "idnomina", referencedColumnName = "idnomina")
    @ManyToOne
    private Nominas idnomina;
    @JoinColumn(name = "idempleado", referencedColumnName = "idempleado")
    @ManyToOne
    private Empleados idempleado;
    @JoinColumn(name = "iddeduccion", referencedColumnName = "iddeduccion")
    @ManyToOne
    private Deducciones iddeduccion;

    public Detallesnominas() {
    }

    public Detallesnominas(Integer iddetallesnominas) {
        this.iddetallesnominas = iddetallesnominas;
    }

    public Detallesnominas(Integer iddetallesnominas, String responsable, String fechaActualizacion, int estado) {
        this.iddetallesnominas = iddetallesnominas;
        this.responsable = responsable;
        this.fechaActualizacion = fechaActualizacion;
        this.estado = estado;
    }

    public Integer getIddetallesnominas() {
        return iddetallesnominas;
    }

    public void setIddetallesnominas(Integer iddetallesnominas) {
        this.iddetallesnominas = iddetallesnominas;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
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

    public Nominas getIdnomina() {
        return idnomina;
    }

    public void setIdnomina(Nominas idnomina) {
        this.idnomina = idnomina;
    }

    public Empleados getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleados idempleado) {
        this.idempleado = idempleado;
    }

    public Deducciones getIddeduccion() {
        return iddeduccion;
    }

    public void setIddeduccion(Deducciones iddeduccion) {
        this.iddeduccion = iddeduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetallesnominas != null ? iddetallesnominas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallesnominas)) {
            return false;
        }
        Detallesnominas other = (Detallesnominas) object;
        if ((this.iddetallesnominas == null && other.iddetallesnominas != null) || (this.iddetallesnominas != null && !this.iddetallesnominas.equals(other.iddetallesnominas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Detallesnominas[ iddetallesnominas=" + iddetallesnominas + " ]";
    }
    
}
