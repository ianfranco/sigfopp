/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "recaudacion_extra", catalog = "sigf_v3", schema = "")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "RecaudacionExtra.findAll", query = "SELECT r FROM RecaudacionExtra r")
    , @NamedQuery(name = "RecaudacionExtra.findByRecaudacionExtraId", query = "SELECT r FROM RecaudacionExtra r WHERE r.recaudacionExtraId = :recaudacionExtraId")
    , @NamedQuery(name = "RecaudacionExtra.findByFechaRecaudacion", query = "SELECT r FROM RecaudacionExtra r WHERE r.recaudacionExtraIdRecaudacion.recaudacionFecha =:recaudacionFecha AND r.recaudacionExtraIdRecaudacion.recaudacionIdCaja =:recaudacionIdCaja")
    , @NamedQuery(name = "RecaudacionExtra.findByRecaudacionExtraNombre", query = "SELECT r FROM RecaudacionExtra r WHERE r.recaudacionExtraNombre = :recaudacionExtraNombre")
    , @NamedQuery(name = "RecaudacionExtra.findByRecaudacionExtraMonto", query = "SELECT r FROM RecaudacionExtra r WHERE r.recaudacionExtraMonto = :recaudacionExtraMonto")})
public class RecaudacionExtra extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recaudacion_extra_id")
    private Integer recaudacionExtraId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "recaudacion_extra_nombre")
    private String recaudacionExtraNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recaudacion_extra_monto")
    private int recaudacionExtraMonto;
    @JoinColumn(name = "recaudacion_extra_id_recaudacion", referencedColumnName = "recaudacion_id")
    @ManyToOne(optional = false)
    private Recaudacion recaudacionExtraIdRecaudacion;

    public RecaudacionExtra() {
    }

    public RecaudacionExtra(Integer recaudacionExtraId) {
        this.recaudacionExtraId = recaudacionExtraId;
    }

    public RecaudacionExtra(Integer recaudacionExtraId, String recaudacionExtraNombre, int recaudacionExtraMonto) {
        this.recaudacionExtraId = recaudacionExtraId;
        this.recaudacionExtraNombre = recaudacionExtraNombre;
        this.recaudacionExtraMonto = recaudacionExtraMonto;
    }

    public Integer getRecaudacionExtraId() {
        return recaudacionExtraId;
    }

    public void setRecaudacionExtraId(Integer recaudacionExtraId) {
        this.recaudacionExtraId = recaudacionExtraId;
    }

    public String getRecaudacionExtraNombre() {
        return recaudacionExtraNombre;
    }

    public void setRecaudacionExtraNombre(String recaudacionExtraNombre) {
        this.recaudacionExtraNombre = recaudacionExtraNombre;
    }

    public int getRecaudacionExtraMonto() {
        return recaudacionExtraMonto;
    }

    public void setRecaudacionExtraMonto(int recaudacionExtraMonto) {
        this.recaudacionExtraMonto = recaudacionExtraMonto;
    }

    public Recaudacion getRecaudacionExtraIdRecaudacion() {
        return recaudacionExtraIdRecaudacion;
    }

    public void setRecaudacionExtraIdRecaudacion(Recaudacion recaudacionExtraIdRecaudacion) {
        this.recaudacionExtraIdRecaudacion = recaudacionExtraIdRecaudacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recaudacionExtraId != null ? recaudacionExtraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecaudacionExtra)) {
            return false;
        }
        RecaudacionExtra other = (RecaudacionExtra) object;
        if ((this.recaudacionExtraId == null && other.recaudacionExtraId != null) || (this.recaudacionExtraId != null && !this.recaudacionExtraId.equals(other.recaudacionExtraId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RecaudacionExtra[ recaudacionExtraId=" + recaudacionExtraId + " ]";
    }

}
