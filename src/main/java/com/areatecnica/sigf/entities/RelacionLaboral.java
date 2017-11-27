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
@Table(name = "relacion_laboral", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelacionLaboral.findAll", query = "SELECT r FROM RelacionLaboral r")
    , @NamedQuery(name = "RelacionLaboral.findByRelacionLaboralId", query = "SELECT r FROM RelacionLaboral r WHERE r.relacionLaboralId = :relacionLaboralId")
    , @NamedQuery(name = "RelacionLaboral.findByRelacionLaboralFechaInicio", query = "SELECT r FROM RelacionLaboral r WHERE r.relacionLaboralFechaInicio = :relacionLaboralFechaInicio")
    , @NamedQuery(name = "RelacionLaboral.findByRelacionLaboralFechaFin", query = "SELECT r FROM RelacionLaboral r WHERE r.relacionLaboralFechaFin = :relacionLaboralFechaFin")
    , @NamedQuery(name = "RelacionLaboral.findByRelacionLaboralSueldoBase", query = "SELECT r FROM RelacionLaboral r WHERE r.relacionLaboralSueldoBase = :relacionLaboralSueldoBase")
    , @NamedQuery(name = "RelacionLaboral.findByRelacionLaboralActivo", query = "SELECT r FROM RelacionLaboral r WHERE r.relacionLaboralActivo = :relacionLaboralActivo")
    })
