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
@Table(name = "control_horario_servicio", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlHorarioServicio.findAll", query = "SELECT c FROM ControlHorarioServicio c")
    , @NamedQuery(name = "ControlHorarioServicio.findByControlHorarioServicioId", query = "SELECT c FROM ControlHorarioServicio c WHERE c.controlHorarioServicioId = :controlHorarioServicioId")
    , @NamedQuery(name = "ControlHorarioServicio.findByControlHorarioServicioTiempo", query = "SELECT c FROM ControlHorarioServicio c WHERE c.controlHorarioServicioTiempo = :controlHorarioServicioTiempo")
    , @NamedQuery(name = "ControlHorarioServicio.findByControlHorarioServicioMulta", query = "SELECT c FROM ControlHorarioServicio c WHERE c.controlHorarioServicioMulta = :controlHorarioServicioMulta")
    , @NamedQuery(name = "ControlHorarioServicio.findByControlHorarioServicioPeso", query = "SELECT c FROM ControlHorarioServicio c WHERE c.controlHorarioServicioPeso = :controlHorarioServicioPeso")})
public class ControlHorarioServicio extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "control_horario_servicio_id")
    private Integer controlHorarioServicioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_horario_servicio_tiempo")
    private int controlHorarioServicioTiempo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_horario_servicio_multa")
    private int controlHorarioServicioMulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_horario_servicio_peso")
    private int controlHorarioServicioPeso;
    @JoinColumn(name = "control_horario_servicio_id_control", referencedColumnName = "control_servicio_id_control_servicio", nullable = false)
    @ManyToOne(optional = false)
    private ControlServicio controlHorarioServicioIdControl;
    @JoinColumn(name = "control_horario_servicio_id_horario", referencedColumnName = "horario_servicio_id", nullable = false)
    @ManyToOne(optional = false)
    private HorarioServicio controlHorarioServicioIdHorario;

    public ControlHorarioServicio() {
    }

    public ControlHorarioServicio(Integer controlHorarioServicioId) {
        this.controlHorarioServicioId = controlHorarioServicioId;
    }

    public ControlHorarioServicio(Integer controlHorarioServicioId, int controlHorarioServicioTiempo, int controlHorarioServicioMulta, int controlHorarioServicioPeso) {
        this.controlHorarioServicioId = controlHorarioServicioId;
        this.controlHorarioServicioTiempo = controlHorarioServicioTiempo;
        this.controlHorarioServicioMulta = controlHorarioServicioMulta;
        this.controlHorarioServicioPeso = controlHorarioServicioPeso;
    }

    public Integer getControlHorarioServicioId() {
        return controlHorarioServicioId;
    }

    public void setControlHorarioServicioId(Integer controlHorarioServicioId) {
        this.controlHorarioServicioId = controlHorarioServicioId;
    }

    public int getControlHorarioServicioTiempo() {
        return controlHorarioServicioTiempo;
    }

    public void setControlHorarioServicioTiempo(int controlHorarioServicioTiempo) {
        this.controlHorarioServicioTiempo = controlHorarioServicioTiempo;
    }

    public int getControlHorarioServicioMulta() {
        return controlHorarioServicioMulta;
    }

    public void setControlHorarioServicioMulta(int controlHorarioServicioMulta) {
        this.controlHorarioServicioMulta = controlHorarioServicioMulta;
    }

    public int getControlHorarioServicioPeso() {
        return controlHorarioServicioPeso;
    }

    public void setControlHorarioServicioPeso(int controlHorarioServicioPeso) {
        this.controlHorarioServicioPeso = controlHorarioServicioPeso;
    }

    public ControlServicio getControlHorarioServicioIdControl() {
        return controlHorarioServicioIdControl;
    }

    public void setControlHorarioServicioIdControl(ControlServicio controlHorarioServicioIdControl) {
        this.controlHorarioServicioIdControl = controlHorarioServicioIdControl;
    }

    public HorarioServicio getControlHorarioServicioIdHorario() {
        return controlHorarioServicioIdHorario;
    }

    public void setControlHorarioServicioIdHorario(HorarioServicio controlHorarioServicioIdHorario) {
        this.controlHorarioServicioIdHorario = controlHorarioServicioIdHorario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (controlHorarioServicioId != null ? controlHorarioServicioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlHorarioServicio)) {
            return false;
        }
        ControlHorarioServicio other = (ControlHorarioServicio) object;
        if ((this.controlHorarioServicioId == null && other.controlHorarioServicioId != null) || (this.controlHorarioServicioId != null && !this.controlHorarioServicioId.equals(other.controlHorarioServicioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ControlHorarioServicio[ controlHorarioServicioId=" + controlHorarioServicioId + " ]";
    }
    
}
