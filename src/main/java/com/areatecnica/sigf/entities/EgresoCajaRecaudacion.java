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
@Table(name = "egreso_caja_recaudacion", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EgresoCajaRecaudacion.findAll", query = "SELECT e FROM EgresoCajaRecaudacion e")
    , @NamedQuery(name = "EgresoCajaRecaudacion.findByEgresoCajaRecaudacionId", query = "SELECT e FROM EgresoCajaRecaudacion e WHERE e.egresoCajaRecaudacionId = :egresoCajaRecaudacionId")
    , @NamedQuery(name = "EgresoCajaRecaudacion.findByEgresoCajaRecaudacionegresoCajaRecaudacionIdCaja", query = "SELECT e FROM EgresoCajaRecaudacion e WHERE e.egresoCajaRecaudacionIdCaja = :egresoCajaRecaudacionIdCaja ORDER BY e.egresoCajaRecaudacionNumeroOrden ASC")
    , @NamedQuery(name = "EgresoCajaRecaudacion.findByEgresoCajaRecaudacionValorDefecto", query = "SELECT e FROM EgresoCajaRecaudacion e WHERE e.egresoCajaRecaudacionValorDefecto = :egresoCajaRecaudacionValorDefecto")
    , @NamedQuery(name = "EgresoCajaRecaudacion.findByEgresoCajaRecaudacionPorcentaje", query = "SELECT e FROM EgresoCajaRecaudacion e WHERE e.egresoCajaRecaudacionPorcentaje = :egresoCajaRecaudacionPorcentaje")
    , @NamedQuery(name = "EgresoCajaRecaudacion.findByEgresoCajaRecaudacionNumeroOrden", query = "SELECT e FROM EgresoCajaRecaudacion e WHERE e.egresoCajaRecaudacionNumeroOrden = :egresoCajaRecaudacionNumeroOrden")
    , @NamedQuery(name = "EgresoCajaRecaudacion.findByEgresoCajaRecaudacionActivo", query = "SELECT e FROM EgresoCajaRecaudacion e WHERE e.egresoCajaRecaudacionActivo = :egresoCajaRecaudacionActivo")
    })
public class EgresoCajaRecaudacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "egreso_caja_recaudacion_id")
    private Integer egresoCajaRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_caja_recaudacion_valor_defecto")
    private int egresoCajaRecaudacionValorDefecto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_caja_recaudacion_porcentaje")
    private BigDecimal egresoCajaRecaudacionPorcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_caja_recaudacion_numero_orden")
    private int egresoCajaRecaudacionNumeroOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_caja_recaudacion_activo")
    private boolean egresoCajaRecaudacionActivo;    
    @JoinColumn(name = "egreso_caja_recaudacion_id_egreso", referencedColumnName = "egreso_id", nullable = false)
    @ManyToOne(optional = false)
    private Egreso egresoCajaRecaudacionIdEgreso;
    @JoinColumn(name = "egreso_caja_recaudacion_id_caja", referencedColumnName = "caja_recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private CajaRecaudacion egresoCajaRecaudacionIdCaja;

    public EgresoCajaRecaudacion() {
    }

    public EgresoCajaRecaudacion(Integer egresoCajaRecaudacionId) {
        this.egresoCajaRecaudacionId = egresoCajaRecaudacionId;
    }

    public EgresoCajaRecaudacion(Integer egresoCajaRecaudacionId, int egresoCajaRecaudacionValorDefecto, BigDecimal egresoCajaRecaudacionPorcentaje, int egresoCajaRecaudacionNumeroOrden, boolean egresoCajaRecaudacionActivo) {
        this.egresoCajaRecaudacionId = egresoCajaRecaudacionId;
        this.egresoCajaRecaudacionValorDefecto = egresoCajaRecaudacionValorDefecto;
        this.egresoCajaRecaudacionPorcentaje = egresoCajaRecaudacionPorcentaje;
        this.egresoCajaRecaudacionNumeroOrden = egresoCajaRecaudacionNumeroOrden;
        this.egresoCajaRecaudacionActivo = egresoCajaRecaudacionActivo;
    }

    public Integer getEgresoCajaRecaudacionId() {
        return egresoCajaRecaudacionId;
    }

    public void setEgresoCajaRecaudacionId(Integer egresoCajaRecaudacionId) {
        this.egresoCajaRecaudacionId = egresoCajaRecaudacionId;
    }

    public int getEgresoCajaRecaudacionValorDefecto() {
        return egresoCajaRecaudacionValorDefecto;
    }

    public void setEgresoCajaRecaudacionValorDefecto(int egresoCajaRecaudacionValorDefecto) {
        this.egresoCajaRecaudacionValorDefecto = egresoCajaRecaudacionValorDefecto;
    }

    public BigDecimal getEgresoCajaRecaudacionPorcentaje() {
        return egresoCajaRecaudacionPorcentaje;
    }

    public void setEgresoCajaRecaudacionPorcentaje(BigDecimal egresoCajaRecaudacionPorcentaje) {
        this.egresoCajaRecaudacionPorcentaje = egresoCajaRecaudacionPorcentaje;
    }

    public int getEgresoCajaRecaudacionNumeroOrden() {
        return egresoCajaRecaudacionNumeroOrden;
    }

    public void setEgresoCajaRecaudacionNumeroOrden(int egresoCajaRecaudacionNumeroOrden) {
        this.egresoCajaRecaudacionNumeroOrden = egresoCajaRecaudacionNumeroOrden;
    }

    public boolean getEgresoCajaRecaudacionActivo() {
        return egresoCajaRecaudacionActivo;
    }

    public void setEgresoCajaRecaudacionActivo(boolean egresoCajaRecaudacionActivo) {
        this.egresoCajaRecaudacionActivo = egresoCajaRecaudacionActivo;
    }

    public Egreso getEgresoCajaRecaudacionIdEgreso() {
        return egresoCajaRecaudacionIdEgreso;
    }

    public void setEgresoCajaRecaudacionIdEgreso(Egreso egresoCajaRecaudacionIdEgreso) {
        this.egresoCajaRecaudacionIdEgreso = egresoCajaRecaudacionIdEgreso;
    }

    public CajaRecaudacion getEgresoCajaRecaudacionIdCaja() {
        return egresoCajaRecaudacionIdCaja;
    }

    public void setEgresoCajaRecaudacionIdCaja(CajaRecaudacion egresoCajaRecaudacionIdCaja) {
        this.egresoCajaRecaudacionIdCaja = egresoCajaRecaudacionIdCaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egresoCajaRecaudacionId != null ? egresoCajaRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EgresoCajaRecaudacion)) {
            return false;
        }
        EgresoCajaRecaudacion other = (EgresoCajaRecaudacion) object;
        if ((this.egresoCajaRecaudacionId == null && other.egresoCajaRecaudacionId != null) || (this.egresoCajaRecaudacionId != null && !this.egresoCajaRecaudacionId.equals(other.egresoCajaRecaudacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EgresoCajaRecaudacion[ egresoCajaRecaudacionId=" + egresoCajaRecaudacionId + " ]";
    }

}
