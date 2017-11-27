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
@Table(name = "egreso_proceso_recaudacion", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EgresoProcesoRecaudacion.findAll", query = "SELECT e FROM EgresoProcesoRecaudacion e")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionId", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionId = :egresoProcesoRecaudacionId")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionIdProceso", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionIdProceso = :egresoProcesoRecaudacionIdProceso")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionValorDefecto", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionValorDefecto = :egresoProcesoRecaudacionValorDefecto")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionPorcentaje", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionPorcentaje = :egresoProcesoRecaudacionPorcentaje")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionNumeroOrden", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionNumeroOrden = :egresoProcesoRecaudacionNumeroOrden")
    , @NamedQuery(name = "EgresoProcesoRecaudacion.findByEgresoProcesoRecaudacionActivo", query = "SELECT e FROM EgresoProcesoRecaudacion e WHERE e.egresoProcesoRecaudacionActivo = :egresoProcesoRecaudacionActivo")
    })
public class EgresoProcesoRecaudacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "egreso_proceso_recaudacion_id")
    private Integer egresoProcesoRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_proceso_recaudacion_valor_defecto")
    private int egresoProcesoRecaudacionValorDefecto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_proceso_recaudacion_porcentaje")
    private BigDecimal egresoProcesoRecaudacionPorcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_proceso_recaudacion_numero_orden")
    private int egresoProcesoRecaudacionNumeroOrden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_proceso_recaudacion_activo")
    private boolean egresoProcesoRecaudacionActivo;    
    @JoinColumn(name = "egreso_proceso_recaudacion_id_egreso", referencedColumnName = "egreso_id", nullable = false)
    @ManyToOne(optional = false)
    private Egreso egresoProcesoRecaudacionIdEgreso;
    @JoinColumn(name = "egreso_proceso_recaudacion_id_proceso", referencedColumnName = "proceso_recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private ProcesoRecaudacion egresoProcesoRecaudacionIdProceso;

    public EgresoProcesoRecaudacion() {
    }

    public EgresoProcesoRecaudacion(Integer egresoProcesoRecaudacionId) {
        this.egresoProcesoRecaudacionId = egresoProcesoRecaudacionId;
    }

    public EgresoProcesoRecaudacion(Integer egresoProcesoRecaudacionId, int egresoProcesoRecaudacionValorDefecto, BigDecimal egresoProcesoRecaudacionPorcentaje, int egresoProcesoRecaudacionNumeroOrden, boolean egresoProcesoRecaudacionActivo) {
        this.egresoProcesoRecaudacionId = egresoProcesoRecaudacionId;
        this.egresoProcesoRecaudacionValorDefecto = egresoProcesoRecaudacionValorDefecto;
        this.egresoProcesoRecaudacionPorcentaje = egresoProcesoRecaudacionPorcentaje;
        this.egresoProcesoRecaudacionNumeroOrden = egresoProcesoRecaudacionNumeroOrden;
        this.egresoProcesoRecaudacionActivo = egresoProcesoRecaudacionActivo;
    }

    public Integer getEgresoProcesoRecaudacionId() {
        return egresoProcesoRecaudacionId;
    }

    public void setEgresoProcesoRecaudacionId(Integer egresoProcesoRecaudacionId) {
        this.egresoProcesoRecaudacionId = egresoProcesoRecaudacionId;
    }

    public int getEgresoProcesoRecaudacionValorDefecto() {
        return egresoProcesoRecaudacionValorDefecto;
    }

    public void setEgresoProcesoRecaudacionValorDefecto(int egresoProcesoRecaudacionValorDefecto) {
        this.egresoProcesoRecaudacionValorDefecto = egresoProcesoRecaudacionValorDefecto;
    }

    public BigDecimal getEgresoProcesoRecaudacionPorcentaje() {
        return egresoProcesoRecaudacionPorcentaje;
    }

    public void setEgresoProcesoRecaudacionPorcentaje(BigDecimal egresoProcesoRecaudacionPorcentaje) {
        this.egresoProcesoRecaudacionPorcentaje = egresoProcesoRecaudacionPorcentaje;
    }

    public int getEgresoProcesoRecaudacionNumeroOrden() {
        return egresoProcesoRecaudacionNumeroOrden;
    }

    public void setEgresoProcesoRecaudacionNumeroOrden(int egresoProcesoRecaudacionNumeroOrden) {
        this.egresoProcesoRecaudacionNumeroOrden = egresoProcesoRecaudacionNumeroOrden;
    }

    public boolean getEgresoProcesoRecaudacionActivo() {
        return egresoProcesoRecaudacionActivo;
    }

    public void setEgresoProcesoRecaudacionActivo(boolean egresoProcesoRecaudacionActivo) {
        this.egresoProcesoRecaudacionActivo = egresoProcesoRecaudacionActivo;
    }

    public Egreso getEgresoProcesoRecaudacionIdEgreso() {
        return egresoProcesoRecaudacionIdEgreso;
    }

    public void setEgresoProcesoRecaudacionIdEgreso(Egreso egresoProcesoRecaudacionIdEgreso) {
        this.egresoProcesoRecaudacionIdEgreso = egresoProcesoRecaudacionIdEgreso;
    }

    public ProcesoRecaudacion getEgresoProcesoRecaudacionIdProceso() {
        return egresoProcesoRecaudacionIdProceso;
    }

    public void setEgresoProcesoRecaudacionIdProceso(ProcesoRecaudacion egresoProcesoRecaudacionIdProceso) {
        this.egresoProcesoRecaudacionIdProceso = egresoProcesoRecaudacionIdProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egresoProcesoRecaudacionId != null ? egresoProcesoRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EgresoProcesoRecaudacion)) {
            return false;
        }
        EgresoProcesoRecaudacion other = (EgresoProcesoRecaudacion) object;
        if ((this.egresoProcesoRecaudacionId == null && other.egresoProcesoRecaudacionId != null) || (this.egresoProcesoRecaudacionId != null && !this.egresoProcesoRecaudacionId.equals(other.egresoProcesoRecaudacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EgresoProcesoRecaudacion[ egresoProcesoRecaudacionId=" + egresoProcesoRecaudacionId + " ]";
    }

}
