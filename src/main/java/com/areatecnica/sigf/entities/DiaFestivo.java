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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "dia_festivo", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiaFestivo.findAll", query = "SELECT d FROM DiaFestivo d")
    , @NamedQuery(name = "DiaFestivo.findByDiaFestivoId", query = "SELECT d FROM DiaFestivo d WHERE d.diaFestivoId = :diaFestivoId")
    , @NamedQuery(name = "DiaFestivo.findByDiaFestivoNombre", query = "SELECT d FROM DiaFestivo d WHERE d.diaFestivoNombre = :diaFestivoNombre")
    , @NamedQuery(name = "DiaFestivo.findByDiaFestivoFecha", query = "SELECT d FROM DiaFestivo d WHERE d.diaFestivoFecha = :diaFestivoFecha")
    })
public class DiaFestivo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dia_festivo_id")
    private Integer diaFestivoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dia_festivo_nombre")
    private String diaFestivoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_festivo_fecha")
    @Temporal(TemporalType.DATE)
    private Date diaFestivoFecha;
    
    public DiaFestivo() {
    }

    public DiaFestivo(Integer diaFestivoId) {
        this.diaFestivoId = diaFestivoId;
    }

    public DiaFestivo(Integer diaFestivoId, String diaFestivoNombre, Date diaFestivoFecha) {
        this.diaFestivoId = diaFestivoId;
        this.diaFestivoNombre = diaFestivoNombre;
        this.diaFestivoFecha = diaFestivoFecha;
    }

    public Integer getDiaFestivoId() {
        return diaFestivoId;
    }

    public void setDiaFestivoId(Integer diaFestivoId) {
        this.diaFestivoId = diaFestivoId;
    }

    public String getDiaFestivoNombre() {
        return diaFestivoNombre;
    }

    public void setDiaFestivoNombre(String diaFestivoNombre) {
        this.diaFestivoNombre = diaFestivoNombre;
    }

    public Date getDiaFestivoFecha() {
        return diaFestivoFecha;
    }

    public void setDiaFestivoFecha(Date diaFestivoFecha) {
        this.diaFestivoFecha = diaFestivoFecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diaFestivoId != null ? diaFestivoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiaFestivo)) {
            return false;
        }
        DiaFestivo other = (DiaFestivo) object;
        if ((this.diaFestivoId == null && other.diaFestivoId != null) || (this.diaFestivoId != null && !this.diaFestivoId.equals(other.diaFestivoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DiaFestivo[ diaFestivoId=" + diaFestivoId + " ]";
    }
    
}
