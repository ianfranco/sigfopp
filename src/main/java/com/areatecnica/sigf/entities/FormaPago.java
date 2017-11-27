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
@Table(name = "forma_pago", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPago.findAll", query = "SELECT f FROM FormaPago f")
    , @NamedQuery(name = "FormaPago.findByFormaPagoId", query = "SELECT f FROM FormaPago f WHERE f.formaPagoId = :formaPagoId")
    , @NamedQuery(name = "FormaPago.findByFormaPagoNombre", query = "SELECT f FROM FormaPago f WHERE f.formaPagoNombre = :formaPagoNombre")
    })
public class FormaPago extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "forma_pago_id")
    private Integer formaPagoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "forma_pago_nombre")
    private String formaPagoNombre;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorIdFormaPago")
    private List<Trabajador> trabajadorList;

    public FormaPago() {
    }

    public FormaPago(Integer formaPagoId) {
        this.formaPagoId = formaPagoId;
    }

    public FormaPago(Integer formaPagoId, String formaPagoNombre) {
        this.formaPagoId = formaPagoId;
        this.formaPagoNombre = formaPagoNombre;
    }

    public Integer getFormaPagoId() {
        return formaPagoId;
    }

    public void setFormaPagoId(Integer formaPagoId) {
        this.formaPagoId = formaPagoId;
    }

    public String getFormaPagoNombre() {
        return formaPagoNombre;
    }

    public void setFormaPagoNombre(String formaPagoNombre) {
        this.formaPagoNombre = formaPagoNombre;
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
        hash += (formaPagoId != null ? formaPagoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPago)) {
            return false;
        }
        FormaPago other = (FormaPago) object;
        if ((this.formaPagoId == null && other.formaPagoId != null) || (this.formaPagoId != null && !this.formaPagoId.equals(other.formaPagoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.FormaPago[ formaPagoId=" + formaPagoId + " ]";
    }
    
}
