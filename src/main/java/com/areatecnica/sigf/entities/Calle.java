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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "calle", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calle.findAll", query = "SELECT c FROM Calle c")
    , @NamedQuery(name = "Calle.findByCalleId", query = "SELECT c FROM Calle c WHERE c.calleId = :calleId")
    , @NamedQuery(name = "Calle.findByCalleNombre", query = "SELECT c FROM Calle c WHERE c.calleNombre = :calleNombre")
    })
public class Calle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "calle_id")
    private Integer calleId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "calle_nombre")
    private String calleNombre;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calleServicioIdCalle")
    private List<CalleServicio> calleServicioList;
    @JoinColumn(name = "calle_id_comuna", referencedColumnName = "comuna_id", nullable = false)
    @ManyToOne(optional = false)
    private Comuna calleIdComuna;

    public Calle() {
    }

    public Calle(Integer calleId) {
        this.calleId = calleId;
    }

    public Calle(Integer calleId, String calleNombre) {
        this.calleId = calleId;
        this.calleNombre = calleNombre;
    }

    public Integer getCalleId() {
        return calleId;
    }

    public void setCalleId(Integer calleId) {
        this.calleId = calleId;
    }

    public String getCalleNombre() {
        return calleNombre;
    }

    public void setCalleNombre(String calleNombre) {
        this.calleNombre = calleNombre;
    }

    @XmlTransient
    public List<CalleServicio> getCalleServicioList() {
        return calleServicioList;
    }

    public void setCalleServicioList(List<CalleServicio> calleServicioList) {
        this.calleServicioList = calleServicioList;
    }

    public Comuna getCalleIdComuna() {
        return calleIdComuna;
    }

    public void setCalleIdComuna(Comuna calleIdComuna) {
        this.calleIdComuna = calleIdComuna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calleId != null ? calleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calle)) {
            return false;
        }
        Calle other = (Calle) object;
        if ((this.calleId == null && other.calleId != null) || (this.calleId != null && !this.calleId.equals(other.calleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Calle[ calleId=" + calleId + " ]";
    }
    
}
