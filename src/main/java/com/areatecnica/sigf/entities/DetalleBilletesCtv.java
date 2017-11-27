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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "detalle_billetes_ctv", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleBilletesCtv.findAll", query = "SELECT d FROM DetalleBilletesCtv d")
    , @NamedQuery(name = "DetalleBilletesCtv.findByDetalleBilletesCtvId", query = "SELECT d FROM DetalleBilletesCtv d WHERE d.detalleBilletesCtvId = :detalleBilletesCtvId")
    , @NamedQuery(name = "DetalleBilletesCtv.findByDetalleBilletesCtvNumeroSello", query = "SELECT d FROM DetalleBilletesCtv d WHERE d.detalleBilletesCtvNumeroSello = :detalleBilletesCtvNumeroSello")
    , @NamedQuery(name = "DetalleBilletesCtv.findByDetalleBilletesCtvNumeroBolsa", query = "SELECT d FROM DetalleBilletesCtv d WHERE d.detalleBilletesCtvNumeroBolsa = :detalleBilletesCtvNumeroBolsa")
    , @NamedQuery(name = "DetalleBilletesCtv.findByDetalleBilletesCtvDocumentos", query = "SELECT d FROM DetalleBilletesCtv d WHERE d.detalleBilletesCtvDocumentos = :detalleBilletesCtvDocumentos")
    , @NamedQuery(name = "DetalleBilletesCtv.findByDetalleBilletesCtvEfectivo", query = "SELECT d FROM DetalleBilletesCtv d WHERE d.detalleBilletesCtvEfectivo = :detalleBilletesCtvEfectivo")
    , @NamedQuery(name = "DetalleBilletesCtv.findByDetalleBilletesCtvTotalBolsa", query = "SELECT d FROM DetalleBilletesCtv d WHERE d.detalleBilletesCtvTotalBolsa = :detalleBilletesCtvTotalBolsa")})
public class DetalleBilletesCtv extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_billetes_ctv_id")
    private Integer detalleBilletesCtvId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_billetes_ctv_numero_sello")
    private int detalleBilletesCtvNumeroSello;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_billetes_ctv_numero_bolsa")
    private int detalleBilletesCtvNumeroBolsa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_billetes_ctv_documentos")
    private int detalleBilletesCtvDocumentos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_billetes_ctv_efectivo")
    private int detalleBilletesCtvEfectivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_billetes_ctv_total_bolsa")
    private int detalleBilletesCtvTotalBolsa;
    @JoinColumn(name = "detalle_billetes_ctv_id_ctv_resumen", referencedColumnName = "ctv_resumen_id", nullable = false)
    @ManyToOne(optional = false)
    private CtvResumen detalleBilletesCtvIdCtvResumen;

    public DetalleBilletesCtv() {
    }

    public DetalleBilletesCtv(Integer detalleBilletesCtvId) {
        this.detalleBilletesCtvId = detalleBilletesCtvId;
    }

    public DetalleBilletesCtv(Integer detalleBilletesCtvId, int detalleBilletesCtvNumeroSello, int detalleBilletesCtvNumeroBolsa, int detalleBilletesCtvDocumentos, int detalleBilletesCtvEfectivo, int detalleBilletesCtvTotalBolsa) {
        this.detalleBilletesCtvId = detalleBilletesCtvId;
        this.detalleBilletesCtvNumeroSello = detalleBilletesCtvNumeroSello;
        this.detalleBilletesCtvNumeroBolsa = detalleBilletesCtvNumeroBolsa;
        this.detalleBilletesCtvDocumentos = detalleBilletesCtvDocumentos;
        this.detalleBilletesCtvEfectivo = detalleBilletesCtvEfectivo;
        this.detalleBilletesCtvTotalBolsa = detalleBilletesCtvTotalBolsa;
    }

    public Integer getDetalleBilletesCtvId() {
        return detalleBilletesCtvId;
    }

    public void setDetalleBilletesCtvId(Integer detalleBilletesCtvId) {
        this.detalleBilletesCtvId = detalleBilletesCtvId;
    }

    public int getDetalleBilletesCtvNumeroSello() {
        return detalleBilletesCtvNumeroSello;
    }

    public void setDetalleBilletesCtvNumeroSello(int detalleBilletesCtvNumeroSello) {
        this.detalleBilletesCtvNumeroSello = detalleBilletesCtvNumeroSello;
    }

    public int getDetalleBilletesCtvNumeroBolsa() {
        return detalleBilletesCtvNumeroBolsa;
    }

    public void setDetalleBilletesCtvNumeroBolsa(int detalleBilletesCtvNumeroBolsa) {
        this.detalleBilletesCtvNumeroBolsa = detalleBilletesCtvNumeroBolsa;
    }

    public int getDetalleBilletesCtvDocumentos() {
        return detalleBilletesCtvDocumentos;
    }

    public void setDetalleBilletesCtvDocumentos(int detalleBilletesCtvDocumentos) {
        this.detalleBilletesCtvDocumentos = detalleBilletesCtvDocumentos;
    }

    public int getDetalleBilletesCtvEfectivo() {
        return detalleBilletesCtvEfectivo;
    }

    public void setDetalleBilletesCtvEfectivo(int detalleBilletesCtvEfectivo) {
        this.detalleBilletesCtvEfectivo = detalleBilletesCtvEfectivo;
    }

    public int getDetalleBilletesCtvTotalBolsa() {
        return detalleBilletesCtvTotalBolsa;
    }

    public void setDetalleBilletesCtvTotalBolsa(int detalleBilletesCtvTotalBolsa) {
        this.detalleBilletesCtvTotalBolsa = detalleBilletesCtvTotalBolsa;
    }

    public CtvResumen getDetalleBilletesCtvIdCtvResumen() {
        return detalleBilletesCtvIdCtvResumen;
    }

    public void setDetalleBilletesCtvIdCtvResumen(CtvResumen detalleBilletesCtvIdCtvResumen) {
        this.detalleBilletesCtvIdCtvResumen = detalleBilletesCtvIdCtvResumen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleBilletesCtvId != null ? detalleBilletesCtvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleBilletesCtv)) {
            return false;
        }
        DetalleBilletesCtv other = (DetalleBilletesCtv) object;
        if ((this.detalleBilletesCtvId == null && other.detalleBilletesCtvId != null) || (this.detalleBilletesCtvId != null && !this.detalleBilletesCtvId.equals(other.detalleBilletesCtvId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DetalleBilletesCtv[ detalleBilletesCtvId=" + detalleBilletesCtvId + " ]";
    }
    
}
