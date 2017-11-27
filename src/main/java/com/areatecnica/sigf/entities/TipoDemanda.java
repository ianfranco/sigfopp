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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "tipo_demanda", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDemanda.findAll", query = "SELECT t FROM TipoDemanda t")
    , @NamedQuery(name = "TipoDemanda.findByTipoDemandaId", query = "SELECT t FROM TipoDemanda t WHERE t.tipoDemandaId = :tipoDemandaId")
    , @NamedQuery(name = "TipoDemanda.findByTipoDemandaNombre", query = "SELECT t FROM TipoDemanda t WHERE t.tipoDemandaNombre = :tipoDemandaNombre")
    })
public class TipoDemanda extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_demanda_id")
    private Integer tipoDemandaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_demanda_nombre")
    private String tipoDemandaNombre;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "frecuenciaServicioIdTipoDemanda")
    private List<FrecuenciaServicio> frecuenciaServicioList;

    public TipoDemanda() {
    }

    public TipoDemanda(Integer tipoDemandaId) {
        this.tipoDemandaId = tipoDemandaId;
    }

    public TipoDemanda(Integer tipoDemandaId, String tipoDemandaNombre) {
        this.tipoDemandaId = tipoDemandaId;
        this.tipoDemandaNombre = tipoDemandaNombre;
    }

    public Integer getTipoDemandaId() {
        return tipoDemandaId;
    }

    public void setTipoDemandaId(Integer tipoDemandaId) {
        this.tipoDemandaId = tipoDemandaId;
    }

    public String getTipoDemandaNombre() {
        return tipoDemandaNombre;
    }

    public void setTipoDemandaNombre(String tipoDemandaNombre) {
        this.tipoDemandaNombre = tipoDemandaNombre;
    }

    @XmlTransient
    public List<FrecuenciaServicio> getFrecuenciaServicioList() {
        return frecuenciaServicioList;
    }

    public void setFrecuenciaServicioList(List<FrecuenciaServicio> frecuenciaServicioList) {
        this.frecuenciaServicioList = frecuenciaServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoDemandaId != null ? tipoDemandaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDemanda)) {
            return false;
        }
        TipoDemanda other = (TipoDemanda) object;
        if ((this.tipoDemandaId == null && other.tipoDemandaId != null) || (this.tipoDemandaId != null && !this.tipoDemandaId.equals(other.tipoDemandaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoDemanda[ tipoDemandaId=" + tipoDemandaId + " ]";
    }
    
}
