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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "feriado_legal", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeriadoLegal.findAll", query = "SELECT f FROM FeriadoLegal f")
    , @NamedQuery(name = "FeriadoLegal.findByFeriadoLegalId", query = "SELECT f FROM FeriadoLegal f WHERE f.feriadoLegalId = :feriadoLegalId")
    , @NamedQuery(name = "FeriadoLegal.findByFeriadoLegalFechaDesde", query = "SELECT f FROM FeriadoLegal f WHERE f.feriadoLegalFechaDesde = :feriadoLegalFechaDesde")
    , @NamedQuery(name = "FeriadoLegal.findByFeriadoLegalFechaHasta", query = "SELECT f FROM FeriadoLegal f WHERE f.feriadoLegalFechaHasta = :feriadoLegalFechaHasta")
    , @NamedQuery(name = "FeriadoLegal.findByFeriadoLegalDiasHabiles", query = "SELECT f FROM FeriadoLegal f WHERE f.feriadoLegalDiasHabiles = :feriadoLegalDiasHabiles")
    , @NamedQuery(name = "FeriadoLegal.findByFeriadoLegalVacacionesProgresivas", query = "SELECT f FROM FeriadoLegal f WHERE f.feriadoLegalVacacionesProgresivas = :feriadoLegalVacacionesProgresivas")
    , @NamedQuery(name = "FeriadoLegal.findByFeriadoLegalDomingosInhabiles", query = "SELECT f FROM FeriadoLegal f WHERE f.feriadoLegalDomingosInhabiles = :feriadoLegalDomingosInhabiles")
    , @NamedQuery(name = "FeriadoLegal.findByFeriadoLegalFeriadoFraccionado", query = "SELECT f FROM FeriadoLegal f WHERE f.feriadoLegalFeriadoFraccionado = :feriadoLegalFeriadoFraccionado")
    , @NamedQuery(name = "FeriadoLegal.findByFeriadoLegalSaldoPendiente", query = "SELECT f FROM FeriadoLegal f WHERE f.feriadoLegalSaldoPendiente = :feriadoLegalSaldoPendiente")
    , @NamedQuery(name = "FeriadoLegal.findByFeriadoLegalValorFeriado", query = "SELECT f FROM FeriadoLegal f WHERE f.feriadoLegalValorFeriado = :feriadoLegalValorFeriado")
    })
public class FeriadoLegal extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "feriado_legal_id")
    private Integer feriadoLegalId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "feriado_legal_fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date feriadoLegalFechaDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "feriado_legal_fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date feriadoLegalFechaHasta;
    @Column(name = "feriado_legal_dias_habiles")
    private Integer feriadoLegalDiasHabiles;
    @Column(name = "feriado_legal_vacaciones_progresivas")
    private Integer feriadoLegalVacacionesProgresivas;
    @Column(name = "feriado_legal_domingos_inhabiles")
    private Integer feriadoLegalDomingosInhabiles;
    @Column(name = "feriado_legal_feriado_fraccionado")
    private Integer feriadoLegalFeriadoFraccionado;
    @Column(name = "feriado_legal_saldo_pendiente")
    private Integer feriadoLegalSaldoPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "feriado_legal_valor_feriado")
    private int feriadoLegalValorFeriado;    
    @JoinColumn(name = "feriado_legal_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador feriadoLegalIdTrabajador;

    public FeriadoLegal() {
    }

    public FeriadoLegal(Integer feriadoLegalId) {
        this.feriadoLegalId = feriadoLegalId;
    }

    public FeriadoLegal(Integer feriadoLegalId, Date feriadoLegalFechaDesde, Date feriadoLegalFechaHasta, int feriadoLegalValorFeriado) {
        this.feriadoLegalId = feriadoLegalId;
        this.feriadoLegalFechaDesde = feriadoLegalFechaDesde;
        this.feriadoLegalFechaHasta = feriadoLegalFechaHasta;
        this.feriadoLegalValorFeriado = feriadoLegalValorFeriado;
    }

    public Integer getFeriadoLegalId() {
        return feriadoLegalId;
    }

    public void setFeriadoLegalId(Integer feriadoLegalId) {
        this.feriadoLegalId = feriadoLegalId;
    }

    public Date getFeriadoLegalFechaDesde() {
        return feriadoLegalFechaDesde;
    }

    public void setFeriadoLegalFechaDesde(Date feriadoLegalFechaDesde) {
        this.feriadoLegalFechaDesde = feriadoLegalFechaDesde;
    }

    public Date getFeriadoLegalFechaHasta() {
        return feriadoLegalFechaHasta;
    }

    public void setFeriadoLegalFechaHasta(Date feriadoLegalFechaHasta) {
        this.feriadoLegalFechaHasta = feriadoLegalFechaHasta;
    }

    public Integer getFeriadoLegalDiasHabiles() {
        return feriadoLegalDiasHabiles;
    }

    public void setFeriadoLegalDiasHabiles(Integer feriadoLegalDiasHabiles) {
        this.feriadoLegalDiasHabiles = feriadoLegalDiasHabiles;
    }

    public Integer getFeriadoLegalVacacionesProgresivas() {
        return feriadoLegalVacacionesProgresivas;
    }

    public void setFeriadoLegalVacacionesProgresivas(Integer feriadoLegalVacacionesProgresivas) {
        this.feriadoLegalVacacionesProgresivas = feriadoLegalVacacionesProgresivas;
    }

    public Integer getFeriadoLegalDomingosInhabiles() {
        return feriadoLegalDomingosInhabiles;
    }

    public void setFeriadoLegalDomingosInhabiles(Integer feriadoLegalDomingosInhabiles) {
        this.feriadoLegalDomingosInhabiles = feriadoLegalDomingosInhabiles;
    }

    public Integer getFeriadoLegalFeriadoFraccionado() {
        return feriadoLegalFeriadoFraccionado;
    }

    public void setFeriadoLegalFeriadoFraccionado(Integer feriadoLegalFeriadoFraccionado) {
        this.feriadoLegalFeriadoFraccionado = feriadoLegalFeriadoFraccionado;
    }

    public Integer getFeriadoLegalSaldoPendiente() {
        return feriadoLegalSaldoPendiente;
    }

    public void setFeriadoLegalSaldoPendiente(Integer feriadoLegalSaldoPendiente) {
        this.feriadoLegalSaldoPendiente = feriadoLegalSaldoPendiente;
    }

    public int getFeriadoLegalValorFeriado() {
        return feriadoLegalValorFeriado;
    }

    public void setFeriadoLegalValorFeriado(int feriadoLegalValorFeriado) {
        this.feriadoLegalValorFeriado = feriadoLegalValorFeriado;
    }
    
    public Trabajador getFeriadoLegalIdTrabajador() {
        return feriadoLegalIdTrabajador;
    }

    public void setFeriadoLegalIdTrabajador(Trabajador feriadoLegalIdTrabajador) {
        this.feriadoLegalIdTrabajador = feriadoLegalIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feriadoLegalId != null ? feriadoLegalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeriadoLegal)) {
            return false;
        }
        FeriadoLegal other = (FeriadoLegal) object;
        if ((this.feriadoLegalId == null && other.feriadoLegalId != null) || (this.feriadoLegalId != null && !this.feriadoLegalId.equals(other.feriadoLegalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.FeriadoLegal[ feriadoLegalId=" + feriadoLegalId + " ]";
    }
    
}
