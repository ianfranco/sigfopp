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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "tipo_nomina", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNomina.findAll", query = "SELECT t FROM TipoNomina t")
    , @NamedQuery(name = "TipoNomina.findByTipoNominaId", query = "SELECT t FROM TipoNomina t WHERE t.tipoNominaId = :tipoNominaId")
    , @NamedQuery(name = "TipoNomina.findByTipoNominaNombre", query = "SELECT t FROM TipoNomina t WHERE t.tipoNominaNombre = :tipoNominaNombre")
    })
public class TipoNomina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_nomina_id")
    private Integer tipoNominaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_nomina_nombre")
    private String tipoNominaNombre;
    
    public TipoNomina() {
    }

    public TipoNomina(Integer tipoNominaId) {
        this.tipoNominaId = tipoNominaId;
    }

    public TipoNomina(Integer tipoNominaId, String tipoNominaNombre) {
        this.tipoNominaId = tipoNominaId;
        this.tipoNominaNombre = tipoNominaNombre;
    }

    public Integer getTipoNominaId() {
        return tipoNominaId;
    }

    public void setTipoNominaId(Integer tipoNominaId) {
        this.tipoNominaId = tipoNominaId;
    }

    public String getTipoNominaNombre() {
        return tipoNominaNombre;
    }

    public void setTipoNominaNombre(String tipoNominaNombre) {
        this.tipoNominaNombre = tipoNominaNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoNominaId != null ? tipoNominaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNomina)) {
            return false;
        }
        TipoNomina other = (TipoNomina) object;
        if ((this.tipoNominaId == null && other.tipoNominaId != null) || (this.tipoNominaId != null && !this.tipoNominaId.equals(other.tipoNominaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoNomina[ tipoNominaId=" + tipoNominaId + " ]";
    }

}
