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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "fecha", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fecha.findAll", query = "SELECT f FROM Fecha f")
    , @NamedQuery(name = "Fecha.findByFechaId", query = "SELECT f FROM Fecha f WHERE f.fechaId = :fechaId")
    , @NamedQuery(name = "Fecha.findByFecha", query = "SELECT f FROM Fecha f WHERE f.fecha = :fecha")})
public class Fecha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fecha_id")
    private Integer fechaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Fecha() {
    }

    public Fecha(Integer fechaId) {
        this.fechaId = fechaId;
    }

    public Fecha(Integer fechaId, Date fecha) {
        this.fechaId = fechaId;
        this.fecha = fecha;
    }

    public Integer getFechaId() {
        return fechaId;
    }

    public void setFechaId(Integer fechaId) {
        this.fechaId = fechaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechaId != null ? fechaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fecha)) {
            return false;
        }
        Fecha other = (Fecha) object;
        if ((this.fechaId == null && other.fechaId != null) || (this.fechaId != null && !this.fechaId.equals(other.fechaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Fecha[ fechaId=" + fechaId + " ]";
    }
    
}
