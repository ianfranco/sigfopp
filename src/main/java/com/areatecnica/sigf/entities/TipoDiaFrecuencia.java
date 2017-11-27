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
@Table(name = "tipo_dia_frecuencia", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDiaFrecuencia.findAll", query = "SELECT t FROM TipoDiaFrecuencia t")
    , @NamedQuery(name = "TipoDiaFrecuencia.findByTipoDiaFrecuenciaId", query = "SELECT t FROM TipoDiaFrecuencia t WHERE t.tipoDiaFrecuenciaId = :tipoDiaFrecuenciaId")
    , @NamedQuery(name = "TipoDiaFrecuencia.findByTipoDiaFrecuenciaNombre", query = "SELECT t FROM TipoDiaFrecuencia t WHERE t.tipoDiaFrecuenciaNombre = :tipoDiaFrecuenciaNombre")
    , @NamedQuery(name = "TipoDiaFrecuencia.findByTipoDiaFrecuenciaActivo", query = "SELECT t FROM TipoDiaFrecuencia t WHERE t.tipoDiaFrecuenciaActivo = :tipoDiaFrecuenciaActivo")
    })
public class TipoDiaFrecuencia extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_dia_frecuencia_id")
    private Integer tipoDiaFrecuenciaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_dia_frecuencia_nombre")
    private String tipoDiaFrecuenciaNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_dia_frecuencia_activo")
    private boolean tipoDiaFrecuenciaActivo;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "frecuenciaServicioIdTipoDia")
    private List<FrecuenciaServicio> frecuenciaServicioList;

    public TipoDiaFrecuencia() {
    }

    public TipoDiaFrecuencia(Integer tipoDiaFrecuenciaId) {
        this.tipoDiaFrecuenciaId = tipoDiaFrecuenciaId;
    }

    public TipoDiaFrecuencia(Integer tipoDiaFrecuenciaId, String tipoDiaFrecuenciaNombre, boolean tipoDiaFrecuenciaActivo) {
        this.tipoDiaFrecuenciaId = tipoDiaFrecuenciaId;
        this.tipoDiaFrecuenciaNombre = tipoDiaFrecuenciaNombre;
        this.tipoDiaFrecuenciaActivo = tipoDiaFrecuenciaActivo;
    }

    public Integer getTipoDiaFrecuenciaId() {
        return tipoDiaFrecuenciaId;
    }

    public void setTipoDiaFrecuenciaId(Integer tipoDiaFrecuenciaId) {
        this.tipoDiaFrecuenciaId = tipoDiaFrecuenciaId;
    }

    public String getTipoDiaFrecuenciaNombre() {
        return tipoDiaFrecuenciaNombre;
    }

    public void setTipoDiaFrecuenciaNombre(String tipoDiaFrecuenciaNombre) {
        this.tipoDiaFrecuenciaNombre = tipoDiaFrecuenciaNombre;
    }

    public boolean getTipoDiaFrecuenciaActivo() {
        return tipoDiaFrecuenciaActivo;
    }

    public void setTipoDiaFrecuenciaActivo(boolean tipoDiaFrecuenciaActivo) {
        this.tipoDiaFrecuenciaActivo = tipoDiaFrecuenciaActivo;
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
        hash += (tipoDiaFrecuenciaId != null ? tipoDiaFrecuenciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDiaFrecuencia)) {
            return false;
        }
        TipoDiaFrecuencia other = (TipoDiaFrecuencia) object;
        if ((this.tipoDiaFrecuenciaId == null && other.tipoDiaFrecuenciaId != null) || (this.tipoDiaFrecuenciaId != null && !this.tipoDiaFrecuenciaId.equals(other.tipoDiaFrecuenciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoDiaFrecuencia[ tipoDiaFrecuenciaId=" + tipoDiaFrecuenciaId + " ]";
    }
    
}
