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
@Table(name = "frecuencia_servicio", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrecuenciaServicio.findAll", query = "SELECT f FROM FrecuenciaServicio f")
    , @NamedQuery(name = "FrecuenciaServicio.findByFrecuenciaServicioId", query = "SELECT f FROM FrecuenciaServicio f WHERE f.frecuenciaServicioId = :frecuenciaServicioId")
    , @NamedQuery(name = "FrecuenciaServicio.findByFrecuenciaServicioNumeroBuses", query = "SELECT f FROM FrecuenciaServicio f WHERE f.frecuenciaServicioNumeroBuses = :frecuenciaServicioNumeroBuses")
    })
public class FrecuenciaServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "frecuencia_servicio_id")
    private Integer frecuenciaServicioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "frecuencia_servicio_numero_buses")
    private int frecuenciaServicioNumeroBuses;    
    @JoinColumn(name = "frecuencia_servicio_id_periodo", referencedColumnName = "periodo_frecuencia_id", nullable = false)
    @ManyToOne(optional = false)
    private PeriodoFrecuencia frecuenciaServicioIdPeriodo;
    @JoinColumn(name = "frecuencia_servicio_id_servicio", referencedColumnName = "servicio_id", nullable = false)
    @ManyToOne(optional = false)
    private Servicio frecuenciaServicioIdServicio;
    @JoinColumn(name = "frecuencia_servicio_id_tipo_demanda", referencedColumnName = "tipo_demanda_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoDemanda frecuenciaServicioIdTipoDemanda;
    @JoinColumn(name = "frecuencia_servicio_id_tipo_dia", referencedColumnName = "tipo_dia_frecuencia_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoDiaFrecuencia frecuenciaServicioIdTipoDia;

    public FrecuenciaServicio() {
    }

    public FrecuenciaServicio(Integer frecuenciaServicioId) {
        this.frecuenciaServicioId = frecuenciaServicioId;
    }

    public FrecuenciaServicio(Integer frecuenciaServicioId, int frecuenciaServicioNumeroBuses) {
        this.frecuenciaServicioId = frecuenciaServicioId;
        this.frecuenciaServicioNumeroBuses = frecuenciaServicioNumeroBuses;
    }

    public Integer getFrecuenciaServicioId() {
        return frecuenciaServicioId;
    }

    public void setFrecuenciaServicioId(Integer frecuenciaServicioId) {
        this.frecuenciaServicioId = frecuenciaServicioId;
    }

    public int getFrecuenciaServicioNumeroBuses() {
        return frecuenciaServicioNumeroBuses;
    }

    public void setFrecuenciaServicioNumeroBuses(int frecuenciaServicioNumeroBuses) {
        this.frecuenciaServicioNumeroBuses = frecuenciaServicioNumeroBuses;
    }

    public PeriodoFrecuencia getFrecuenciaServicioIdPeriodo() {
        return frecuenciaServicioIdPeriodo;
    }

    public void setFrecuenciaServicioIdPeriodo(PeriodoFrecuencia frecuenciaServicioIdPeriodo) {
        this.frecuenciaServicioIdPeriodo = frecuenciaServicioIdPeriodo;
    }

    public Servicio getFrecuenciaServicioIdServicio() {
        return frecuenciaServicioIdServicio;
    }

    public void setFrecuenciaServicioIdServicio(Servicio frecuenciaServicioIdServicio) {
        this.frecuenciaServicioIdServicio = frecuenciaServicioIdServicio;
    }

    public TipoDemanda getFrecuenciaServicioIdTipoDemanda() {
        return frecuenciaServicioIdTipoDemanda;
    }

    public void setFrecuenciaServicioIdTipoDemanda(TipoDemanda frecuenciaServicioIdTipoDemanda) {
        this.frecuenciaServicioIdTipoDemanda = frecuenciaServicioIdTipoDemanda;
    }

    public TipoDiaFrecuencia getFrecuenciaServicioIdTipoDia() {
        return frecuenciaServicioIdTipoDia;
    }

    public void setFrecuenciaServicioIdTipoDia(TipoDiaFrecuencia frecuenciaServicioIdTipoDia) {
        this.frecuenciaServicioIdTipoDia = frecuenciaServicioIdTipoDia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (frecuenciaServicioId != null ? frecuenciaServicioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrecuenciaServicio)) {
            return false;
        }
        FrecuenciaServicio other = (FrecuenciaServicio) object;
        if ((this.frecuenciaServicioId == null && other.frecuenciaServicioId != null) || (this.frecuenciaServicioId != null && !this.frecuenciaServicioId.equals(other.frecuenciaServicioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.FrecuenciaServicio[ frecuenciaServicioId=" + frecuenciaServicioId + " ]";
    }
    
}
