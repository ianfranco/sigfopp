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
@Table(name = "plan_cuenta", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanCuenta.findAll", query = "SELECT p FROM PlanCuenta p")
    , @NamedQuery(name = "PlanCuenta.findByPlanCuentaId", query = "SELECT p FROM PlanCuenta p WHERE p.planCuentaId = :planCuentaId")
    , @NamedQuery(name = "PlanCuenta.findByPlanCuentaIdClasificacion", query = "SELECT p FROM PlanCuenta p WHERE p.planCuentaIdClasificacion = :planCuentaIdClasificacion")
    , @NamedQuery(name = "PlanCuenta.findByPlanCuentaIdEmpresa", query = "SELECT p FROM PlanCuenta p WHERE p.planCuentaIdEmpresa = :planCuentaIdEmpresa")
    , @NamedQuery(name = "PlanCuenta.findByPlanCuentaNombre", query = "SELECT p FROM PlanCuenta p WHERE p.planCuentaNombre = :planCuentaNombre")
    })
public class PlanCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "plan_cuenta_id")
    private Integer planCuentaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plan_cuenta_id_clasificacion")
    private int planCuentaIdClasificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plan_cuenta_id_empresa")
    private int planCuentaIdEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "plan_cuenta_nombre")
    private String planCuentaNombre;
    
    public PlanCuenta() {
    }

    public PlanCuenta(Integer planCuentaId) {
        this.planCuentaId = planCuentaId;
    }

    public PlanCuenta(Integer planCuentaId, int planCuentaIdClasificacion, int planCuentaIdEmpresa, String planCuentaNombre) {
        this.planCuentaId = planCuentaId;
        this.planCuentaIdClasificacion = planCuentaIdClasificacion;
        this.planCuentaIdEmpresa = planCuentaIdEmpresa;
        this.planCuentaNombre = planCuentaNombre;
    }

    public Integer getPlanCuentaId() {
        return planCuentaId;
    }

    public void setPlanCuentaId(Integer planCuentaId) {
        this.planCuentaId = planCuentaId;
    }

    public int getPlanCuentaIdClasificacion() {
        return planCuentaIdClasificacion;
    }

    public void setPlanCuentaIdClasificacion(int planCuentaIdClasificacion) {
        this.planCuentaIdClasificacion = planCuentaIdClasificacion;
    }

    public int getPlanCuentaIdEmpresa() {
        return planCuentaIdEmpresa;
    }

    public void setPlanCuentaIdEmpresa(int planCuentaIdEmpresa) {
        this.planCuentaIdEmpresa = planCuentaIdEmpresa;
    }

    public String getPlanCuentaNombre() {
        return planCuentaNombre;
    }

    public void setPlanCuentaNombre(String planCuentaNombre) {
        this.planCuentaNombre = planCuentaNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planCuentaId != null ? planCuentaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanCuenta)) {
            return false;
        }
        PlanCuenta other = (PlanCuenta) object;
        if ((this.planCuentaId == null && other.planCuentaId != null) || (this.planCuentaId != null && !this.planCuentaId.equals(other.planCuentaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.PlanCuenta[ planCuentaId=" + planCuentaId + " ]";
    }
    
}
