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
@Table(name = "impuesto_segunda_categoria", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImpuestoSegundaCategoria.findAll", query = "SELECT i FROM ImpuestoSegundaCategoria i")
    , @NamedQuery(name = "ImpuestoSegundaCategoria.findByImpuestoSegundaCategoriaId", query = "SELECT i FROM ImpuestoSegundaCategoria i WHERE i.impuestoSegundaCategoriaId = :impuestoSegundaCategoriaId")
    , @NamedQuery(name = "ImpuestoSegundaCategoria.findByImpuestoSegundaCategoriaDesde", query = "SELECT i FROM ImpuestoSegundaCategoria i WHERE i.impuestoSegundaCategoriaDesde = :impuestoSegundaCategoriaDesde")
    , @NamedQuery(name = "ImpuestoSegundaCategoria.findByImpuestoSegundaCategoriaHasta", query = "SELECT i FROM ImpuestoSegundaCategoria i WHERE i.impuestoSegundaCategoriaHasta = :impuestoSegundaCategoriaHasta")
    , @NamedQuery(name = "ImpuestoSegundaCategoria.findByImpuestoSegundaCategoriaFactor", query = "SELECT i FROM ImpuestoSegundaCategoria i WHERE i.impuestoSegundaCategoriaFactor = :impuestoSegundaCategoriaFactor")
    , @NamedQuery(name = "ImpuestoSegundaCategoria.findByImpuestoSegundaCategoriaRebaja", query = "SELECT i FROM ImpuestoSegundaCategoria i WHERE i.impuestoSegundaCategoriaRebaja = :impuestoSegundaCategoriaRebaja")
    })
public class ImpuestoSegundaCategoria extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "impuesto_segunda_categoria_id")
    private Integer impuestoSegundaCategoriaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "impuesto_segunda_categoria_desde")
    private float impuestoSegundaCategoriaDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "impuesto_segunda_categoria_hasta")
    private float impuestoSegundaCategoriaHasta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "impuesto_segunda_categoria_factor")
    private Float impuestoSegundaCategoriaFactor;
    @Column(name = "impuesto_segunda_categoria_rebaja")
    private Float impuestoSegundaCategoriaRebaja;    

    public ImpuestoSegundaCategoria() {
    }

    public ImpuestoSegundaCategoria(Integer impuestoSegundaCategoriaId) {
        this.impuestoSegundaCategoriaId = impuestoSegundaCategoriaId;
    }

    public ImpuestoSegundaCategoria(Integer impuestoSegundaCategoriaId, float impuestoSegundaCategoriaDesde, float impuestoSegundaCategoriaHasta) {
        this.impuestoSegundaCategoriaId = impuestoSegundaCategoriaId;
        this.impuestoSegundaCategoriaDesde = impuestoSegundaCategoriaDesde;
        this.impuestoSegundaCategoriaHasta = impuestoSegundaCategoriaHasta;
    }

    public Integer getImpuestoSegundaCategoriaId() {
        return impuestoSegundaCategoriaId;
    }

    public void setImpuestoSegundaCategoriaId(Integer impuestoSegundaCategoriaId) {
        this.impuestoSegundaCategoriaId = impuestoSegundaCategoriaId;
    }

    public float getImpuestoSegundaCategoriaDesde() {
        return impuestoSegundaCategoriaDesde;
    }

    public void setImpuestoSegundaCategoriaDesde(float impuestoSegundaCategoriaDesde) {
        this.impuestoSegundaCategoriaDesde = impuestoSegundaCategoriaDesde;
    }

    public float getImpuestoSegundaCategoriaHasta() {
        return impuestoSegundaCategoriaHasta;
    }

    public void setImpuestoSegundaCategoriaHasta(float impuestoSegundaCategoriaHasta) {
        this.impuestoSegundaCategoriaHasta = impuestoSegundaCategoriaHasta;
    }

    public Float getImpuestoSegundaCategoriaFactor() {
        return impuestoSegundaCategoriaFactor;
    }

    public void setImpuestoSegundaCategoriaFactor(Float impuestoSegundaCategoriaFactor) {
        this.impuestoSegundaCategoriaFactor = impuestoSegundaCategoriaFactor;
    }

    public Float getImpuestoSegundaCategoriaRebaja() {
        return impuestoSegundaCategoriaRebaja;
    }

    public void setImpuestoSegundaCategoriaRebaja(Float impuestoSegundaCategoriaRebaja) {
        this.impuestoSegundaCategoriaRebaja = impuestoSegundaCategoriaRebaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (impuestoSegundaCategoriaId != null ? impuestoSegundaCategoriaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImpuestoSegundaCategoria)) {
            return false;
        }
        ImpuestoSegundaCategoria other = (ImpuestoSegundaCategoria) object;
        if ((this.impuestoSegundaCategoriaId == null && other.impuestoSegundaCategoriaId != null) || (this.impuestoSegundaCategoriaId != null && !this.impuestoSegundaCategoriaId.equals(other.impuestoSegundaCategoriaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ImpuestoSegundaCategoria[ impuestoSegundaCategoriaId=" + impuestoSegundaCategoriaId + " ]";
    }
    
}
