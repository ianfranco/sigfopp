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
@Table(name = "marca_bus", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MarcaBus.findAll", query = "SELECT m FROM MarcaBus m")
    , @NamedQuery(name = "MarcaBus.findByMarcaBusId", query = "SELECT m FROM MarcaBus m WHERE m.marcaBusId = :marcaBusId")
    , @NamedQuery(name = "MarcaBus.findByMarcaBusNombre", query = "SELECT m FROM MarcaBus m WHERE m.marcaBusNombre = :marcaBusNombre")
    })
public class MarcaBus extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "marca_bus_id")
    private Integer marcaBusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "marca_bus_nombre")
    private String marcaBusNombre;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modeloMarcaBusIdMarca")
    private List<ModeloMarcaBus> modeloMarcaBusList;

    public MarcaBus() {
    }

    public MarcaBus(Integer marcaBusId) {
        this.marcaBusId = marcaBusId;
    }

    public MarcaBus(Integer marcaBusId, String marcaBusNombre) {
        this.marcaBusId = marcaBusId;
        this.marcaBusNombre = marcaBusNombre;
    }

    public Integer getMarcaBusId() {
        return marcaBusId;
    }

    public void setMarcaBusId(Integer marcaBusId) {
        this.marcaBusId = marcaBusId;
    }

    public String getMarcaBusNombre() {
        return marcaBusNombre;
    }

    public void setMarcaBusNombre(String marcaBusNombre) {
        this.marcaBusNombre = marcaBusNombre;
    }

    @XmlTransient
    public List<ModeloMarcaBus> getModeloMarcaBusList() {
        return modeloMarcaBusList;
    }

    public void setModeloMarcaBusList(List<ModeloMarcaBus> modeloMarcaBusList) {
        this.modeloMarcaBusList = modeloMarcaBusList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (marcaBusId != null ? marcaBusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarcaBus)) {
            return false;
        }
        MarcaBus other = (MarcaBus) object;
        if ((this.marcaBusId == null && other.marcaBusId != null) || (this.marcaBusId != null && !this.marcaBusId.equals(other.marcaBusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.MarcaBus[ marcaBusId=" + marcaBusId + " ]";
    }
    
}
