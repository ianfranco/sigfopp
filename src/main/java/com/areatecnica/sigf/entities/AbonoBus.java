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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "abono_bus", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AbonoBus.findAll", query = "SELECT a FROM AbonoBus a")
    , @NamedQuery(name = "AbonoBus.findByAbonoBusId", query = "SELECT a FROM AbonoBus a WHERE a.abonoBusId = :abonoBusId")
    , @NamedQuery(name = "AbonoBus.findByAbonoBusFechaInicio", query = "SELECT a FROM AbonoBus a WHERE a.abonoBusFechaInicio = :abonoBusFechaInicio")
    , @NamedQuery(name = "AbonoBus.findByAbonoBusFechaTermino", query = "SELECT a FROM AbonoBus a WHERE a.abonoBusFechaTermino = :abonoBusFechaTermino")
    , @NamedQuery(name = "AbonoBus.findByAbonoBusCuotaActual", query = "SELECT a FROM AbonoBus a WHERE a.abonoBusCuotaActual = :abonoBusCuotaActual")
    , @NamedQuery(name = "AbonoBus.findByAbonoBusTotalCuotas", query = "SELECT a FROM AbonoBus a WHERE a.abonoBusTotalCuotas = :abonoBusTotalCuotas")
    , @NamedQuery(name = "AbonoBus.findByAbonoBusMontoFijo", query = "SELECT a FROM AbonoBus a WHERE a.abonoBusMontoFijo = :abonoBusMontoFijo")
    , @NamedQuery(name = "AbonoBus.findByAbonoBusDescripcion", query = "SELECT a FROM AbonoBus a WHERE a.abonoBusDescripcion = :abonoBusDescripcion")
    , @NamedQuery(name = "AbonoBus.findByAbonoBusActivo", query = "SELECT a FROM AbonoBus a WHERE a.abonoBusActivo = :abonoBusActivo")
    })
public class AbonoBus extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "abono_bus_id")
    private Integer abonoBusId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abono_bus_fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date abonoBusFechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abono_bus_fecha_termino")
    @Temporal(TemporalType.DATE)
    private Date abonoBusFechaTermino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abono_bus_cuota_actual")
    private int abonoBusCuotaActual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abono_bus_total_cuotas")
    private int abonoBusTotalCuotas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abono_bus_monto_fijo")
    private int abonoBusMontoFijo;
    @Size(max = 100)
    @Column(name = "abono_bus_descripcion")
    private String abonoBusDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abono_bus_activo")
    private boolean abonoBusActivo;    
    @JoinColumn(name = "abono_bus_id_bus", referencedColumnName = "bus_id", nullable = false)
    @ManyToOne(optional = false)
    private Bus abonoBusIdBus;
    @JoinColumn(name = "abono_bus_id_tipo_abono", referencedColumnName = "tipo_abono_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoAbono abonoBusIdTipoAbono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "abonoLiquidacionIdAbono")
    private List<AbonoLiquidacion> abonoLiquidacionList;

    public AbonoBus() {
    }

    public AbonoBus(Integer abonoBusId) {
        this.abonoBusId = abonoBusId;
    }

    public AbonoBus(Integer abonoBusId, Date abonoBusFechaInicio, Date abonoBusFechaTermino, int abonoBusCuotaActual, int abonoBusTotalCuotas, int abonoBusMontoFijo, boolean abonoBusActivo) {
        this.abonoBusId = abonoBusId;
        this.abonoBusFechaInicio = abonoBusFechaInicio;
        this.abonoBusFechaTermino = abonoBusFechaTermino;
        this.abonoBusCuotaActual = abonoBusCuotaActual;
        this.abonoBusTotalCuotas = abonoBusTotalCuotas;
        this.abonoBusMontoFijo = abonoBusMontoFijo;
        this.abonoBusActivo = abonoBusActivo;
    }

    public Integer getAbonoBusId() {
        return abonoBusId;
    }

    public void setAbonoBusId(Integer abonoBusId) {
        this.abonoBusId = abonoBusId;
    }

    public Date getAbonoBusFechaInicio() {
        return abonoBusFechaInicio;
    }

    public void setAbonoBusFechaInicio(Date abonoBusFechaInicio) {
        this.abonoBusFechaInicio = abonoBusFechaInicio;
    }

    public Date getAbonoBusFechaTermino() {
        return abonoBusFechaTermino;
    }

    public void setAbonoBusFechaTermino(Date abonoBusFechaTermino) {
        this.abonoBusFechaTermino = abonoBusFechaTermino;
    }

    public int getAbonoBusCuotaActual() {
        return abonoBusCuotaActual;
    }

    public void setAbonoBusCuotaActual(int abonoBusCuotaActual) {
        this.abonoBusCuotaActual = abonoBusCuotaActual;
    }

    public int getAbonoBusTotalCuotas() {
        return abonoBusTotalCuotas;
    }

    public void setAbonoBusTotalCuotas(int abonoBusTotalCuotas) {
        this.abonoBusTotalCuotas = abonoBusTotalCuotas;
    }

    public int getAbonoBusMontoFijo() {
        return abonoBusMontoFijo;
    }

    public void setAbonoBusMontoFijo(int abonoBusMontoFijo) {
        this.abonoBusMontoFijo = abonoBusMontoFijo;
    }

    public String getAbonoBusDescripcion() {
        return abonoBusDescripcion;
    }

    public void setAbonoBusDescripcion(String abonoBusDescripcion) {
        this.abonoBusDescripcion = abonoBusDescripcion;
    }

    public boolean getAbonoBusActivo() {
        return abonoBusActivo;
    }

    public void setAbonoBusActivo(boolean abonoBusActivo) {
        this.abonoBusActivo = abonoBusActivo;
    }
    
    public Bus getAbonoBusIdBus() {
        return abonoBusIdBus;
    }

    public void setAbonoBusIdBus(Bus abonoBusIdBus) {
        this.abonoBusIdBus = abonoBusIdBus;
    }

    public TipoAbono getAbonoBusIdTipoAbono() {
        return abonoBusIdTipoAbono;
    }

    public void setAbonoBusIdTipoAbono(TipoAbono abonoBusIdTipoAbono) {
        this.abonoBusIdTipoAbono = abonoBusIdTipoAbono;
    }

    @XmlTransient
    public List<AbonoLiquidacion> getAbonoLiquidacionList() {
        return abonoLiquidacionList;
    }

    public void setAbonoLiquidacionList(List<AbonoLiquidacion> abonoLiquidacionList) {
        this.abonoLiquidacionList = abonoLiquidacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (abonoBusId != null ? abonoBusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbonoBus)) {
            return false;
        }
        AbonoBus other = (AbonoBus) object;
        if ((this.abonoBusId == null && other.abonoBusId != null) || (this.abonoBusId != null && !this.abonoBusId.equals(other.abonoBusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.AbonoBus[ abonoBusId=" + abonoBusId + " ]";
    }
    
}
