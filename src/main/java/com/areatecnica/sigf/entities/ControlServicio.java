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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "control_servicio", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlServicio.findAll", query = "SELECT c FROM ControlServicio c")
    , @NamedQuery(name = "ControlServicio.findByControlServicioIdControlServicio", query = "SELECT c FROM ControlServicio c WHERE c.controlServicioIdControlServicio = :controlServicioIdControlServicio")
    , @NamedQuery(name = "ControlServicio.findByControlServicioTiempo", query = "SELECT c FROM ControlServicio c WHERE c.controlServicioTiempo = :controlServicioTiempo")
    , @NamedQuery(name = "ControlServicio.findByControlServicioNumeroOrden", query = "SELECT c FROM ControlServicio c WHERE c.controlServicioNumeroOrden = :controlServicioNumeroOrden")
})
public class ControlServicio extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "control_servicio_id_control_servicio")
    private Integer controlServicioIdControlServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_servicio_tiempo")
    @Temporal(TemporalType.TIME)
    private Date controlServicioTiempo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_servicio_numero_orden")
    private int controlServicioNumeroOrden;
    @JoinColumn(name = "control_servicio_id_control", referencedColumnName = "control_id", nullable = false)
    @ManyToOne(optional = false)
    private Control controlServicioIdControl;
    @JoinColumn(name = "control_servicio_id_servicio", referencedColumnName = "servicio_id", nullable = false)
    @ManyToOne(optional = false)
    private Servicio controlServicioIdServicio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "controlHorarioServicioIdControl")
    private List<ControlHorarioServicio> controlHorarioServicioList;

    public ControlServicio() {
    }

    public ControlServicio(Integer controlServicioIdControlServicio) {
        this.controlServicioIdControlServicio = controlServicioIdControlServicio;
    }

    public ControlServicio(Integer controlServicioIdControlServicio, Date controlServicioTiempo, int controlServicioNumeroOrden) {
        this.controlServicioIdControlServicio = controlServicioIdControlServicio;
        this.controlServicioTiempo = controlServicioTiempo;
        this.controlServicioNumeroOrden = controlServicioNumeroOrden;
    }

    public Integer getControlServicioIdControlServicio() {
        return controlServicioIdControlServicio;
    }

    public void setControlServicioIdControlServicio(Integer controlServicioIdControlServicio) {
        this.controlServicioIdControlServicio = controlServicioIdControlServicio;
    }

    public Date getControlServicioTiempo() {
        return controlServicioTiempo;
    }

    public void setControlServicioTiempo(Date controlServicioTiempo) {
        this.controlServicioTiempo = controlServicioTiempo;
    }

    public int getControlServicioNumeroOrden() {
        return controlServicioNumeroOrden;
    }

    public void setControlServicioNumeroOrden(int controlServicioNumeroOrden) {
        this.controlServicioNumeroOrden = controlServicioNumeroOrden;
    }

    public Control getControlServicioIdControl() {
        return controlServicioIdControl;
    }

    public void setControlServicioIdControl(Control controlServicioIdControl) {
        this.controlServicioIdControl = controlServicioIdControl;
    }

    public Servicio getControlServicioIdServicio() {
        return controlServicioIdServicio;
    }

    public void setControlServicioIdServicio(Servicio controlServicioIdServicio) {
        this.controlServicioIdServicio = controlServicioIdServicio;
    }

    @XmlTransient
    public List<ControlHorarioServicio> getControlHorarioServicioList() {
        return controlHorarioServicioList;
    }

    public void setControlHorarioServicioList(List<ControlHorarioServicio> controlHorarioServicioList) {
        this.controlHorarioServicioList = controlHorarioServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (controlServicioIdControlServicio != null ? controlServicioIdControlServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlServicio)) {
            return false;
        }
        ControlServicio other = (ControlServicio) object;
        if ((this.controlServicioIdControlServicio == null && other.controlServicioIdControlServicio != null) || (this.controlServicioIdControlServicio != null && !this.controlServicioIdControlServicio.equals(other.controlServicioIdControlServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ControlServicio[ controlServicioIdControlServicio=" + controlServicioIdControlServicio + " ]";
    }

}
