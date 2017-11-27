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
@Table(name = "control_asistencia", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlAsistencia.findAll", query = "SELECT c FROM ControlAsistencia c")
    , @NamedQuery(name = "ControlAsistencia.findByControlAsistenciaId", query = "SELECT c FROM ControlAsistencia c WHERE c.controlAsistenciaId = :controlAsistenciaId")
    , @NamedQuery(name = "ControlAsistencia.findByControlAsistenciaHorarioSalida", query = "SELECT c FROM ControlAsistencia c WHERE c.controlAsistenciaHorarioSalida = :controlAsistenciaHorarioSalida")
    , @NamedQuery(name = "ControlAsistencia.findByControlAsistenciaHorarioEntrada", query = "SELECT c FROM ControlAsistencia c WHERE c.controlAsistenciaHorarioEntrada = :controlAsistenciaHorarioEntrada")})
public class ControlAsistencia extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "control_asistencia_id")
    private Integer controlAsistenciaId;
    @Column(name = "control_asistencia_horario_salida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date controlAsistenciaHorarioSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "control_asistencia_horario_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date controlAsistenciaHorarioEntrada;
    @JoinColumn(name = "control_asistencia_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador controlAsistenciaIdTrabajador;

    public ControlAsistencia() {
    }

    public ControlAsistencia(Integer controlAsistenciaId) {
        this.controlAsistenciaId = controlAsistenciaId;
    }

    public ControlAsistencia(Integer controlAsistenciaId, Date controlAsistenciaHorarioEntrada) {
        this.controlAsistenciaId = controlAsistenciaId;
        this.controlAsistenciaHorarioEntrada = controlAsistenciaHorarioEntrada;
    }

    public Integer getControlAsistenciaId() {
        return controlAsistenciaId;
    }

    public void setControlAsistenciaId(Integer controlAsistenciaId) {
        this.controlAsistenciaId = controlAsistenciaId;
    }

    public Date getControlAsistenciaHorarioSalida() {
        return controlAsistenciaHorarioSalida;
    }

    public void setControlAsistenciaHorarioSalida(Date controlAsistenciaHorarioSalida) {
        this.controlAsistenciaHorarioSalida = controlAsistenciaHorarioSalida;
    }

    public Date getControlAsistenciaHorarioEntrada() {
        return controlAsistenciaHorarioEntrada;
    }

    public void setControlAsistenciaHorarioEntrada(Date controlAsistenciaHorarioEntrada) {
        this.controlAsistenciaHorarioEntrada = controlAsistenciaHorarioEntrada;
    }

    public Trabajador getControlAsistenciaIdTrabajador() {
        return controlAsistenciaIdTrabajador;
    }

    public void setControlAsistenciaIdTrabajador(Trabajador controlAsistenciaIdTrabajador) {
        this.controlAsistenciaIdTrabajador = controlAsistenciaIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (controlAsistenciaId != null ? controlAsistenciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlAsistencia)) {
            return false;
        }
        ControlAsistencia other = (ControlAsistencia) object;
        if ((this.controlAsistenciaId == null && other.controlAsistenciaId != null) || (this.controlAsistenciaId != null && !this.controlAsistenciaId.equals(other.controlAsistenciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ControlAsistencia[ controlAsistenciaId=" + controlAsistenciaId + " ]";
    }
    
}
