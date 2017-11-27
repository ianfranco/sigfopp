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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "representante_empresa", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepresentanteEmpresa.findAll", query = "SELECT r FROM RepresentanteEmpresa r")
    , @NamedQuery(name = "RepresentanteEmpresa.findByRepresentanteEmpresaId", query = "SELECT r FROM RepresentanteEmpresa r WHERE r.representanteEmpresaId = :representanteEmpresaId")})
public class RepresentanteEmpresa extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "representante_empresa_id")
    private Integer representanteEmpresaId;
    @JoinColumn(name = "representante_empresa_id_empresa", referencedColumnName = "empresa_id", nullable = false)
    @ManyToOne(optional = false)
    private Empresa representanteEmpresaIdEmpresa;
    @JoinColumn(name = "representante_empresa_id_representante_legal", referencedColumnName = "representante_legal_id", nullable = false)
    @ManyToOne(optional = false)
    private RepresentanteLegal representanteEmpresaIdRepresentanteLegal;

    public RepresentanteEmpresa() {
    }

    public RepresentanteEmpresa(Integer representanteEmpresaId) {
        this.representanteEmpresaId = representanteEmpresaId;
    }

    public Integer getRepresentanteEmpresaId() {
        return representanteEmpresaId;
    }

    public void setRepresentanteEmpresaId(Integer representanteEmpresaId) {
        this.representanteEmpresaId = representanteEmpresaId;
    }

    public Empresa getRepresentanteEmpresaIdEmpresa() {
        return representanteEmpresaIdEmpresa;
    }

    public void setRepresentanteEmpresaIdEmpresa(Empresa representanteEmpresaIdEmpresa) {
        this.representanteEmpresaIdEmpresa = representanteEmpresaIdEmpresa;
    }

    public RepresentanteLegal getRepresentanteEmpresaIdRepresentanteLegal() {
        return representanteEmpresaIdRepresentanteLegal;
    }

    public void setRepresentanteEmpresaIdRepresentanteLegal(RepresentanteLegal representanteEmpresaIdRepresentanteLegal) {
        this.representanteEmpresaIdRepresentanteLegal = representanteEmpresaIdRepresentanteLegal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (representanteEmpresaId != null ? representanteEmpresaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepresentanteEmpresa)) {
            return false;
        }
        RepresentanteEmpresa other = (RepresentanteEmpresa) object;
        if ((this.representanteEmpresaId == null && other.representanteEmpresaId != null) || (this.representanteEmpresaId != null && !this.representanteEmpresaId.equals(other.representanteEmpresaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RepresentanteEmpresa[ representanteEmpresaId=" + representanteEmpresaId + " ]";
    }
    
}
