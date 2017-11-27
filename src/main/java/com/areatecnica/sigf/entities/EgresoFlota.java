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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "egreso_flota", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EgresoFlota.findAll", query = "SELECT e FROM EgresoFlota e")
    , @NamedQuery(name = "EgresoFlota.findByEgresoFlotaId", query = "SELECT e FROM EgresoFlota e WHERE e.egresoFlotaId = :egresoFlotaId")
    , @NamedQuery(name = "EgresoFlota.findByEgresoFlotaIdFlota", query = "SELECT e FROM EgresoFlota e WHERE e.egresoFlotaIdFlota = :egresoFlotaIdFlota ORDER BY e.egresoFlotaNumeroOrden ASC")
    , @NamedQuery(name = "EgresoFlota.findByEgresoFlotaValorDefecto", query = "SELECT e FROM EgresoFlota e WHERE e.egresoFlotaValorDefecto = :egresoFlotaValorDefecto")
    , @NamedQuery(name = "EgresoFlota.findByEgresoFlotaPorcentaje", query = "SELECT e FROM EgresoFlota e WHERE e.egresoFlotaPorcentaje = :egresoFlotaPorcentaje")
    , @NamedQuery(name = "EgresoFlota.findByEgresoFlotaNumeroOrden", query = "SELECT e FROM EgresoFlota e WHERE e.egresoFlotaNumeroOrden = :egresoFlotaNumeroOrden")
    , @NamedQuery(name = "EgresoFlota.findByEgresoFlotaActivo", query = "SELECT e FROM EgresoFlota e WHERE e.egresoFlotaActivo = :egresoFlotaActivo")
    })
public class EgresoFlota extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "egreso_flota_id")
    private Integer egresoFlotaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_flota_valor_defecto")
    private int egresoFlotaValorDefecto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_flota_porcentaje")
    private BigDecimal egresoFlotaPorcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_flota_numero_orden")
    @OrderBy("egresoFlotaNumeroOrden ASC")
    private int egresoFlotaNumeroOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_flota_activo")
    private boolean egresoFlotaActivo;    
    @JoinColumn(name = "egreso_flota_id_egreso", referencedColumnName = "egreso_id", nullable = false)
    @ManyToOne(optional = false)
    private Egreso egresoFlotaIdEgreso;
    @JoinColumn(name = "egreso_flota_id_flota", referencedColumnName = "flota_id", nullable = false)
    @ManyToOne(optional = false)
    private Flota egresoFlotaIdFlota;

    public EgresoFlota() {
    }

    public EgresoFlota(Integer egresoFlotaId) {
        this.egresoFlotaId = egresoFlotaId;
    }

    public EgresoFlota(Integer egresoFlotaId, int egresoFlotaValorDefecto, BigDecimal egresoFlotaPorcentaje, int egresoFlotaNumeroOrden, boolean egresoFlotaActivo) {
        this.egresoFlotaId = egresoFlotaId;
        this.egresoFlotaValorDefecto = egresoFlotaValorDefecto;
        this.egresoFlotaPorcentaje = egresoFlotaPorcentaje;
        this.egresoFlotaNumeroOrden = egresoFlotaNumeroOrden;
        this.egresoFlotaActivo = egresoFlotaActivo;
    }

    public Integer getEgresoFlotaId() {
        return egresoFlotaId;
    }

    public void setEgresoFlotaId(Integer egresoFlotaId) {
        this.egresoFlotaId = egresoFlotaId;
    }

    public int getEgresoFlotaValorDefecto() {
        return egresoFlotaValorDefecto;
    }

    public void setEgresoFlotaValorDefecto(int egresoFlotaValorDefecto) {
        this.egresoFlotaValorDefecto = egresoFlotaValorDefecto;
    }

    public BigDecimal getEgresoFlotaPorcentaje() {
        return egresoFlotaPorcentaje;
    }

    public void setEgresoFlotaPorcentaje(BigDecimal egresoFlotaPorcentaje) {
        this.egresoFlotaPorcentaje = egresoFlotaPorcentaje;
    }

    public int getEgresoFlotaNumeroOrden() {
        return egresoFlotaNumeroOrden;
    }

    public void setEgresoFlotaNumeroOrden(int egresoFlotaNumeroOrden) {
        this.egresoFlotaNumeroOrden = egresoFlotaNumeroOrden;
    }

    public boolean getEgresoFlotaActivo() {
        return egresoFlotaActivo;
    }

    public void setEgresoFlotaActivo(boolean egresoFlotaActivo) {
        this.egresoFlotaActivo = egresoFlotaActivo;
    }

    public Egreso getEgresoFlotaIdEgreso() {
        return egresoFlotaIdEgreso;
    }

    public void setEgresoFlotaIdEgreso(Egreso egresoFlotaIdEgreso) {
        this.egresoFlotaIdEgreso = egresoFlotaIdEgreso;
    }

    public Flota getEgresoFlotaIdFlota() {
        return egresoFlotaIdFlota;
    }

    public void setEgresoFlotaIdFlota(Flota egresoFlotaIdFlota) {
        this.egresoFlotaIdFlota = egresoFlotaIdFlota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egresoFlotaId != null ? egresoFlotaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EgresoFlota)) {
            return false;
        }
        EgresoFlota other = (EgresoFlota) object;
        if ((this.egresoFlotaId == null && other.egresoFlotaId != null) || (this.egresoFlotaId != null && !this.egresoFlotaId.equals(other.egresoFlotaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EgresoFlota[ egresoFlotaId=" + egresoFlotaId + " ]";
    }

}
