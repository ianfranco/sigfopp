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
@Table(name = "calle_servicio", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalleServicio.findAll", query = "SELECT c FROM CalleServicio c")
    , @NamedQuery(name = "CalleServicio.findByCalleServicioId", query = "SELECT c FROM CalleServicio c WHERE c.calleServicioId = :calleServicioId")
    , @NamedQuery(name = "CalleServicio.findByCalleServicioNumeroOrden", query = "SELECT c FROM CalleServicio c WHERE c.calleServicioNumeroOrden = :calleServicioNumeroOrden")
    , @NamedQuery(name = "CalleServicio.findByCalleServicioSentido", query = "SELECT c FROM CalleServicio c WHERE c.calleServicioSentido = :calleServicioSentido")
    })
public class CalleServicio extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "calle_servicio_id")
    private Integer calleServicioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calle_servicio_numero_orden")
    private int calleServicioNumeroOrden;
    @Column(name = "calle_servicio_sentido")
    private Boolean calleServicioSentido;    
    @JoinColumn(name = "calle_servicio_id_calle", referencedColumnName = "calle_id", nullable = false)
    @ManyToOne(optional = false)
    private Calle calleServicioIdCalle;
    @JoinColumn(name = "calle_servicio_id_servicio", referencedColumnName = "servicio_id", nullable = false)
    @ManyToOne(optional = false)
    private Servicio calleServicioIdServicio;

    public CalleServicio() {
    }

    public CalleServicio(Integer calleServicioId) {
        this.calleServicioId = calleServicioId;
    }

    public CalleServicio(Integer calleServicioId, int calleServicioNumeroOrden) {
        this.calleServicioId = calleServicioId;
        this.calleServicioNumeroOrden = calleServicioNumeroOrden;
    }

    public Integer getCalleServicioId() {
        return calleServicioId;
    }

    public void setCalleServicioId(Integer calleServicioId) {
        this.calleServicioId = calleServicioId;
    }

    public int getCalleServicioNumeroOrden() {
        return calleServicioNumeroOrden;
    }

    public void setCalleServicioNumeroOrden(int calleServicioNumeroOrden) {
        this.calleServicioNumeroOrden = calleServicioNumeroOrden;
    }

    public Boolean getCalleServicioSentido() {
        return calleServicioSentido;
    }

    public void setCalleServicioSentido(Boolean calleServicioSentido) {
        this.calleServicioSentido = calleServicioSentido;
    }

    public Calle getCalleServicioIdCalle() {
        return calleServicioIdCalle;
    }

    public void setCalleServicioIdCalle(Calle calleServicioIdCalle) {
        this.calleServicioIdCalle = calleServicioIdCalle;
    }

    public Servicio getCalleServicioIdServicio() {
        return calleServicioIdServicio;
    }

    public void setCalleServicioIdServicio(Servicio calleServicioIdServicio) {
        this.calleServicioIdServicio = calleServicioIdServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calleServicioId != null ? calleServicioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalleServicio)) {
            return false;
        }
        CalleServicio other = (CalleServicio) object;
        if ((this.calleServicioId == null && other.calleServicioId != null) || (this.calleServicioId != null && !this.calleServicioId.equals(other.calleServicioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CalleServicio[ calleServicioId=" + calleServicioId + " ]";
    }
    
}
