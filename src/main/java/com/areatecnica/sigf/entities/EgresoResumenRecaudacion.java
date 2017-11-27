/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
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
@Table(name = "egreso_resumen_recaudacion", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EgresoResumenRecaudacion.findAll", query = "SELECT e FROM EgresoResumenRecaudacion e")
    , @NamedQuery(name = "EgresoResumenRecaudacion.findByEgresoResumenRecaudacionId", query = "SELECT e FROM EgresoResumenRecaudacion e WHERE e.egresoResumenRecaudacionId = :egresoResumenRecaudacionId")
    , @NamedQuery(name = "EgresoResumenRecaudacion.findByEgresoResumenRecaudacionTotal", query = "SELECT e FROM EgresoResumenRecaudacion e WHERE e.egresoResumenRecaudacionTotal = :egresoResumenRecaudacionTotal")})
public class EgresoResumenRecaudacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "egreso_resumen_recaudacion_id")
    private Integer egresoResumenRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "egreso_resumen_recaudacion_total")
    private int egresoResumenRecaudacionTotal;
    @JoinColumn(name = "egreso_resumen_recaudacion_id_egreso", referencedColumnName = "egreso_id", nullable = false)
    @ManyToOne(optional = false)
    private Egreso egresoResumenRecaudacionIdEgreso;
    @JoinColumn(name = "egreso_resumen_recaudacion_id_resumen", referencedColumnName = "resumen_recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private ResumenRecaudacion egresoResumenRecaudacionIdResumen;

    public EgresoResumenRecaudacion() {
    }

    public EgresoResumenRecaudacion(Integer egresoResumenRecaudacionId) {
        this.egresoResumenRecaudacionId = egresoResumenRecaudacionId;
    }

    public EgresoResumenRecaudacion(Integer egresoResumenRecaudacionId, int egresoResumenRecaudacionTotal) {
        this.egresoResumenRecaudacionId = egresoResumenRecaudacionId;
        this.egresoResumenRecaudacionTotal = egresoResumenRecaudacionTotal;
    }

    public Integer getEgresoResumenRecaudacionId() {
        return egresoResumenRecaudacionId;
    }

    public void setEgresoResumenRecaudacionId(Integer egresoResumenRecaudacionId) {
        this.egresoResumenRecaudacionId = egresoResumenRecaudacionId;
    }

    public int getEgresoResumenRecaudacionTotal() {
        return egresoResumenRecaudacionTotal;
    }

    public void setEgresoResumenRecaudacionTotal(int egresoResumenRecaudacionTotal) {
        this.egresoResumenRecaudacionTotal = egresoResumenRecaudacionTotal;
    }

    public Egreso getEgresoResumenRecaudacionIdEgreso() {
        return egresoResumenRecaudacionIdEgreso;
    }

    public void setEgresoResumenRecaudacionIdEgreso(Egreso egresoResumenRecaudacionIdEgreso) {
        this.egresoResumenRecaudacionIdEgreso = egresoResumenRecaudacionIdEgreso;
    }

    public ResumenRecaudacion getEgresoResumenRecaudacionIdResumen() {
        return egresoResumenRecaudacionIdResumen;
    }

    public void setEgresoResumenRecaudacionIdResumen(ResumenRecaudacion egresoResumenRecaudacionIdResumen) {
        this.egresoResumenRecaudacionIdResumen = egresoResumenRecaudacionIdResumen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egresoResumenRecaudacionId != null ? egresoResumenRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EgresoResumenRecaudacion)) {
            return false;
        }
        EgresoResumenRecaudacion other = (EgresoResumenRecaudacion) object;
        if ((this.egresoResumenRecaudacionId == null && other.egresoResumenRecaudacionId != null) || (this.egresoResumenRecaudacionId != null && !this.egresoResumenRecaudacionId.equals(other.egresoResumenRecaudacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EgresoResumenRecaudacion[ egresoResumenRecaudacionId=" + egresoResumenRecaudacionId + " ]";
    }
    
}
