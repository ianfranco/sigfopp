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
@Table(name = "sindicato", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sindicato.findAll", query = "SELECT s FROM Sindicato s")
    , @NamedQuery(name = "Sindicato.findBySindicatoId", query = "SELECT s FROM Sindicato s WHERE s.sindicatoId = :sindicatoId")
    , @NamedQuery(name = "Sindicato.findBySindicatoNombre", query = "SELECT s FROM Sindicato s WHERE s.sindicatoNombre = :sindicatoNombre")
    , @NamedQuery(name = "Sindicato.findBySindicatoCuota", query = "SELECT s FROM Sindicato s WHERE s.sindicatoCuota = :sindicatoCuota")})
public class Sindicato extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sindicato_id")
    private Integer sindicatoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sindicato_nombre")
    private String sindicatoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sindicato_cuota")
    private int sindicatoCuota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorIdSindicato")
    private List<Trabajador> trabajadorList;
    @JoinColumn(name = "sindicato_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta sindicatoIdCuenta;

    public Sindicato() {
    }

    public Sindicato(Integer sindicatoId) {
        this.sindicatoId = sindicatoId;
    }

    public Sindicato(Integer sindicatoId, String sindicatoNombre, int sindicatoCuota) {
        this.sindicatoId = sindicatoId;
        this.sindicatoNombre = sindicatoNombre;
        this.sindicatoCuota = sindicatoCuota;
    }

    public Integer getSindicatoId() {
        return sindicatoId;
    }

    public void setSindicatoId(Integer sindicatoId) {
        this.sindicatoId = sindicatoId;
    }

    public String getSindicatoNombre() {
        return sindicatoNombre;
    }

    public void setSindicatoNombre(String sindicatoNombre) {
        this.sindicatoNombre = sindicatoNombre;
    }

    public int getSindicatoCuota() {
        return sindicatoCuota;
    }

    public void setSindicatoCuota(int sindicatoCuota) {
        this.sindicatoCuota = sindicatoCuota;
    }

    @XmlTransient
    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    public Cuenta getSindicatoIdCuenta() {
        return sindicatoIdCuenta;
    }

    public void setSindicatoIdCuenta(Cuenta sindicatoIdCuenta) {
        this.sindicatoIdCuenta = sindicatoIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sindicatoId != null ? sindicatoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sindicato)) {
            return false;
        }
        Sindicato other = (Sindicato) object;
        if ((this.sindicatoId == null && other.sindicatoId != null) || (this.sindicatoId != null && !this.sindicatoId.equals(other.sindicatoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Sindicato[ sindicatoId=" + sindicatoId + " ]";
    }
    
}
