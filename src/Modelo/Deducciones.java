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
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "contingencias_comunes", nullable = false)
    private double contingenciasComunes;
    @Basic(optional = false)
    @Column(name = "seguridad_social", nullable = false)
    private double seguridadSocial;
    @Basic(optional = false)
    @Column(name = "desempleo", nullable = false)
    private double desempleo;
    @Basic(optional = false)
    @Column(name = "formacion_profesional", nullable = false)
    private double formacionProfesional;
    @Basic(optional = false)
    @Column(name = "fecha_actualizacion", nullable = false, length = 255)
    private String fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private int estado;
    @OneToMany(mappedBy = "iddeduccion")
    private List<Detallesnominas> detallesnominasList;

    public Deducciones() {
    }

    public Deducciones(Integer iddeduccion) {
        this.iddeduccion = iddeduccion;
    }

    public Deducciones(Integer iddeduccion, String nombre, String descripcion, double contingenciasComunes, double seguridadSocial, double desempleo, double formacionProfesional, String fechaActualizacion, int estado) {
        this.iddeduccion = iddeduccion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contingenciasComunes = contingenciasComunes;
        this.seguridadSocial = seguridadSocial;
        this.desempleo = desempleo;
        this.formacionProfesional = formacionProfesional;
        this.fechaActualizacion = fechaActualizacion;
        this.estado = estado;
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

    public double getContingenciasComunes() {
        return contingenciasComunes;
    }

    public void setContingenciasComunes(double contingenciasComunes) {
        this.contingenciasComunes = contingenciasComunes;
    }

    public double getSeguridadSocial() {
        return seguridadSocial;
    }

    public void setSeguridadSocial(double seguridadSocial) {
        this.seguridadSocial = seguridadSocial;
    }

    public double getDesempleo() {
        return desempleo;
    }

    public void setDesempleo(double desempleo) {
        this.desempleo = desempleo;
    }

    public double getFormacionProfesional() {
        return formacionProfesional;
    }

    public void setFormacionProfesional(double formacionProfesional) {
        this.formacionProfesional = formacionProfesional;
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
