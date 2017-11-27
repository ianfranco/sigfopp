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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "mutual", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mutual.findAll", query = "SELECT m FROM Mutual m")
    , @NamedQuery(name = "Mutual.findByMutualId", query = "SELECT m FROM Mutual m WHERE m.mutualId = :mutualId")
    , @NamedQuery(name = "Mutual.findByMutualNombre", query = "SELECT m FROM Mutual m WHERE m.mutualNombre = :mutualNombre")
    , @NamedQuery(name = "Mutual.findByMutualComision", query = "SELECT m FROM Mutual m WHERE m.mutualComision = :mutualComision")
    , @NamedQuery(name = "Mutual.findByMutualPorcentajeDescuento", query = "SELECT m FROM Mutual m WHERE m.mutualPorcentajeDescuento = :mutualPorcentajeDescuento")
    })
public class Mutual extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mutual_id")
    private Integer mutualId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "mutual_nombre")
    private String mutualNombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mutual_comision")
    private Float mutualComision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mutual_porcentaje_descuento")
    private float mutualPorcentajeDescuento;    
    @JoinColumn(name = "mutual_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta mutualIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaIdMutual")
    private List<Empresa> empresaList;

    public Mutual() {
    }

    public Mutual(Integer mutualId) {
        this.mutualId = mutualId;
    }

    public Mutual(Integer mutualId, String mutualNombre, float mutualPorcentajeDescuento) {
        this.mutualId = mutualId;
        this.mutualNombre = mutualNombre;
        this.mutualPorcentajeDescuento = mutualPorcentajeDescuento;
    }

    public Integer getMutualId() {
        return mutualId;
    }

    public void setMutualId(Integer mutualId) {
        this.mutualId = mutualId;
    }

    public String getMutualNombre() {
        return mutualNombre;
    }

    public void setMutualNombre(String mutualNombre) {
        this.mutualNombre = mutualNombre;
    }

    public Float getMutualComision() {
        return mutualComision;
    }

    public void setMutualComision(Float mutualComision) {
        this.mutualComision = mutualComision;
    }

    public float getMutualPorcentajeDescuento() {
        return mutualPorcentajeDescuento;
    }

    public void setMutualPorcentajeDescuento(float mutualPorcentajeDescuento) {
        this.mutualPorcentajeDescuento = mutualPorcentajeDescuento;
    }

    public Cuenta getMutualIdCuenta() {
        return mutualIdCuenta;
    }

    public void setMutualIdCuenta(Cuenta mutualIdCuenta) {
        this.mutualIdCuenta = mutualIdCuenta;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mutualId != null ? mutualId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mutual)) {
            return false;
        }
        Mutual other = (Mutual) object;
        if ((this.mutualId == null && other.mutualId != null) || (this.mutualId != null && !this.mutualId.equals(other.mutualId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Mutual[ mutualId=" + mutualId + " ]";
    }
    
}
