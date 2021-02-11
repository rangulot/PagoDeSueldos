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
@Table(name = "deducciones", catalog = "pago_de_sueldos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deducciones.findAll", query = "SELECT d FROM Deducciones d")
    , @NamedQuery(name = "Deducciones.findByIddeduccion", query = "SELECT d FROM Deducciones d WHERE d.iddeduccion = :iddeduccion")
    , @NamedQuery(name = "Deducciones.findByNombre", query = "SELECT d FROM Deducciones d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "Deducciones.findByDescripcion", query = "SELECT d FROM Deducciones d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "Deducciones.findByContingenciasComunes", query = "SELECT d FROM Deducciones d WHERE d.contingenciasComunes = :contingenciasComunes")
    , @NamedQuery(name = "Deducciones.findBySeguridadSocial", query = "SELECT d FROM Deducciones d WHERE d.seguridadSocial = :seguridadSocial")
    , @NamedQuery(name = "Deducciones.findByDesempleo", query = "SELECT d FROM Deducciones d WHERE d.desempleo = :desempleo")
    , @NamedQuery(name = "Deducciones.findByFormacionProfesional", query = "SELECT d FROM Deducciones d WHERE d.formacionProfesional = :formacionProfesional")
    , @NamedQuery(name = "Deducciones.findByFechaActualizacion", query = "SELECT d FROM Deducciones d WHERE d.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "Deducciones.findByEstado", query = "SELECT d FROM Deducciones d WHERE d.estado = :estado")})
public class Deducciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddeduccion", nullable = false)
    private Integer iddeduccion;
    @Column(name = "nombre", length = 255)
    private String nombre;
    @Column(name = "descripcion", length = 255)
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "contingencias_comunes", precision = 22)
    private Double contingenciasComunes;
    @Column(name = "seguridad_social", precision = 22)
    private Double seguridadSocial;
    @Column(name = "desempleo", precision = 22)
    private Double desempleo;
    @Column(name = "formacion_profesional", precision = 22)
    private Double formacionProfesional;
    @Column(name = "fecha_actualizacion", length = 255)
    private String fechaActualizacion;
    @Column(name = "estado")
    private Integer estado;
    @OneToMany(mappedBy = "iddeduccion")
    private List<Detallesnominas> detallesnominasList;

    public Deducciones() {
    }

    public Deducciones(Integer iddeduccion) {
        this.iddeduccion = iddeduccion;
    }

    public Integer getIddeduccion() {
        return iddeduccion;
    }

    public void setIddeduccion(Integer iddeduccion) {
        this.iddeduccion = iddeduccion;
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

    public Double getContingenciasComunes() {
        return contingenciasComunes;
    }

    public void setContingenciasComunes(Double contingenciasComunes) {
        this.contingenciasComunes = contingenciasComunes;
    }

    public Double getSeguridadSocial() {
        return seguridadSocial;
    }

    public void setSeguridadSocial(Double seguridadSocial) {
        this.seguridadSocial = seguridadSocial;
    }

    public Double getDesempleo() {
        return desempleo;
    }

    public void setDesempleo(Double desempleo) {
        this.desempleo = desempleo;
    }

    public Double getFormacionProfesional() {
        return formacionProfesional;
    }

    public void setFormacionProfesional(Double formacionProfesional) {
        this.formacionProfesional = formacionProfesional;
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
    public List<Detallesnominas> getDetallesnominasList() {
        return detallesnominasList;
    }

    public void setDetallesnominasList(List<Detallesnominas> detallesnominasList) {
        this.detallesnominasList = detallesnominasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddeduccion != null ? iddeduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deducciones)) {
            return false;
        }
        Deducciones other = (Deducciones) object;
        if ((this.iddeduccion == null && other.iddeduccion != null) || (this.iddeduccion != null && !this.iddeduccion.equals(other.iddeduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Deducciones[ iddeduccion=" + iddeduccion + " ]";
    }
    
}
