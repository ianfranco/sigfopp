/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "guia", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@Cacheable(false)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Guia.findAll", query = "SELECT g FROM Guia g")
    , @NamedQuery(name = "Guia.findByGuiaId", query = "SELECT g FROM Guia g WHERE g.guiaId = :guiaId")
    , @NamedQuery(name = "Guia.findByGuiaFolio", query = "SELECT g FROM Guia g WHERE g.guiaFolio = :guiaFolio")
    , @NamedQuery(name = "Guia.findByGuiaFecha", query = "SELECT g FROM Guia g WHERE g.guiaFecha = :guiaFecha")
    , @NamedQuery(name = "Guia.findByGuiaTotalIngreso", query = "SELECT g FROM Guia g WHERE g.guiaTotalIngreso = :guiaTotalIngreso")
})
public class Guia extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "guia_id")
    private Integer guiaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "guia_folio")
    private int guiaFolio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "guia_fecha")
    @Temporal(TemporalType.DATE)
    private Date guiaFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "guia_total_ingreso")
    private int guiaTotalIngreso;
    @JoinColumn(name = "guia_id_bus", referencedColumnName = "bus_id", nullable = false)
    @ManyToOne(optional = false)
    private Bus guiaIdBus;
    @JoinColumn(name = "guia_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador guiaIdTrabajador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registroBoletoIdGuia")
    private List<RegistroBoleto> registroBoletoList;

    public Guia() {
    }

    public Guia(Integer guiaId) {
        this.guiaId = guiaId;
    }

    public Guia(Integer guiaId, int guiaFolio, Date guiaFecha, int guiaTotalIngreso) {
        this.guiaId = guiaId;
        this.guiaFolio = guiaFolio;
        this.guiaFecha = guiaFecha;
        this.guiaTotalIngreso = guiaTotalIngreso;
    }

    public Integer getGuiaId() {
        return guiaId;
    }

    public void setGuiaId(Integer guiaId) {
        this.guiaId = guiaId;
    }

    public int getGuiaFolio() {
        return guiaFolio;
    }

    public void setGuiaFolio(int guiaFolio) {
        this.guiaFolio = guiaFolio;
    }

    public Date getGuiaFecha() {
        return guiaFecha;
    }

    public void setGuiaFecha(Date guiaFecha) {
        this.guiaFecha = guiaFecha;
    }

    public int getGuiaTotalIngreso() {
        return guiaTotalIngreso;
    }

    public void setGuiaTotalIngreso(int guiaTotalIngreso) {
        this.guiaTotalIngreso = guiaTotalIngreso;
    }

    public Bus getGuiaIdBus() {
        return guiaIdBus;
    }

    public void setGuiaIdBus(Bus guiaIdBus) {
        this.guiaIdBus = guiaIdBus;
    }

    public Trabajador getGuiaIdTrabajador() {
        return guiaIdTrabajador;
    }

    public void setGuiaIdTrabajador(Trabajador guiaIdTrabajador) {
        this.guiaIdTrabajador = guiaIdTrabajador;
    }

    @XmlTransient
    public List<RegistroBoleto> getRegistroBoletoList() {
        return registroBoletoList;
    }

    public void setRegistroBoletoList(List<RegistroBoleto> registroBoletoList) {
        this.registroBoletoList = registroBoletoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guiaId != null ? guiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guia)) {
            return false;
        }
        Guia other = (Guia) object;
        if ((this.guiaId == null && other.guiaId != null) || (this.guiaId != null && !this.guiaId.equals(other.guiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Guia[ guiaId=" + guiaId + " ]";
    }

}
