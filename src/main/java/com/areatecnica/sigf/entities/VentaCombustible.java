/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "venta_combustible", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaCombustible.findAll", query = "SELECT v FROM VentaCombustible v")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleId", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleId = :ventaCombustibleId")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleTerminalFecha", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleIdSurtidor.surtidorIdTerminal = :surtidorIdTerminal AND v.ventaCombustibleFecha=:ventaCombustibleFecha")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleTerminalRecaudado", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleIdSurtidor.surtidorIdTerminal = :surtidorIdTerminal AND v.ventaCombustibleRecaudado = :ventaCombustibleRecaudado")    
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleBusRecaudado", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleIdBus = :ventaCombustibleIdBus AND v.ventaCombustibleRecaudado = :ventaCombustibleRecaudado")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleFecha", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleFecha = :ventaCombustibleFecha")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustiblePrecio", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustiblePrecio = :ventaCombustiblePrecio")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleCantidad", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleCantidad = :ventaCombustibleCantidad")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleTotal", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleTotal = :ventaCombustibleTotal")
    , @NamedQuery(name = "VentaCombustible.findByVentaCombustibleNumeroBoleta", query = "SELECT v FROM VentaCombustible v WHERE v.ventaCombustibleNumeroBoleta = :ventaCombustibleNumeroBoleta")})
public class VentaCombustible extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "venta_combustible_id")
    private Integer ventaCombustibleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_fecha")
    @Temporal(TemporalType.DATE)
    private Date ventaCombustibleFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_hora")
    @Temporal(TemporalType.TIME)
    private Date ventaCombustibleHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_precio")
    private float ventaCombustiblePrecio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_cantidad")
    private float ventaCombustibleCantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_total")
    private int ventaCombustibleTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_numero_boleta")
    private int ventaCombustibleNumeroBoleta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_combustible_recaudado")
    private boolean ventaCombustibleRecaudado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recaudacionCombustibleIdVentaCombustible")
    private List<RecaudacionCombustible> recaudacionCombustibleList;
    @JoinColumn(name = "venta_combustible_id_bus", referencedColumnName = "bus_id", nullable = false)
    @ManyToOne(optional = false)
    private Bus ventaCombustibleIdBus;
    @JoinColumn(name = "venta_combustible_id_surtidor", referencedColumnName = "surtidor_id", nullable = false)
    @ManyToOne(optional = false)
    private Surtidor ventaCombustibleIdSurtidor;

    public VentaCombustible() {
    }

    public VentaCombustible(Integer ventaCombustibleId) {
        this.ventaCombustibleId = ventaCombustibleId;
    }

    public VentaCombustible(Integer ventaCombustibleId, Date ventaCombustibleFecha, Date ventaCombustibleHora, float ventaCombustiblePrecio, float ventaCombustibleCantidad, int ventaCombustibleTotal, int ventaCombustibleNumeroBoleta, boolean ventaCombustibleRecaudado) {
        this.ventaCombustibleId = ventaCombustibleId;
        this.ventaCombustibleFecha = ventaCombustibleFecha;
        this.ventaCombustibleHora = ventaCombustibleHora;
        this.ventaCombustiblePrecio = ventaCombustiblePrecio;
        this.ventaCombustibleCantidad = ventaCombustibleCantidad;
        this.ventaCombustibleTotal = ventaCombustibleTotal;
        this.ventaCombustibleNumeroBoleta = ventaCombustibleNumeroBoleta;
        this.ventaCombustibleRecaudado = ventaCombustibleRecaudado;
    }

    public Integer getVentaCombustibleId() {
        return ventaCombustibleId;
    }

    public void setVentaCombustibleId(Integer ventaCombustibleId) {
        this.ventaCombustibleId = ventaCombustibleId;
    }

    public Date getVentaCombustibleFecha() {
        return ventaCombustibleFecha;
    }

    public void setVentaCombustibleFecha(Date ventaCombustibleFecha) {
        this.ventaCombustibleFecha = ventaCombustibleFecha;
    }

    public Date getVentaCombustibleHora() {
        return ventaCombustibleHora;
    }

    public void setVentaCombustibleHora(Date ventaCombustibleHora) {
        this.ventaCombustibleHora = ventaCombustibleHora;
    }
    
    public float getVentaCombustiblePrecio() {
        return ventaCombustiblePrecio;
    }

    public void setVentaCombustiblePrecio(float ventaCombustiblePrecio) {
        this.ventaCombustiblePrecio = ventaCombustiblePrecio;
    }

    public float getVentaCombustibleCantidad() {
        return ventaCombustibleCantidad;
    }

    public void setVentaCombustibleCantidad(float ventaCombustibleCantidad) {
        this.ventaCombustibleCantidad = ventaCombustibleCantidad;
    }

    public int getVentaCombustibleTotal() {
        return ventaCombustibleTotal;
    }

    public void setVentaCombustibleTotal(int ventaCombustibleTotal) {
        this.ventaCombustibleTotal = ventaCombustibleTotal;
    }

    public int getVentaCombustibleNumeroBoleta() {
        return ventaCombustibleNumeroBoleta;
    }

    public void setVentaCombustibleNumeroBoleta(int ventaCombustibleNumeroBoleta) {
        this.ventaCombustibleNumeroBoleta = ventaCombustibleNumeroBoleta;
    }
    
    public boolean getVentaCombustibleRecaudado() {
        return ventaCombustibleRecaudado;
    }

    public void setVentaCombustibleRecaudado(boolean ventaCombustibleRecaudado) {
        this.ventaCombustibleRecaudado = ventaCombustibleRecaudado;
    }
    
    @XmlTransient
    public List<RecaudacionCombustible> getRecaudacionCombustibleList() {
        return recaudacionCombustibleList;
    }

    public void setRecaudacionCombustibleList(List<RecaudacionCombustible> recaudacionCombustibleList) {
        this.recaudacionCombustibleList = recaudacionCombustibleList;
    }

    public Bus getVentaCombustibleIdBus() {
        return ventaCombustibleIdBus;
    }

    public void setVentaCombustibleIdBus(Bus ventaCombustibleIdBus) {
        this.ventaCombustibleIdBus = ventaCombustibleIdBus;
    }

    public Surtidor getVentaCombustibleIdSurtidor() {
        return ventaCombustibleIdSurtidor;
    }

    public void setVentaCombustibleIdSurtidor(Surtidor ventaCombustibleIdSurtidor) {
        this.ventaCombustibleIdSurtidor = ventaCombustibleIdSurtidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaCombustibleId != null ? ventaCombustibleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaCombustible)) {
            return false;
        }
        VentaCombustible other = (VentaCombustible) object;
        if ((this.ventaCombustibleId == null && other.ventaCombustibleId != null) || (this.ventaCombustibleId != null && !this.ventaCombustibleId.equals(other.ventaCombustibleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.VentaCombustible[ ventaCombustibleId=" + ventaCombustibleId + " ]";
    }
    
}
