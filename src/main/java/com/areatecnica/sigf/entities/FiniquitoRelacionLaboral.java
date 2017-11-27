/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "finiquito_relacion_laboral", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FiniquitoRelacionLaboral.findAll", query = "SELECT f FROM FiniquitoRelacionLaboral f")
    , @NamedQuery(name = "FiniquitoRelacionLaboral.findByFiniquitoRelacionLaboralId", query = "SELECT f FROM FiniquitoRelacionLaboral f WHERE f.finiquitoRelacionLaboralId = :finiquitoRelacionLaboralId")
    , @NamedQuery(name = "FiniquitoRelacionLaboral.findByFiniquitoRelacionLaboralFechaFiniquito", query = "SELECT f FROM FiniquitoRelacionLaboral f WHERE f.finiquitoRelacionLaboralFechaFiniquito = :finiquitoRelacionLaboralFechaFiniquito")
    , @NamedQuery(name = "FiniquitoRelacionLaboral.findByFiniquitoRelacionLaboralMontoFiniquito", query = "SELECT f FROM FiniquitoRelacionLaboral f WHERE f.finiquitoRelacionLaboralMontoFiniquito = :finiquitoRelacionLaboralMontoFiniquito")
    , @NamedQuery(name = "FiniquitoRelacionLaboral.findByFiniquitoRelacionLaboralAniosServicio", query = "SELECT f FROM FiniquitoRelacionLaboral f WHERE f.finiquitoRelacionLaboralAniosServicio = :finiquitoRelacionLaboralAniosServicio")
    , @NamedQuery(name = "FiniquitoRelacionLaboral.findByFiniquitoRelacionLaboralAniosAdicionales", query = "SELECT f FROM FiniquitoRelacionLaboral f WHERE f.finiquitoRelacionLaboralAniosAdicionales = :finiquitoRelacionLaboralAniosAdicionales")
    })
