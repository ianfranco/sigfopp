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
@Table(name = "centro_costo", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CentroCosto.findAll", query = "SELECT c FROM CentroCosto c")
    , @NamedQuery(name = "CentroCosto.findByCentroCostoId", query = "SELECT c FROM CentroCosto c WHERE c.centroCostoId = :centroCostoId")
    , @NamedQuery(name = "CentroCosto.findByCentroCostoNombre", query = "SELECT c FROM CentroCosto c WHERE c.centroCostoNombre = :centroCostoNombre")
    })
public class CentroCosto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "centro_costo_id")
    private Integer centroCostoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "centro_costo_nombre")
    private String centroCostoNombre;    
    @JoinColumn(name = "centro_costo_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta centroCostoIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorIdCentroCosto")
    private List<Trabajador> trabajadorList;

    public CentroCosto() {
    }

    public CentroCosto(Integer centroCostoId) {
        this.centroCostoId = centroCostoId;
    }

    public CentroCosto(Integer centroCostoId, String centroCostoNombre) {
        this.centroCostoId = centroCostoId;
        this.centroCostoNombre = centroCostoNombre;
    }

    public Integer getCentroCostoId() {
        return centroCostoId;
    }

    public void setCentroCostoId(Integer centroCostoId) {
        this.centroCostoId = centroCostoId;
    }

    public String getCentroCostoNombre() {
        return centroCostoNombre;
    }

    public void setCentroCostoNombre(String centroCostoNombre) {
        this.centroCostoNombre = centroCostoNombre;
    }

    public Cuenta getCentroCostoIdCuenta() {
        return centroCostoIdCuenta;
    }

    public void setCentroCostoIdCuenta(Cuenta centroCostoIdCuenta) {
        this.centroCostoIdCuenta = centroCostoIdCuenta;
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
        hash += (centroCostoId != null ? centroCostoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CentroCosto)) {
            return false;
        }
        CentroCosto other = (CentroCosto) object;
        if ((this.centroCostoId == null && other.centroCostoId != null) || (this.centroCostoId != null && !this.centroCostoId.equals(other.centroCostoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CentroCosto[ centroCostoId=" + centroCostoId + " ]";
    }
    
}
