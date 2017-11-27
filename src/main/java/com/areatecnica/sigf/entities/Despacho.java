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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "despacho", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Despacho.findAll", query = "SELECT d FROM Despacho d")
    , @NamedQuery(name = "Despacho.findByDespachoId", query = "SELECT d FROM Despacho d WHERE d.despachoId = :despachoId")
    , @NamedQuery(name = "Despacho.findByDespachoFechaHora", query = "SELECT d FROM Despacho d WHERE d.despachoFechaHora = :despachoFechaHora")
    })
public class Despacho extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "despacho_id")
    private Integer despachoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "despacho_fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date despachoFechaHora;    
    @JoinColumn(name = "despacho_id_bus", referencedColumnName = "bus_id")
    @ManyToOne(optional = false)
    private Bus despachoIdBus;
    @JoinColumn(name = "despacho_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador despachoIdTrabajador;
    @JoinColumn(name = "despacho_id_inspector", referencedColumnName = "usuario_id", nullable = false)
    @ManyToOne(optional = false)
    private Usuario despachoIdInspector;
    @JoinColumn(name = "despacho_id_servicio", referencedColumnName = "servicio_id", nullable = false)
    @ManyToOne(optional = false)
    private Servicio despachoIdServicio;

    public Despacho() {
    }

    public Despacho(Integer despachoId) {
        this.despachoId = despachoId;
    }

    public Despacho(Integer despachoId, Date despachoFechaHora) {
        this.despachoId = despachoId;
        this.despachoFechaHora = despachoFechaHora;
    }

    public Integer getDespachoId() {
        return despachoId;
    }

    public void setDespachoId(Integer despachoId) {
        this.despachoId = despachoId;
    }

    public Date getDespachoFechaHora() {
        return despachoFechaHora;
    }

    public void setDespachoFechaHora(Date despachoFechaHora) {
        this.despachoFechaHora = despachoFechaHora;
    }

    public Bus getDespachoIdBus() {
        return despachoIdBus;
    }

    public void setDespachoIdBus(Bus despachoIdBus) {
        this.despachoIdBus = despachoIdBus;
    }

    public Trabajador getDespachoIdTrabajador() {
        return despachoIdTrabajador;
    }

    public void setDespachoIdTrabajador(Trabajador despachoIdTrabajador) {
        this.despachoIdTrabajador = despachoIdTrabajador;
    }

    public Usuario getDespachoIdInspector() {
        return despachoIdInspector;
    }

    public void setDespachoIdInspector(Usuario despachoIdInspector) {
        this.despachoIdInspector = despachoIdInspector;
    }

    public Servicio getDespachoIdServicio() {
        return despachoIdServicio;
    }

    public void setDespachoIdServicio(Servicio despachoIdServicio) {
        this.despachoIdServicio = despachoIdServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (despachoId != null ? despachoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Despacho)) {
            return false;
        }
        Despacho other = (Despacho) object;
        if ((this.despachoId == null && other.despachoId != null) || (this.despachoId != null && !this.despachoId.equals(other.despachoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Despacho[ despachoId=" + despachoId + " ]";
    }
    
}
