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
@Table(name = "causal_finiquito", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CausalFiniquito.findAll", query = "SELECT c FROM CausalFiniquito c")
    , @NamedQuery(name = "CausalFiniquito.findByCausalFiniquitoId", query = "SELECT c FROM CausalFiniquito c WHERE c.causalFiniquitoId = :causalFiniquitoId")
    , @NamedQuery(name = "CausalFiniquito.findByCausalFiniquitoCodigo", query = "SELECT c FROM CausalFiniquito c WHERE c.causalFiniquitoCodigo = :causalFiniquitoCodigo")
    , @NamedQuery(name = "CausalFiniquito.findByCausalFiniquitoDescripcion", query = "SELECT c FROM CausalFiniquito c WHERE c.causalFiniquitoDescripcion = :causalFiniquitoDescripcion")})
public class CausalFiniquito extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "causal_finiquito_id")
    private Integer causalFiniquitoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "causal_finiquito_codigo")
    private String causalFiniquitoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "causal_finiquito_descripcion")
    private String causalFiniquitoDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "finiquitoRelacionLaboralIdCausalFiniquito")
    private List<FiniquitoRelacionLaboral> finiquitoRelacionLaboralList;

    public CausalFiniquito() {
    }

    public CausalFiniquito(Integer causalFiniquitoId) {
        this.causalFiniquitoId = causalFiniquitoId;
    }

    public CausalFiniquito(Integer causalFiniquitoId, String causalFiniquitoCodigo, String causalFiniquitoDescripcion) {
        this.causalFiniquitoId = causalFiniquitoId;
        this.causalFiniquitoCodigo = causalFiniquitoCodigo;
        this.causalFiniquitoDescripcion = causalFiniquitoDescripcion;
    }

    public Integer getCausalFiniquitoId() {
        return causalFiniquitoId;
    }

    public void setCausalFiniquitoId(Integer causalFiniquitoId) {
        this.causalFiniquitoId = causalFiniquitoId;
    }

    public String getCausalFiniquitoCodigo() {
        return causalFiniquitoCodigo;
    }

    public void setCausalFiniquitoCodigo(String causalFiniquitoCodigo) {
        this.causalFiniquitoCodigo = causalFiniquitoCodigo;
    }

    public String getCausalFiniquitoDescripcion() {
        return causalFiniquitoDescripcion;
    }

    public void setCausalFiniquitoDescripcion(String causalFiniquitoDescripcion) {
        this.causalFiniquitoDescripcion = causalFiniquitoDescripcion;
    }

    @XmlTransient
    public List<FiniquitoRelacionLaboral> getFiniquitoRelacionLaboralList() {
        return finiquitoRelacionLaboralList;
    }

    public void setFiniquitoRelacionLaboralList(List<FiniquitoRelacionLaboral> finiquitoRelacionLaboralList) {
        this.finiquitoRelacionLaboralList = finiquitoRelacionLaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (causalFiniquitoId != null ? causalFiniquitoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CausalFiniquito)) {
            return false;
        }
        CausalFiniquito other = (CausalFiniquito) object;
        if ((this.causalFiniquitoId == null && other.causalFiniquitoId != null) || (this.causalFiniquitoId != null && !this.causalFiniquitoId.equals(other.causalFiniquitoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CausalFiniquito[ causalFiniquitoId=" + causalFiniquitoId + " ]";
    }
    
}
