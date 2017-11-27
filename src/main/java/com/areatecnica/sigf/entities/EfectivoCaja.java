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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "efectivo_caja", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EfectivoCaja.findAll", query = "SELECT e FROM EfectivoCaja e")
    , @NamedQuery(name = "EfectivoCaja.findByEfectivoCajaId", query = "SELECT e FROM EfectivoCaja e WHERE e.efectivoCajaId = :efectivoCajaId")
    , @NamedQuery(name = "EfectivoCaja.findByEfectivoCajaValorEfectivo", query = "SELECT e FROM EfectivoCaja e WHERE e.efectivoCajaValorEfectivo = :efectivoCajaValorEfectivo")
    })
public class EfectivoCaja extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "efectivo_caja_id")
    private Integer efectivoCajaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "efectivo_caja_valor_efectivo")
    private int efectivoCajaValorEfectivo;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleResumenRecaudacionIdEfectivoCaja")
    private List<DetalleResumenRecaudacion> detalleResumenRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleMetalicoCtvIdEfectivoCaja")
    private List<DetalleMetalicoCtv> detalleMetalicoCtvList;
    @JoinColumn(name = "efectivo_caja_id_metodo_pago", referencedColumnName = "metodo_pago_id", nullable = false)
    @ManyToOne(optional = false)
    private MetodoPago efectivoCajaIdMetodoPago;

    public EfectivoCaja() {
    }

    public EfectivoCaja(Integer efectivoCajaId) {
        this.efectivoCajaId = efectivoCajaId;
    }

    public EfectivoCaja(Integer efectivoCajaId, int efectivoCajaValorEfectivo) {
        this.efectivoCajaId = efectivoCajaId;
        this.efectivoCajaValorEfectivo = efectivoCajaValorEfectivo;
    }

    public Integer getEfectivoCajaId() {
        return efectivoCajaId;
    }

    public void setEfectivoCajaId(Integer efectivoCajaId) {
        this.efectivoCajaId = efectivoCajaId;
    }

    public int getEfectivoCajaValorEfectivo() {
        return efectivoCajaValorEfectivo;
    }

    public void setEfectivoCajaValorEfectivo(int efectivoCajaValorEfectivo) {
        this.efectivoCajaValorEfectivo = efectivoCajaValorEfectivo;
    }

    @XmlTransient
    public List<DetalleResumenRecaudacion> getDetalleResumenRecaudacionList() {
        return detalleResumenRecaudacionList;
    }

    public void setDetalleResumenRecaudacionList(List<DetalleResumenRecaudacion> detalleResumenRecaudacionList) {
        this.detalleResumenRecaudacionList = detalleResumenRecaudacionList;
    }

    @XmlTransient
    public List<DetalleMetalicoCtv> getDetalleMetalicoCtvList() {
        return detalleMetalicoCtvList;
    }

    public void setDetalleMetalicoCtvList(List<DetalleMetalicoCtv> detalleMetalicoCtvList) {
        this.detalleMetalicoCtvList = detalleMetalicoCtvList;
    }

    public MetodoPago getEfectivoCajaIdMetodoPago() {
        return efectivoCajaIdMetodoPago;
    }

    public void setEfectivoCajaIdMetodoPago(MetodoPago efectivoCajaIdMetodoPago) {
        this.efectivoCajaIdMetodoPago = efectivoCajaIdMetodoPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (efectivoCajaId != null ? efectivoCajaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EfectivoCaja)) {
            return false;
        }
        EfectivoCaja other = (EfectivoCaja) object;
        if ((this.efectivoCajaId == null && other.efectivoCajaId != null) || (this.efectivoCajaId != null && !this.efectivoCajaId.equals(other.efectivoCajaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.EfectivoCaja[ efectivoCajaId=" + efectivoCajaId + " ]";
    }
    
}
