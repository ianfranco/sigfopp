/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "caja_recaudacion", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CajaRecaudacion.findAll", query = "SELECT c FROM CajaRecaudacion c")
    , @NamedQuery(name = "CajaRecaudacion.findAllByCuenta", query = "SELECT c FROM CajaRecaudacion c WHERE c.cajaRecaudacionIdCuenta =:idCuenta")
    , @NamedQuery(name = "CajaRecaudacion.findByCajaRecaudacionId", query = "SELECT c FROM CajaRecaudacion c WHERE c.cajaRecaudacionId = :cajaRecaudacionId")
    , @NamedQuery(name = "CajaRecaudacion.findByCajaRecaudacionIdUsuario", query = "SELECT c FROM CajaRecaudacion c WHERE c.cajaRecaudacionIdUsuario = :cajaRecaudacionIdUsuario AND c.cajaRecaudacionActiva = 1")
    , @NamedQuery(name = "CajaRecaudacion.findByCajaRecaudacionNombre", query = "SELECT c FROM CajaRecaudacion c WHERE c.cajaRecaudacionNombre = :cajaRecaudacionNombre")
    , @NamedQuery(name = "CajaRecaudacion.findByCajaRecaudacionTieneEgresos", query = "SELECT c FROM CajaRecaudacion c WHERE c.cajaRecaudacionTieneEgresos = :cajaRecaudacionTieneEgresos")
    , @NamedQuery(name = "CajaRecaudacion.findByCajaRecaudacionActiva", query = "SELECT c FROM CajaRecaudacion c WHERE c.cajaRecaudacionActiva = :cajaRecaudacionActiva")
    })
public class CajaRecaudacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "caja_recaudacion_id")
    private Integer cajaRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "caja_recaudacion_nombre")
    private String cajaRecaudacionNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "caja_recaudacion_tiene_egresos")
    private boolean cajaRecaudacionTieneEgresos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "caja_recaudacion_activa")
    private boolean cajaRecaudacionActiva;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "egresoCajaRecaudacionIdCaja")
    @OrderBy("egresoCajaRecaudacionNumeroOrden ASC")
    private List<EgresoCajaRecaudacion> egresoCajaRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recaudacionIdCaja")
    private List<Recaudacion> recaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cajaProcesoIdCaja")
    private List<CajaProceso> cajaProcesoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resumenRecaudacionIdCaja")
    private List<ResumenRecaudacion> resumenRecaudacionList;
    @JoinColumn(name = "caja_recaudacion_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta cajaRecaudacionIdCuenta;
    @JoinColumn(name = "caja_recaudacion_id_terminal", referencedColumnName = "terminal_id", nullable = false)
    @ManyToOne(optional = false)
    private Terminal cajaRecaudacionIdTerminal;
    @JoinColumn(name = "caja_recaudacion_id_usuario", referencedColumnName = "usuario_id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario cajaRecaudacionIdUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioCajaIdCaja")
    private List<InventarioCaja> inventarioCajaList;

    public CajaRecaudacion() {
    }

    public CajaRecaudacion(Integer cajaRecaudacionId) {
        this.cajaRecaudacionId = cajaRecaudacionId;
    }

    public CajaRecaudacion(Integer cajaRecaudacionId, String cajaRecaudacionNombre, boolean cajaRecaudacionTieneEgresos, boolean cajaRecaudacionActiva) {
        this.cajaRecaudacionId = cajaRecaudacionId;
        this.cajaRecaudacionNombre = cajaRecaudacionNombre;
        this.cajaRecaudacionTieneEgresos = cajaRecaudacionTieneEgresos;
        this.cajaRecaudacionActiva = cajaRecaudacionActiva;
    }

    public Integer getCajaRecaudacionId() {
        return cajaRecaudacionId;
    }

    public void setCajaRecaudacionId(Integer cajaRecaudacionId) {
        this.cajaRecaudacionId = cajaRecaudacionId;
    }

    public String getCajaRecaudacionNombre() {
        return cajaRecaudacionNombre;
    }

    public void setCajaRecaudacionNombre(String cajaRecaudacionNombre) {
        this.cajaRecaudacionNombre = cajaRecaudacionNombre;
    }

    public boolean getCajaRecaudacionTieneEgresos() {
        return cajaRecaudacionTieneEgresos;
    }

    public void setCajaRecaudacionTieneEgresos(boolean cajaRecaudacionTieneEgresos) {
        this.cajaRecaudacionTieneEgresos = cajaRecaudacionTieneEgresos;
    }

    public boolean getCajaRecaudacionActiva() {
        return cajaRecaudacionActiva;
    }

    public void setCajaRecaudacionActiva(boolean cajaRecaudacionActiva) {
        this.cajaRecaudacionActiva = cajaRecaudacionActiva;
    }

    @XmlTransient
    public List<EgresoCajaRecaudacion> getEgresoCajaRecaudacionList() {
        return egresoCajaRecaudacionList;
    }

    public void setEgresoCajaRecaudacionList(List<EgresoCajaRecaudacion> egresoCajaRecaudacionList) {
        this.egresoCajaRecaudacionList = egresoCajaRecaudacionList;
    }

    @XmlTransient
    public List<Recaudacion> getRecaudacionList() {
        return recaudacionList;
    }

    public void setRecaudacionList(List<Recaudacion> recaudacionList) {
        this.recaudacionList = recaudacionList;
    }

    @XmlTransient
    public List<CajaProceso> getCajaProcesoList() {
        return cajaProcesoList;
    }

    public void setCajaProcesoList(List<CajaProceso> cajaProcesoList) {
        this.cajaProcesoList = cajaProcesoList;
    }

    @XmlTransient
    public List<ResumenRecaudacion> getResumenRecaudacionList() {
        return resumenRecaudacionList;
    }

    public void setResumenRecaudacionList(List<ResumenRecaudacion> resumenRecaudacionList) {
        this.resumenRecaudacionList = resumenRecaudacionList;
    }

    public Cuenta getCajaRecaudacionIdCuenta() {
        return cajaRecaudacionIdCuenta;
    }

    public void setCajaRecaudacionIdCuenta(Cuenta cajaRecaudacionIdCuenta) {
        this.cajaRecaudacionIdCuenta = cajaRecaudacionIdCuenta;
    }

    public Terminal getCajaRecaudacionIdTerminal() {
        return cajaRecaudacionIdTerminal;
    }

    public void setCajaRecaudacionIdTerminal(Terminal cajaRecaudacionIdTerminal) {
        this.cajaRecaudacionIdTerminal = cajaRecaudacionIdTerminal;
    }

    public Usuario getCajaRecaudacionIdUsuario() {
        return cajaRecaudacionIdUsuario;
    }

    public void setCajaRecaudacionIdUsuario(Usuario cajaRecaudacionIdUsuario) {
        this.cajaRecaudacionIdUsuario = cajaRecaudacionIdUsuario;
    }

    @XmlTransient
    public List<InventarioCaja> getInventarioCajaList() {
        return inventarioCajaList;
    }

    public void setInventarioCajaList(List<InventarioCaja> inventarioCajaList) {
        this.inventarioCajaList = inventarioCajaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cajaRecaudacionId != null ? cajaRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CajaRecaudacion)) {
            return false;
        }
        CajaRecaudacion other = (CajaRecaudacion) object;
        if ((this.cajaRecaudacionId == null && other.cajaRecaudacionId != null) || (this.cajaRecaudacionId != null && !this.cajaRecaudacionId.equals(other.cajaRecaudacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CajaRecaudacion[ cajaRecaudacionId=" + cajaRecaudacionId + " ]";
    }

}
