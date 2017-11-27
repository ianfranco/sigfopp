/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "recaudacion_combustible", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecaudacionCombustible.findAll", query = "SELECT r FROM RecaudacionCombustible r")
    , @NamedQuery(name = "RecaudacionCombustible.findByIdRecaudacionCombustible", query = "SELECT r FROM RecaudacionCombustible r WHERE r.recaudacionCombustibleId = :idRecaudacionCombustible")
    , @NamedQuery(name = "RecaudacionCombustible.findByFechaRecaudacion", query = "SELECT r FROM RecaudacionCombustible r WHERE r.recaudacionCombustibleIdRecaudacion.recaudacionFecha =:recaudacionFecha AND r.recaudacionCombustibleIdRecaudacion.recaudacionIdCaja =:recaudacionIdCaja")
    , @NamedQuery(name = "RecaudacionCombustible.findByRecaudacionCombustibleMonto", query = "SELECT r FROM RecaudacionCombustible r WHERE r.recaudacionCombustibleMonto = :recaudacionCombustibleMonto")})
public class RecaudacionCombustible extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recaudacion_combustible_id")
    private Integer recaudacionCombustibleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recaudacion_combustible_monto")
    private int recaudacionCombustibleMonto;
    @JoinColumn(name = "recaudacion_combustible_id_recaudacion", referencedColumnName = "recaudacion_id")
    @ManyToOne(optional = false)
    private Recaudacion recaudacionCombustibleIdRecaudacion;
    @JoinColumn(name = "recaudacion_combustible_id_venta_combustible", referencedColumnName = "venta_combustible_id")
    @ManyToOne(optional = false)
    private VentaCombustible recaudacionCombustibleIdVentaCombustible;

    public RecaudacionCombustible() {
    }

    public RecaudacionCombustible(Integer recaudacionCombustibleId) {
        this.recaudacionCombustibleId = recaudacionCombustibleId;
    }

    public RecaudacionCombustible(Integer idRecaudacionCombustible, int recaudacionCombustibleMonto) {
        this.recaudacionCombustibleId = idRecaudacionCombustible;
        this.recaudacionCombustibleMonto = recaudacionCombustibleMonto;
    }

    public Integer getRecaudacionCombustibleId() {
        return recaudacionCombustibleId;
    }

    public void setRecaudacionCombustibleId(Integer recaudacionCombustibleId) {
        this.recaudacionCombustibleId = recaudacionCombustibleId;
    }

    public int getRecaudacionCombustibleMonto() {
        return recaudacionCombustibleMonto;
    }

    public void setRecaudacionCombustibleMonto(int recaudacionCombustibleMonto) {
        this.recaudacionCombustibleMonto = recaudacionCombustibleMonto;
    }

    public Recaudacion getRecaudacionCombustibleIdRecaudacion() {
        return recaudacionCombustibleIdRecaudacion;
    }

    public void setRecaudacionCombustibleIdRecaudacion(Recaudacion recaudacionCombustibleIdRecaudacion) {
        this.recaudacionCombustibleIdRecaudacion = recaudacionCombustibleIdRecaudacion;
    }

    public VentaCombustible getRecaudacionCombustibleIdVentaCombustible() {
        return recaudacionCombustibleIdVentaCombustible;
    }

    public void setRecaudacionCombustibleIdVentaCombustible(VentaCombustible recaudacionCombustibleIdVentaCombustible) {
        this.recaudacionCombustibleIdVentaCombustible = recaudacionCombustibleIdVentaCombustible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recaudacionCombustibleId != null ? recaudacionCombustibleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecaudacionCombustible)) {
            return false;
        }
        RecaudacionCombustible other = (RecaudacionCombustible) object;
        if ((this.recaudacionCombustibleId == null && other.recaudacionCombustibleId != null) || (this.recaudacionCombustibleId != null && !this.recaudacionCombustibleId.equals(other.recaudacionCombustibleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RecaudacionCombustible[ recaudacionCombustibleId=" + recaudacionCombustibleId + " ]";
    }

}
