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
@Table(name = "empleados", catalog = "pago_de_sueldos", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e")
    , @NamedQuery(name = "Empleados.findByIdempleado", query = "SELECT e FROM Empleados e WHERE e.idempleado = :idempleado")
    , @NamedQuery(name = "Empleados.findByNombres", query = "SELECT e FROM Empleados e WHERE e.nombres = :nombres")
    , @NamedQuery(name = "Empleados.findByApellidos", query = "SELECT e FROM Empleados e WHERE e.apellidos = :apellidos")
    , @NamedQuery(name = "Empleados.findByDireccion", query = "SELECT e FROM Empleados e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Empleados.findByDni", query = "SELECT e FROM Empleados e WHERE e.dni = :dni")
    , @NamedQuery(name = "Empleados.findByCuentaCorriente", query = "SELECT e FROM Empleados e WHERE e.cuentaCorriente = :cuentaCorriente")
    , @NamedQuery(name = "Empleados.findByTelefono", query = "SELECT e FROM Empleados e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empleados.findByFechaActualizacion", query = "SELECT e FROM Empleados e WHERE e.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "Empleados.findByEstado", query = "SELECT e FROM Empleados e WHERE e.estado = :estado")})
public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idempleado", nullable = false)
    private Integer idempleado;
    @Basic(optional = false)
    @Column(name = "nombres", nullable = false, length = 255)
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos", nullable = false, length = 255)
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;
    @Basic(optional = false)
    @Column(name = "dni", nullable = false, length = 20)
    private String dni;
    @Basic(optional = false)
    @Column(name = "cuenta_corriente", nullable = false, length = 20)
    private String cuentaCorriente;
    @Basic(optional = false)
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;
    @Basic(optional = false)
    @Column(name = "fecha_actualizacion", nullable = false, length = 255)
    private String fechaActualizacion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private int estado;
    @OneToMany(mappedBy = "idempleado")
    private List<Contratos> contratosList;
    @OneToMany(mappedBy = "idempleado")
    private List<Detallesnominas> detallesnominasList;

    public Empleados() {
    }

    public Empleados(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public Empleados(Integer idempleado, String nombres, String apellidos, String direccion, String dni, String cuentaCorriente, String telefono, String fechaActualizacion, int estado) {
        this.idempleado = idempleado;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.cuentaCorriente = cuentaCorriente;
        this.telefono = telefono;
        this.fechaActualizacion = fechaActualizacion;
        this.estado = estado;
    }

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(String cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        hash += (idempleado != null ? idempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.idempleado == null && other.idempleado != null) || (this.idempleado != null && !this.idempleado.equals(other.idempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Empleados[ idempleado=" + idempleado + " ]";
    }
    
}
