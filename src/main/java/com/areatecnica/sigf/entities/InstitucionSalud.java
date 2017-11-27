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
@Table(name = "institucion_salud", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InstitucionSalud.findAll", query = "SELECT i FROM InstitucionSalud i")
    , @NamedQuery(name = "InstitucionSalud.findByInstitucionSaludId", query = "SELECT i FROM InstitucionSalud i WHERE i.institucionSaludId = :institucionSaludId")
    , @NamedQuery(name = "InstitucionSalud.findByInstitucionSaludNombre", query = "SELECT i FROM InstitucionSalud i WHERE i.institucionSaludNombre = :institucionSaludNombre")})
public class InstitucionSalud extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "institucion_salud_id")
    private Integer institucionSaludId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "institucion_salud_nombre")
    private String institucionSaludNombre;
    @JoinColumn(name = "institucion_salud_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta institucionSaludIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorIdInstitucionSalud")
    private List<Trabajador> trabajadorList;

    public InstitucionSalud() {
    }

    public InstitucionSalud(Integer institucionSaludId) {
        this.institucionSaludId = institucionSaludId;
    }

    public InstitucionSalud(Integer institucionSaludId, String institucionSaludNombre) {
        this.institucionSaludId = institucionSaludId;
        this.institucionSaludNombre = institucionSaludNombre;
    }

    public Integer getInstitucionSaludId() {
        return institucionSaludId;
    }

    public void setInstitucionSaludId(Integer institucionSaludId) {
        this.institucionSaludId = institucionSaludId;
    }

    public String getInstitucionSaludNombre() {
        return institucionSaludNombre;
    }

    public void setInstitucionSaludNombre(String institucionSaludNombre) {
        this.institucionSaludNombre = institucionSaludNombre;
    }

    public Cuenta getInstitucionSaludIdCuenta() {
        return institucionSaludIdCuenta;
    }

    public void setInstitucionSaludIdCuenta(Cuenta institucionSaludIdCuenta) {
        this.institucionSaludIdCuenta = institucionSaludIdCuenta;
    }

    @XmlTransient
    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (institucionSaludId != null ? institucionSaludId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstitucionSalud)) {
            return false;
        }
        InstitucionSalud other = (InstitucionSalud) object;
        if ((this.institucionSaludId == null && other.institucionSaludId != null) || (this.institucionSaludId != null && !this.institucionSaludId.equals(other.institucionSaludId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.InstitucionSalud[ institucionSaludId=" + institucionSaludId + " ]";
    }
    
}
