/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "periodo_frecuencia", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoFrecuencia.findAll", query = "SELECT p FROM PeriodoFrecuencia p")
    , @NamedQuery(name = "PeriodoFrecuencia.findByPeriodoFrecuenciaId", query = "SELECT p FROM PeriodoFrecuencia p WHERE p.periodoFrecuenciaId = :periodoFrecuenciaId")
    , @NamedQuery(name = "PeriodoFrecuencia.findByPeriodoFrecuenciaIdCuenta", query = "SELECT p FROM PeriodoFrecuencia p WHERE p.periodoFrecuenciaIdCuenta = :periodoFrecuenciaIdCuenta")
    , @NamedQuery(name = "PeriodoFrecuencia.findByPeriodoFrecuenciaDesde", query = "SELECT p FROM PeriodoFrecuencia p WHERE p.periodoFrecuenciaDesde = :periodoFrecuenciaDesde")
    , @NamedQuery(name = "PeriodoFrecuencia.findByPeriodoFrecuenciaHasta", query = "SELECT p FROM PeriodoFrecuencia p WHERE p.periodoFrecuenciaHasta = :periodoFrecuenciaHasta")
    })
public class PeriodoFrecuencia extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "periodo_frecuencia_id")
    private Integer periodoFrecuenciaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_frecuencia_id_cuenta")
    private int periodoFrecuenciaIdCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_frecuencia_desde")
    @Temporal(TemporalType.TIME)
    private Date periodoFrecuenciaDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_frecuencia_hasta")
    @Temporal(TemporalType.TIME)
    private Date periodoFrecuenciaHasta;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "frecuenciaServicioIdPeriodo")
    private List<FrecuenciaServicio> frecuenciaServicioList;

    public PeriodoFrecuencia() {
    }

    public PeriodoFrecuencia(Integer periodoFrecuenciaId) {
        this.periodoFrecuenciaId = periodoFrecuenciaId;
    }

    public PeriodoFrecuencia(Integer periodoFrecuenciaId, int periodoFrecuenciaIdCuenta, Date periodoFrecuenciaDesde, Date periodoFrecuenciaHasta) {
        this.periodoFrecuenciaId = periodoFrecuenciaId;
        this.periodoFrecuenciaIdCuenta = periodoFrecuenciaIdCuenta;
        this.periodoFrecuenciaDesde = periodoFrecuenciaDesde;
        this.periodoFrecuenciaHasta = periodoFrecuenciaHasta;
    }

    public Integer getPeriodoFrecuenciaId() {
        return periodoFrecuenciaId;
    }

    public void setPeriodoFrecuenciaId(Integer periodoFrecuenciaId) {
        this.periodoFrecuenciaId = periodoFrecuenciaId;
    }

    public int getPeriodoFrecuenciaIdCuenta() {
        return periodoFrecuenciaIdCuenta;
    }

    public void setPeriodoFrecuenciaIdCuenta(int periodoFrecuenciaIdCuenta) {
        this.periodoFrecuenciaIdCuenta = periodoFrecuenciaIdCuenta;
    }

    public Date getPeriodoFrecuenciaDesde() {
        return periodoFrecuenciaDesde;
    }

    public void setPeriodoFrecuenciaDesde(Date periodoFrecuenciaDesde) {
        this.periodoFrecuenciaDesde = periodoFrecuenciaDesde;
    }

    public Date getPeriodoFrecuenciaHasta() {
        return periodoFrecuenciaHasta;
    }

    public void setPeriodoFrecuenciaHasta(Date periodoFrecuenciaHasta) {
        this.periodoFrecuenciaHasta = periodoFrecuenciaHasta;
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
        hash += (periodoFrecuenciaId != null ? periodoFrecuenciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoFrecuencia)) {
            return false;
        }
        PeriodoFrecuencia other = (PeriodoFrecuencia) object;
        if ((this.periodoFrecuenciaId == null && other.periodoFrecuenciaId != null) || (this.periodoFrecuenciaId != null && !this.periodoFrecuenciaId.equals(other.periodoFrecuenciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.PeriodoFrecuencia[ periodoFrecuenciaId=" + periodoFrecuenciaId + " ]";
    }
    
}
