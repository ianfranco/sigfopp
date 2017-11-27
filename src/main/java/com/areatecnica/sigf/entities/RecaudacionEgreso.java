/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
@Table(name = "recaudacion_egreso", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecaudacionEgreso.findAll", query = "SELECT r FROM RecaudacionEgreso r")
    , @NamedQuery(name = "RecaudacionEgreso.findByRecaudacionEgresoId", query = "SELECT r FROM RecaudacionEgreso r WHERE r.recaudacionEgresoId = :recaudacionEgresoId")
    , @NamedQuery(name = "RecaudacionEgreso.findByRecaudacionEgresoMonto", query = "SELECT r FROM RecaudacionEgreso r WHERE r.recaudacionEgresoMonto = :recaudacionEgresoMonto")})
public class RecaudacionEgreso extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recaudacion_egreso_id")
    private Integer recaudacionEgresoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recaudacion_egreso_monto")
    private int recaudacionEgresoMonto;
    @JoinColumn(name = "recaudacion_egreso_id_egreso", referencedColumnName = "egreso_id", nullable = false)
    @ManyToOne(optional = false)
    private Egreso recaudacionEgresoIdEgreso;
    @JoinColumn(name = "recaudacion_egreso_id_recaudacion", referencedColumnName = "recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private Recaudacion recaudacionEgresoIdRecaudacion;

    public RecaudacionEgreso() {
    }

    public RecaudacionEgreso(Integer recaudacionEgresoId) {
        this.recaudacionEgresoId = recaudacionEgresoId;
    }

    public RecaudacionEgreso(Integer recaudacionEgresoId, int recaudacionEgresoMonto) {
        this.recaudacionEgresoId = recaudacionEgresoId;
        this.recaudacionEgresoMonto = recaudacionEgresoMonto;
    }

    public Integer getRecaudacionEgresoId() {
        return recaudacionEgresoId;
    }

    public void setRecaudacionEgresoId(Integer recaudacionEgresoId) {
        this.recaudacionEgresoId = recaudacionEgresoId;
    }

    public int getRecaudacionEgresoMonto() {
        return recaudacionEgresoMonto;
    }

    public void setRecaudacionEgresoMonto(int recaudacionEgresoMonto) {
        this.recaudacionEgresoMonto = recaudacionEgresoMonto;
    }

    public Egreso getRecaudacionEgresoIdEgreso() {
        return recaudacionEgresoIdEgreso;
    }

    public void setRecaudacionEgresoIdEgreso(Egreso recaudacionEgresoIdEgreso) {
        this.recaudacionEgresoIdEgreso = recaudacionEgresoIdEgreso;
    }

    public Recaudacion getRecaudacionEgresoIdRecaudacion() {
        return recaudacionEgresoIdRecaudacion;
    }

    public void setRecaudacionEgresoIdRecaudacion(Recaudacion recaudacionEgresoIdRecaudacion) {
        this.recaudacionEgresoIdRecaudacion = recaudacionEgresoIdRecaudacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recaudacionEgresoId != null ? recaudacionEgresoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecaudacionEgreso)) {
            return false;
        }
        RecaudacionEgreso other = (RecaudacionEgreso) object;
        if ((this.recaudacionEgresoId == null && other.recaudacionEgresoId != null) || (this.recaudacionEgresoId != null && !this.recaudacionEgresoId.equals(other.recaudacionEgresoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RecaudacionEgreso[ recaudacionEgresoId=" + recaudacionEgresoId + " ]";
    }
    
}
