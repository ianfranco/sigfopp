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
@Table(name = "valor_minuto", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorMinuto.findAll", query = "SELECT v FROM ValorMinuto v")
    , @NamedQuery(name = "ValorMinuto.findByValorMinutoId", query = "SELECT v FROM ValorMinuto v WHERE v.valorMinutoId = :valorMinutoId")
    , @NamedQuery(name = "ValorMinuto.findByValorMinutoNombre", query = "SELECT v FROM ValorMinuto v WHERE v.valorMinutoNombre = :valorMinutoNombre")
    , @NamedQuery(name = "ValorMinuto.findByValorMinutoPrecio", query = "SELECT v FROM ValorMinuto v WHERE v.valorMinutoPrecio = :valorMinutoPrecio")
    })
public class ValorMinuto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "valor_minuto_id")
    private Integer valorMinutoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "valor_minuto_nombre")
    private String valorMinutoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_minuto_precio")
    private int valorMinutoPrecio;    
    @JoinColumn(name = "valor_minuto_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta valorMinutoIdCuenta;    

    public ValorMinuto() {
    }

    public ValorMinuto(Integer valorMinutoId) {
        this.valorMinutoId = valorMinutoId;
    }

    public ValorMinuto(Integer valorMinutoId, String valorMinutoNombre, int valorMinutoPrecio) {
        this.valorMinutoId = valorMinutoId;
        this.valorMinutoNombre = valorMinutoNombre;
        this.valorMinutoPrecio = valorMinutoPrecio;
    }

    public Integer getValorMinutoId() {
        return valorMinutoId;
    }

    public void setValorMinutoId(Integer valorMinutoId) {
        this.valorMinutoId = valorMinutoId;
    }

    public String getValorMinutoNombre() {
        return valorMinutoNombre;
    }

    public void setValorMinutoNombre(String valorMinutoNombre) {
        this.valorMinutoNombre = valorMinutoNombre;
    }

    public int getValorMinutoPrecio() {
        return valorMinutoPrecio;
    }

    public void setValorMinutoPrecio(int valorMinutoPrecio) {
        this.valorMinutoPrecio = valorMinutoPrecio;
    }

    public Cuenta getValorMinutoIdCuenta() {
        return valorMinutoIdCuenta;
    }

    public void setValorMinutoIdCuenta(Cuenta valorMinutoIdCuenta) {
        this.valorMinutoIdCuenta = valorMinutoIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valorMinutoId != null ? valorMinutoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValorMinuto)) {
            return false;
        }
        ValorMinuto other = (ValorMinuto) object;
        if ((this.valorMinutoId == null && other.valorMinutoId != null) || (this.valorMinutoId != null && !this.valorMinutoId.equals(other.valorMinutoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ValorMinuto[ valorMinutoId=" + valorMinutoId + " ]";
    }
    
}
