/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "egreso_bus", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EgresoBus.findAll", query = "SELECT e FROM EgresoBus e")
    , @NamedQuery(name = "EgresoBus.findByEgresoBusId", query = "SELECT e FROM EgresoBus e WHERE e.egresoBusId = :egresoBusId")
    , @NamedQuery(name = "EgresoBus.findByEgresoBusIdBus", query = "SELECT e FROM EgresoBus e WHERE e.egresoBusIdBus = :egresoBusIdBus")
    , @NamedQuery(name = "EgresoBus.findByEgresoBusValorDefecto", query = "SELECT e FROM EgresoBus e WHERE e.egresoBusValorDefecto = :egresoBusValorDefecto")
    , @NamedQuery(name = "EgresoBus.findByEgresoBusPorcentaje", query = "SELECT e FROM EgresoBus e WHERE e.egresoBusPorcentaje = :egresoBusPorcentaje")
    , @NamedQuery(name = "EgresoBus.findByEgresoBusNumeroOrden", query = "SELECT e FROM EgresoBus e WHERE e.egresoBusNumeroOrden = :egresoBusNumeroOrden")
    , @NamedQuery(name = "EgresoBus.findByEgresoBusActivo", query = "SELECT e FROM EgresoBus e WHERE e.egresoBusActivo = :egresoBusActivo")
    })
public class EgresoBus extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "egreso_bus_id")
    private Integer egresoBusId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_bus_valor_defecto")
    private int egresoBusValorDefecto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_bus_porcentaje")
    private BigDecimal egresoBusPorcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_bus_numero_orden")
    private int egresoBusNumeroOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_bus_activo")
    private boolean egresoBusActivo;    
    @JoinColumn(name = "egreso_bus_id_egreso", referencedColumnName = "egreso_id", nullable = false)
    @ManyToOne(optional = false)
    private Egreso egresoBusIdEgreso;
    @JoinColumn(name = "egreso_bus_id_bus", referencedColumnName = "bus_id", nullable = false)
    @ManyToOne(optional = false)
    private Bus egresoBusIdBus;

    public EgresoBus() {
    }

    public EgresoBus(Integer egresoBusId) {
        this.egresoBusId = egresoBusId;
    }

    public EgresoBus(Integer egresoBusId, int egresoBusValorDefecto, BigDecimal egresoBusPorcentaje, int egresoBusNumeroOrden, boolean egresoBusActivo) {
        this.egresoBusId = egresoBusId;
        this.egresoBusValorDefecto = egresoBusValorDefecto;
        this.egresoBusPorcentaje = egresoBusPorcentaje;
        this.egresoBusNumeroOrden = egresoBusNumeroOrden;
        this.egresoBusActivo = egresoBusActivo;
    }

    public Integer getEgresoBusId() {
        return egresoBusId;
    }

    public void setEgresoBusId(Integer egresoBusId) {
        this.egresoBusId = egresoBusId;
    }

    public int getEgresoBusValorDefecto() {
        return egresoBusValorDefecto;
    }

    public void setEgresoBusValorDefecto(int egresoBusValorDefecto) {
        this.egresoBusValorDefecto = egresoBusValorDefecto;
    }

    public BigDecimal getEgresoBusPorcentaje() {
        return egresoBusPorcentaje;
    }

    public void setEgresoBusPorcentaje(BigDecimal egresoBusPorcentaje) {
        this.egresoBusPorcentaje = egresoBusPorcentaje;
    }

    public int getEgresoBusNumeroOrden() {
        return egresoBusNumeroOrden;
    }

    public void setEgresoBusNumeroOrden(int egresoBusNumeroOrden) {
        this.egresoBusNumeroOrden = egresoBusNumeroOrden;
    }

    public boolean getEgresoBusActivo() {
        return egresoBusActivo;
    }

    public void setEgresoBusActivo(boolean egresoBusActivo) {
        this.egresoBusActivo = egresoBusActivo;
    }

    public Egreso getEgresoBusIdEgreso() {
        return egresoBusIdEgreso;
    }

    public void setEgresoBusIdEgreso(Egreso egresoBusIdEgreso) {
        this.egresoBusIdEgreso = egresoBusIdEgreso;
    }

    public Bus getEgresoBusIdBus() {
        return egresoBusIdBus;
    }

    public void setEgresoBusIdBus(Bus egresoBusIdBus) {
        this.egresoBusIdBus = egresoBusIdBus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egresoBusId != null ? egresoBusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EgresoBus)) {
            return false;
        }
        EgresoBus other = (EgresoBus) object;
        if ((this.egresoBusId == null && other.egresoBusId != null) || (this.egresoBusId != null && !this.egresoBusId.equals(other.egresoBusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EgresoBus[ egresoBusId=" + egresoBusId + " ]";
    }

}
