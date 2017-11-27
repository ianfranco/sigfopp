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
@Table(name = "departamento", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
    , @NamedQuery(name = "Departamento.findByDepartamentoId", query = "SELECT d FROM Departamento d WHERE d.departamentoId = :departamentoId")
    , @NamedQuery(name = "Departamento.findByDepartamentoNombre", query = "SELECT d FROM Departamento d WHERE d.departamentoNombre = :departamentoNombre")
    , @NamedQuery(name = "Departamento.findByDepartamentoActivo", query = "SELECT d FROM Departamento d WHERE d.departamentoActivo = :departamentoActivo")
    })
public class Departamento extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "departamento_id")
    private Integer departamentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "departamento_nombre")
    private String departamentoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "departamento_activo")
    private boolean departamentoActivo;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gastoAdministracionMensualIdDepartamento")
    private List<GastoAdministracionMensual> gastoAdministracionMensualList;
    @JoinColumn(name = "departamento_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta departamentoIdCuenta;

    public Departamento() {
    }

    public Departamento(Integer departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Departamento(Integer departamentoId, String departamentoNombre, boolean departamentoActivo) {
        this.departamentoId = departamentoId;
        this.departamentoNombre = departamentoNombre;
        this.departamentoActivo = departamentoActivo;
    }

    public Integer getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Integer departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getDepartamentoNombre() {
        return departamentoNombre;
    }

    public void setDepartamentoNombre(String departamentoNombre) {
        this.departamentoNombre = departamentoNombre;
    }

    public boolean getDepartamentoActivo() {
        return departamentoActivo;
    }

    public void setDepartamentoActivo(boolean departamentoActivo) {
        this.departamentoActivo = departamentoActivo;
    }

    @XmlTransient
    public List<GastoAdministracionMensual> getGastoAdministracionMensualList() {
        return gastoAdministracionMensualList;
    }

    public void setGastoAdministracionMensualList(List<GastoAdministracionMensual> gastoAdministracionMensualList) {
        this.gastoAdministracionMensualList = gastoAdministracionMensualList;
    }

    public Cuenta getDepartamentoIdCuenta() {
        return departamentoIdCuenta;
    }

    public void setDepartamentoIdCuenta(Cuenta departamentoIdCuenta) {
        this.departamentoIdCuenta = departamentoIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (departamentoId != null ? departamentoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.departamentoId == null && other.departamentoId != null) || (this.departamentoId != null && !this.departamentoId.equals(other.departamentoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Departamento[ departamentoId=" + departamentoId + " ]";
    }
    
}