public class FiniquitoRelacionLaboral extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "finiquito_relacion_laboral_id")
    private Integer finiquitoRelacionLaboralId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "finiquito_relacion_laboral_fecha_finiquito")
    @Temporal(TemporalType.DATE)
    private Date finiquitoRelacionLaboralFechaFiniquito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "finiquito_relacion_laboral_monto_finiquito")
    private int finiquitoRelacionLaboralMontoFiniquito;
    @Basic(optional = false)
    @NotNull
    @Column(name = "finiquito_relacion_laboral_anios_servicio")
    private int finiquitoRelacionLaboralAniosServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "finiquito_relacion_laboral_anios_adicionales")
    private int finiquitoRelacionLaboralAniosAdicionales;    
    @JoinColumn(name = "finiquito_relacion_laboral_id_causal_finiquito", referencedColumnName = "causal_finiquito_id", nullable = false)
    @ManyToOne(optional = false)
    private CausalFiniquito finiquitoRelacionLaboralIdCausalFiniquito;
    @JoinColumn(name = "finiquito_relacion_laboral_id_relacion_laboral", referencedColumnName = "relacion_laboral_id", nullable = false)
    @ManyToOne(optional = false)
    private RelacionLaboral finiquitoRelacionLaboralIdRelacionLaboral;

    public FiniquitoRelacionLaboral() {
    }

    public FiniquitoRelacionLaboral(Integer finiquitoRelacionLaboralId) {
        this.finiquitoRelacionLaboralId = finiquitoRelacionLaboralId;
    }

    public FiniquitoRelacionLaboral(Integer finiquitoRelacionLaboralId, Date finiquitoRelacionLaboralFechaFiniquito, int finiquitoRelacionLaboralMontoFiniquito, int finiquitoRelacionLaboralAniosServicio, int finiquitoRelacionLaboralAniosAdicionales) {
        this.finiquitoRelacionLaboralId = finiquitoRelacionLaboralId;
        this.finiquitoRelacionLaboralFechaFiniquito = finiquitoRelacionLaboralFechaFiniquito;
        this.finiquitoRelacionLaboralMontoFiniquito = finiquitoRelacionLaboralMontoFiniquito;
        this.finiquitoRelacionLaboralAniosServicio = finiquitoRelacionLaboralAniosServicio;
        this.finiquitoRelacionLaboralAniosAdicionales = finiquitoRelacionLaboralAniosAdicionales;
    }

    public Integer getFiniquitoRelacionLaboralId() {
        return finiquitoRelacionLaboralId;
    }

    public void setFiniquitoRelacionLaboralId(Integer finiquitoRelacionLaboralId) {
        this.finiquitoRelacionLaboralId = finiquitoRelacionLaboralId;
    }

    public Date getFiniquitoRelacionLaboralFechaFiniquito() {
        return finiquitoRelacionLaboralFechaFiniquito;
    }

    public void setFiniquitoRelacionLaboralFechaFiniquito(Date finiquitoRelacionLaboralFechaFiniquito) {
        this.finiquitoRelacionLaboralFechaFiniquito = finiquitoRelacionLaboralFechaFiniquito;
    }

    public int getFiniquitoRelacionLaboralMontoFiniquito() {
        return finiquitoRelacionLaboralMontoFiniquito;
    }

    public void setFiniquitoRelacionLaboralMontoFiniquito(int finiquitoRelacionLaboralMontoFiniquito) {
        this.finiquitoRelacionLaboralMontoFiniquito = finiquitoRelacionLaboralMontoFiniquito;
    }

    public int getFiniquitoRelacionLaboralAniosServicio() {
        return finiquitoRelacionLaboralAniosServicio;
    }

    public void setFiniquitoRelacionLaboralAniosServicio(int finiquitoRelacionLaboralAniosServicio) {
        this.finiquitoRelacionLaboralAniosServicio = finiquitoRelacionLaboralAniosServicio;
    }

    public int getFiniquitoRelacionLaboralAniosAdicionales() {
        return finiquitoRelacionLaboralAniosAdicionales;
    }

    public void setFiniquitoRelacionLaboralAniosAdicionales(int finiquitoRelacionLaboralAniosAdicionales) {
        this.finiquitoRelacionLaboralAniosAdicionales = finiquitoRelacionLaboralAniosAdicionales;
    }

    public CausalFiniquito getFiniquitoRelacionLaboralIdCausalFiniquito() {
        return finiquitoRelacionLaboralIdCausalFiniquito;
    }

    public void setFiniquitoRelacionLaboralIdCausalFiniquito(CausalFiniquito finiquitoRelacionLaboralIdCausalFiniquito) {
        this.finiquitoRelacionLaboralIdCausalFiniquito = finiquitoRelacionLaboralIdCausalFiniquito;
    }

    public RelacionLaboral getFiniquitoRelacionLaboralIdRelacionLaboral() {
        return finiquitoRelacionLaboralIdRelacionLaboral;
    }

    public void setFiniquitoRelacionLaboralIdRelacionLaboral(RelacionLaboral finiquitoRelacionLaboralIdRelacionLaboral) {
        this.finiquitoRelacionLaboralIdRelacionLaboral = finiquitoRelacionLaboralIdRelacionLaboral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (finiquitoRelacionLaboralId != null ? finiquitoRelacionLaboralId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FiniquitoRelacionLaboral)) {
            return false;
        }
        FiniquitoRelacionLaboral other = (FiniquitoRelacionLaboral) object;
        if ((this.finiquitoRelacionLaboralId == null && other.finiquitoRelacionLaboralId != null) || (this.finiquitoRelacionLaboralId != null && !this.finiquitoRelacionLaboralId.equals(other.finiquitoRelacionLaboralId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.FiniquitoRelacionLaboral[ finiquitoRelacionLaboralId=" + finiquitoRelacionLaboralId + " ]";
    }
    
}