public class RelacionLaboral extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "relacion_laboral_id")
    private Integer relacionLaboralId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "relacion_laboral_fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date relacionLaboralFechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "relacion_laboral_fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date relacionLaboralFechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "relacion_laboral_sueldo_base")
    private int relacionLaboralSueldoBase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "relacion_laboral_activo")
    private boolean relacionLaboralActivo;    
    @JoinColumn(name = "relacion_laboral_id_terminal", referencedColumnName = "terminal_id", nullable = false)
    @ManyToOne(optional = false)
    private Terminal relacionLaboralIdTerminal;
    @JoinColumn(name = "relacion_laboral_id_tipo_contrato", referencedColumnName = "tipo_contrato_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoContrato relacionLaboralIdTipoContrato;
    @JoinColumn(name = "relacion_laboral_id_empresa", referencedColumnName = "empresa_id", nullable = false)
    @ManyToOne(optional = false)
    private Empresa relacionLaboralIdEmpresa;
    @JoinColumn(name = "relacion_laboral_id_operador", referencedColumnName = "operador_transporte_id", nullable = false)
    @ManyToOne(optional = false)
    private OperadorTransporte relacionLaboralIdOperador;
    @JoinColumn(name = "relacion_laboral_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador relacionLaboralIdTrabajador;
    @JoinColumn(name = "relacion_laboral_id_tipo_trabajador", referencedColumnName = "tipo_trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoTrabajador relacionLaboralIdTipoTrabajador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "finiquitoRelacionLaboralIdRelacionLaboral")
    private List<FiniquitoRelacionLaboral> finiquitoRelacionLaboralList;

    public RelacionLaboral() {
    }

    public RelacionLaboral(Integer relacionLaboralId) {
        this.relacionLaboralId = relacionLaboralId;
    }

    public RelacionLaboral(Integer relacionLaboralId, Date relacionLaboralFechaInicio, Date relacionLaboralFechaFin, int relacionLaboralSueldoBase, boolean relacionLaboralActivo) {
        this.relacionLaboralId = relacionLaboralId;
        this.relacionLaboralFechaInicio = relacionLaboralFechaInicio;
        this.relacionLaboralFechaFin = relacionLaboralFechaFin;
        this.relacionLaboralSueldoBase = relacionLaboralSueldoBase;
        this.relacionLaboralActivo = relacionLaboralActivo;
    }

    public Integer getRelacionLaboralId() {
        return relacionLaboralId;
    }

    public void setRelacionLaboralId(Integer relacionLaboralId) {
        this.relacionLaboralId = relacionLaboralId;
    }

    public Date getRelacionLaboralFechaInicio() {
        return relacionLaboralFechaInicio;
    }

    public void setRelacionLaboralFechaInicio(Date relacionLaboralFechaInicio) {
        this.relacionLaboralFechaInicio = relacionLaboralFechaInicio;
    }

    public Date getRelacionLaboralFechaFin() {
        return relacionLaboralFechaFin;
    }

    public void setRelacionLaboralFechaFin(Date relacionLaboralFechaFin) {
        this.relacionLaboralFechaFin = relacionLaboralFechaFin;
    }

    public int getRelacionLaboralSueldoBase() {
        return relacionLaboralSueldoBase;
    }

    public void setRelacionLaboralSueldoBase(int relacionLaboralSueldoBase) {
        this.relacionLaboralSueldoBase = relacionLaboralSueldoBase;
    }

    public boolean getRelacionLaboralActivo() {
        return relacionLaboralActivo;
    }

    public void setRelacionLaboralActivo(boolean relacionLaboralActivo) {
        this.relacionLaboralActivo = relacionLaboralActivo;
    }
    
    public Terminal getRelacionLaboralIdTerminal() {
        return relacionLaboralIdTerminal;
    }

    public void setRelacionLaboralIdTerminal(Terminal relacionLaboralIdTerminal) {
        this.relacionLaboralIdTerminal = relacionLaboralIdTerminal;
    }

    public TipoContrato getRelacionLaboralIdTipoContrato() {
        return relacionLaboralIdTipoContrato;
    }

    public void setRelacionLaboralIdTipoContrato(TipoContrato relacionLaboralIdTipoContrato) {
        this.relacionLaboralIdTipoContrato = relacionLaboralIdTipoContrato;
    }

    public Empresa getRelacionLaboralIdEmpresa() {
        return relacionLaboralIdEmpresa;
    }

    public void setRelacionLaboralIdEmpresa(Empresa relacionLaboralIdEmpresa) {
        this.relacionLaboralIdEmpresa = relacionLaboralIdEmpresa;
    }

    public OperadorTransporte getRelacionLaboralIdOperador() {
        return relacionLaboralIdOperador;
    }

    public void setRelacionLaboralIdOperador(OperadorTransporte relacionLaboralIdOperador) {
        this.relacionLaboralIdOperador = relacionLaboralIdOperador;
    }

    public Trabajador getRelacionLaboralIdTrabajador() {
        return relacionLaboralIdTrabajador;
    }

    public void setRelacionLaboralIdTrabajador(Trabajador relacionLaboralIdTrabajador) {
        this.relacionLaboralIdTrabajador = relacionLaboralIdTrabajador;
    }

    public TipoTrabajador getRelacionLaboralIdTipoTrabajador() {
        return relacionLaboralIdTipoTrabajador;
    }

    public void setRelacionLaboralIdTipoTrabajador(TipoTrabajador relacionLaboralIdTipoTrabajador) {
        this.relacionLaboralIdTipoTrabajador = relacionLaboralIdTipoTrabajador;
    }

    @XmlTransient
    public List<FiniquitoRelacionLaboral> getFiniquitoRelacionLaboralList() {
        return finiquitoRelacionLaboralList;
    }

    public void setFiniquitoRelacionLaboralList(List<FiniquitoRelacionLaboral> finiquitoRelacionLaboralList) {
        this.finiquitoRelacionLaboralList = finiquitoRelacionLaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relacionLaboralId != null ? relacionLaboralId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacionLaboral)) {
            return false;
        }
        RelacionLaboral other = (RelacionLaboral) object;
        if ((this.relacionLaboralId == null && other.relacionLaboralId != null) || (this.relacionLaboralId != null && !this.relacionLaboralId.equals(other.relacionLaboralId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RelacionLaboral[ relacionLaboralId=" + relacionLaboralId + " ]";
    }
    
}
