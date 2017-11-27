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
@Table(name = "estado_bus", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoBus.findAll", query = "SELECT e FROM EstadoBus e")
    , @NamedQuery(name = "EstadoBus.findByEstadoBusId", query = "SELECT e FROM EstadoBus e WHERE e.estadoBusId = :estadoBusId")
    , @NamedQuery(name = "EstadoBus.findByEstadoBusNombre", query = "SELECT e FROM EstadoBus e WHERE e.estadoBusNombre = :estadoBusNombre")
    })
public class EstadoBus extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "estado_bus_id")
    private Integer estadoBusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "estado_bus_nombre")
    private String estadoBusNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busIdEstadoBus")
    private List<Bus> busList;

    public EstadoBus() {
    }

    public EstadoBus(Integer estadoBusId) {
        this.estadoBusId = estadoBusId;
    }

    public EstadoBus(Integer estadoBusId, String estadoBusNombre) {
        this.estadoBusId = estadoBusId;
        this.estadoBusNombre = estadoBusNombre;
    }

    public Integer getEstadoBusId() {
        return estadoBusId;
    }

    public void setEstadoBusId(Integer estadoBusId) {
        this.estadoBusId = estadoBusId;
    }

    public String getEstadoBusNombre() {
        return estadoBusNombre;
    }

    public void setEstadoBusNombre(String estadoBusNombre) {
        this.estadoBusNombre = estadoBusNombre;
    }

    @XmlTransient
    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoBusId != null ? estadoBusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoBus)) {
            return false;
        }
        EstadoBus other = (EstadoBus) object;
        if ((this.estadoBusId == null && other.estadoBusId != null) || (this.estadoBusId != null && !this.estadoBusId.equals(other.estadoBusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EstadoBus[ estadoBusId=" + estadoBusId + " ]";
    }
    
}
